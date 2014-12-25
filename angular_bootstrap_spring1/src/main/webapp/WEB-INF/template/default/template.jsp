<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Default tiles template</title>
<link href="<c:url value="/resources/star-rating/jquery.rating.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/star-rating/jquery.js" />"></script>
<script src="<c:url value="/resources/star-rating/jquery.rating.js" />"></script>


<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	height: 100%;
	overflow: hidden;
}

.page {
	min-height: 100%;
	position: relative;
}

.header {
	margin: 0px 10px 0px 0px;
	width: 100%;
	text-align: right;
}

.content {
	padding: 10px;
	padding-bottom: 20px; /* Height of the footer element */
	overflow: hidden;
}

.menu {
	padding: 50px 10px 0px 10px;
	width: 200px;
	float: left;
}

.body {
	margin: 50px 10px 0px 250px;
}

.footer {
	clear: both;
	position: absolute;
	bottom: 0;
	left: 0;
	text-align: center;
	width: 100%;
	height: 20px;
}

.textarea {
	width: 100%;
	font-size: 10pt;
	display: block;
	background-color: #F8F8F8;
	height: 200px;
	resize: none;
	border: 1px solid #CCC;
}

.input {
	resize: none;
	font-size: 10pt;
	display: block;
	background-color: #F8F8F8;
	width: 260px;
	border: 1px solid #CCC;
}
</style>
</head>
<body>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>