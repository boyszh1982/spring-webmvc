package com.boyz.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		/**
		 * session test
		 */
		HttpSession session = request.getSession();
		Staff staff = new Staff();
		staff.setStaffId("1234567");
		session.setAttribute("sta", staff);
		logger.info("..........................................staffid = 1234567");
		Staff update = (Staff) session.getAttribute("sta");
		update.setStaffId("00000000");
		logger.info("..........................................staffid = 0000000");
		
		/**
		 * 此处在页面显示staffId = 00000000 
		 * 证明无需将 update 重新执行 session.setAttribute("sta",update); 方法。
		 * 为什么在以下的update方法中没有进行this.session.setAttribute(Constants.SESSION_KEY_UPLOAD_PROGRESS_INFO, ps);
		 * http://rensanning.iteye.com/blog/2248760 [spring MVC 断点续传+进度条]
		 * 
		@Component
		public class CustomProgressListener implements ProgressListener {
			
			private HttpSession session;  
		 
		    public void setSession(HttpSession session){
		        this.session = session;
		        ProgressInfo ps = new ProgressInfo();
		        this.session.setAttribute(Constants.SESSION_KEY_UPLOAD_PROGRESS_INFO, ps);
		    }
		    
		    @Override
		    public void update(long pBytesRead, long pContentLength, int pItems) {
		        ProgressInfo ps = (ProgressInfo) session.getAttribute(Constants.SESSION_KEY_UPLOAD_PROGRESS_INFO);
		        ps.setBytesRead(pBytesRead);
		        ps.setContentLength(pContentLength);
		        ps.setItemSeq(pItems);
		    }
		}


		 */
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
