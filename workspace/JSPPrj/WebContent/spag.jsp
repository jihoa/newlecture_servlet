<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result","hello");
%>
<body>
	<%=request.getAttribute("result") %>입니다.
	${requestScope.result}<br >
	${names[1]}<br >
	${notice.id}<br >
	${result}<br >
	${param.n/2}  <br>
	${header.accept}
</body>
</html>