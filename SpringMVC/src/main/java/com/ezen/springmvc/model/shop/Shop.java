package com.ezen.springmvc.model.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class Shop {
	
	@Autowired
	Pos pos;
	
	@Autowired
	Milk milk; 
	
	@Autowired
	Snack snack; 
	
	
	
	
}
