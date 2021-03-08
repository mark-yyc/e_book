const app = getApp()
// pages/books/books.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        books: [],
        filterBooks: [],
        searchValue: "",
        show: false,
        book: null,
        active: 0,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        if (!app.globalData.userLogged) {
            wx.navigateTo({
                url: '../index/index'
            })
        } else {
            wx.request({
                url: 'http://localhost:8080/getBooks',
                method: "POST",
                data: {
                    11: 11
                },
                header: {
                    'content-type': 'application/json', // 默认值
                    'cookie': wx.getStorageSync("sessionid") //cookie
                },
                success(res) {
                    // console.log(res);
                    that.setData({
                        books: res.data,
                        filterBooks: res.data
                    })
                }
            });
        }
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
    //点击书籍
    bindViewTap: function (event) {
        this.setData({show: true, book: event.currentTarget.dataset.book});
        //console.log(event.currentTarget.dataset.book);
    },
    //搜索框
    bindSearch: function (event) {
        //console.log(event.detail);
        this.setData({
            searchValue: event.detail
        });
    },

    onChange(e) {

    },

    onSearch() {
        const filterText = this.data.searchValue;
        //console.log(this.data.searchValue);
        let dataSource = [];
        for (let key in this.data.books) {
            //console.log( key+' : '+this.state.books[key].name );
            if (this.data.books[key].name.indexOf(filterText) === -1) {
                continue;
            } else {
                dataSource.push(this.data.books[key]);
            }
        }
        this.setData({
            filterBooks: dataSource
        })
    },
    //点击遮罩层
    onClickHide() {
        this.setData({show: false});
    },
    //点击坐下图标
    onClickIcon() {
    },
    //点击右下按钮
    onClickButton() {
    },
    //点击底边栏
    onChange(event) {
        // event.detail 的值为当前选中项的索引
        this.setData({active: event.detail});
    },

    moreInformation() {
        console.log(this.data.book.bookId);
        wx.setStorageSync("bookId", this.data.book.bookId);
        wx.navigateTo({
            url: '../addCartPage/book'
        })

    },

    toBookstore() {
        wx.redirectTo({
            url: '../books/books'
        })
    },

    toCart() {
        wx.redirectTo({
            url: "../cart/cart"
        })
    }
})


