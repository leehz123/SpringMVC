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
	
	 ������ ������ ���ؼ��� 
	 method�� POST�� �����ϰ� (Get������δ� ������ ���� �� ����)
	 enctype �� multipart/form-data�� �����ؾ� �Ѵ�. 
	
	-->
	
	<form action="./" method="POST" enctype="multipart/form-data">

		���ϴ� ���� ���� : <input type="file" name="file" value="" />
		<input type="submit" value="���" />
	</form>

</body>
</html>