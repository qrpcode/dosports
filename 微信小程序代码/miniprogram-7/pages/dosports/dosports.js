// pages/dosports/dosports.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    url: "http://www.miankou.cn/do_sport/wx/",
    apiUrl: "https://www.miankou.cn/",
    lastTime: "2019-12-30",
    cardHeight: "height:150rpx;",
    screen: -1,
    payData: [],
    keepData: [],
    height: 0,
    historyHeight: 0,
    listHeight: 0,
    bubblePlace: "left:50rpx;",
    keepDataShow: false,
    payDataShow: true,
    keepLastOne:true,
    payLastOne:true,
    keepHeight:0,
    payHeight:0,
    keepNum: 0,
    payNum: 0,
    wxid: '',
    wxidCode: '',
    getLoginShow: false
  },

  //用户点击登录按钮触发获取用户信息
  getUserInfo: function(res) {
    var that = this;
    wx.login({
      success: function(data) {
        wx.request({
          url: 'http://www.miankou.cn/do_sport/member/login',
          method:'GET',
          data: {
            code: data.code,
            gender: res.detail.userInfo.gender,
            nickName: res.detail.userInfo.nickName,
            avatar: res.detail.userInfo.avatarUrl
          },
          success: function(getWxId) {
            that.setData({
              wxid: getWxId.data.openid,
              getLoginShow: true
            });
            that.getSet(getWxId.data.openid);
          }
        })
      }
    })
  },
  //根据wxid获取用户信息
  getSet: function(wxId) {
    
    var that = this;
    wx.request({
      url: 'http://www.miankou.cn/do_sport/member/info',
      method: 'GET',
      data: {
        wxId: wxId
      },
      success: function (getSet) {
        console.log(getSet)
        that.setData({
          lastTime: getSet.data.expireTime,
          wxidCode: 'http://qr.topscan.com/api.php?text=' + wxId
        })
      }
    })

    that.keepGetMore();
    that.payGetMore();
  },

  //卡片展示
  cardShow: function() {
    var that = this;
    if (that.data.cardHeight == "height:800rpx;animation: cardShow 0.5s") {
      that.setData({
        cardHeight: "height:150rpx;animation: cardHide 0.5s"
      })
      wx.setScreenBrightness({
        value: that.data.screen
      });
      this.heightShow(150);
    } else {
      that.setData({
        cardHeight: "height:800rpx;animation: cardShow 0.5s"
      })
      wx.getScreenBrightness({
        success: function(res) {
          that.setData({
            screen: res.value
          })
        }
      })
      wx.setScreenBrightness({
        value: 1
      });
      this.heightShow(800);
    }
  },
  heightShow: function(vipHeight) {
    var that = this;
    wx.getSystemInfo({
      success: function(res) {
        var clientHeight = res.windowHeight;
        var clientWidth = res.windowWidth;
        var ratio = 750 / clientWidth;
        var height = clientHeight * ratio - 20;
        that.setData({
          height: height,
          historyHeight: height - vipHeight,
          listHeight: height - vipHeight - 100
        });
      }
    });
  },
  goKeepData: function() {
    var that = this;
    if (that.data.bubblePlace == "left:400rpx;animation: bubbleToPay 0.2s") {
      this.setData({
        bubblePlace: "left:50rpx;animation: bubbleToKeep 0.2s",
        payDataShow: true,
        keepDataShow: false,
        keepHeight:100,
        payHeight:0
      })
    }
  },
  goPayData: function() {
    var that = this;
    if (that.data.bubblePlace != "left:400rpx;animation: bubbleToPay 0.2s") {
      this.setData({
        bubblePlace: "left:400rpx;animation: bubbleToPay 0.2s",
        payDataShow: false,
        keepDataShow: true,
        keepHeight: 0,
        payHeight: 100
      })
    }
  },

  //加载更多充值数据
  payGetMore: function() {
    var that = this;
    wx.request({
      url: 'http://www.miankou.cn/do_sport/pay/getByWxId',
      method: 'GET',
      data: {
        wxId: that.data.wxid,
        payNum: that.data.payNum,
        allShow: 0
      },
      success: function (getData) {
        if (getData.data.lastOne == 1){
          console.log('最后支付');
          that.setData({
            payLastOne: true
          })
        }else{
          that.setData({
            payLastOne: false
          })
        }
        console.log(getData);
        var payNum = getData.getData;
        that.setData({
          payNum: payNum + that.data.payNum
        });
        var newData = that.data.payData.concat(getData.data.payData);
        that.setData({
          payData: newData
        });
      }
    })
  },
  //加载更多健身数据
  keepGetMore: function () {
    var that = this;
    wx.request({
      url: 'http://www.miankou.cn/do_sport/keep/getByWxId',
      method: 'GET',
      data: {
        wxId: that.data.wxid,
        keepNum: 0,
        allShow: 0
      },
      success: function (getData) {
        if (getData.data.lastOne == 1) {
          console.log('健身支付');
          that.setData({
            keepLastOne: true
          })
        }else {
          that.setData({
            keepLastOne: false
          })
        }
        console.log(getData);
        var keepNum = getData.getData;
        that.setData({
          keepNum: keepNum + that.data.keepNum
        });
        var newData = that.data.keepData.concat(getData.data.keepData);
        that.setData({
          keepData: newData
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    this.heightShow(150);
    var that = this;
    var wxId = wx.getStorageSync('wechatId')
    if (wxId) {
      that.setData({
        wxid: wechatId,
        getLoginShow: true
      })
      that.getSet(wxId)
    } else {
      that.setData({
        getLoginShow: false
      })
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {
    return {
      title: "程序员健身中心，头秃也不能阻挡健身热情",
      path: "pages/dosports/dosports",
      imageUrl: 'https://www.miankou.cn/wx/shareimg.jpg'
    }
  }
})