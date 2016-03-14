package com.boyz.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

/*
 * 必须再弄出来个接口extends ProgressListener 
 * 添加上 setSession方法，方便在Spring中配置。
 */
public interface CustomProgressListenerInter  extends ProgressListener {

	public void setSession(HttpSession session);
	
}
