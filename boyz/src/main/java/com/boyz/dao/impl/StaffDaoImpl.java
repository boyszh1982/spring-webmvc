package com.boyz.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boyz.dao.StaffDao;
import com.boyz.model.Staff;

@Repository
public class StaffDaoImpl implements StaffDao {

	private static final Logger logger = Logger.getLogger(StaffDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Staff getStaffById(String staffId) {
		// TODO Auto-generated method stub
		String sql = "select * from staff where staff_id = ?";
		Object[] args = new Object[]{staffId};
		return this.jdbcTemplate.query(sql, args, new ResultSetExtractor<Staff>(){
			public Staff extractData(ResultSet arg0) throws SQLException,
					DataAccessException {
				// TODO Auto-generated method stub
				Staff staff = new Staff();
				if(arg0.next()){
					staff.setActive(arg0.getBoolean("active"));
					staff.setAddressId(arg0.getString("address_id"));
					staff.setEmail(arg0.getString("email"));
					staff.setFirstName(arg0.getString("first_name"));
					staff.setLastName(arg0.getString("last_name"));
					staff.setLastUpdate(arg0.getTimestamp("last_update"));
					staff.setPassword(arg0.getString("password"));
					staff.setPicture(arg0.getBytes("picture"));
					staff.setStaffId(arg0.getString("staff_id"));
					staff.setStoreId(arg0.getString("store_id"));
					staff.setUsername(arg0.getString("username"));
				}
				return staff;
			}
		});
	}

	public Staff updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		String sql = "update staff set last_update = ? where staff_id = ?";
		int cnt = this.jdbcTemplate.update(sql, new Object[]{staff.getLastUpdate(),staff.getStaffId()});
		return staff;
	}

	public List<Staff> getStaffs() {
		// TODO Auto-generated method stub
		String sql = "select * from staff";
		return this.jdbcTemplate.query(sql,new RowMapper<Staff>(){
			public Staff mapRow(ResultSet arg0, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Staff staff = new Staff();
				staff.setActive(arg0.getBoolean("active"));
				staff.setAddressId(arg0.getString("address_id"));
				staff.setEmail(arg0.getString("email"));
				staff.setFirstName(arg0.getString("first_name"));
				staff.setLastName(arg0.getString("last_name"));
				staff.setLastUpdate(arg0.getTimestamp("last_update"));
				staff.setPassword(arg0.getString("password"));
				staff.setPicture(arg0.getBytes("picture"));
				staff.setStaffId(arg0.getString("staff_id"));
				staff.setStoreId(arg0.getString("store_id"));
				staff.setUsername(arg0.getString("username"));
				return staff;
			}
		});
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Staff updateStaffWithTransactional(Staff staff) {
		// TODO Auto-generated method stub
		String sql_1 = "update staff set last_update = ? where staff_id = ?";
		int cnt_1 = this.jdbcTemplate.update(sql_1, new Object[]{staff.getLastUpdate(),staff.getStaffId()});
		
		String sql_2 = "update rental set last_update = ? where staff_id = ?";
		int cnt_2 = this.jdbcTemplate.update(sql_2, new Object[]{staff.getLastUpdate(),staff.getStaffId()});
		
		logger.info("cnt_1="+cnt_1 + ",cnt_2="+cnt_2);
		
		if( cnt_1 != 1 ){
			throw new RuntimeException(sql_1 + " cnt is not equal 1.");
		}
		if( cnt_2 != 1 ){
			throw new RuntimeException(sql_2 + " cnt is not equal 1.");
		}
		
		return staff;
	}
}
