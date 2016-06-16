package test.boyz.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boyz.model.OnlineApp;
import com.boyz.service.OnlineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-jdbc.xml"})
public class OnlineTest {

	private static final Logger logger = Logger.getLogger(OnlineTest.class);
	
	@Autowired
	@Qualifier("onlineServiceImpl")
	private OnlineService onlineService ;
	
	@Test
	public void test_default(){
		System.out.println("......");
	}
	//@Test
	public void test_getApps(){
		List<OnlineApp> list = onlineService.getApps(0, null);
		for(OnlineApp oa : list ){
			System.out.println(oa.getMenuTitle());
		}
	}
	
}
