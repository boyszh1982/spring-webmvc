<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/UploadController/add.do" 
		method="POST" 
		enctype="multipart/form-data">
		
		staffId&nbsp;&nbsp; <input type="text" name="staffId" />&nbsp;&nbsp; 
		file&nbsp;&nbsp; <input type="file" name="mfile" /><br />
		
		<input type="submit" value="upload" />
	</form>
</body>
</html>