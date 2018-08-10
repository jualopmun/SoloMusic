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
	
	<div style="width:30%; margin: auto;">
		<div style="width: 200%; margin: auto; display: inline-block;">
			<form:form action="userspace/comment/save.do" modelAttribute="comment">
				<form:hidden path="id"/>
				<form:hidden path="version"/>
				<form:hidden path="actor"/>
				<form:hidden path="date"/>
			
				<div class="form-group" style="width: 50%;">
				<form:errors path="*" class="has-error" />
					
					<label><spring:message code="comment.text" /></label>
						<form:textarea cols="30" rows="10" path="text" class="form-control" />
						<form:errors cssClass="error" path="text" />
						<br />
					
					<label> <spring:message code="comment.puntuation" />
					</label><br /> <input class="form-control" value="${comment.puntuacion}"
						type="number" name="puntuacion"  />
					<form:errors cssClass="error" path="puntuacion" />
				<br/>
				<br/>
					
			  <spring:message code="actor.save" var="actorSaveHeader"/>
				
				<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
				
				<spring:message code="actor.cancel" var="actorCancelHeader"/>
				<jstl:if test="${actor.userSpace.id==userspace.id}">
				<input onclick="window.location='userspace/user/view.do'" class="btn btn-danger" type="button" name="cancel" value="${actorCancelHeader}"/>
				</jstl:if>
				<jstl:if test="${actor.userSpace.id!=userspace.id}">
				<input onclick="window.location='userspace/user/spaceview.do?q=${userspace.id}'" class="btn btn-danger" type="button" name="cancel" value="${actorCancelHeader}"/>
				</jstl:if>
			</div>
			</form:form>
		</div>
	</div>
</security:authorize>