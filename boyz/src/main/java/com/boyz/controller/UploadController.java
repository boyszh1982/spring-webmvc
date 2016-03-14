package com.boyz.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boyz.model.Staff;
import com.boyz.service.StaffService;

@Controller
@RequestMapping("/UploadController")
public class UploadController {

	private static final Logger logger = Logger
			.getLogger(UploadController.class);

	@Autowired
	@Qualifier("staffServiceImpl")
	private StaffService staffService ;
	
	@RequestMapping(value = "/list")
	public String list(Model model , HttpServletRequest request ) {
		//model.addAttribute("staffs", staffService.getStaffs());
		logger.info("在这个方法被调用后，看看request.getAttribute(\"staffs\")中是否有值:"+request.getAttribute("staffs"));
		/**
		 * 1、在用户首次调用http://localhost:8080/boyz/UploadController/list.do时显示为'null'
		 * 2、经过
		ModelAndView mav = new ModelAndView();
		mav.addObject("staffs","");
		mav.setViewName("redirect:/UploadController/list.do");
				之后，页面显示如下http://localhost:8080/boyz/UploadController/list.do?staffs=，且
				
		 */
		request.setAttribute("staffs",  staffService.getStaffs());
		return "upload/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		logger.info("add get .................................................. ");
		return "upload/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@RequestParam MultipartFile mfile, HttpServletRequest request) {
		logger.info("add post .................................................. ");
		if (mfile.isEmpty()) {
			; // 文件空
		} else {
			System.out.println("文件长度: " + mfile.getSize());
			System.out.println("文件类型: " + mfile.getContentType());
			System.out.println("文件名称: " + mfile.getName());
			System.out.println("文件原名: " + mfile.getOriginalFilename());
			System.out.println("========================================");
			
			String contextpath = request.getSession().getServletContext().getRealPath("/WEB-INF/views/upload");
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			try {
				File nfile = new File(contextpath, mfile.getOriginalFilename());
				System.out.println("new file path : = " + nfile.getAbsolutePath());
				FileUtils.copyInputStreamToFile(mfile.getInputStream(), nfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String staffId = request.getParameter("staffId");
		staffService.removeCache(); // 不remove 会陆续增加
		                                           // cache 会不会自动进行增量判断，比如KEY值相同不增加
		List<Staff> list = staffService.getStaffs();
		logger.info("................................. listsize 1 = " + list.size());
		list.add(staffService.getStaffById(staffId));
		logger.info("................................. listsize 2 = " + list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("staffs",list);
		//mav.setViewName("redirect:/UploadController/list.do");  // 测试redirect不能将参数staffs带入list
		mav.setViewName("upload/list"); //测试forward可以将参数staffs带入list
		return mav;
	}

}
