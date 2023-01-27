package com.ezen.springmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class ControllerTest {
	
	@Autowired
	private WebApplicationContext context; //@WebAppConfiguration달아야 사용 가능
	
	private MockMvc mockMvc; 
	//가짜 mockMvc

	//자동 임포트 하면 이상한 걸로 임포트 됨. 행오버해서 org.junit.BeforeClass로 change 해주기o
	@Before  
	public void testSetup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		//매 테스트마다 MockMvcBuilder로 MockMvc를 생성한다.
	}
	
	
	
	//테스트 수행하는데 servlet cookie? 어쩌고 없다고 콘솔에 class not found 에러 메시지 뜸
	//놀랍게도 서블렛 구버전은 cookie어쩌고가 없대 그래서 메이븐 레포 가서 디펜던씨 복 pom.xml에 붙 
	@Ignore
	@Test
	public void testBlogController1() {
		try {
			//MockmvcRequestBuilder로 가짜 요청을 만들어줌
			String nextView = mockMvc.perform(
					MockMvcRequestBuilders.get("/hi/blog")  //get방식으로 요청을 보내보고 뭐가 없는지 확인할 수 있음
				).andReturn().getModelAndView().getViewName();
			log.info("next view : ", nextView);
			assertEquals("hello/blog", nextView);
			
		} catch (Exception e) {
			fail(); //예외가 발생하면 테스트 실패다. 
		}
	}
	
	
	
	
	@Test
	public void shouldSaveUploadFile() throws Exception {
		MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "my File Upload Test ...".getBytes());
		
		//multipart() 멀티파트 방식으로 데이터를 보내겠다는 뜻
		mockMvc.perform(multipart("/fileupload/").file(multipartFile));
		
	}
	

	
}	
