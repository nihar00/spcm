<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- nihar change -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- nihar change -->

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	margin-bottom: -460px;
	float: left
}

div#container {
	width: 1084px;
	height: 921px;
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
h5 {
	font-family: Arial;
	font-size: 19px;
	font-weight: normal;
	font-style: normal;
	text-decoration: none;
	color: #000000;
	margin: 0 0 0 0;
	padding: 0 0 0 0;
	display: inline;
}

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
			style="position: absolute; left: 6px; top: 46px; width: 994px; height: 873px; z-index: 4">
		</div>
		<div id="bv_Form1"
			style="position: absolute; background-color: #F0F0F0; left: 400px; top: 372px; width: 583px; height: 523px; z-index: 5">

			<a href="${pageContext.request.contextPath}/logout.htm">Log Out</a>

			<div id="bv_Image4"
				style="margin: 0; padding: 0; position: absolute; left: 87px; top: 69px; width: 105px; height: 93px; text-align: left; z-index: 0;">
				<img
					src="${pageContext.request.contextPath}/resources/images/admin-icon-om.png"
					id="Image4" alt="" align="top" border="0"
					style="width: 105px; height: 93px;">
			</div>
			<div id="bv_Text1"
				style="margin: 0; padding: 0; position: absolute; left: 197px; top: 79px; width: 190px; height: 22px; text-align: left; z-index: 1;">
				<h5><b>Patient Info</b></h5><br>
				<h5>${patient.person.firstName}</h5>
				<h5>${patient.person.lastName}</h5><br>
				<h5>${patient.person.address}</h5>
			</div>

			<div id="bv_Table1"
				style="margin: 0; padding: 0; position: absolute; left: 57px; top: 180px; width: 491px; height: 150px; text-align: left; z-index: 3;">

				<!-- nihar changes -->
			<c:if test="${!empty medical}">
				<table border=1 width="100%">
					<tr>
						<th>Record Date</th>
						<th>Description</th>
						<th>Prescription</th>
						
					</tr>
					<c:forEach items="${medical}" var="pl">

						<tr>
							<td>${pl.date}</td>

							<td>${pl.description}</td>
							
							<td>${pl.prescription}</td>
							
						</tr>

					</c:forEach>
				</table>
			</c:if>
			<!-- nihar changes -->
			
			</div>

		</div>
		<div id="bv_Image2"
			style="margin: 0; padding: 0; position: absolute; left: 18px; top: 372px; width: 365px; height: 322px; text-align: left; z-index: 6;">
			<img
				src="${pageContext.request.contextPath}/resources/images/hospital.jpg"
				id="Image2" alt="" align="top" border="0"
				style="width: 365px; height: 322px;">
		</div>
		<div id="bv_Image3"
			style="margin: 0; padding: 0; position: absolute; left: 88px; top: 752px; width: 200px; height: 71px; text-align: left; z-index: 7;">
			<img
				src="${pageContext.request.contextPath}/resources/images/hippa.jpg"
				id="Image3" alt="" align="top" border="0"
				style="width: 200px; height: 71px;">
		</div>
		<div id="bv_Image1"
			style="margin: 0; padding: 0; position: absolute; left: 6px; top: 48px; width: 993px; height: 279px; text-align: left; z-index: 8;">
			<img
				src="${pageContext.request.contextPath}/resources/images/hospital-art-toy.jpg"
				id="Image1" alt="" align="top" border="0"
				style="width: 993px; height: 279px;">
		</div>
	</div>
</body>
</html>