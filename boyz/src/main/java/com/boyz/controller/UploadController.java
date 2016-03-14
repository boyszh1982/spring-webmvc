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
		logger.info("��������������ú󣬿���request.getAttribute(\"staffs\")���Ƿ���ֵ:"+request.getAttribute("staffs"));
		/**
		 * 1�����û��״ε���http://localhost:8080/boyz/UploadController/list.doʱ��ʾΪ'null'
		 * 2������
		ModelAndView mav = new ModelAndView();
		mav.addObject("staffs","");
		mav.setViewName("redirect:/UploadController/list.do");
				֮��ҳ����ʾ����http://localhost:8080/boyz/UploadController/list.do?staffs=����
				
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
			; // �ļ���
		} else {
			System.out.println("�ļ�����: " + mfile.getSize());
			System.out.println("�ļ�����: " + mfile.getContentType());
			System.out.println("�ļ�����: " + mfile.getName());
			System.out.println("�ļ�ԭ��: " + mfile.getOriginalFilename());
			System.out.println("========================================");
			
			String contextpath = request.getSession().getServletContext().getRealPath("/WEB-INF/views/upload");
			// ���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����
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
		staffService.removeCache(); // ��remove ��½������
		                                           // cache �᲻���Զ����������жϣ�����KEYֵ��ͬ������
		List<Staff> list = staffService.getStaffs();
		logger.info("................................. listsize 1 = " + list.size());
		list.add(staffService.getStaffById(staffId));
		logger.info("................................. listsize 2 = " + list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("staffs",list);
		//mav.setViewName("redirect:/UploadController/list.do");  // ����redirect���ܽ�����staffs����list
		mav.setViewName("upload/list"); //����forward���Խ�����staffs����list
		return mav;
	}

}
