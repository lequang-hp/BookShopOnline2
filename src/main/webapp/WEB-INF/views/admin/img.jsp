<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "imageURL" value = "/api/upload-image"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<form action='<c:url value = '/admin/image'/>' method = "post" enctype="multipart/form-data">
	<input type="file" name="imageFile">
	<input type="submit" value = "Upload">	
</form> 

</body>
</html>