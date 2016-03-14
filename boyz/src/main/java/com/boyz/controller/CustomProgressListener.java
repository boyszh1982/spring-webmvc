package com.boyz.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

public class CustomProgressListener implements CustomProgressListenerInter {

	public static final String UPLOAD_PROGRESS_SESSION = "UPLOAD_PROGRESS_SESSION";
	private HttpSession session ;
	
	public void setSession(HttpSession session){
		this.session = session;
		JSONObject json = new JSONObject();
		json.put("pBytesRead",0);
		json.put("pContentLength", 0);
		json.put("pItems", 0);
		this.session.setAttribute(UPLOAD_PROGRESS_SESSION, json);
	}
	
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// TODO Auto-generated method stub
		JSONObject json = (JSONObject)this.session.getAttribute(UPLOAD_PROGRESS_SESSION);
		json.remove("pBytesRead");
		json.remove("pContentLength");
		json.remove("pItems");
		json.put("pBytesRead",pBytesRead);
		json.put("pContentLength", pContentLength);
		json.put("pItems", pItems);
	}

}
