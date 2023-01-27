<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>업로드된 파일 목록 보기</title>
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
			<!-- 아니 이거 아니었던 것 같은데;; 진짜 아니네; 근데 나는 왜 주소에 fileupload/이거 안 붙어서 나오지 -->
			<!-- <a href="fileupload/${file }">${file }</a>	<br /> -->
			<a href="${file }">${file }</a>	<br />	
			<!-- 다운로드 구현 후에 img태그로 접근하면 웹 브라우저로 조회할 수 있다. -->
			<!-- 이것도 앞에 fileupload/ 안 붙이면 안 뜸;; -->
			<!-- <img src="fileupload/${file }"/> -->
			<img src="${file }"/>
			</li>
		</c:forEach>
	</ul>
	

</body>
</html>