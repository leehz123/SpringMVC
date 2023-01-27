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
		log.info("��Ʈ�ѷ����� �� ������");
		
		//�̰� 08_Controller.txt ���� ���� �س��� ���ϰ�
		//return "news"; //�������� ����ڿ��� �������� �� view�� �̸��� ���ڿ��� �����Ѵ�. 
		// �׷��� �ּ�â�� http://localhost:8888/springmvc/hello/news �Է��ϸ� 
		// ���ϰ��� news�� �Ἥ  /WEB-INF/views/news.jsp�� �����Ϸ��� �ϴ� �� 

		
		
		//�̰� 08_Controller.txt �������� �س��� ���ϰ�
		return "hello/news";//���� �� ���� �̰ɷ� ���� 
		//hello �տ� �����ô� �ٿ��� ������ ���°� �����̰���
		//servlet-context.xml ���� ���� <beans:property name="prefix" value="/WEB-INF/views/" /> �̷��� �� �ڿ� /�� �ٿ������ϱ�		
		//�տ��� ���� �ּҸ� ���̰� 
		//�ڿ��� <beans:property name="suffix" value=".jsp" /> �ּҸ� ����
		
		// /WEB-INF/views/ + ���ϰ� + .jsp �� �ٿ��� ���� ��θ� �ϼ���Ŵ
		
		//http://localhost:8888/springmvc/hello/news �� �����غ���
	}
	//http://localhost:8888/springmvc/hello/news�� �����ϸ� �������� �� ������
	//[09:41:34.960] INFO  [http-nio-8888-exec-7] com.ezen.springmvc.Controller.HelloController news - ��Ʈ�ѷ����� �� ������
	// �߸� �Ȱ�

	//�������� �̸� �������� �����ļ����� Ȱ���ؼ� ������� ������ �� (�� �ȿ� �ҽ��� ��� �� �ִ��� �𸣰ʹµ�)
	//web.xml���Ͽ� ���� 19�ٿ� <servlet-name>appServlet</servlet-name> ����
	//init param �±� �ȿ� <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>�� ����  
	// servlet-context.xml������ �� ���Ƶ鿩�� ��¼�� �Ѵ�
	// <context-param>�� ���� �ʱ�ȭ �Ķ��� �ǹ��߾���? �׷��� ���� �Ķ���ʹ� web.xml�� �������ϰ� ��� �߰����� ���� root-context.xml�� �߰��ϸ� ��	
	
	
	
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
//		log.info("coffee�����Ͱ� �����ϴ� ��");
//		//return "home";//�ϴ� �̷��� �ϸ� ���ε��� �Ǹ鼭 forward�� ��
//		return "redirect:/"; //���ٷ��� �̷���. /�� HomeController�� ��û�� ������ ��
//	}

	
	
	
	@PostMapping("/coffee/add1")
	public String coffeeAdd1(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException { //�̷��� request�� �Ķ���ͷ� �޴� �͵� ����!	
		
				
		log.info("coffee add1 (�ڵ� ���ε��� ������� ����)");
		
		req.setCharacterEncoding("EUC-KR");	
		log.info("coffee name : " + req.getParameter("name"));
		log.info("coffee price : " + req.getParameter("price"));
		log.info("hot coffee : " + req.getParameter("hot"));
	
		
		//request�� �޾Ƽ� Coffee�� ����ִ� ����� �ְ���
		req.setAttribute("coffee", new Coffee(
					req.getParameter("name"),
					Integer.parseInt(req.getParameter("price")),
					Boolean.parseBoolean(req.getParameter("hot"))
				));
		
		
		//������
		return "home"; //�̸��� �������� ��Ʈ����Ʈ�� �ڵ����� �߰��� ���� html�ڵ忡 ���� ǥ�õ�
	}
	
	
	
	@PostMapping("/coffee/add2")
	public String coffeeAdd2(Coffee coffee) {//�̷��� ���൵ �޼��� �ȿ��� ������ �ִ� �۾� �� �ߴµ�, �ޱ⸸ ���µ� �ȿ� ���� ��� �ְ� �� (�ڵ����ε�) 
	//�̰� �ٷ� �ڵ� ���ε�. �޼����� �Ű������� �޴� �������� Ÿ���� �����ϸ� �״�� �ڵ����� ä����!
	
		//request�� ��� �ִ� �������� Ÿ���� �� String�̾��µ� 
		//Coffee coffee�� �ް� ������ Ÿ���� ���� boolean�״�� ��. �̷� ����!
			
		log.info("coffee add2 (�ڵ� ���ε� �����)");
		log.info(coffee);

		if(coffee.isHot()) {
			log.info("�߰ſ� Ŀ�ǰ� �߰� �ƽ��ϴ�.");			
		} else {
			log.info("������ Ŀ�ǰ� �߰� �ƽ��ϴ�.");
		}
		
		//return "redirect:/"; //���ٷ� �ؼ� home���� ������ / ���ָ� ��
		
		return "home"; //�̸��� �������� ��Ʈ����Ʈ�� �ڵ����� �߰��� ���� html�ڵ忡 ���� ǥ�õ�
	}
	
	
	
	//�ֿܼ� ����� ������ ��Ʈ����Ʈ�� �ڵ����� �Ǹ����� ����
	//�״ϱ� ��Ʈ���� �ڵ����� �Ǹ����� �Ű������� �����͸𵨷� ���̵��� �����ص� Ŭ������ Ÿ������ ����� �ϴµ�
	/*
	 	@PostMapping("/coffee/add3")
	public String coffeeAdd3(String name, Integer price, Boolean hot) { 
		log.info("name : " + name);
		log.info("price : " + price);
		log.info("hot : " + hot);
		
		return "home";
	}
	 */
	
	
	
	//@ModelAttribute("�����͸��� �Ӽ��� ��ġ�ϴ� �̸�") ������̼� �̿��ϴ� ��
	@PostMapping("/coffee/add3")
	public String coffeeAdd3(
				@ModelAttribute("name") String a,
				@ModelAttribute("price") Integer b,
				@ModelAttribute("hot") Boolean c ) { 
	//@ModelAttribute������̼��� ����ϴ� ��쿡�� ()��ȣ �ȿ��� �Ӽ����� �������ֱ� ������ �Ű��������� �����͸��� �Ӽ���� �Ȱ��� ���� �ʾƵ� ��
			
		log.info("name : " + a);
		log.info("price : " + b);
		log.info("hot : " + c);
		
		return "home";
	}

	
	
	@RequestMapping(value = "/garden", method = RequestMethod.GET) 
	public void garden() {
		log.info("garden");
	}
	
	
	
	//http://localhost:8888/springmvc/hello/redirect�� ���� �ϸ� Ŀ�Ǹ޴� �߰��ϱ� ȭ���� ��
	@GetMapping(value="/redirect")
	public String redirect() {
		return "redirect:/hello/coffee/add";
	}
	
	
	
	

}

