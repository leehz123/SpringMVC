<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h3>garden </h3>

	<p>/WEB-INF/views/hello/garden.jsp</p>
	
	<p><%=application.getRealPath("./") %></p> 
	<!-- 현재 위치인 C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVC\ -->

	<p><%=application.getRealPath("./garden") %></p>
	<!-- C:\JavaAWS\spring-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVC\garden -->
</body>
</html>