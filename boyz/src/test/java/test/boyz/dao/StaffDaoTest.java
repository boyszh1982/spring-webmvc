package test.boyz.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.boyz.model.Staff;
import com.boyz.service.StaffService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-jdbc.xml"})
public class StaffDaoTest {
	
	private static final Logger logger = Logger.getLogger(StaffDaoTest.class);
	
	@Autowired
	@Qualifier("staffServiceImpl")
	private StaffService staffService ;
	
	@Test
	public void test(){
		;
	}
	
	//@Test
	public void test_getStaffById(){
		Staff staff = staffService.getStaffById("1");
		logger.info(JSON.toJSON(staff));
	}
	
	//@Test
	public void test_updateStaff(){
		Staff staff = staffService.getStaffById("1");
		staff.setLastUpdate(new java.util.Date());
		staffService.updateStaff(staff);
	}
	
	//@Test
	public void test_updateStaffWithTransactional(){
		//1.首次查询，触发DB查询
		Staff staff = staffService.getStaffById("1");
		logger.info("1."+JSON.toJSONStringWithDateFormat(staff, "yyyy-MM-dd HH:mm:ss"));
		//2.二次查询，触发CACHE查询
		staff = staffService.getStaffById("1");
		logger.info("2."+JSON.toJSONStringWithDateFormat(staff, "yyyy-MM-dd HH:mm:ss"));
		//修改操作，修改将失败
		try{
			staff.setLastUpdate(new java.util.Date());
			staffService.updateStaffWithTransactional(staff);
		}catch(RuntimeException rex){
			rex.printStackTrace();
		}
		//3.修改方法rollback,触发CACHE查询,不触发DB查询
		staff = staffService.getStaffById("1");
		logger.info("3."+JSON.toJSONStringWithDateFormat(staff, "yyyy-MM-dd HH:mm:ss"));
		
	}
	
	//@Test
	public void test_geteStaffs(){
		List<Staff> staffs = staffService.getStaffs();
		logger.info(JSON.toJSON(staffs));	
	}
	
	//@Test
	public void test_staffCache(){
		//1.应该查询数据库,调用StaffServiceImpl.getStaffById
		Staff staff_1 = staffService.getStaffById("1");
		logger.info("1."+JSON.toJSONStringWithDateFormat(staff_1, "yyyy-MM-dd HH:mm:ss"));
		//2.应直接查询缓存,不调用StaffServiceImpl.getStaffById
		logger.info("2."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("1"), "yyyy-MM-dd HH:mm:ss"));
		
		//修改数据库内容，触发缓存清空动作
		staff_1.setLastUpdate(new java.util.Date());
		staffService.updateStaff(staff_1);
		
		//3.应该查询数据库,调用StaffServiceImpl.getStaffById
		Staff staff_2 = staffService.getStaffById("1");
		logger.info("3."+JSON.toJSONStringWithDateFormat(staff_2, "yyyy-MM-dd HH:mm:ss"));
		//4.应直接查询缓存,不调用StaffServiceImpl.getStaffById
		logger.info("4."+JSON.toJSONStringWithDateFormat(staff_2, "yyyy-MM-dd HH:mm:ss"));
		
		//5.当参数变化时，且该参数未被缓存过，则调用StaffServiceImpl.getStaffById
		logger.info("5."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("2"), "yyyy-MM-dd HH:mm:ss"));
		//6.应直接查询缓存,不调用StaffServiceImpl.getStaffById
		logger.info("6."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("2"), "yyyy-MM-dd HH:mm:ss"));
	}
	
	//@Test
	public void test_getStaffs(){
		//1.首次查询，触发DB查询
		List<Staff> staffList = staffService.getStaffs();
		logger.info("1."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		//2.二次查询，触发CACHE查询
		staffList = staffService.getStaffs();
		logger.info("2."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		//修改Staff属性
		Staff staff = staffList.get(0);
		staff.setLastUpdate(new java.util.Date());
		staffService.updateStaff(staff);
		//3.在修改了index为0的Staff后，并没有触发DB查询，而是触发CACHE查询，并且CACHE中的属性已经被修改。
		staffList = staffService.getStaffs();
		logger.info("3."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		staffService.removeCache();
		//4.在removeCache()[@CacheEvict(value="staffCache",allEntries=true)]后，调用了DB查询。
		staffList = staffService.getStaffs();
		logger.info("4."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
	}
}
