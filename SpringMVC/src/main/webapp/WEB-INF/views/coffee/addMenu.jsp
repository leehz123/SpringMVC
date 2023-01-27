<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Add Coffee Menu</title>
</head>
<body>



	<h3>커피 메뉴 추가하기</h3>
	<p>
	08_Controller.txt 中
	# 컨트롤러의 파라미터 자동 수집 기능 <br />
	- 컨트롤러 매개변수에서 자바빈 스타일의 데이터 클래스를 사용한다면<br />
  	내부 필드의 이름과 파라미터의 이름이 일치할 때 자동으로 바인딩한다.<br />
  	(바인딩 : 해당 인스턴스의 내용을 자동으로 채움)
	</p>

	<!-- <form action="<c:url value="/hello/coffee/add"/>" method="POST"> -->
	<form id="form" action="" method="POST">
		<!-- name은 Coffee클래스에 들어있는 속성과 이름이 일치해야 함 -->
		name:	<input id="coffeeName" type="text" name="name" value="Americano"/> 
		price:	<input id="coffeePrice" type="number" name="price" value="1700"/>
		hot : 	<input id="coffeeHot" type="radio" name="hot" value="true"/>
				<input id="coffeeHot" type="radio" name="hot" value="false"/>
		<input type="submit" value="submit!"/>
	</form>

	<button id="btn1">add1</button>
	<!-- 홈컨트롤러의 add1메서드는 자동바인딩을 안 해서 애트리뷰트에 심어지지 않음. 그래서 home.jsp에서 자바 주석 이용해야 했고 -->

	<!-- add2를 누르면 /springmvc/hello/coffee/add2로 submit . 아 이렇게 해줘도되는구나-->	
	<button id="btn2" form="form" 
					formaction="<c:url value="/hello/coffee/add2"/>">add2</button>
					
					
	<button id="btn3" form="form" 
					formaction="<c:url value="/hello/coffee/add3"/>">add3</button>					
	

	<script>
	
	//add1을 누르면 /springmvc/hello/coffee/add1로 submit
	const btn1 = document.getElementById('btn1'); 
	
	function postData(path) {
		document.getElementById("form").action = path;
		document.getElementById("form").submit();
	};
	
	//btn1.addEventListener('click', postData("/springmvc/hello/coffee/add1"));
	btn1.addEventListener('click', ()=> {
		document.getElementById("form").action = "/springmvc/hello/coffee/add1";
		document.getElementById("form").submit();
	});
	
	
	</script>
	
	
</body>
</html>