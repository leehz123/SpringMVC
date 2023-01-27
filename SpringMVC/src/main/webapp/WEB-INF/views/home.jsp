<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %> <!-- this code means I won't use session in this page. -->
<%

String coffeeName = (String)request.getParameter("name");
String coffeePrice = (String)request.getParameter("price");
String coffeeHot = (String)request.getParameter("hot");

%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>Hello world!</h1>

<!-- 에밋 다시 깔아야것네 -->
<p>The time on the server is ${serverTime}.</p>

<!-- 경고창 뜨는데 일단 save as UTF-8 누르기 -->

<!-- HelloController에서 @GETMapping 또는 @PostMapping 달아놓은 메서드의 리턴값을 통해서 여기로 올 텐데,  
	메서드의 매개변수로 HttpServletRequest와 HttpServletResponse를 써놓은 메서드들(매개변수로 Coffee coffee를 쓰지 않은 메서드들)은 
	아래의 꺽쇄퍼센이퀄 주석을 없애면 여기서 데이터 값을 받지 못함. 
-->
<p>name : <%= coffeeName %> ${coffee.name }</p> 
<p>price : <%= coffeePrice %> ${coffee.price }</p>
<p>HOT : <%= coffeeHot %> ${coffee.hot }</p>
<!-- 제대로 나옴 -->

<!-- toString구현해놔서 그냥 coffee라고 해도 toString대로 나올 것 -->
<p>${coffee }</p>


<h5>HomeController에서 add3로 받아서 여기로 보냈을 때</h5>
<p>
	coffee info : ${name }, ${price }, ${hot }
</p>

</body>
</html>
