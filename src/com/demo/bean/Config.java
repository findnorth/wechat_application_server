package com.demo.bean;

public class Config {
	 private String appId;     //公众号appId  
//	    private String ticket;   //获取到的凭证  
	    private String nonceStr; //随机字串  
	    private String timestamp;//时间戳  
	    private String signature;//获取到的签名  
	      
	    public String getAppId() {  
	        return appId;  
	    }  
	    public void setAppId(String appId) {  
	        this.appId = appId;  
	    }  
	   /* public String getTicket() {  
	        return ticket;  
	    }  
	    public void setTicket(String ticket) {  
	        this.ticket = ticket;  
	    }  */
	    public String getNonceStr() {  
	        return nonceStr;  
	    }  
	    public void setNonceStr(String nonceStr) {  
	        this.nonceStr = nonceStr;  
	    }  
	    public String getTimestamp() {  
	        return timestamp;  
	    }  
	    public void setTimestamp(String timestamp) {  
	        this.timestamp = timestamp;  
	    }  
	    public String getSignature() {  
	        return signature;  
	    }  
	    public void setSignature(String signature) {  
	        this.signature = signature;  
	    }  
	      
}
