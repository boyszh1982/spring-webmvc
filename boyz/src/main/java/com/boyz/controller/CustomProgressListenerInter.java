package com.boyz.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

/*
 * ������Ū�������ӿ�extends ProgressListener 
 * ����� setSession������������Spring�����á�
 */
public interface CustomProgressListenerInter  extends ProgressListener {

	public void setSession(HttpSession session);
	
}
