<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Index.jsp</title>
</head>
<body>
	Index page!<br/>
	
	<%
		String context = request.getContextPath();
	%>
	<form action="<%=context%>/student" method="post">
		student id : <input type="text" name="id"> <br />
		<input type="submit" value="Submit">
	</form>

</body>
</html>