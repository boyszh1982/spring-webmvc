package com.boyz.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.boyz.dao.StaffDao;
import com.boyz.model.Staff;

@Repository
public class StaffDaoImpl implements StaffDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Staff getStaffById(String staffId) {
		// TODO Auto-generated method stub
		String sql = "select * from sakila.staff where staff_id = ?";
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
					staff.setLastUpdate(arg0.getDate("last_update"));
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
}
