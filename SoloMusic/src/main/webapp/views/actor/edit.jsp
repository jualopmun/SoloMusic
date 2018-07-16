<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="isAnonymous()">

	<%-- Stored message variables --%>
	<spring:message code="actor.save" var="save" />
	
	<form:form id="form" action="${requestURI}" modelAttribute="arf">
	
		<acme:textbox code="actor.userAccount.username" path="username"/>
		<br/>
		<acme:password code="actor.userAccount.password" path="password"/>
		<br/>
		<acme:password code="actor.repeatPassword" path="repeatPassword"/>
		<br/>
		<acme:textbox code="actor.name" path="name"/>
		<br/>
		<acme:textbox code="actor.surname" path="surname"/>
		<br/>
		<acme:textbox code="actor.email" path="email"/>
		<br/>
		<acme:textbox code="actor.birthDate" path="birthDate"/>
		<br/>
	
		<form:label path="acceptedTerms" >
			<spring:message code="actor.terms.text" />
		</form:label>
		<a href="welcome/terms.do" target="_blank"><spring:message code="actor.terms.link" /></a>
		<form:checkbox path="acceptedTerms" required="required"/>
		<form:errors path="acceptedTerms" cssClass="error" />
		<br/>
		
		<input type="submit" name="save" value="${save}"/>&nbsp;
		<acme:cancel code="actor.cancel" url="welcome/index.do"/>
	
	</form:form>
</security:authorize>