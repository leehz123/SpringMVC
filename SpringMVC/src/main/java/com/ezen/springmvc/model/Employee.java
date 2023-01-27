package com.ezen.springmvc.model;

public class Employee {

	private Integer employee_id;
	
	
	//���÷��� ���� ���� ��ȣ�� �������� �ʿ䰡 �հٳ�
	public Employee(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	
	
	//hashCode() : �� Ŭ������ �ν��Ͻ��� �����ϰ� ������ �� �ִ� ��. �⺻�� = �޸𸮻��� �ּ�
	@Override
	public int hashCode() {
		return super.hashCode(); //������ �̰ǵ� 
		
		//return this.employee_id; //�̷��� ���Ƿ� �ٲ��༭ emp1.equals(emp3)�� true�� ���� �� (�̺κ� ���ƴµ� �� ������) 
	}
	
	
	//equals() : ���� �ν��Ͻ��� ���޹��� �ν��Ͻ��� ���Ͽ� �� �ν��Ͻ��� ���� ��ü���� �Ǻ��� �� �ִ� �޼���
	@Override
	public boolean equals(Object obj) {
		//return (Employee)obj; 
		//obj�� ����(�׳� String, Integer... �� �� �� �� ����) ���� �𸣴� ��Ȳ���� (Employee)�� �ٿ����̽� ���ִ� �� ��������? 
		
		//�׷��� �ؽ��ڵ�� ���ϵ��� equals()�޼��带 ��������� ��
		return obj.hashCode() == this.hashCode();
		//equals���� �ַ� �ؽ��ڵ带 ����ϴµ� �ؽ��ڵ�� �޸𸮻��� �ּҸ� ���ϴ� ��
	}
	//equals() �ο����� - ObjectŬ������ equals()�޼���� ���� '==' ���Ϻ���. �ٵ� �̰� �������̵��ؼ� �����(�����) �ϰ� ���� ��ǥ�� ��ü�� StringŬ������.
	
	
	public static void main(String[] args) {
		Employee emp1 = new Employee(13);
		Employee emp2 = new Employee(101);
		Employee emp3 = new Employee(13);
		
		//16������ �ٲ���� ��¼��
		System.out.println(Integer.toString(emp1.hashCode(), 16).toUpperCase()); //2D363FB3
		System.out.println(Integer.toString(emp2.hashCode(), 16).toUpperCase()); //7D6F77CC
		System.out.println(Integer.toString(emp3.hashCode(), 16).toUpperCase());
	
		System.out.println("emp1 eq emp2 : " + emp1.equals(emp2));
		//��� false 
		
		System.out.println("emp1 eq \"I'm String\" : " + emp1.equals("I'm String"));
		
		System.out.println("emp1 eq emp3 : " + emp1.equals(emp3));
		//�굵 Object Ŭ������ equals()�� �״�� �޾Ƽ� ����ߴٸ� ���Ϻ�(������(������ �����ϰ� �ִ� ��ü�� �޸𸮻� �ּ�)) �񱳶� false�ε�
		//equals()�޼����� �� ��� hashCode()�� �������̵� �ؼ� return ���� ��ü�� �������� �ٲ��ָ� (hashcode()�� ���� ���ϰ��� hashcode ��, ��ü�� �޸𸮻� �ּ���) 
		//equals()�� ����񱳸� �ؼ� true�� ����
		//�� �Ʒ� ���� ���� ����� �ϼ̴���! equals()�޼��� �������� �� ���Ϻ񱳰� �ƴ϶� ������Ϸ��� hashCode()�� return ���� ������������Ѵٴ� �׷�!
	}
}

/*
equals()�� hashcode()�� ���� �������ؾ� �ϴ� ����
���� equals()�� hashcode() �� �ϳ��� ������ �ϸ� ��� �ɱ�? 
�� �������� �õ��� hashcode()�� ������ ���� ������ ���� �� ��ü�� �ؽð��� �ٸ� �� �ִ�. 
���� HashTable���� �ش� ��ü�� ����� ��Ŷ�� ã�� �� ����.

�ݴ�� equals()�� ���������� ������ hashcode()�� ���� �ؽð��� �̿��� ��ü�� ����� ��Ŷ�� ã�� ���� ������ 
�ش� ��ü�� �ڽŰ� ���� ��ü���� ���� ���� �� ���� ������ null�� �����ϰ� �ȴ�. ���� ���� ���ϴ� ��ü�� ã�� �� ����.

�̷��� ������ ��ü�� ��Ȯ�� ���� ��(����, �� �ּҰ� �ƴ� �� ��. = ���� ��. �������� �����ϴ� = �ּҴ� �޶� ���� ����.)�� ���ؼ��� 
(Ư�� Hash ���� �÷��� �����ӿ�ũ�� ����Ҷ�!) 
Object�� equals() �޼ҵ常 ���������� ���� hashCode()�޼ҵ嵵 �������ؼ� ���� ���� ��ü�ϰ�� ������ �ؽ��ڵ尡 ���ϵǵ��� �ؾ��Ѵ�. 
https://jisooo.tistory.com/entry/java-hashcode%EC%99%80-equals-%EB%A9%94%EC%84%9C%EB%93%9C%EB%8A%94-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B3%A0-%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C
 */
