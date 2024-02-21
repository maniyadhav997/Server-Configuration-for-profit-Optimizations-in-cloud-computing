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
			String sql = "select * from virtualmachine";
			ResultSet rs = CloudDao.getData(sql);
			String h = "";
		%>
		<table class="w3-table w3-bordered"
			style="width: 650px; font-size: 20px; font-family: monospace; margin-top: 30px;">
			<tr>
				<th>Virtual Machines</th>
				<th>UserId</th>
				<th>Host</th>
				<th>Location</th>
				<th>Capacity</th>
				<th>Build</th>
			</tr>
			<%
				while (rs.next()) {
					if (rs.getString(1) == null) {
						String s = "Not Available";
			%>
			<tr>
				<td><%=s%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getDouble(5) / 1000%>MB</td>
				<td><a href="Create.jsp?loc=<%=rs.getString(3) %>" class="w3-button w3-black w3-opacity">Build</a></td>
			</tr>
			<%
				}
				}
			%>
		</table>
	</center>

</body>
</html>