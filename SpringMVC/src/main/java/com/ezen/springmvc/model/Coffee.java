package com.ezen.springmvc.model;

import org.springframework.stereotype.Component;

//Component로 어노테이션 해준 담에 임포트 해주기
@Component
public class Coffee {
	private String name;
	private Integer price;
	private boolean hot;

	//자빈오가 되려면 기본생성자가 반드시 존재해야 한다. (자바빈옵젝)
	public Coffee() {
		name = "default name";
		price = 0;
		hot = true;
	}
	
	
	public Coffee(String name, Integer price, Boolean hot) {
		this.name = name; 
		this.price = price;
		this.hot = hot;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}
	
	public String getTable() {
		return String.format("<table><tr></tr></table>");
	}
	
	@Override
	public String toString() {
		return String.format("%s/%s/%s", name, price, hot);
	}
	
	
	
	
	
}
