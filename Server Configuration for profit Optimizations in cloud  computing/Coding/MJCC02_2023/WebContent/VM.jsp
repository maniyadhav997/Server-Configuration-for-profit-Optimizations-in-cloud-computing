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
			String sql = "select * from virtualmachine;";
			ResultSet rs = CloudDao.getData(sql);
		%>
		<table class="w3-table w3-bordered"
			style="margin-top: 25px; width: 650px; font-family: serif; font-size: 20px;">
			<tr>
				<th>HostId</th>
				<th>Virtual Machine</th>
				<th>UserId</th>
				<th>Usage</th>
				<th>Location</th>
			</tr>
			<%
				while (rs.next()) {
						String s = "select capacity from host1 where hostid='"
								+ rs.getString(4) + "'";
						ResultSet r = CloudDao.getData(s);
						double d = 0;
						while (r.next()) {
							d = r.getDouble(1);
						}
			%>
			<tr>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getDouble(5) / 1000%>MB
				<meter value=<%=rs.getDouble(5) / 1000 %> min=0 max=<%=d /1000 %>></meter>
				</td>
				<td><%=rs.getString(3)%></td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>