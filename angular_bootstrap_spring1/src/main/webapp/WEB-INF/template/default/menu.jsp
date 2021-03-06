<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="menu">
	Menu
	<ul>
		<li><spring:url value="/home" var="homeUrl" htmlEscape="true" />
			<a href="${homeUrl}">Home</a></li>

		<li><spring:url value="/user" var="noteUrl" htmlEscape="true" />
			<a href="${noteUrl}">Note</a></li>
		<li><spring:url value="/noteList" var="noteUrl" htmlEscape="true" />
			<a href="${noteUrl}">Note ranking</a></li>
		<li><spring:url value="/about" var="aboutUrl" htmlEscape="true" />
			<a href="${aboutUrl}">About</a></li>
	</ul>
</div>