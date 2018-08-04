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
<spring:message code="advertisement.cancel" var="cancel"/>

	<form:form action="advertisement/user/up.do" enctype="multipart/form-data">
		
		<div class="form-group" style="width: 20%;">
		<form:errors path="*" class="has-error" />

			
			
			<label> <spring:message code="advertisement.mainImg" />
			</label><br /> <input class="form-control"
				type="file" name="mainImg" accept=".jpg" />
			</div>
			
	  <spring:message code="actor.save" var="actorSaveHeader"/>
		<spring:message code="actor.cancel" var="actorCancelHeader"/>
		<input type="submit" class="btn btn-primary" name="save" value="${actorSaveHeader}" />
		<input onclick="window.location='advertisement/user/list.do?q=0'" class="btn btn-warning" type="button" name="cancel" value="${cancel}"/>

	</form:form>
</security:authorize>
