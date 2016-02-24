package com.boyz.service;

import java.util.List;

import com.boyz.model.Staff;

public interface StaffService {

	public Staff getStaffById(String staffId);
	
	public Staff updateStaff(Staff staff);
	
	public Staff updateStaffWithTransactional(Staff staff);
	
	public List<Staff> getStaffs() ;
	
	public void removeCache() ;
}
