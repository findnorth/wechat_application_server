package com.demo.bean;

public class Ticket {
	 private String ticket;//获取到的凭证  
	    private int expiresIn;//凭证有效时间,单位：秒  
	      
	    public String getTicket() {  
	        return ticket;  
	    }  
	    public void setTicket(String ticket) {  
	        this.ticket = ticket;  
	    }  
	    public int getExpiresIn() {  
	        return expiresIn;  
	    }  
	    public void setExpiresIn(int expiresIn) {  
	        this.expiresIn = expiresIn;  
	    }  
}
