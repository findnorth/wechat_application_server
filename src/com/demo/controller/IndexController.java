package com.demo.controller;

import com.demo.utils.SignUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;

import net.sf.json.JSONObject;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		System.out.println(CacheKit.get("wechat", "time"));
		setAttr("cacheTime", CacheKit.get("wechat", "time"));
		renderJsp("index.jsp");
	}
	
	//获取微信签名的接口
	public void getWechatParm(){
		String url = getPara("url");
		String verify = CacheKit.get("properties", "verify"); 
		if(verify.equals("true")){
			String appdUrl = CacheKit.get("properties", "appdUrl");
			String len[] = appdUrl.split(",");
			for(int i=0;i<len.length;i++){
				if(url.contains(len[i])){
					renderMsg(url);
					return;
				}
			}
			renderText("你的网址是不被认证的，请勿恶意调用，上证信息技术公司保留一切权利");
			return;
		}
		renderMsg(url);
	}
	public void renderMsg(String url){
		//读取缓存里的ticket
		String ticketCache = CacheKit.get("wechat", "ticket");  
		JSONObject j = SignUtil.getSignature(CacheKit.get("properties", "appid").toString(), ticketCache, url);
		renderJson(j);
	}
}





