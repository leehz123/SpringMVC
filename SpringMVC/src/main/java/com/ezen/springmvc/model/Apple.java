package com.ezen.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@NoArgsConstructor //Apple() ����
@RequiredArgsConstructor //@nonNull�� ���� �ʵ常 ������ ������ ����
@AllArgsConstructor //Apple(int int, boolean, boolean) ����
@ToString
@Getter
//@Setter �Ʒ��� ���ϴ� ����(size, price)�� @Setter�������ְ� ������ �� @Setter�� ������ ��.
//@Data //�Һ� ������̼�
public class Apple {
	
	@NonNull
	@Setter //�̷��� �ϸ� size���� getter �޸�
	private Integer size; //@NonNull is meaningless on a primitive. ��� ���� int�� Integer�� �ٲ���
	@NonNull
	@Setter
	private Integer price;
	private boolean red;
	private boolean fresh; 
	
	public static void main(String[] args) {
		System.out.println(new Apple());
	}
}
