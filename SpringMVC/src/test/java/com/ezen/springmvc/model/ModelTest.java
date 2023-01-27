package com.ezen.springmvc.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		})
//루트컨텍스트가 필요하면 배열 이용해서 추가하면 됨 
public class ModelTest {
	
	@Autowired
	Coffee coffee;
	
	@Test
	public void appleTest() {
		log.info(coffee);
		assertNotNull("coffee가 null입니다.", coffee);
	}
	
}
