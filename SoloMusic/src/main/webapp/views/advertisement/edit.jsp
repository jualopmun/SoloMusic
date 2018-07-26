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

	<spring:message code="advertisement.save" var="save"/>
	<spring:message code="advertisement.delete" var="delete"/>
	<spring:message code="advertisement.cancel" var="cancel"/>

	<form:form action="${requestURI}" modelAttribute="advertisement">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="actorOwener" />
		<form:hidden path="actorRegisters" />
		
		<div class="form-group" style="width: 20%;">
			<form:errors path="*" class="has-error" />
	
				<label><spring:message code="advertisement.title" /></label>
				<input class="form-control" value="${advertisement.title}" type="text" name="title" />
				<form:errors cssClass="error" path="title" />
				<br />
				
				<label><spring:message code="advertisement.description" /></label>
				<form:textarea cols="30" rows="10" path="description" class="form-control" />
				<form:errors cssClass="error" path="description" />
				<br />
				
				<label><spring:message code="advertisement.locationUrl" /></label>
				<input class="form-control" value="${advertisement.locationUrl}" type="text" name="locationUrl" />
				<form:errors cssClass="error" path="locationUrl" />
				<br />
				
				<label><spring:message code="advertisement.mainImg" /></label>
				<input class="form-control" value="${advertisement.mainImg}" type="text" name="mainImg" />
				<form:errors cssClass="error" path="mainImg" />
				<br />
				
				<label><spring:message code="advertisement.startDate" /></label>
				<input class="form-control" value="${advertisement.startDate}" type="text" name="startDate" placeholder="dd/mm/yyyy"/>
				<form:errors cssClass="error" path="startDate" />
				<br />
				
				<label><spring:message code="advertisement.endDate" /></label>
				<input class="form-control" value="${advertisement.endDate}" type="text" name="endDate" placeholder="dd/mm/yyyy"/>
				<form:errors cssClass="error" path="endDate" />
				<br />
				
				<label><spring:message code="advertisement.price" /></label>
				<input class="form-control" value="${advertisement.price}" type="number" name="price" />
				<form:errors cssClass="error" path="price" />
				<br />
		</div>
		
		<input type="submit" class="btn btn-primary" name="save" value="${save}" />
		<input type="submit" class="btn btn-primary" name="delete" value="${delete}"/>
		<input onclick="window.location='advertisement/user/list.do?q=0'" class="btn btn-warning" type="button" name="cancel" value="${cancel}"/>

	</form:form>
</security:authorize>
