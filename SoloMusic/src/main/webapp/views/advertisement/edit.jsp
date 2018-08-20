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

	<div class="container">
		
			<spring:message code="advertisement.save" var="save"/>
			<spring:message code="advertisement.delete" var="delete"/>
			<spring:message code="advertisement.cancel" var="cancel"/>
	
			<form:form action="${requestURI}" modelAttribute="advertisement">
	
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="actorOwener" />
				<form:hidden path="actorRegisters" />
				<form:hidden path="mainImg" />
			
				<div class="form-group">
			
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
						
						
		
		
						<label> <spring:message code="advertisement.startDate" />
						</label><br /> <input class="form-control" value='<fmt:formatDate pattern="dd/MM/yyyy" value="${advertisement.startDate}"/>'
							type="text" name="startDate" placeholder="dd/MM/yyyy"  />
						<form:errors cssClass="error" path="startDate" />
						<br />
						
						<label><spring:message code="advertisement.endDate" /></label>
						<input class="form-control" value='<fmt:formatDate pattern="dd/MM/yyyy" value="${advertisement.endDate}"/>'
						type="text" name="endDate" placeholder="dd/MM/yyyy" />
						<form:errors cssClass="error" path="endDate" />
						<br />
						
						<label><spring:message code="advertisement.price" /></label>
						<input class="form-control" value="${advertisement.price}" type="number" name="price" />
						<form:errors cssClass="error" path="price" />
						<br />
				</div>
			
				<input type="submit" class="btn btn-primary" name="save" value="${save}" />
				
				<input onclick="window.location='advertisement/user/list.do?q=0'" class="btn btn-danger" type="button" name="cancel" value="${cancel}"/>
	
			</form:form>
		</div>
	
</security:authorize>
