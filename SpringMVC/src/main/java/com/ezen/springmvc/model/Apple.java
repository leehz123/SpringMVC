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
@NoArgsConstructor //Apple() 생성
@RequiredArgsConstructor //@nonNull이 붙은 필드만 가지고 생성자 생성
@AllArgsConstructor //Apple(int int, boolean, boolean) 생성
@ToString
@Getter
//@Setter 아래에 원하는 변수(size, price)만 @Setter설정해주고 싶으면 이 @Setter는 지워야 됨.
//@Data //롬복 어노테이션
public class Apple {
	
	@NonNull
	@Setter //이렇게 하면 size에만 getter 달림
	private Integer size; //@NonNull is meaningless on a primitive. 라고 떠서 int를 Integer로 바꿔줌
	@NonNull
	@Setter
	private Integer price;
	private boolean red;
	private boolean fresh; 
	
	public static void main(String[] args) {
		System.out.println(new Apple());
	}
}
