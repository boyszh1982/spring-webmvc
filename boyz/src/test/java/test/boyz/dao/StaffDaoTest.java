package test.boyz.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.boyz.dao.StaffDao;
import com.boyz.model.Staff;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-jdbc.xml"})
public class StaffDaoTest {
	
	private static final Logger logger = Logger.getLogger(StaffDaoTest.class);
	
	@Autowired
	private StaffDao staffDao ;

	@Test
	@Qualifier("staffDaoImpl")
	public void test_getStaffById(){
		Staff staff = staffDao.getStaffById("1");
		logger.info(JSON.toJSON(staff));
	}
}
