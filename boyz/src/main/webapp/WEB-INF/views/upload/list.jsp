<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${staffs}" var="staff">  
    	${staff.staffId} , ${staff.firstName}  , ${staff.lastName } , ${staff.email }
		<br />
	</c:forEach>
	<br />
	<a href="<%=request.getContextPath()%>/UploadController/add.do">继续添加用户</a>
</body>
</html>