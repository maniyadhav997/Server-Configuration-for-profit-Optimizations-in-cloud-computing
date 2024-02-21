<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="w3.css">
<title>Insert title here</title>
</head>
<body background="Pics/h7.jpg" class="w3-text-white">
	<h1
		style="background-image: url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family: fantasy;"
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
		<form action="Compose" method="post" enctype="multipart/form-data">
<center>
<div style="margin-top: 30px;font-size: 25px;font-family: fantasy;">
<a href="UserHome.jsp">Home</a>
<a href="Compose.jsp" style="margin-left: 90px;">Compose</a>
<a href="Inbox.jsp" style="margin-left: 90px;">Inbox</a>
<a href="Sent.jsp" style="margin-left: 90px;">View Sent Data</a>
<a href="Store.jsp" style="margin-left: 90px;">My Account</a>
<a href="Logout.jsp" style="margin-left: 90px;">Logout</a>
</div>
<table class="w3-table" style="width: 500px;border: 1px solid black; margin-top: 30px;">
<tr><td>Recipient</td><td><input type="text" name="rcp" class="w3-input" style="width: 370px;"></td></tr>
<tr><td>Subject</td><td><input type="text" name="sub" class="w3-input" style="width: 370px;"></td></tr>
<tr><td>Body</td><td><textarea rows="10" cols="40" name="body"></textarea> </td></tr>
<tr><td>File</td><td><input type="file" name="photo" class="w3-input"></td></tr>
</table>
<input type="submit" value="Send" class="w3-button w3-blue-gray">
</center>
</form>
</body>
</html>