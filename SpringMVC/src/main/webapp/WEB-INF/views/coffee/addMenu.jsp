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



	<h3>Ŀ�� �޴� �߰��ϱ�</h3>
	<p>
	08_Controller.txt ��
	# ��Ʈ�ѷ��� �Ķ���� �ڵ� ���� ��� <br />
	- ��Ʈ�ѷ� �Ű��������� �ڹٺ� ��Ÿ���� ������ Ŭ������ ����Ѵٸ�<br />
  	���� �ʵ��� �̸��� �Ķ������ �̸��� ��ġ�� �� �ڵ����� ���ε��Ѵ�.<br />
  	(���ε� : �ش� �ν��Ͻ��� ������ �ڵ����� ä��)
	</p>

	<!-- <form action="<c:url value="/hello/coffee/add"/>" method="POST"> -->
	<form id="form" action="" method="POST">
		<!-- name�� CoffeeŬ������ ����ִ� �Ӽ��� �̸��� ��ġ�ؾ� �� -->
		name:	<input id="coffeeName" type="text" name="name" value="Americano"/> 
		price:	<input id="coffeePrice" type="number" name="price" value="1700"/>
		hot : 	<input id="coffeeHot" type="radio" name="hot" value="true"/>
				<input id="coffeeHot" type="radio" name="hot" value="false"/>
		<input type="submit" value="submit!"/>
	</form>

	<button id="btn1">add1</button>
	<!-- Ȩ��Ʈ�ѷ��� add1�޼���� �ڵ����ε��� �� �ؼ� ��Ʈ����Ʈ�� �ɾ����� ����. �׷��� home.jsp���� �ڹ� �ּ� �̿��ؾ� �߰� -->

	<!-- add2�� ������ /springmvc/hello/coffee/add2�� submit . �� �̷��� ���൵�Ǵ±���-->	
	<button id="btn2" form="form" 
					formaction="<c:url value="/hello/coffee/add2"/>">add2</button>
					
					
	<button id="btn3" form="form" 
					formaction="<c:url value="/hello/coffee/add3"/>">add3</button>					
	

	<script>
	
	//add1�� ������ /springmvc/hello/coffee/add1�� submit
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