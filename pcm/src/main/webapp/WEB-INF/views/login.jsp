<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="resources/style.css" />
<title>Login</title>
<meta name="GENERATOR"
	content="Created by BlueVoda Website builder http://www.bluevoda.com">
<meta name="HOSTING"
	content="Hosting Provided By VodaHost http://www.vodahost.com">
<style type="text/css">
html,body {
	height: 100%;
}

div#space {
	width: 1px;
	height: 50%;
	margin-bottom: -408px;
	float: left
}

div#container {
	width: 1084px;
	height: 816px;
	margin: 0 auto;
	position: relative;
	clear: left;
}
</style>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background-color: #FFFFFF;
	color: #000000;
}
</style>
<style type="text/css">
a:hover {
	color: #90F518;
}
</style>
<!--[if lt IE 7]>
<style type="text/css">
   img { behavior: url("pngfix.htc"); }
</style>
<![endif]-->
</head>
<body>
	<div id="space">
		<br>
	</div>
	<div id="container">
		<div id="Html1"
			style="position: absolute; left: 6px; top: 46px; width: 994px; height: 755px; z-index: 0">
		</div>
		<div id="bv_Image1"
			style="margin: 0; padding: 0; position: absolute; left: 96px; top: 59px; width: 549px; height: 193px; text-align: left; z-index: 1;">
			<img src="${pageContext.request.contextPath}/resources/images/home_old.png" id="Image1" alt=""
				align="top" border="0" style="width: 749px; height: 193px;">
		</div>
		<div id="bv_Login1"
			style="margin: 0; padding: 0; position: absolute; left: 567px; top: 261px; width: 253px; height: 150px; text-align: right; z-index: 2;">
			<form:form commandName="userAccount" method="post" action="login.htm"
				id="loginform">
				<table cellspacing="4" cellpadding="0"
					style="background-color: #EFF6FF; border-color: #BFDBFF; border-width: 1px; border-style: solid; color: #006BF5; font-family: Verdana; font-size: 11px; width: 253px; height: 150px;">
					<tr>
						<td colspan="2" align="center"
							style="height: 17px; background-color: #BFDBFF; color: #006BF5;">Log
							In</td>

					</tr>
					<tr>
						<td align="right" style="height: 20px; width: 103px">User
							Name:</td>
						<td align="left"><form:input path="username" type="text"
								id="username" value=""
								style="width:100px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;" /></td>
					</tr>
					<tr>
						<td align="right" style="height: 20px">Password:</td>
						<td align="left"><form:input path="password" type="password"
								id="password" value=""
								style="width:100px;height:18px;px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td style="height: 20px" align="left"><input id="rememberme"
							type="checkbox" name="rememberme">Remember me</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="left" valign="bottom"><input type="submit"
							name="login" value="Log In" id="login"
							style="color: #006BF5; background-color: #FFFFFF; border-color: #BFDBFF; border-width: 1px; border-style: solid; font-family: Verdana; font-size: 11px; width: 70px; height: 20px;"></td>
					</tr>
					
					<!-- nihar phase 3 changes-->
					<form:errors path="*" cssClass="error" />
					<!-- nihar phase 3 changes-->
					
				</table>
			</form:form>
		</div>
		<div id="bv_Image2"
			style="margin: 0; padding: 0; position: absolute; left: 119px; top: 261px; width: 425px; height: 296px; text-align: left; z-index: 3;">
			<img src="${pageContext.request.contextPath}/resources/images/login.jpg" id="Image2" alt="" align="top"
				border="0" style="width: 425px; height: 296px;">
		</div>
		<div id="bv_Image3"
			style="margin: 0; padding: 0; position: absolute; left: 668px; top: 168px; width: 200px; height: 71px; text-align: left; z-index: 4;">
			<!-- <img src="resources/images/hippa.jpg" id="Image3" alt="" align="top" border="0" style="width:200px;height:71px;"> -->
		</div>
	</div>
</body>
</html>