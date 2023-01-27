package com.ezen.springmvc.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

//���� �ΰŰ� �����Ƿ� log4j�� ��Ȯ�� ����Ʈ ���� �� ����!!
//import org.apache.log4j.Logger;  //�ٵ� ���� @Log4j �Һ��� �޾Ҵٸ� �ʿ� ����. �׸��� ���� log4j2�� ������ �� log4j���������� ����Ʈ�� �� ��
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.springmvc.model.Coffee;
import com.ezen.springmvc.model.shop.Shop;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Log4j �� ���� �� ��
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class); //�̰� sl4j�� �ΰŰ�
	//�츮�� Log4j�� ����� ��
	
	//private Logger log = org.apache.log4j.Logger.getLogger(this.getClass()); //���� Ŭ���� (HomeController)�� �Ѱ���� ��
	//�׷��� �� Ŭ������ �α� ����
	//�ٵ� �̰͸����� �Һ����� �� �� ���� @Log4j �޾��ָ� ��
	
	@Autowired //�굵 ����Ʈ ���ֱ�
	Coffee coffee; //�����ڿ� ������ ���ܼ� �ٲ�� �� �� 50���� �����ڸ� �� �ٲ��� �ʰ� @Component ������̼Ǹ� ���� ���̸� �ȴٴ� ����!
	
	@Autowired
	Shop shop;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//logger.info("Welcome home! The client locale is {}.", locale);  
		
		//System.out.println("COFFEE : " + coffee);//���� �� ������ ���� �� 
		//System.out.println("SHOP : " + shop);
		
		log.error("this is error msg");
		log.info("information");
		log.debug("I am debug msg");
		log.trace("tarce msg");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//��Ʈ�����͸� request�� �ƴ϶� model�̶�� �Ϳ� �߰��ϰ� �ֳ� �������̶� �� �ٸ���
		model.addAttribute("coffee", coffee);
		
		return "home"; //home.jsp�� ����� ��
	}

}
