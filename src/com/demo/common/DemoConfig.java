package com.demo.common;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.demo.controller.IndexController;
import com.demo.task.CacheWeChatTask;
import com.demo.utils.PropertiesUtil;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;


/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 * 
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run config 中自动生成
	 * 一条启动配置，可对该自动生成的配置再添加额外的配置项，例如 VM argument 可配置为：
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) { 
		
		/**
		 * 特别注意：Eclipse 之下建议的启动方式
		 */
		JFinal.start("WebRoot", 8101, "/", 5);
		/**
		 * 特别注意：IDEA 之下建议的启动方式，仅比 eclipse 之下少了最后一个参数
		 */
		// JFinal.start("WebRoot", 80, "/");
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		// 第三个参数为该Controller的视图存放路径
		me.add("/", IndexController.class, "/index");	
	}
	
	public void configEngine(Engine me) {
	}
	
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		//ehCachePlugin 缓存插件
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 程序启动后调用
	 */
	public void afterJFinalStart() {
		//缓存配置文件
		PropertiesUtil.cache();
		//定时任务 每隔7000秒缓存一次微信token  官方失效7200秒
		Runnable runnable = new Runnable() {  
            public void run() {  
                // task to run goes here  
            	CacheWeChatTask.run(); 
            }  
        };  
        ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间单位秒
        service.scheduleAtFixedRate(runnable, 5, 7000, TimeUnit.SECONDS);  
    
	}
}
