package com.boyz.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/MSOfficeViewController")
public class MSOfficeViewController {
	private static final Logger logger = Logger
			.getLogger(MSOfficeViewController.class);
	
	@RequestMapping(value="/viewxls")
	public ModelAndView viewxls(Model model ,  HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.addObject("path","C:/temp/彩信日报V15.htm");
		mav.setViewName("msoffice/viewxls");
		return mav;
	}
	
	@RequestMapping(value="/{unique}/notice")
	public ModelAndView notice(@PathVariable("unique") String unique ,HttpServletRequest request ){
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("msoffice/notice/"+unique+"/"+unique+"");
		//redirect:/business/shops/my.jsp
		//路径映射怎么解决
		//mav.setViewName("redirect:/notice/"+unique+"/show.htm");
		mav.setViewName("../../notice/"+unique+"/show");
		return mav;
	}
}
