package com.boyz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CustomProgressMultipartResolver extends CommonsMultipartResolver {
	
	
	public void setCustomProgressListener(
			CustomProgressListenerInter customProgressListener) {
		this.customProgressListener = customProgressListener;
	}
	
	private CustomProgressListenerInter customProgressListener ; 
	


	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request)
			throws MultipartException {
		// TODO Auto-generated method stub
		super.setMaxUploadSize(9000000000L);
		
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		
		/**
		 * 设置上传文件的监听器
		 */
		customProgressListener.setSession(request.getSession());
		fileUpload.setProgressListener(customProgressListener);
		
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		}
		catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		}
		catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}
}
