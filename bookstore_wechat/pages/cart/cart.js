// pages/cart/cart.js
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        bookInCart: null,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        let id = wx.getStorageSync('userId');
        wx.request({
            url: 'http://localhost:8080/getShoppingCart',
            method: "POST",
            data: {
                userId: id
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            success(res) {
                console.log(res.data);
                that.setData({
                    bookInCart: res.data
                })
            }
        });
    },

    addOrder() {
        let date = new Date();
        date.setMinutes(date.getMinutes() - date.getTimezoneOffset());
        let day = date.toJSON().substr(0, 10).replace(/[-T]/g, '-');
        // console.log(date.toJSON().substr(0, 10).replace(/[-T]/g, '-'));

        // console.log(date);
        var that = this;
        let id = wx.getStorageSync('userId');
        // console.log(id);
        wx.request({
            url: 'http://localhost:8080/addOrder',
            method: "POST",
            data: {
                userId: id,
                orderDate: day,
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded', // 默认值
                // 'cookie': wx.getStorageSync("sessionid") //cookie
            },
            success(res) {
                console.log(res.data);
                // that.setData({
                //   bookInCart:res.data
                // })
                wx.showToast({
                    title: '成功',
                    icon: 'success',
                    duration: 2000
                })
            }
        });

    },

    returnTobooks() {
        wx.redirectTo({
            url: '../books/books'
        })
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

    }
})