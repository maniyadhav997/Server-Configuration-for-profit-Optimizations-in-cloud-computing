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
</head>
<body background="Pics/h7.jpg" class="w3-text-white">
	<h1
		style="background-image: url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family: fantasy;"
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
<center>
<div style="margin-top: 30px;font-size: 25px;font-family: fantasy;">
<a href="UserHome.jsp">Home</a>
<a href="Compose.jsp" style="margin-left: 90px;">Compose</a>
<a href="Inbox.jsp" style="margin-left: 90px;">Inbox</a>
<a href="Sent.jsp" style="margin-left: 90px;">Sent</a>
<a href="Store.jsp" style="margin-left: 90px;">My Account</a>
<a href="Logout.jsp" style="margin-left: 90px;">Logout</a>
</div>
<%
	String sql = "select * from users where email='"+session.getAttribute("email")+"'";
%>
<table class="w3-table w3-bordered" style="width: 500px;margin-top: 30px; font-size: 20px;" >
<%
	ResultSet rs = CloudDao.getData(sql);
while(rs.next()){
%>

<tr><td>FirstName</td><td><input type="text" name="fn" value="<%=rs.getString(1) %>"></td></tr>
<tr><td>LastName</td><td><input type="text" name="ln"  value="<%=rs.getString(2) %>"></td></tr>
<tr><td>Email</td><td><%=rs.getString(3) %></td></tr>
<tr><td>Mobile</td><td><input type="text" name="mob" value="<%=rs.getString(5) %>"></td></tr>
<tr><td>Location</td><td><%=rs.getString(6) %></td></tr>
<tr><td><a href="Profile.jsp">Edit Profile</a> </td><td><a href="Password.jsp">Change Password</a></td></tr>
<%} %>
</table>
</center>
</body>
</html>