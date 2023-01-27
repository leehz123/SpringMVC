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
	//�ٸ� �������� ���� ��Ű���Ƿ� ����Ʈ�� �ʿ� ����.
	//�ڹٰ� ��Ű���� �籸����. �ٸ� ������ �ִ��� ���� ��Ű���� �ν��� �Ѵٴ� ��. �̰� �ڹ� ��Ű���� ����
	
	int a, b;
	static int i;
	@Ignore
	@Before
	public void before() {
		System.out.println("Execute @Before (" + ++i + ")");
											//i�� static�̶�� �����ϳ�
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
		
		//�Ķ���ͷ� ����ϴ� �� �� ����� �־ �׽�Ʈ�� �� �� �ִ�. 
		assertEquals(30, result);
	}
	
	@Ignore
	@Test  //�׽�Ʈ�� ������ public void���� ��!
	public void randomTest1() {
		for(int i = a; i < b; ++i) {
			assertTrue(calc.randomInt(i) < i - 1);
		}
	}
	
	// 1) �����ϸ� ¦���� ���� ������ �����Ͽ� ��ȯ�ϴ� �޼��带 ������ ��, �˸��� �׽�Ʈ ���̽��� �ۼ�
	@Ignore
	@Test
	public void evenTest() {
		assertTrue(calc.getEven() %2 == 0);
	}
	
	// 2) � ���ڸ� �ϳ� �����ϸ� �Ҽ����� �ƴ��� �Ǻ����ִ� �޼��带 ������ ��, �˸��� �׽�Ʈ ���̽��� �ۼ�	
	@Ignore
	@Test
	public void primeTest() {
		//assertEquals(calc.isPrime(11), true);
		assertTrue(calc.isPrime(1));//���� ���� �ᱹ �̰���
	}	
	
	
	
	//���� 1. ����Ǯ��
	@Test
	public void getEvenTest1() {
		int num = calc.getEven();
		assertTrue(num%2==0);
	}
	//�̷��� �ٹ������ �׽�Ʈ�� ���ָ� ����. 
	@Test
	public void getEvenTest2() {
		int num = calc.getEven();
		assertFalse((num%2==1));
	}
	
	// ����� �ڹ� 1.7 �̻���� �Ǵµ� ���� 1.6�����̶� ��� �� �� ��� �־ ���׸��� ���簡 �� ��
	//pom.xml ���� 143, 144�� 1.8 �� ��ġ�� ���� - ��Ŭ�� - ���̺� - ������Ʈ ������Ʈ �ϸ� ������Ʈ ��
	@Test
	public void getEvenTest3() {
		ArrayList<Integer> evens = new ArrayList<>();
		for(int i = 0; i < 20000; ++i) {
			evens.add(calc.getEven());
		}
		assertTrue("evens�� 0�� �߰ߵƽ��ϴ�.", evens.contains(0));
		//�̷��� �޽����� �޾Ƴ����� �׽�Ʈ ���н� �޽����� ��		
	}
	
	
	
	
	//���� 2. ����Ǯ��
	//�Ҽ��� �� ����ֱ�
	@Test
	public void isPrimeTest1() {
		assertTrue(calc.isPrime(13));
	}
	//�Ҽ��� �ƴ� ���� ����ֱ�
	@Test
	public void isPrimeTest2() {
		assertFalse(calc.isPrime(14));
	}
	//1�� �Ҽ��� �ƴϴϱ� false�� ���;� ��
	@Test
	public void isPrimeTest3() {
		assertFalse("1�� �Ҽ��� �Ǻ���", calc.isPrime(1));
	}	
	//������ ���� false ���;� ��
	@Test
	public void isPrimeTest4() {
		assertTrue("-7�� �Ҽ��� �Ǻ���", calc.isPrime(-7));
	}	
}




