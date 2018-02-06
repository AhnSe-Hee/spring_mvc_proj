<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
create Page!
<br />

<% 
	String conPath = request.getContextPath(); //com.javalec.spring_ex_proj에서 세번째 인자인 "spring_ex_proj" 
%>
>> conPath : <%=conPath %>

<form action="student/create">
	name : <input type="text" name="name" value="${student.name}"> <br />
	id : <input type="text" name="id" value="${student.id}"> <br />
	<input type="submit" value="Submit"> <br />
</form>

</body>
</html>