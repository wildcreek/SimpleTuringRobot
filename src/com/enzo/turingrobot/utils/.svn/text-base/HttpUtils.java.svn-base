package com.enzo.turingrobot.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Date;

import com.enzo.turingrobot.Bean.ChatMessage;
import com.enzo.turingrobot.Bean.ChatMessage.Type;
import com.enzo.turingrobot.Bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

 

public class HttpUtils {
	
	  
	public static final String API_KEY = "1c9974f4ff6c6a1e8bb80255902edd61";
    public static final String URL = "http://www.tuling123.com/openapi/api";
	//发送消息，得到返回消息
    public static ChatMessage sendMessage(String msg){
		ChatMessage chatMessage =new ChatMessage();
		String jsonRes = doGet(msg);
		Gson gson = new Gson();
		Result result= null;
		try {
			result= gson.fromJson(jsonRes,Result.class);
			chatMessage.setMsg(result.getText());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			chatMessage.setMsg("返回数据异常");
		}
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		return chatMessage;
	}
    
    public static String doGet(String msg) {
    	String result ="";
    	String url = setParams(msg);
    	ByteArrayOutputStream bos =null;
    	InputStream is=null;
    	try {
			java.net.URL urls = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
			conn.setReadTimeout(3*1000);
			conn.setConnectTimeout(3*1000);
			conn.setRequestMethod("GET");
			is = conn.getInputStream();
			
			int len = -1;
			byte[] buffer = new byte[128];
			bos = new ByteArrayOutputStream();
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			result = new String(bos.toByteArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "\"code\":"+100000+",\"text\":\"无法与服务器建立连接\"";
		}finally{
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
    }

	private static String setParams(String msg) {
		String urlStr="";
		try {
			urlStr = URL+"?key="+API_KEY+"&info="+URLEncoder.encode(msg,"UTF-8");
			return urlStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return urlStr;
	
	}
   
}

