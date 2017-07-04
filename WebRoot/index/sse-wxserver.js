$(function(){
	var curUrl = document.URL.split("#")[0];
	var curTitle = document.title;
	$.ajax({
		url: "http://test.sse.com.cn/wxserver/getWechatParm",
		type: "post",
		data: {url:curUrl},
		dataType: "json",
		success:function(data) {
		   wx.config({
				debug: false,
				appId: data.appId,
				timestamp: data.timestamp,
				nonceStr: data.nonceStr,
				signature: data.signature,
				jsApiList: [
				  'onMenuShareTimeline',
				  'onMenuShareAppMessage'
				]
			});
		},
		error : function() {
		   console.log("微信接口请求失败!");
		}
	});
	wx.ready(function () {
	  // 2. 分享接口
	  // 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareAppMessage({
		  title: curTitle,
		  link: curUrl,
		  imgUrl: 'http://test.sse.com.cn/images/ui/weichaticon.png',
		  trigger: function (res) {
			// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
			console.log('用户点击发送给朋友');
		  },
		  success: function (res) {
			console.log('已分享');
		  },
		  cancel: function (res) {
			console.log('已取消');
		  },
		  fail: function (res) {
			console.log(JSON.stringify(res));
		  }
		});

	  // 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareTimeline({
		  title: curTitle,
		  link: curUrl,
		  imgUrl: 'http://test.sse.com.cn/images/ui/weichaticon.png',
		  trigger: function (res) {
			// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
			console.log('用户点击分享到朋友圈');
		  },
		  success: function (res) {
			console.log('已分享');
		  },
		  cancel: function (res) {
			console.log('已取消');
		  },
		  fail: function (res) {
			console.log(JSON.stringify(res));
		  }
		});
	});

	wx.error(function (res) {
	  console.log(res.errMsg);
	});
});