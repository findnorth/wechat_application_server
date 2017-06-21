package com.demo.task;

/**
 * 微信数据缓存任务
 * @author yaokwok.com
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.demo.bean.Ticket;
import com.demo.utils.WeChatUtil;
import com.jfinal.plugin.cron4j.ITask;
import com.jfinal.plugin.ehcache.CacheKit;

public class CacheWeChatTask {
		   public static void run() {
		      // 这里放被执行的调试任务代码
			    
	          //获取微信token
	          String tokenCache = WeChatUtil.getAccessToken(CacheKit.get("properties", "appid").toString(),CacheKit.get("properties", "appsecret").toString()).getToken();
	          //获取微信ticket
	          Ticket ticket = WeChatUtil.getTicket(tokenCache);
	          Date nowDate = new Date();
	          DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	          String nowTime = format.format(nowDate);
	          //参数(缓存对象名,key,value)
	          CacheKit.put("wechat", "token", tokenCache);  
	          CacheKit.put("wechat", "ticket", ticket.getTicket());
	          CacheKit.put("wechat", "time", nowTime);
	          Logger.getAnonymousLogger().info("缓存时间已更新:"+nowTime);
		   }
		   
}
