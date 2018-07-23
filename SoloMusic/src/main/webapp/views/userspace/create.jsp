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




<security:authorize access="hasRole('USER')">

	<form:form action="userspace/user/save.do" modelAttribute="userSpace">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="perfomances" />
		<form:hidden path="events" />
		<form:hidden path="playLists" />
		<form:hidden path="donations" />
		
		
		<div class="form-group" style="width: 20%;">
		<form:errors path="*" class="has-error" />

			<label> <spring:message code="userspace.title" />
			</label><br /> <input class="form-control" value="${userSpace.title}"
				type="text" name="title" />
			<form:errors cssClass="error" path="title" />
			
			<br />
		    
			<form:label path="description">
				<spring:message code="userspace.description" />:
		</form:label>
			<br />
			<form:textarea cols="30" rows="10" path="description"
				class="form-control" />
			<form:errors cssClass="error" path="description" />
			
			<br /> <label> <spring:message code="userspace.profileimg" />
			</label><br /> <input class="form-control" value="${userSpace.profileImg}"
				type="text" name="profileImg" />
			<form:errors cssClass="error" path="profileImg" />
			<br /> <br />
			
			<label> <spring:message code="userspace.contact" />
			</label><br /> <input class="form-control" value="${userSpace.contact}"
				type="text" name="contact" />
			<form:errors cssClass="error" path="contact" />
			
			<br />
			</div>
			
			
	  <spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.location='userspace/user/view.do'" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>

	</form:form>
</security:authorize>
