<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="w3.css">
<title>Server Configuration for profit Optimizations in cloud computing</title>
</head>
<body background="images.jpg">
	<div class="w3-bar w3-text-white w3-center"
		style="width: 100%; height: 120px;">
		<h2 style="font-size: 38px; font-style: italic; font-weight: bold;">Server Configuration for profit Optimizations in cloud computing</h2>
	</div>
	<%
		session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("index.html");
	%>
</body>
</html>