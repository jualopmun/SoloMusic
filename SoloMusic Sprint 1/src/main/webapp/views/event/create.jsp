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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<security:authorize access="hasRole('USER')">

	<form:form action="event/user/save.do" modelAttribute="event">

		<form:hidden path="id" />
		<form:hidden path="version" />
		
		
		
		
		
		<form:errors path="*" class="has-error" />
<div class="form-group" style="width: 55%;">
			<label> <spring:message code="event.title" />
			</label><br /> <input class="form-control" value="${event.title}"
				type="text" name="title" />
			<form:errors cssClass="error" path="title" />
			
			<br />
		    
			<form:label path="description">
				<spring:message code="event.description" />:
		</form:label>
			<br />
			<form:textarea cols="30" rows="10" path="description"
				class="form-control" />
			<form:errors cssClass="error" path="description" />
			
			<br /> <label> <spring:message code="event.locationUrl" />
			</label><br /> <input class="form-control" value="${event.locationUrl}"
				type="text" name="locationUrl" />
			<form:errors cssClass="error" path="locationUrl" />
			<br /> <br />
			
			<label> <spring:message code="event.startDate" />
			
			</label><br /> <input class="form-control"
				type="text" name="startDate" placeholder="dd/MM/yyyy" />
			<form:errors cssClass="error" path="startDate" />
			
			<br />
			</div>
			
			
			
	  <spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.location='event/user/view.do?p=${actor.userSpace.id}'" class="btn btn-warning" type="button" name="cancel" value="${actorCancelHeader}"/>

	</form:form>
</security:authorize>
