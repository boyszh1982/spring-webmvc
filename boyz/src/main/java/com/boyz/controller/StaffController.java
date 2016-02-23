package com.boyz.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boyz.model.Staff;

@Controller
@RequestMapping("/StaffController")
public class StaffController {

	private static final Logger logger = Logger.getLogger(StaffController.class);
	
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request){
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(HttpServletRequest request ){
		request.getSession().setAttribute("LOGIN_TOKEN", UUID.randomUUID().toString());
		Staff staff = new Staff();
		return new ModelAndView("login").addObject(staff);
	}
	
	@RequestMapping("/toLoginCheck")
	public String sucess(@ModelAttribute Staff staff,  HttpServletRequest request){
		System.out.println("username = "+ staff.getStaffId() );
		System.out.println("token = "+ request.getParameter("login_token") );
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String token = request.getParameter("token");
		
		request.setAttribute("username", username);
		System.out.println("token = "+ token );
		System.out.println("username = "+ username );
		System.out.println("password = "+ password );
		*/
		return "sucess";
	}
}
