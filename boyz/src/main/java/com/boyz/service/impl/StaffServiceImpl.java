package com.boyz.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.boyz.dao.StaffDao;
import com.boyz.model.Staff;
import com.boyz.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	private static final Logger logger = Logger.getLogger(StaffServiceImpl.class);
	
	@Autowired
	@Qualifier("staffDaoImpl")
	private StaffDao staffDao ;
	
	@Cacheable(value="staffCache")
	public Staff getStaffById(String staffId) {
		// TODO Auto-generated method stub
		logger.info("....................... getStaffById="+staffId);
		return staffDao.getStaffById(staffId);
	}

	@CacheEvict(value="staffCache" , key="#staff.getStaffId()")
	public Staff updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.updateStaff(staff);
	}
	
	@CacheEvict(value="staffCache" , key="#staff.getStaffId()")
	public Staff updateStaffWithTransactional(Staff staff) {
		// TODO Auto-generated method stub
		return staffDao.updateStaffWithTransactional(staff);
	}

	//List<Staff> ÈçºÎcache?
	@Cacheable(value="staffCache")
	public List<Staff> getStaffs() {
		// TODO Auto-generated method stub
		logger.info("....................... getStaffs=");
		return staffDao.getStaffs();
	}
	
	@CacheEvict(value="staffCache",allEntries=true)
	public void removeCache(){}

}
