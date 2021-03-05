// pages/book/book.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        book: null,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let bookId = wx.getStorageSync("bookId");
        console.log(bookId);
        // 清除缓存中的页面参数
        wx.removeStorageSync('bookId');
        var that = this;
        wx.request({
            url: 'http://localhost:8080/getBook',
            method: "POST",
            data: {
                id: bookId
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success(res) {
                if (res.data.bookId != undefined) {
                    console.log(res.data);
                    //app.globalData.userLogged = true;
                    //this.setData({book:res.data});
                    that.setData({book: res.data});
                } else {
                    console.log(res.data);
                }
            }
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },

    onClickIcon: function () {
        wx.redirectTo({
            url: "../cart/cart"
        })
    },

    onClickButton: function () {
        var that = this;
        let id = wx.getStorageSync('userId');
        wx.request({
            url: 'http://localhost:8080/addCartItem',
            method: "POST",
            data: {
                userId: id,
                bookId: this.data.book.bookId
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            success(res) {
                // console.log(res.data);
                wx.showToast({
                    title: '成功',
                    icon: 'success',
                    duration: 2000
                })
            }
        });
    }
})

