<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="w3.css">
<title>Insert title here</title>
</head>
<body background="Pics/h7.jpg" class="w3-text-white"
	style="font-family: fantasy;">
	<form action="Migration" method="post">
	<h1
		style="background-image: url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family: fantasy;"
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
	<center>
		<div style="font-size: 20px;">
			<a href="AdminHome.jsp">Home</a> <a href="Network.jsp"
				style="margin-left: 70px;">Network Creation</a> <a href="Host.jsp"
				style="margin-left: 70px;">Host Failure Detection</a> <a
				href="VM.jsp" style="margin-left: 70px;">Virtual Machines</a> <a
				href="index.html" style="margin-left: 70px;">Logout</a>
		</div>

		<%
			String host = request.getParameter("host");
			String vm = request.getParameter("vm");
			String loc = request.getParameter("loc");
		%>
		</center>
		<div class="w3-bar" style="margin-top: 30px; margin-left: 100px;"> 
		<div class="w3-bar-item">
		<img alt="" src="Pics/n3.png" width="250px;">
		</div>
		<div class="w3-bar-item" style="margin-left: 100px; width: 300px; font-size: 20px;">
		<table class="w3-table">
		<tr><td></td><td><input type="hidden" name="host" value="<%=host%>"></td></tr>
		<tr><td><input type="hidden" name="loc" value="<%=loc%>"></td><td><input type="hidden" name="vm" value="<%=vm%>"></td></tr>
		<tr><td>Host</td><td><%=host %></td></tr>
		<tr><td>VirtualMachine</td><td><%=vm %></td></tr>
		<tr><td>Location</td><td><%=loc %></td></tr>
		<tr><td>New Host</td><td><input type="text" name="nhost" class="w3-input" style="width: 250px; height: 30px;"> </td></tr>
		</table>
		<center><input type="submit" value="Migrate" class="w3-button w3-black w3-opacity"> </center>
		</div>
		</div>
		</form>
</body>
</html>