<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dao.CloudDao"%>
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
		<div style="margin-top: 30px; font-size: 25px; font-family: fantasy;">
			<a href="UserHome.jsp">Home</a> <a href="Compose.jsp"
				style="margin-left: 90px;">Compose</a> <a href="Inbox.jsp"
				style="margin-left: 90px;">Inbox</a> <a href="Sent.jsp"
				style="margin-left: 90px;">Sent</a> <a href="Store.jsp"
				style="margin-left: 90px;">My Account</a> <a href="Logout.jsp"
				style="margin-left: 90px;">Logout</a>
		</div>
		<%
			String host = request.getParameter("host");
			String ta = request.getParameter("ta");
			String da = request.getParameter("da");
			Connection con = CloudDao.connect1(host);
			String sql = "select * from " + ta + " where userid='"
					+ session.getAttribute("email") + "' and date='"+da+"'";
			ResultSet rs = CloudDao.getData1(sql, con);
		%>
		<table class="w3-table w3-bordered" style="width: 500px;margin-top: 30px;font-size: 20px;">
<%
while(rs.next()){
%>
<tr><td>To</td><td><%=rs.getString(2) %></td></tr>
<tr><td>Subject</td><td><%=rs.getString(3) %></td></tr>
<tr><td>Body</td><td><%=rs.getString(4) %></td></tr>
<tr><td>File</td><td>
<%if(rs.getString(7).equals("application/octet-stream")){%>
	NoFile<%
}else{%>
	<a href="Download1?host=<%=host%>&&ta=<%=ta%>&&da=<%=da%>">File</a>
<%} %>
</td></tr>
<%} %>

</table>
	</center>
</body>
</html>