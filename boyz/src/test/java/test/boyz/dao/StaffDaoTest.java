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
		//1.�״β�ѯ������DB��ѯ
		Staff staff = staffService.getStaffById("1");
		logger.info("1."+JSON.toJSONStringWithDateFormat(staff, "yyyy-MM-dd HH:mm:ss"));
		//2.���β�ѯ������CACHE��ѯ
		staff = staffService.getStaffById("1");
		logger.info("2."+JSON.toJSONStringWithDateFormat(staff, "yyyy-MM-dd HH:mm:ss"));
		//�޸Ĳ������޸Ľ�ʧ��
		try{
			staff.setLastUpdate(new java.util.Date());
			staffService.updateStaffWithTransactional(staff);
		}catch(RuntimeException rex){
			rex.printStackTrace();
		}
		//3.�޸ķ���rollback,����CACHE��ѯ,������DB��ѯ
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
		//1.Ӧ�ò�ѯ���ݿ�,����StaffServiceImpl.getStaffById
		Staff staff_1 = staffService.getStaffById("1");
		logger.info("1."+JSON.toJSONStringWithDateFormat(staff_1, "yyyy-MM-dd HH:mm:ss"));
		//2.Ӧֱ�Ӳ�ѯ����,������StaffServiceImpl.getStaffById
		logger.info("2."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("1"), "yyyy-MM-dd HH:mm:ss"));
		
		//�޸����ݿ����ݣ�����������ն���
		staff_1.setLastUpdate(new java.util.Date());
		staffService.updateStaff(staff_1);
		
		//3.Ӧ�ò�ѯ���ݿ�,����StaffServiceImpl.getStaffById
		Staff staff_2 = staffService.getStaffById("1");
		logger.info("3."+JSON.toJSONStringWithDateFormat(staff_2, "yyyy-MM-dd HH:mm:ss"));
		//4.Ӧֱ�Ӳ�ѯ����,������StaffServiceImpl.getStaffById
		logger.info("4."+JSON.toJSONStringWithDateFormat(staff_2, "yyyy-MM-dd HH:mm:ss"));
		
		//5.�������仯ʱ���Ҹò���δ��������������StaffServiceImpl.getStaffById
		logger.info("5."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("2"), "yyyy-MM-dd HH:mm:ss"));
		//6.Ӧֱ�Ӳ�ѯ����,������StaffServiceImpl.getStaffById
		logger.info("6."+JSON.toJSONStringWithDateFormat( staffService.getStaffById("2"), "yyyy-MM-dd HH:mm:ss"));
	}
	
	//@Test
	public void test_getStaffs(){
		//1.�״β�ѯ������DB��ѯ
		List<Staff> staffList = staffService.getStaffs();
		logger.info("1."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		//2.���β�ѯ������CACHE��ѯ
		staffList = staffService.getStaffs();
		logger.info("2."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		//�޸�Staff����
		Staff staff = staffList.get(0);
		staff.setLastUpdate(new java.util.Date());
		staffService.updateStaff(staff);
		//3.���޸���indexΪ0��Staff�󣬲�û�д���DB��ѯ�����Ǵ���CACHE��ѯ������CACHE�е������Ѿ����޸ġ�
		staffList = staffService.getStaffs();
		logger.info("3."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
		staffService.removeCache();
		//4.��removeCache()[@CacheEvict(value="staffCache",allEntries=true)]�󣬵�����DB��ѯ��
		staffList = staffService.getStaffs();
		logger.info("4."+JSON.toJSONStringWithDateFormat( staffList, "yyyy-MM-dd HH:mm:ss"));
	}
}
