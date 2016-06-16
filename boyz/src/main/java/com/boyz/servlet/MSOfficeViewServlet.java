package com.boyz.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MSOfficeViewServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletConfig().getServletContext();
		File f = new File("C:/temp/≤ –≈»’±®V15.xls");
		String filename = new String(f.getName().getBytes(),"ISO-8859-1");
		int length = 0;
		ServletOutputStream op = resp.getOutputStream();
		resp.setContentLength((int) f.length());
		//resp.setContentType( "application/msword" );
        resp.setContentType("application/vnd.ms-excel");
		//response.setHeader("Content-type","application/pdf"); 
        resp.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        //resp.setHeader("Pragma", "No-cache");
        //resp.setHeader("Cache-Control", "No-cache");	
        //resp.setDateHeader("Expires", 0);
        byte[] bytes = new byte[1024];
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        while ((in != null) && ((length = in.read(bytes)) != -1)) {
            op.write(bytes, 0, length);
        }
        op.close();
        resp.flushBuffer();
        
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
