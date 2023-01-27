package com.ezen.springmvc.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

//여러 로거가 있으므로 log4j로 정확히 임포트 해줄 것 주의!!
//import org.apache.log4j.Logger;  //근데 만약 @Log4j 롬복을 달았다면 필요 없음. 그리고 이제 log4j2로 업글할 때 log4j지워버려서 임포트도 안 됨
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;
import com.ezen.springmvc.model.shop.Shop;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Log4j 얘 이제 못 씀
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class); //이건 sl4j의 로거고
	//우리는 Log4j를 사용할 것
	
	//private Logger log = org.apache.log4j.Logger.getLogger(this.getClass()); //현재 클래스 (HomeController)를 넘겨줘야 됨
	//그러면 이 클래스를 로그 해줌
	//근데 이것마저도 롬복으로 할 수 있음 @Log4j 달아주면 됨
	
	@Autowired //얘도 임포트 해주기
	Coffee coffee; //생성자에 문제가 생겨서 바꿔야 할 때 50군데 생성자를 다 바꾸지 않고 @Component 어노테이션만 갖다 붙이면 된다는 장점!
	
	@Autowired
	Shop shop;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//logger.info("Welcome home! The client locale is {}.", locale);  
		
		//System.out.println("COFFEE : " + coffee);//서버 켤 때마다 나올 것 
		//System.out.println("SHOP : " + shop);
		
		log.error("this is error msg");
		log.info("information");
		log.debug("I am debug msg");
		log.trace("tarce msg");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//애트리뷰터를 request가 아니라 model이라는 것에 추가하고 있네 스프링이라 좀 다르대
		model.addAttribute("coffee", coffee);
		
		return "home"; //home.jsp로 가라는 뜻
	}

}
