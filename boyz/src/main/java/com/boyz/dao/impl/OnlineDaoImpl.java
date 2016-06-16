package com.boyz.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.boyz.dao.OnlineDao;
import com.boyz.model.OnlineApp;

@Repository
public class OnlineDaoImpl implements OnlineDao{

	private static final Logger logger = Logger.getLogger(OnlineDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<OnlineApp> getApps(int currentPage, String conds) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_menu_notice order by record_insert_time desc ";
		return this.jdbcTemplate.query(sql,new RowMapper<OnlineApp>(){
			public OnlineApp mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				OnlineApp onApp = new OnlineApp();
				onApp.setIdx( rs.getString("idx")==null ? "" : rs.getString("idx") );
				onApp.setMenuCycle( rs.getString("menu_cycle")==null ? "" : rs.getString("menu_cycle") );
				onApp.setMenuId( rs.getString("menu_id")==null ? "" : rs.getString("menu_id") );
				onApp.setMenuMemo( rs.getString("menu_memo")==null ? "" : rs.getString("menu_memo") );
				onApp.setMenuOrg( rs.getString("menu_org")==null ? "" : rs.getString("menu_org") );
				onApp.setMenuOwner( rs.getString("menu_owner")==null ? "" : rs.getString("menu_owner") );
				onApp.setMenuPath( rs.getString("menu_path")==null ? "" : rs.getString("menu_path") );
				onApp.setMenuTitle( rs.getString("menu_title")==null ? "" : rs.getString("menu_title") );
				onApp.setOpDay( rs.getString("op_day")==null ? "" : rs.getString("op_day") );
				onApp.setRecordInsertTime( rs.getString("record_insert_time")==null ? "" : rs.getString("record_insert_time") );
				return onApp;
			}
		});
	}

}
