package com.ezen.springmvc.model;

public class Employee {

	private Integer employee_id;
	
	
	//임플로이 만들 적에 번호를 생성해줄 필요가 잇겟네
	public Employee(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	
	
	//hashCode() : 이 클래스의 인스턴스를 유일하게 구분할 수 있는 값. 기본값 = 메모리상의 주소
	@Override
	public int hashCode() {
		return super.hashCode(); //원래는 이건데 
		
		//return this.employee_id; //이렇게 임의로 바꿔줘서 emp1.equals(emp3)가 true로 나온 듯 (이부분 놓쳤는데 내 추측임) 
	}
	
	
	//equals() : 현재 인스턴스와 전달받은 인스턴스를 비교하여 두 인스턴스가 같은 객체인지 판별할 수 있는 메서드
	@Override
	public boolean equals(Object obj) {
		//return (Employee)obj; 
		//obj로 뭐가(그냥 String, Integer... 가 올 수 도 있음) 올지 모르는 상황에서 (Employee)로 다운케이스 해주는 건 위험하지? 
		
		//그래서 해시코드로 비교하도록 equals()메서드를 구현해줘야 됨
		return obj.hashCode() == this.hashCode();
		//equals에는 주로 해시코드를 사용하는데 해시코드는 메모리상의 주소를 비교하는 것
	}
	//equals() 부연설명 - Object클래스의 equals()메서드는 원래 '==' 동일비교임. 근데 이걸 오버라이드해서 동등비교(내용비교) 하게 만든 대표적 객체가 String클래스임.
	
	
	public static void main(String[] args) {
		Employee emp1 = new Employee(13);
		Employee emp2 = new Employee(101);
		Employee emp3 = new Employee(13);
		
		//16진수로 바꿔줘야 어쩌고
		System.out.println(Integer.toString(emp1.hashCode(), 16).toUpperCase()); //2D363FB3
		System.out.println(Integer.toString(emp2.hashCode(), 16).toUpperCase()); //7D6F77CC
		System.out.println(Integer.toString(emp3.hashCode(), 16).toUpperCase());
	
		System.out.println("emp1 eq emp2 : " + emp1.equals(emp2));
		//얘는 false 
		
		System.out.println("emp1 eq \"I'm String\" : " + emp1.equals("I'm String"));
		
		System.out.println("emp1 eq emp3 : " + emp1.equals(emp3));
		//얘도 Object 클래스의 equals()를 그대로 받아서 사용했다면 동일비교(참조값(변수가 참조하고 있는 객체의 메모리상 주소)) 비교라 false인데
		//equals()메서드의 비교 대상 hashCode()를 오버라이드 해서 return 값을 객체의 내용으로 바꿔주면 (hashcode()의 원래 리턴값은 hashcode 즉, 객체의 메모리상 주소임) 
		//equals()가 동등비교를 해서 true가 나옴
		//아 아래 설명 예시 들려고 하셨던듯! equals()메서드 재정의할 때 동일비교가 아니라 동등비교하려면 hashCode()도 return 값을 재정의해줘야한다는 그런!
	}
}

/*
equals()와 hashcode()를 같이 재정의해야 하는 이유
만약 equals()와 hashcode() 중 하나만 재정의 하면 어떻게 될까? 
위 예에서도 봤듯이 hashcode()를 재정의 하지 않으면 같은 값 객체라도 해시값이 다를 수 있다. 
따라서 HashTable에서 해당 객체가 저장된 버킷을 찾을 수 없다.

반대로 equals()를 재정의하지 않으면 hashcode()가 만든 해시값을 이용해 객체가 저장된 버킷을 찾을 수는 있지만 
해당 객체가 자신과 같은 객체인지 값을 비교할 수 없기 때문에 null을 리턴하게 된다. 따라서 역시 원하는 객체를 찾을 수 없다.

이러한 이유로 객체의 정확한 동등 비교(내용, 즉 주소가 아닌 값 비교. = 논리적 비교. 논리적으로 동등하다 = 주소는 달라도 값은 같다.)를 위해서는 
(특히 Hash 관련 컬렉션 프레임워크를 사용할때!) 
Object의 equals() 메소드만 재정의하지 말고 hashCode()메소드도 재정의해서 논리적 동등 객체일경우 동일한 해시코드가 리턴되도록 해야한다. 
https://jisooo.tistory.com/entry/java-hashcode%EC%99%80-equals-%EB%A9%94%EC%84%9C%EB%93%9C%EB%8A%94-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B3%A0-%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C
 */
