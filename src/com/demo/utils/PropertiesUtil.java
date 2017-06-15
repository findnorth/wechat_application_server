package com.demo.utils;
/**
 * 配置文件缓存
 * @author yaokwok.com
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.jfinal.plugin.ehcache.CacheKit;

public class PropertiesUtil {
	public static void cache(){
		Properties prop = new Properties();    
		try {
		InputStream in = PropertiesUtil.class.getResourceAsStream("/wechat.properties");
		prop.load(in);  
		}catch (IOException e) {
             e.printStackTrace();
        }
		Enumeration enu = prop.propertyNames();
		while(enu.hasMoreElements()){
		    String keyName = (String)enu.nextElement();
		    CacheKit.put("properties", keyName, prop.get(keyName)); 
		    System.out.println(keyName+"已缓存");
		} 
	}
}
