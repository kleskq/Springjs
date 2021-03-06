<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Login</h1>

	<div id="login-error">${error}</div>

	<c:url value="/j_spring_security_check" var="secureUrl" />

	<form action="${secureUrl}" method="post">
		<%
			ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeGAQATAAAAAIrTJ0Zp2cSi89SqFcMciu-ZXOOP", "6LeGAQATAAAAAHX024h7xgWfBkJFkVMRGKrkwu-Z", false);
			out.print(c.createRecaptchaHtml(null, null));
		%>
		<p>
			<label for="j_username">Username</label> <input id="j_username"
				name="j_username" type="text" />
		</p>
		<p>
			<label for="j_password">Password</label> <input id="j_password"
				name="j_password" type="password" />
		</p>
		<input type="submit" value="Login" />
	</form>

</body>
</html>