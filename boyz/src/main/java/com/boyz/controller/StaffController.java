package com.boyz.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boyz.model.Staff;
import com.boyz.service.StaffService;

@Controller
@RequestMapping("/StaffController")
public class StaffController {

	private static final Logger logger = Logger.getLogger(StaffController.class);
	
	@Autowired
	@Qualifier("staffServiceImpl")
	private StaffService staffService ;
	
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
	public ModelAndView sucess(@ModelAttribute Staff staff,  HttpServletRequest request){
		System.out.println("staff.getStaffId() = "+ staff.getStaffId() );
		System.out.println("token = "+ request.getParameter("login_token") );
		Staff st =  staffService.getStaffById(staff.getStaffId());
		System.out.println("getAddressId="+st.getAddressId());
		System.out.println("getAddressId="+st.getEmail());
		System.out.println("getFirstName="+st.getFirstName());
		System.out.println("getLastName="+st.getLastName());
		System.out.println("getPassword="+st.getPassword());
		System.out.println("getStaffId="+st.getStaffId());
		System.out.println("getStoreId="+st.getStoreId());
		System.out.println("getUsername="+st.getUsername());
		System.out.println("getActive="+st.getActive());
		System.out.println("getLastUpdate="+st.getLastUpdate());
		System.out.println("getPicture="+st.getPicture());
		return new ModelAndView("sucess").addObject(st);
	}
}
