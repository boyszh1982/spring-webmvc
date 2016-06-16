package com.boyz.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boyz.model.OnlineApp;
import com.boyz.service.OnlineService;
import com.boyz.utils.DateUtil;

@Controller
@RequestMapping("/OnlineController")
public class OnlineController {
	
	private static final Logger logger = Logger.getLogger(OnlineController.class);
	
	@Autowired
	private OnlineService onlineService;
	
	@RequestMapping(value="/show")
	public ModelAndView show( HttpServletRequest request ) throws UnsupportedEncodingException{
		
		String msgs = request.getParameter("msgs");
		String demsgs = msgs == null ? "" : URLDecoder.decode(msgs, "UTF-8");
		if(demsgs.trim().equals("")){
			demsgs = DateUtil.formatDate("{date:yyyy-MM,0}", new Date() );
		}
		//logger.info(" ------ demsgs="+demsgs);
		List<OnlineApp> onlineApps = onlineService.getApps(0, "");
		List<OnlineApp> markedOnlineApps = onlineService.markApps(onlineApps, demsgs);
		ModelAndView model = new ModelAndView();
		model.clear();
		model.addObject("onlineApps",markedOnlineApps);
		model.addObject("demsgs",demsgs);
		model.setViewName("online/show");
		return model;
	}
	
}
