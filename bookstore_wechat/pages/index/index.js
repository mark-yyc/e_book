//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
        userName: "",
        password: "",
        errorMessage: "",
    },
    //用户名输入
    bindNameInput: function (event) {
        this.setData({userName: event.detail.value})
    },
    //密码输入
    bindPasswordInput: function (event) {
        this.setData({password: event.detail.value})
    },
    //事件处理函数
    bindViewTap: function () {
        var that = this;
        wx.request({
            url: 'http://localhost:8080/checkUser',
            method: "POST",
            data: {
                username: this.data.userName,
                password: this.data.password
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded' // 默认值
            },
            success(res) {
                if (res.data.userId != undefined) {
                    //console.log(res.data.userId);
                    app.globalData.userLogged = true;
                    wx.setStorageSync('userId', res.data.userId);
                    // wx.setStorageSync("sessionid", res.header["Set-Cookie"]); //存储cookie
                    wx.navigateTo({
                        url: '../books/books'
                    })
                } else {
                    that.setData({"errorMessage": "用户名密码错误"});
                    //console.log(res.data);
                }
            }
        });
    },
    onLoad: function () {
    },

})
