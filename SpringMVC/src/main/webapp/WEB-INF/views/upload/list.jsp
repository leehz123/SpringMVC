<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���ε�� ���� ��� ����</title>
<style>
	a{text-decoration : none;}
</style>
</head>
<body>

	<h3>Uploaded File list</h3>
	<ul>
		<c:forEach items="${files }" var="file">
			<!-- <li>${file.absolutePath }</li> -->
			
			<li>
			<!-- �ƴ� �̰� �ƴϾ��� �� ������;; ��¥ �ƴϳ�; �ٵ� ���� �� �ּҿ� fileupload/�̰� �� �پ ������ -->
			<!-- <a href="fileupload/${file }">${file }</a>	<br /> -->
			<a href="${file }">${file }</a>	<br />	
			<!-- �ٿ�ε� ���� �Ŀ� img�±׷� �����ϸ� �� �������� ��ȸ�� �� �ִ�. -->
			<!-- �̰͵� �տ� fileupload/ �� ���̸� �� ��;; -->
			<!-- <img src="fileupload/${file }"/> -->
			<img src="${file }"/>
			</li>
		</c:forEach>
	</ul>
	

</body>
</html>