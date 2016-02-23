package com.boyz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.boyz.dao.StaffDao;
import com.boyz.model.Staff;
import com.boyz.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	@Qualifier("staffDaoImpl")
	private StaffDao staffDao ;
	
	public Staff getStaffById(String staffId) {
		// TODO Auto-generated method stub
		return staffDao.getStaffById(staffId);
	}

}
