package com.boyz.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.boyz.model.Staff;

public interface StaffDao {

	public Staff getStaffById(String staffId) ;
	
	public Staff updateStaff(Staff staff);
	
	public Staff updateStaffWithTransactional(Staff staff);
	
	public List<Staff> getStaffs() ;
	
}
