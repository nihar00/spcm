<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
<script type="text/javascript">
<!--
	function ValidateHospitalAdmin(theForm) {
		var regexp;
		if (theForm.TextArea1.value == "") {
			alert("description should be entered");
			theForm.TextArea1.focus();
			return false;
		}
		if (theForm.TextArea1.value.length < 1) {
			alert("description should be entered");
			theForm.TextArea1.focus();
			return false;
		}
		if (theForm.TextArea1.value.length > 100) {
			alert("description should be entered");
			theForm.TextArea1.focus();
			return false;
		}
		if (theForm.TextArea2.value == "") {
			alert("enter prescription");
			theForm.TextArea2.focus();
			return false;
		}
		if (theForm.TextArea2.value.length < 1) {
			alert("enter prescription");
			theForm.TextArea2.focus();
			return false;
		}
		if (theForm.TextArea2.value.length > 100) {
			alert("enter prescription");
			theForm.TextArea2.focus();
			return false;
		}
		return true;
	}
//-->
</script>
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
			style="position: absolute; left: 6px; top: 46px; width: 994px; height: 873px; z-index: 9">
		</div>
		<div id="bv_Form1"
			style="position: absolute; background-color: #F0F0F0; left: 400px; top: 372px; width: 583px; height: 523px; z-index: 10">
			<form:form commandName="record" method="post" action="create-medical-record.htm"
				id="Form1"
				onsubmit="return ValidateHospitalAdmin(this)">

				<div id="bv_Image4"
					style="margin: 0; padding: 0; position: absolute; left: 24px; top: 30px; width: 101px; height: 101px; text-align: left; z-index: 0;">
					<img src="resources/images/admin-icon-om.png" id="Image4" alt=""
						align="top" border="0" style="width: 101px; height: 101px;">
				</div>
				<div id="bv_Text1"
					style="margin: 0; padding: 0; position: absolute; left: 147px; top: 69px; width: 190px; height: 22px; text-align: left; z-index: 1;">
					<h5>Insert Medical Record</h5>
				</div>

				<form:textarea path="description" name="description" id="TextArea1"
					style="position:absolute;left:316px;top:181px;width:148px;height:104px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:2"
					rows="4" cols="11"></form:textarea>
				<div
					style="position: absolute; left: 313px; top: 134px; width: 148px; height: 18px; border: 1px #C0C0C0 solid; z-index: 3">

					<form:select path="recordType" name="recordType" size="1"
						id="Combobox1"
						style="position:absolute;left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:'Courier New';font-size:16px;">
						<option value="1">General</option>
						<option value="2">Respiratory</option>
						<option value="4">Oral</option>
						<option value="8">Gastritis</option>
						<option value="16">Skin</option>
						<option value="32">Orthopedic</option>
						<option value="64">Cognitive</option>
						<option value="128">Neurological</option>
						<option value="256">Eye Nose Ear</option>
						<option value="512">Immunization</option>
						<option value="1024">Allergy</option>
					</form:select>
				</div>

				<form:textarea path="prescription" name="prescription"
					id="TextArea2"
					style="position:absolute;left:319px;top:313px;width:148px;height:104px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:4"
					rows="4" cols="11"></form:textarea>
				<input type="submit" id="Button1" name="Button1"
					value="Create Record"
					style="position: absolute; left: 207px; top: 443px; width: 136px; height: 24px; font-family: Arial; font-size: 13px; z-index: 5">

				<div id="bv_Text2"
					style="margin: 0; padding: 0; position: absolute; left: 131px; top: 137px; width: 150px; height: 16px; text-align: left; z-index: 6;">
					<font style="font-size: 13px" color="#000000" face="Arial">Record
						Type</font>
				</div>
				<div id="bv_Text3"
					style="margin: 0; padding: 0; position: absolute; left: 132px; top: 223px; width: 150px; height: 16px; text-align: left; z-index: 7;">
					<font style="font-size: 13px" color="#000000" face="Arial">Description</font>
				</div>
				<div id="bv_Text4"
					style="margin: 0; padding: 0; position: absolute; left: 133px; top: 346px; width: 150px; height: 16px; text-align: left; z-index: 8;">
					<font style="font-size: 13px" color="#000000" face="Arial">Prescription</font>
				</div>

			</form:form>
		</div>

		<div id="bv_Image2"
			style="margin: 0; padding: 0; position: absolute; left: 18px; top: 372px; width: 365px; height: 322px; text-align: left; z-index: 11;">
			<img src="resources/images/hospital.jpg" id="Image2" alt=""
				align="top" border="0" style="width: 365px; height: 322px;">
		</div>
		<div id="bv_Image3"
			style="margin: 0; padding: 0; position: absolute; left: 88px; top: 752px; width: 200px; height: 71px; text-align: left; z-index: 12;">
			<img src="resources/images/hippa.jpg" id="Image3" alt="" align="top"
				border="0" style="width: 200px; height: 71px;">
		</div>
		<div id="bv_Image1"
			style="margin: 0; padding: 0; position: absolute; left: 6px; top: 48px; width: 993px; height: 279px; text-align: left; z-index: 13;">
			<img src="resources/images/hospital-art-toy.jpg" id="Image1" alt="" align="top"
				border="0" style="width: 993px; height: 279px;">
		</div>
	</div>
</body>
</html>