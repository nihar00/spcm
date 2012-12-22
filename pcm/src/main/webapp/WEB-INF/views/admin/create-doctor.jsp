<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../resources/style.css" />
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
a:hover {
	color: #90F518;
}
</style>
<script type="text/javascript">
<!--
	function ValidateHospitalAdmin(theForm) {
		var regexp;
		if (theForm.Editbox4.value == "") {
			alert("Enter All Fields");
			theForm.Editbox4.focus();
			return false;
		}
		if (theForm.Editbox4.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox4.focus();
			return false;
		}
		if (theForm.Editbox4.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox4.focus();
			return false;
		}
		if (theForm.Editbox5.value == "") {
			alert("Enter All Fields");
			theForm.Editbox5.focus();
			return false;
		}
		if (theForm.Editbox5.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox5.focus();
			return false;
		}
		if (theForm.Editbox5.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox5.focus();
			return false;
		}
		if (theForm.Editbox6.value == "") {
			alert("Enter All Fields");
			theForm.Editbox6.focus();
			return false;
		}
		if (theForm.Editbox6.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox6.focus();
			return false;
		}
		if (theForm.Editbox6.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox6.focus();
			return false;
		}
		if (theForm.Editbox8.value == "") {
			alert("Enter All Fields");
			theForm.Editbox8.focus();
			return false;
		}
		if (theForm.Editbox8.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox8.focus();
			return false;
		}
		if (theForm.Editbox8.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox8.focus();
			return false;
		}
		if (theForm.Editbox9.value == "") {
			alert("Enter All Fields");
			theForm.Editbox9.focus();
			return false;
		}
		if (theForm.Editbox9.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox9.focus();
			return false;
		}
		if (theForm.Editbox9.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox9.focus();
			return false;
		}
		if (theForm.Editbox10.value == "") {
			alert("Enter All Fields");
			theForm.Editbox10.focus();
			return false;
		}
		if (theForm.Editbox10.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox10.focus();
			return false;
		}
		if (theForm.Editbox10.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox10.focus();
			return false;
		}
		if (theForm.Editbox11.value == "") {
			alert("Enter All Fields");
			theForm.Editbox11.focus();
			return false;
		}
		if (theForm.Editbox11.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox11.focus();
			return false;
		}
		if (theForm.Editbox11.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox11.focus();
			return false;
		}
		if (theForm.Editbox12.value == "") {
			alert("Enter All Fields");
			theForm.Editbox12.focus();
			return false;
		}
		if (theForm.Editbox12.value.length < 1) {
			alert("Enter All Fields");
			theForm.Editbox12.focus();
			return false;
		}
		if (theForm.Editbox12.value.length > 20) {
			alert("Enter All Fields");
			theForm.Editbox12.focus();
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
			style="position: absolute; left: 6px; top: 46px; width: 994px; height: 873px; z-index: 23">
		</div>
		<div id="bv_Form1"
			style="position: absolute; background-color: #F0F0F0; left: 400px; top: 372px; width: 583px; height: 523px; z-index: 24">
			<form:form commandName="createDoctor" method="post"
				action="${pageContext.request.contextPath}/admin/create-doctor.htm" id="Form1"
				onsubmit="return ValidateHospitalAdmin(this)">
				<form:input path="doctor.person.firstName" type="text" id="Editbox4"
					style="position:absolute;left:394px;top:167px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:0"
					value="" maxlength="20" />
				<div id="bv_Text4"
					style="margin: 0; padding: 0; position: absolute; left: 275px; top: 171px; width: 103px; height: 32px; text-align: left; z-index: 1;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; First Name</font>
				</div>
				<form:input path="doctor.person.lastName" type="text" id="Editbox5"
					style="position:absolute;left:395px;top:204px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:2"
					name="Editbox5" value="" maxlength="20" />
				<div id="bv_Text5"
					style="margin: 0; padding: 0; position: absolute; left: 275px; top: 206px; width: 103px; height: 16px; text-align: left; z-index: 3;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; Last Name</font>
				</div>
				<form:input path="userAccount.username" type="text" id="Editbox6"
					style="position:absolute;left:152px;top:164px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:4"
					name="Editbox6" value="" maxlength="20" />
				<form:input path="userAccount.password" type="password"
					id="Editbox7"
					style="position:absolute;left:153px;top:201px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:5"
					name="Editbox7" value="" />
				<div id="bv_Text8"
					style="margin: 0; padding: 0; position: absolute; left: 35px; top: 242px; width: 103px; height: 16px; text-align: left; z-index: 6;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; Date Of Birth</font>
				</div>
				<div id="bv_Text9"
					style="margin: 0; padding: 0; position: absolute; left: 35px; top: 277px; width: 103px; height: 32px; text-align: left; z-index: 7;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Address</font>
				</div>
				<form:input path="doctor.person.dob" type="text" id="Editbox8"
					style="position:absolute;left:154px;top:238px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:8"
					name="Editbox8" value="" maxlength="20" />
				<form:input path="doctor.person.address" type="text" id="Editbox9"
					style="position:absolute;left:155px;top:275px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:9"
					name="Editbox9" value="" maxlength="20" />
				<form:input path="doctor.person.ssn" type="password" id="Editbox10"
					style="position:absolute;left:396px;top:238px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:10"
					name="Editbox10" value="" maxlength="20" />
				<form:input path="doctor.person.phone" type="text" id="Editbox11"
					style="position:absolute;left:397px;top:275px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:11"
					name="Editbox11" value="" maxlength="20" />
				<div id="bv_Text10"
					style="margin: 0; padding: 0; position: absolute; left: 277px; top: 242px; width: 103px; height: 16px; text-align: left; z-index: 12;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
						SSN</font>
				</div>
				<div id="bv_Text11"
					style="margin: 0; padding: 0; position: absolute; left: 277px; top: 277px; width: 103px; height: 32px; text-align: left; z-index: 13;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Phone</font>
				</div>
				<div id="bv_Text12"
					style="margin: 0; padding: 0; position: absolute; left: 36px; top: 312px; width: 103px; height: 16px; text-align: left; z-index: 14;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; Email Id</font>
				</div>
				<form:input path="doctor.person.emailId" type="text" id="Editbox12"
					style="position:absolute;left:156px;top:310px;width:148px;height:20px;border:1px #C0C0C0 solid;font-family:'Courier New';font-size:16px;z-index:15"
					name="Editbox12" value="" maxlength="20" />
				<div
					style="position: absolute; left: 398px; top: 313px; width: 148px; height: 18px; border: 1px #C0C0C0 solid; z-index: 16">
					<form:select path="doctor.person.gender" name="Combobox1" size="1"
						id="Combobox1"
						style="position:absolute;left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:'Courier New';font-size:16px;">
						<option selected value="true">male</option>
						<option value="false">female</option>
					</form:select>
				</div>
				<div id="bv_Text13"
					style="margin: 0; padding: 0; position: absolute; left: 280px; top: 312px; width: 103px; height: 32px; text-align: left; z-index: 17;">
					<font style="font-size: 13px" color="#000000" face="Arial">&nbsp;&nbsp;
						&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; Gender</font>
				</div>
				<div id="bv_Text6"
					style="margin: 0; padding: 0; position: absolute; left: 29px; top: 171px; width: 113px; height: 16px; text-align: left; z-index: 18;">
					<font style="font-size: 13px" color="#000000" face="Arial">Doctor
						Username</font>
				</div>
				<div id="bv_Text7"
					style="margin: 0; padding: 0; position: absolute; left: 29px; top: 206px; width: 113px; height: 16px; text-align: left; z-index: 19;">
					<font style="font-size: 13px" color="#000000" face="Arial">Doctor
						Password</font>
				</div>
				<input type="submit" id="Button1" name="Register" value="Submit"
					style="position: absolute; left: 286px; top: 363px; width: 75px; height: 24px; font-family: Arial; font-size: 13px; z-index: 20" />

				<!-- nihar phase 3 changes-->
				<form:errors path="userAccount.username" cssClass="error" />
				<!-- nihar phase 3 changes-->

				<div id="bv_Image4"
					style="margin: 0; padding: 0; position: absolute; left: 69px; top: 72px; width: 101px; height: 101px; text-align: left; z-index: 21;">
					<img src="../resources/images/admin-icon-om.png" id="Image4" alt=""
						align="top" border="0" style="width: 101px; height: 101px;">
				</div>
				<div id="bv_Text2"
					style="margin: 0; padding: 0; position: absolute; left: 250px; top: 115px; width: 139px; height: 22px; text-align: left; z-index: 22;">
					<font style="font-size: 19px" color="#000000" face="Arial"><b>Doctor
							Details</b></font>
				</div>
			</form:form>
		</div>
		<div id="bv_Image2"
			style="margin: 0; padding: 0; position: absolute; left: 18px; top: 372px; width: 365px; height: 322px; text-align: left; z-index: 25;">
			<img src="${pageContext.request.contextPath}/resources/images/hospital.jpg" id="Image2" alt=""
				align="top" border="0" style="width: 365px; height: 322px;">
		</div>
		<div id="bv_Image1"
			style="margin: 0; padding: 0; position: absolute; left: 17px; top: 53px; width: 968px; height: 310px; text-align: left; z-index: 26;">
			<img src="${pageContext.request.contextPath}/resources/images/hospital-art-toy.jpg" id="Image1" alt=""
				align="top" border="0" style="width: 968px; height: 310px;">
		</div>
		<div id="bv_Image3"
			style="margin: 0; padding: 0; position: absolute; left: 88px; top: 752px; width: 200px; height: 71px; text-align: left; z-index: 27;">
			<img src="${pageContext.request.contextPath}/resources/images/hippa.jpg" id="Image3" alt="" align="top"
				border="0" style="width: 200px; height: 71px;">
		</div>
	</div>
</body>
</html>