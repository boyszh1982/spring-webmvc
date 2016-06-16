package com.boyz.dao;

import java.util.List;

import com.boyz.model.OnlineApp;

public interface OnlineDao {

	public List<OnlineApp> getApps(int currentPage , String conds ) ;
}
