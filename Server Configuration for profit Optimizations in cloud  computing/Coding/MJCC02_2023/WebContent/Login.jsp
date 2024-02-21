<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="w3.css">
<title>Insert title here</title>
<style type="text/css">


.center1 {
	position: absolute;
	top: 317px;
	left: 540px;
	text-transform: translate(-50%, -50%);
	opacity: 0.5;
}

.center2 {
	position: absolute;
	top: 356px;
	left: 540px;
	text-transform: translate(-50%, -50%);
	opacity: 0.5;
}

.center3 {
	position: absolute;
	top: 395px;
	left: 540px;
	text-transform: translate(-50%, -50%);
	opacity: 0;
}

.center4 {
	position: absolute;
	top: 427px;
	left: 540px;
	text-transform: translate(-50%, -50%);
	opacity: 0;
}
</style>
</head>
<body background="Pics/h7.jpg">
<form action="Login" method="post">
	<h1
		style="background-image:url('Pics/h5.jpg'); font-size: 50px; font-style: oblique; font-family:fantasy; "
		class="w3-center w3-text-white">Server Configuration for profit Optimizations in cloud computing</h1>
<center>

<div class="w3-bar">
<div class="w3-bar-item"><a href="index.html"><img alt="" src="Pics/ho1.jpg" width="250px;" style="margin-left: 120px;margin-top: 120px;"></a></div>
<div class="w3-bar-item">
<img alt="" src="Pics/log.png" width="450px;" style="margin-top: 30px;margin-left: 40px;">
<div class="center1">
				<input type="text" name="uid"
					style="width: 295px; height: 30px; border: 0px;">
			</div>
			<div class="center2">
				<input type="password" name="pwd"
					style="width: 295px; height: 30px; border: 0px;">
			</div>
			<div class="center4">
				<a href="Register.jsp"  style="width: 60px;">Sign Up</a>
			</div>
			<div class="center3">
				<input type="submit" value="Sign-in!"
					style="width: 295px; height: 30px; border: 0px;"
					class="w3-text-white w3-blue ">
			</div>
			</div>
</div>
</center>
</form>
</body>
</html>