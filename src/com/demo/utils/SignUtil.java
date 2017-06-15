package com.demo.utils;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.demo.bean.Config;

import net.sf.json.JSONObject;

/** 
 * 请求校验工具类 
 * @author yaokwok.com
 */  
public class SignUtil {
	 /** 
     * jsapi_ticket使用权限签名算法 
     * @return 
     */  
    public static JSONObject getSignature(String appid,String ticket,String url){  
        String nonceStr = getNonceStr();  
        String timestamp = getTimeStamp();  
        //字典排序的签名参数  
        String string1 = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";  
        string1 = String.format(string1, ticket, nonceStr, timestamp, url);  
        //对string1进行sha1签名，得到signature  
        String signature = md5(string1).toLowerCase();  
        //最后组装json数据  
        Config config = new Config();  
        config.setAppId(appid);  
        config.setTicket(ticket);  
        config.setNonceStr(nonceStr);  
        config.setSignature(signature);  
        config.setTimestamp(timestamp);  
        JSONObject jsonObject = JSONObject.fromObject(config);  
        System.out.println("json = " + jsonObject);  
        return jsonObject;  
    }  
  
    /** 
     * md5加密 
     * @param string 
     */  
    public static String md5(String string){  
        String tmpStr = null;  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            byte[] digest = md.digest(string.getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return tmpStr;  
    }  
      
    /** 
     * 参数按字典序排序 
     * @param params 
     * @return 
     */  
    public static String sortParams(Object... params){  
        Arrays.sort(params);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < params.length; i++) {  
            content.append(params[i]).append(i==params.length-1?"=%s":"=%s&");  
        }  
        return content.toString();  
    }  
      
    /** 
     * 获取随机字串（16位） 
     * @return 
     */  
    public static String getNonceStr(){  
        String nonce_str = "";  
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  
        int maxPos = chars.length();  
        for (int i = 0; i < 16; i++){  
           nonce_str += chars.charAt((int) Math.floor(Math.random() * maxPos));  
        }  
        return nonce_str;  
    }  
  
    /** 
     * 获取时间戳 
     * @return 
     */  
    public static String getTimeStamp() {  
        return String.valueOf(System.currentTimeMillis() / 1000);  
    }  
      
    /** 
     * 将字节数组转换为十六进制字符串 
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
      
    /* 
    * 16进制数字字符集 
    */  
    private static String hexString = "0123456789ABCDEF"; //此处不可随意改动  
      
    /* 
    * 将字符串编码成16进制数字  
    */  
    public static String encode(String str) {  
       // 根据默认编码获取字节数组  
       byte[] bytes = str.getBytes();  
       StringBuilder sb = new StringBuilder(bytes.length * 2);  
       // 将字节数组中每个字节拆解成2位16进制整数  
       for (int i = 0; i < bytes.length; i++) {  
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));  
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));  
       }  
       return sb.toString();  
    }  
  
    /* 
    * 将16进制数字解码成字符串 
    */  
    public static String decode(String bytes) {  
       ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);  
       // 将每2位16进制整数组装成一个字节  
       for (int i = 0; i < bytes.length(); i += 2){  
           baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));  
       }  
       return new String(baos.toByteArray());  
    }  
}
