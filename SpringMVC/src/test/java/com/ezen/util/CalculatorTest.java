package com.ezen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTest {
	
	Calculator calc = new Calculator();
	//다른 폴더지만 같은 패키지므로 임포트가 필요 없음.
	//자바가 패키지를 재구성함. 다른 폴더에 있더라도 같은 패키지로 인식을 한다는 것. 이게 자바 패키지의 역할
	
	int a, b;
	static int i;
	@Ignore
	@Before
	public void before() {
		System.out.println("Execute @Before (" + ++i + ")");
											//i가 static이라야 증가하네
		a= 10; 
		b= 20;
	}
	
	@Ignore
	@After
	public void After() {
		System.out.println("Execute @After(" + ++i + ")");
	} 

	@Ignore
	@Test
	public void test() {
		int result = calc.add(a, b);
		
		//파라미터로 기대하는 값 과 결과를 넣어서 테스트를 할 수 있다. 
		assertEquals(30, result);
	}
	
	@Ignore
	@Test  //테스트는 무조건 public void여야 함!
	public void randomTest1() {
		for(int i = a; i < b; ++i) {
			assertTrue(calc.randomInt(i) < i - 1);
		}
	}
	
	// 1) 실행하면 짝수인 양의 정수를 생성하여 반환하는 메서드를 정의한 후, 알맞은 테스트 케이스를 작성
	@Ignore
	@Test
	public void evenTest() {
		assertTrue(calc.getEven() %2 == 0);
	}
	
	// 2) 어떤 숫자를 하나 전달하면 소수인지 아닌지 판별해주는 메서드를 정의한 후, 알맞은 테스트 케이스를 작성	
	@Ignore
	@Test
	public void primeTest() {
		//assertEquals(calc.isPrime(11), true);
		assertTrue(calc.isPrime(1));//위에 줄이 결국 이거지
	}	
	
	
	
	//문제 1. 정답풀이
	@Test
	public void getEvenTest1() {
		int num = calc.getEven();
		assertTrue(num%2==0);
	}
	//이렇게 다방면으로 테스트를 해주면 좋다. 
	@Test
	public void getEvenTest2() {
		int num = calc.getEven();
		assertFalse((num%2==1));
	}
	
	// 어레리는 자바 1.7 이상부터 되는데 지금 1.6버전이라 어레리 안 됨 어레리 있어도 제네릭이 없든가 할 것
	//pom.xml 가서 143, 144행 1.8 로 고치고 플젝 - 우클릭 - 메이븐 - 업데이트 프로젝트 하면 업데이트 됨
	@Test
	public void getEvenTest3() {
		ArrayList<Integer> evens = new ArrayList<>();
		for(int i = 0; i < 20000; ++i) {
			evens.add(calc.getEven());
		}
		assertTrue("evens에 0이 발견됐습니다.", evens.contains(0));
		//이렇게 메시지를 달아놓으면 테스트 실패시 메시지가 뜸		
	}
	
	
	
	
	//문제 2. 정답풀이
	//소수인 것 집어넣기
	@Test
	public void isPrimeTest1() {
		assertTrue(calc.isPrime(13));
	}
	//소수가 아닌 것을 집어넣기
	@Test
	public void isPrimeTest2() {
		assertFalse(calc.isPrime(14));
	}
	//1은 소수가 아니니까 false가 나와야 됨
	@Test
	public void isPrimeTest3() {
		assertFalse("1을 소수로 판별함", calc.isPrime(1));
	}	
	//음수일 때도 false 나와야 됨
	@Test
	public void isPrimeTest4() {
		assertTrue("-7을 소수로 판별함", calc.isPrime(-7));
	}	
}




