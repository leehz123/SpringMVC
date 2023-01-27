package com.ezen.util;

public class Calculator {
	public int add(int a, int b) {
		return a + b;
	}
	
	public int randomInt(int size) {
		return (int)(Math.random() * size);
	}
	
	
	
	
	
	// 1) �����ϸ� ¦���� ���� ������ �����Ͽ� ��ȯ�ϴ� �޼��带 ������ ��, �˸��� �׽�Ʈ ���̽��� �ۼ�
	public int getEven() {
		//�� Ǯ�� : return 2 * (int)(Math.random() * 101);
		
		//����
		int num = (int)(Math.random() * 101);
		return num % 2 == 0 ? num : num + 1; 
	}
	
	
	
	
	
	// 2) � ���ڸ� �ϳ� �����ϸ� �Ҽ����� �ƴ��� �Ǻ����ִ� �޼��带 ������ ��, �˸��� �׽�Ʈ ���̽��� �ۼ�
	public boolean isPrime(int num) {

		if(num < 2) {
			return false;
		}
		//�� �´� �������� ���������� Ǯ�������;; 
		//num�� �����ٱ����� ������ �Ҽ����� �ƴ��� �� �� �����ϱ�!
		double sqrt = Math.sqrt(num);
		for(int i =2; i <= sqrt; ++i) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
}


