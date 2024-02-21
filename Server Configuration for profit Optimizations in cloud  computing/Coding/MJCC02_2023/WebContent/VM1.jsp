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
<body background="Pics/h7.jpg" class="w3-text-white"
	style="font-family: fantasy;">
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
			String host = request.getParameter("host");
			String loc = request.getParameter("loc");
			String sql = "select * from virtualmachine where hostid='" + host
					+ "' and location='" + loc + "'";
			ResultSet rs = CloudDao.getData(sql);
			String h = "";
		%>
		<table class="w3-table w3-bordered"
			style="width: 750px; font-size: 20px; font-family: monospace; margin-top: 20px;">
			<tr>
				<th>Virtual Machines</th>
				<th>UserId</th>
				<th>Usage</th>
				<th>Location</th>
				<th>Migration</th>
			</tr>
			<%
				while (rs.next()) {
			%>
			<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getInt(5)%></td>
				<td><%=rs.getString(3)%></td>
				<td><a href="Migration.jsp?vm=<%=rs.getString(1)%>&&loc=<%=rs.getString(3) %>&&host=<%=host %>"
					class="w3-button w3-opacity w3-black">Migration </a></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>