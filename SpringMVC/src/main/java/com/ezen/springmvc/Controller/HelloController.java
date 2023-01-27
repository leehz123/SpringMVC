package com.ezen.springmvc.Controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping(value= {"/hello", "/hi", "/welcome"})
@Controller
public class HelloController {
	
	
	@RequestMapping(
			value = "/news", 
			method = {RequestMethod.GET, RequestMethod.POST})
	public String news() {
		log.info("컨트롤러에는 잘 도착함");
		
		//이건 08_Controller.txt 들어가기 전애 해놓은 리턴값
		//return "news"; //다음으로 사용자에게 보여져야 할 view의 이름을 문자열로 리턴한다. 
		// 그래서 주소창에 http://localhost:8888/springmvc/hello/news 입력하면 
		// 리턴값인 news를 써서  /WEB-INF/views/news.jsp로 접속하려고 하는 거 

		
		
		//이건 08_Controller.txt 수업에서 해놓은 리턴값
		return "hello/news";//위에 거 말고 이걸로 리턴 
		//hello 앞에 슬래시는 붙여도 돼지만 뺴는게 정석이겠지
		//servlet-context.xml 가서 보면 <beans:property name="prefix" value="/WEB-INF/views/" /> 이렇게 맨 뒤에 /를 붙여놨으니까		
		//앞에는 위의 주소를 붙이고 
		//뒤에는 <beans:property name="suffix" value=".jsp" /> 주소를 붙임
		
		// /WEB-INF/views/ + 리턴값 + .jsp 를 붙여서 뷰의 경로를 완성시킴
		
		//http://localhost:8888/springmvc/hello/news 로 접속해보셈
	}
	//http://localhost:8888/springmvc/hello/news로 접속하면 페이지는 안 뜨지만
	//[09:41:34.960] INFO  [http-nio-8888-exec-7] com.ezen.springmvc.Controller.HelloController news - 컨트롤러에는 잘 도착함
	// 뜨면 된거

	//스프링이 미리 만들어놓은 디스패쳐서블릿을 활용해서 여기까지 들어오는 것 (그 안에 소스가 어떻게 돼 있는진 모르것는디)
	//web.xml파일에 들어가면 19줄에 <servlet-name>appServlet</servlet-name> 있음
	//init param 태그 안에 <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>을 보면  
	// servlet-context.xml내용을 쫙 빨아들여서 어쩌고 한다
	// <context-param>은 전역 초기화 파람을 의미했었지? 그래서 전역 파라미터는 web.xml에 지저분하게 계속 추가하지 말고 root-context.xml에 추가하면 됨	
	
	
	
	@GetMapping("/blog")
	public String blog() {
		return "hello/blog";
	}
	
	
	//http://localhost:8888/springmvc/hello/coffee/add
	@GetMapping("/coffee/add")
	public String coffeeAddForm() {
		return "coffee/addMenu";
	}
	
	

//	@PostMapping("/coffee/add")
//	public String coffeeAdd() {
//		log.info("coffee데이터가 도착하는 곳");
//		//return "home";//일단 이렇게 하면 리로딩이 되면서 forward로 감
//		return "redirect:/"; //리다렉은 이렇게. /를 HomeController로 요청을 보내는 것
//	}

	
	
	
	@PostMapping("/coffee/add1")
	public String coffeeAdd1(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException { //이렇게 request를 파라미터로 받는 것도 가능!	
		
				
		log.info("coffee add1 (자동 바인딩을 사용하지 않음)");
		
		req.setCharacterEncoding("EUC-KR");	
		log.info("coffee name : " + req.getParameter("name"));
		log.info("coffee price : " + req.getParameter("price"));
		log.info("hot coffee : " + req.getParameter("hot"));
	
		
		//request로 받아서 Coffee에 담아주는 방법도 있겠지
		req.setAttribute("coffee", new Coffee(
					req.getParameter("name"),
					Integer.parseInt(req.getParameter("price")),
					Boolean.parseBoolean(req.getParameter("hot"))
				));
		
		
		//포워딩
		return "home"; //이리로 보내보면 애트리뷰트에 자동으로 추가된 값이 html코드에 의해 표시됨
	}
	
	
	
	@PostMapping("/coffee/add2")
	public String coffeeAdd2(Coffee coffee) {//이렇게 해줘도 메서드 안에서 데이터 넣는 작업 안 했는데, 받기만 헀는데 안에 값이 들어 있게 됨 (자동바인딩) 
	//이게 바로 자동 바인딩. 메서드의 매개변수로 받는 데이터의 타입이 존재하면 그대로 자동으로 채워줌!
	
		//request에 들어 있던 데이터의 타입은 다 String이었는데 
		//Coffee coffee로 받고 데이터 타입을 보니 boolean그대로 옴. 이런 편리함!
			
		log.info("coffee add2 (자동 바인딩 사용함)");
		log.info(coffee);

		if(coffee.isHot()) {
			log.info("뜨거운 커피가 추가 됐습니다.");			
		} else {
			log.info("차가운 커피가 추가 됐습니다.");
		}
		
		//return "redirect:/"; //리다렉 해서 home으로 가려면 / 해주면 됨
		
		return "home"; //이리로 보내보면 애트리뷰트에 자동으로 추가된 값이 html코드에 의해 표시됨
	}
	
	
	
	//콘솔에 출력은 되지만 어트리뷰트에 자동으로 실리지는 않음
	//그니까 애트리에 자동으로 실리려면 매개변수를 데이터모델로 쓰이도록 선언해둔 클래스의 타입으로 해줘야 하는듯
	/*
	 	@PostMapping("/coffee/add3")
	public String coffeeAdd3(String name, Integer price, Boolean hot) { 
		log.info("name : " + name);
		log.info("price : " + price);
		log.info("hot : " + hot);
		
		return "home";
	}
	 */
	
	
	
	//@ModelAttribute("데이터모델의 속성과 일치하는 이름") 어노테이션 이용하는 법
	@PostMapping("/coffee/add3")
	public String coffeeAdd3(
				@ModelAttribute("name") String a,
				@ModelAttribute("price") Integer b,
				@ModelAttribute("hot") Boolean c ) { 
	//@ModelAttribute어노테이션을 사용하는 경우에는 ()괄호 안에서 속성명을 지정해주기 때문에 매개변수명을 데이터모델의 속성명과 똑같이 짓지 않아도 됨
			
		log.info("name : " + a);
		log.info("price : " + b);
		log.info("hot : " + c);
		
		return "home";
	}

	
	
	@RequestMapping(value = "/garden", method = RequestMethod.GET) 
	public void garden() {
		log.info("garden");
	}
	
	
	
	//http://localhost:8888/springmvc/hello/redirect로 접속 하면 커피메뉴 추가하기 화면이 뜸
	@GetMapping(value="/redirect")
	public String redirect() {
		return "redirect:/hello/coffee/add";
	}
	
	
	
	

}

