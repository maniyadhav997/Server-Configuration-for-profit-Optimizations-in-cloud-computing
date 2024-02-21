<%@page import="com.dao.CloudDao"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="w3.css">
<title>Insert title here</title>
<style type="text/css">
input {
	height: 35px;
}
</style>
</head>
<body background="Pics/h7.jpg" class="w3-text-white">
<form action="Password" method="post">
	<h1
		style="background-image: url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family: fantasy;"
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
<center>
<div style="margin-top: 30px;font-size: 25px;font-family: fantasy;">
<a href="UserHome.jsp">Home</a>
<a href="Compose.jsp" style="margin-left: 90px;">Compose</a>
<a href="Inbox.jsp" style="margin-left: 90px;">Inbox</a>
<a href="Sent.jsp" style="margin-left: 90px;">View Sent Data</a>
<a href="Store.jsp" style="margin-left: 90px;">My Account</a>
<a href="Logout.jsp" style="margin-left: 90px;">Logout</a>
</div>
<table class="w3-table" style="width: 500px;margin-top: 40px; font-size: 20px; font-family: fantasy;" >
<tr><td>Email</td><td><input type="text" name="email" class="w3-input"></td></tr>
<tr><td>Old Password</td><td><input type="password" name="opwd" class="w3-input"></td></tr>
<tr><td>New Password</td><td><input type="password" name="pwd" class="w3-input"></td></tr>
</table>
<input type="submit" value="Change" class="w3-button w3-black w3-opacity" style="margin-top: 10px;" >
</center>
</form>
</body>
</html>