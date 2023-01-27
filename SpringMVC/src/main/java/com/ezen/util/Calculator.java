package com.ezen.util;

public class Calculator {
	public int add(int a, int b) {
		return a + b;
	}
	
	public int randomInt(int size) {
		return (int)(Math.random() * size);
	}
	
	
	
	
	
	// 1) 실행하면 짝수인 양의 정수를 생성하여 반환하는 메서드를 정의한 후, 알맞은 테스트 케이스를 작성
	public int getEven() {
		//내 풀이 : return 2 * (int)(Math.random() * 101);
		
		//정답
		int num = (int)(Math.random() * 101);
		return num % 2 == 0 ? num : num + 1; 
	}
	
	
	
	
	
	// 2) 어떤 숫자를 하나 전달하면 소수인지 아닌지 판별해주는 메서드를 정의한 후, 알맞은 테스트 케이스를 작성
	public boolean isPrime(int num) {

		if(num < 2) {
			return false;
		}
		//아 맞다 프라임은 제곱근으로 풀어줬었지;; 
		//num의 제곱근까지만 돌려도 소수인지 아닌지 알 수 있으니까!
		double sqrt = Math.sqrt(num);
		for(int i =2; i <= sqrt; ++i) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}


