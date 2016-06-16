package com.boyz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.boyz.dao.OnlineDao;
import com.boyz.model.OnlineApp;
import com.boyz.service.OnlineService;
import com.boyz.utils.StringUtil;

@Service
public class OnlineServiceImpl implements OnlineService {
	private static final Logger logger = Logger.getLogger(OnlineServiceImpl.class);

	@Autowired
	private OnlineDao onlineDao ;
	
	@Cacheable(value="onlineAppCache")
	public List<OnlineApp> getApps(int currentPage, String conds) {
		// TODO Auto-generated method stub
		return onlineDao.getApps(currentPage, conds);
	}
	
	@CacheEvict(value="onlineAppCache",allEntries=true)
	public void removeCache(){}

	public List<OnlineApp> markApps(List<OnlineApp> onlineApps, String demsgs) {
		// TODO Auto-generated method stub
		if( demsgs.trim().equals("")
				|| demsgs.equalsIgnoreCase("all") 
				|| demsgs.equals("全部")  ){
			return onlineApps;
		}
		List<OnlineApp> markedOnlineApps = new ArrayList<OnlineApp>();
		for(OnlineApp onlineApp : onlineApps){
			OnlineApp markedOnlineApp = markApp(onlineApp, demsgs);
			if(markedOnlineApp.isMarked()) {
				markedOnlineApps.add(markedOnlineApp);
			}
		}
		return markedOnlineApps;
	}
	
	private OnlineApp markApp(OnlineApp onlineApp , String demsgs ){
		boolean marked = false ;
		/**
		 * OnlineApp markedOnlineApp = onlineApp;
		 * 改变markedOnlineApp的值同时修改了onlineApp的值？是的。
		 */
		//OnlineApp markedOnlineApp = onlineApp;
		OnlineApp markedOnlineApp = new OnlineApp();
		markedOnlineApp.setIdx(onlineApp.getIdx());
		markedOnlineApp.setMarked(onlineApp.isMarked());
		markedOnlineApp.setMenuCycle(onlineApp.getMenuCycle());
		markedOnlineApp.setMenuId(onlineApp.getMenuId());
		markedOnlineApp.setMenuMemo(onlineApp.getMenuMemo());
		markedOnlineApp.setMenuOrg(onlineApp.getMenuOrg());
		markedOnlineApp.setMenuOwner(onlineApp.getMenuOwner());
		markedOnlineApp.setMenuPath(onlineApp.getMenuPath());
		markedOnlineApp.setMenuTitle(onlineApp.getMenuTitle());
		markedOnlineApp.setOpDay(onlineApp.getOpDay());
		markedOnlineApp.setRecordInsertTime(onlineApp.getRecordInsertTime());

		String strMenuCycle = onlineApp.getMenuCycle();
		String strMenuTitle = onlineApp.getMenuTitle();
		String strMenuPath = onlineApp.getMenuPath();
		String strMenuMemo = onlineApp.getMenuMemo();
		String strMenuOwner = onlineApp.getMenuOwner();
		String strMenuOrg = onlineApp.getMenuOrg();
		String strRecordInsertTime = onlineApp.getRecordInsertTime();
		
		String[] msgArr = demsgs.split(" ");
		for(int i=0;i<msgArr.length;i++){
			String msg = msgArr[i];
						
			if( StringUtil.ignoreCaseIndexOf(strMenuCycle , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuCycle,new String[]{"<font color=red>","</font>"} )   ) {
				strMenuCycle = mixMarked(strMenuCycle, msg );
				markedOnlineApp.setMenuCycle( strMenuCycle );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf(strMenuTitle , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuTitle,new String[]{"<font color=red>","</font>"} )   ) {
				strMenuTitle = mixMarked(strMenuTitle, msg );
				markedOnlineApp.setMenuTitle( strMenuTitle );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf( strMenuPath , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuPath,new String[]{"<font color=red>","</font>"} )   ){
				strMenuPath = mixMarked(strMenuPath, msg );
				markedOnlineApp.setMenuPath( strMenuPath );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf( strMenuMemo , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuMemo,new String[]{"<font color=red>","</font>"} )   ){
				strMenuMemo = mixMarked(strMenuMemo, msg );
				markedOnlineApp.setMenuMemo( strMenuMemo );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf( strMenuOwner , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuOwner,new String[]{"<font color=red>","</font>"} )   ){
				strMenuOwner = mixMarked(strMenuOwner, msg );
				markedOnlineApp.setMenuOwner( strMenuOwner );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf( strMenuOrg , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strMenuOrg,new String[]{"<font color=red>","</font>"} )   ){
				strMenuOrg = mixMarked(strMenuOrg, msg );
				markedOnlineApp.setMenuOrg( strMenuOrg );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
			if( StringUtil.ignoreCaseIndexOf( strRecordInsertTime , msg , 0 ) > -1 
					&& !StringUtil.regularContain(strRecordInsertTime,new String[]{"<font color=red>","</font>"} )   ){
				strRecordInsertTime = mixMarked(strRecordInsertTime, msg );
				markedOnlineApp.setRecordInsertTime( strRecordInsertTime );
				mixMarkedBy(markedOnlineApp,msg);
				marked = true ;
			}
			
		}
		
		markedOnlineApp.setMarked(marked);
		
		return markedOnlineApp;
	}
	
	private String mixMarked(String msg , String regular ){
		String result = msg ;
		List<String> reglist = StringUtil.regularSubstring( msg, "(?i)"+regular) ; 
		for(String reg:reglist){
			result = result.replaceAll(reg ,  "<font color=red>"+reg+"</font>");
		}
		return result;
	}
	
	private void mixMarkedBy(OnlineApp markedOnlineApp ,String msg ){
		String markedByPre = markedOnlineApp.getMarkedBy() == null ? "" :markedOnlineApp.getMarkedBy() ;
		/*
		 * 如果标签关键字已经出现则不需要再次加入标签关键字
		 * 如果msg在标签关键字中未出现过，则加入msg。
		 */
		if( ! (markedByPre.indexOf(msg) > -1) )
			markedOnlineApp.setMarkedBy( markedByPre + " " + msg);
	}
		
}
