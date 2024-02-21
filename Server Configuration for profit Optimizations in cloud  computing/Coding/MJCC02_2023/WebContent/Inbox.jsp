<%@page import="java.sql.Connection"%>
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
<a href="Sent.jsp" style="margin-left: 90px;">View Sent Data</a>
<a href="Store.jsp" style="margin-left: 90px;">My Account</a>
<a href="Logout.jsp" style="margin-left: 90px;">Logout</a>
</div>
<%
	session = request.getSession(false);
String user = (String)session.getAttribute("user");
String host = "",ta = "";
String sql = "select hostid from host1 where location='"+session.getAttribute("loc")+"'";
ResultSet rs = CloudDao.getData(sql);
if(rs.next()){
host = rs.getString(1);
}else{
	host = "Host1";
}
sql = "select sent from loc where location='"+session.getAttribute("loc")+"'";
rs = CloudDao.getData(sql);
rs.next();
ta = rs.getString(1);
Connection con = CloudDao.connect1(host);
sql = "select * from "+ta+" where recipient='"+session.getAttribute("email")+"'";
rs = CloudDao.getData1(sql, con);
%>
<table class="w3-table w3-bordered" style="width: 650px; margin-top: 30px;">
<tr><th>From</th><th>Subject</th><th>Date</th><th>View</th></tr> 
<%
while (rs.next()){
%>
<tr><td><%=rs.getString(1) %></td><td><%=rs.getString(3) %> </td><td><%=rs.getString(6) %></td><td><a href="View.jsp?host=<%=host%>&&ta=<%=ta%>&&da=<%=rs.getString(6)%>">View</a> </td></tr>
<%} %>
</table>
</center>
</body>
</html>