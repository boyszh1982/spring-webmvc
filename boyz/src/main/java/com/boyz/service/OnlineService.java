package com.boyz.service;

import java.util.List;

import com.boyz.model.OnlineApp;

public interface OnlineService {
	
	public List<OnlineApp> getApps(int currentPage , String conds );
	
	public List<OnlineApp> markApps(List<OnlineApp> onlineApps , String demsgs);
	
}
