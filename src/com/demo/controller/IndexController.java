package com.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

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
		Logger.getAnonymousLogger().info("缓存时间："+CacheKit.get("wechat", "time").toString());
		Date nowDate = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String nowTime = format.format(nowDate); 
		setAttr("cacheTime", CacheKit.get("wechat", "time"));
		setAttr("token",CacheKit.get("wechat","token")==null?"null":"ok");
		setAttr("ticket",CacheKit.get("wechat","ticket")==null?"null":"ok");
		setAttr("nowTime",nowTime);
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
			Logger.getAnonymousLogger().info("未授权的url调用了接口："+url);
			renderText("你的网址是不被认证的，请勿恶意调用，上证信息技术公司保留一切权利");
			return;
		}
		renderMsg(url);
	}
	public void renderMsg(String url){
		//读取缓存里的ticket
		String ticketCache = CacheKit.get("wechat", "ticket");  
		JSONObject j = SignUtil.getSignature(CacheKit.get("properties", "appid").toString(), ticketCache, url);
		Logger.getAnonymousLogger().info(url+"：已成功返回json");
		renderJson(j);
	}
}





