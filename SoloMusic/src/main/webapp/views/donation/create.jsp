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
		
			<form:form action="donation/user/save.do" modelAttribute="donation">
		
				<form:hidden path="id" />
				<form:hidden path="version" />
				
				<div class="form-group" >
		
					<label> <spring:message code="event.title" />
					</label><br /> <input class="form-control" value="${donation.title}"
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
					
					<br /> <label> <spring:message code="donation.price" />
					</label><br /> <input class="form-control" value="${donation.price}"
						type="text" name="price" />
					<form:errors cssClass="error" path="price" />
					<br /> <br />
					
					</div>
					
			  <spring:message code="actor.save" var="actorSaveHeader"/>
				<spring:message code="actor.cancel" var="actorCancelHeader"/>
				<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
				<input onclick="window.location='userspace/user/view.do?p=${actor.userSpace.id}'" class="btn btn-danger" type="button" name="cancel" value="${actorCancelHeader}"/>
		
			</form:form>
		</div>
	
</security:authorize>
