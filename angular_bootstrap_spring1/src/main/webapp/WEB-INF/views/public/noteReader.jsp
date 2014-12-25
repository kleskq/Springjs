<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
<title>Star rating demo</title>

<link href="<c:url value="/resources/star-rating/jquery.rating.css" />" rel="stylesheet">
<script src="<c:url value="/resources/star-rating/jquery.js" />"></script>
<script src="<c:url value="/resources/star-rating/jquery.rating.js" />"></script>


</head>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<body>
			<div class="body">
				<h2>Note title: ${note.noteTitle}</h2>
				Create by: ${note.userName} on ${date} <br> <br>
				<form:textarea class="textarea" path="note.noteText" readonly="true"></form:textarea>



				<form>
					<input type="radio" name="rating" value="1" class="star"> <input
						type="radio" name="rating" value="2" class="star"> <input
						type="radio" name="rating" value="3" class="star"> <input
						type="radio" name="rating" value="4" class="star"> <input
						type="radio" name="rating" value="5" class="star">
				</form>

			</div>
		</body>
	</tiles:putAttribute>
</tiles:insertDefinition>

</html>