<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>





<div class="container">
<security:authorize access="hasRole('USER')">

	<form:form action="userspace/user/save.do" modelAttribute="userSpace">
	<div class="form-group">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="perfomances" />
		<form:hidden path="events" />
		<form:hidden path="playLists" />
		<form:hidden path="donations" />
		<form:hidden path="comments" />
		<form:hidden path="profileImg" />
		
		
		<div class="form-group">

			<label> <spring:message code="userspace.title" />
			</label><br /> <input class="form-control" value="${userSpace.title}"
				type="text" name="title" />
			<form:errors cssClass="error" path="title" />
			
			<br />
		    
			<form:label path="description">
				<spring:message code="userspace.description" />
			</form:label>
			<br />
			<form:textarea cols="30" rows="10" path="description"
				class="form-control" />
			<form:errors cssClass="error" path="description" />
				<br />
			
			
			<label> <spring:message code="userspace.contact" />
			</label><br /> <input class="form-control" value="${userSpace.contact}"
				type="text" name="contact" />
			<form:errors cssClass="error" path="contact" />
				<br />

			
			<label> <spring:message code="userspace.genre" /></label><br/> 
			<form:select path="genre">
				<form:option value="${null}" label="Género"/>
				 <jstl:forEach items="${genres}" var="genre">
                    <form:option value="${genre}" label="${genre.genre}"/>
              	</jstl:forEach>
			</form:select>
			<form:errors cssClass="error" path="genre" />

			<br />
			</div>
			
			
	  <spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.location='userspace/user/view.do'" class="btn btn-danger" type="button" name="cancel" value="${actorCancelHeader}"/>

</div>
	</form:form>
</security:authorize>

</div>
