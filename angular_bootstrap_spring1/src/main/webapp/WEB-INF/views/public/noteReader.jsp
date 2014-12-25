<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		
			<div class="body">
				<h2>Note title: ${note.noteTitle}</h2>
				Create by: ${note.userName} on ${date} <br> <br>
				<form:textarea class="textarea" path="note.noteText" readonly="true"></form:textarea>


				<form:form id="envselection" action="/abse/user/addNote" method="POST">
				<form:radiobutton path="rating" value="1" class="star" onclick="submitForm()"/>
				<form:radiobutton path="rating" value="2" class="star" onclick="submitForm()"/>
				<form:radiobutton path="rating" value="3" class="star" onclick="submitForm()"/>
				<form:radiobutton path="rating" value="4" class="star" onclick="submitForm()"/>
				<form:radiobutton path="rating" value="5" class="star" onclick="submitForm()"/>
				
				</form:form>

			</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>

