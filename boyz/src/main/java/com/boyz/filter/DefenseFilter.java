package com.boyz.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefenseFilter implements Filter {

	private static final Logger logger = Logger.getLogger(DefenseFilter.class);

	@Value("#{paramsProperties['VALIDATE_XSS']}")
	private String us ;
	
	private String validate[] = null;

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("...... defense filter init ......");
		//String us = arg0.getInitParameter("VALIDATE");
		//String us = ".*<.*;.*>.*;.*/>.*;.*</.*;.*'.*;.*\".*;.*select .*;.*update .*;.*alter .*;.*delete .*;.*truncate .*;.*script .*;.*set .*;";
		validate = us != null ? us.split(";") : null;
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*
		 * 网络安全防御 xss\csrf\ 如何设置http-only 如何系统判断session有效性
		 */
		boolean flag = true;

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURL().toString();
		// URL中的XSS攻击判断
		if (contain(url)) {
			flag = false;
			Enumeration<String> enu = request.getHeaderNames();
			while (enu.hasMoreElements()) {
				String headername = enu.nextElement();
				String headervalue = request.getHeader(headername);
				logger.info("XSS ... " + headername + " : "+ headervalue);
			}
			logger.info("XSS ... request.getRemoteAddr() : "+ request.getRemoteAddr());
			logger.info("XSS ... request.getRequestURI() : "+ request.getRequestURI());
			logger.info("XSS ... [" + url + "]请求包含XSS攻击！");

		} else {
			// PARAM中的XSS攻击判断
			Enumeration<String> e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String pname = e.nextElement();
				String pvalue = request.getParameter(pname);
				if (contain(pvalue)) {
					flag = false;
					Enumeration<String> enu = request.getHeaderNames();
					while (enu.hasMoreElements()) {
						String headername = enu.nextElement();
						String headervalue = request.getHeader(headername);
						logger.info("XSS ... " + headername + " : "+ headervalue);
					}
					logger.info("XSS ... request.getRemoteAddr() : "+ request.getRemoteAddr());
					logger.info("XSS ... request.getRequestURI() : "+ request.getRequestURI());
					logger.info("XSS ... [" + url + "][" + pname + "="+ pvalue + "]请求包含XSS攻击危险！");
				}
			}
		}

		if (flag) {
			// XssHttpServletRequestWrapper xssRequest = new
			// XssHttpServletRequestWrapper((HttpServletRequest) request);
			// arg2.doFilter(xssRequest , arg1);
			arg2.doFilter(arg0, arg1);
		} else {
			// response.sendRedirect("/xss.html");
			return;
			// response.getWriter().write("<html><body>"+msg.toString()+"</body></html>");
			// return ;
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("...... defense filter destroy ......");
	}

	public boolean contain(String url) {
		boolean flag = false;
		for (int i = 0; i < validate.length; i++) {
			String validateStr = validate[i];
			Pattern p = Pattern.compile(validateStr);
			Matcher m = p.matcher(url);
			flag = m.matches();
			if (flag)
				break;
		}
		return flag;
	}
}
