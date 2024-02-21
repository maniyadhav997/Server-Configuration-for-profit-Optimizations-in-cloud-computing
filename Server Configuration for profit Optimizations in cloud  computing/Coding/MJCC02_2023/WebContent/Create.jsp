<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
input {
	height: 40px;
}
</style>
<link rel="stylesheet" href="w3.css">
<title>Insert title here</title>
</head>
<body background="Pics/h7.jpg" class="w3-text-white"
	style="font-family: fantasy;">
	<form action="Build" method="post">
	<h1
		style="background-image: url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family: fantasy;"
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
	<center>
		<div style="font-size: 20px; margin-top: 25px;">
			<a href="AdminHome.jsp">Home</a> <a href="Network.jsp"
				style="margin-left: 70px;">Network Creation</a> <a href="Host.jsp"
				style="margin-left: 70px;">Host Failure Detection</a> <a
				href="VM.jsp" style="margin-left: 70px;">Virtual Machines</a> <a
				href="index.html" style="margin-left: 70px;">Logout</a>
		</div>

		<%
			String loc = request.getParameter("loc");
		%>
		<div class="w3-bar">
		<h1 style="font-weight: bold;margin-top: 15px;">Creation</h1>
		</div>
		</center>
		<div class="w3-bar">
		<div class="w3-bar-item">
		<table class="w3-table" style="width: 500px; font-size: 25px;margin-left: 90px;">
		<tr><td></td><td><input type="hidden" name="loc" value="<%=loc%>">
		<tr><td>Location</td><td><%=loc %></td></tr>
		<tr><td>Host</td><td><input type="text" name="host" class="w3-input"></td></tr>
		<tr><td>Virtual Machine</td><td><input type="text" name="vm" class="w3-input"></td></tr>
		<tr><td>Capacity</td><td><input type="text" name="cap" class="w3-input"></td></tr>
		</table>
		<center><input type="submit" value="Build" class="w3-button w3-black w3-opacity w3-input" style="width: 150px;"></center>
		</div>
		<div class="w3-bar-item" style="margin-left: 50px; margin-top: 10px;">
		<img alt="" src="Pics/n1.png"> 
		</div>
		</div>
		</form>
</body>
</html>