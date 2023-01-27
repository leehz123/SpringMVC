<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>/fileupload/form</title>
</head>
<body>

	
	<!--
	
	 파일을 보내기 위해서는 
	 method를 POST로 설정하고 (Get방식으로는 파일을 보낼 수 없다)
	 enctype 을 multipart/form-data로 설정해야 한다. 
	
	-->
	
	<form action="./" method="POST" enctype="multipart/form-data">

		원하는 파일 선택 : <input type="file" name="file" value="" />
		<input type="submit" value="등록" />
	</form>

</body>
</html>