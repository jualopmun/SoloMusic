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
	<spring:message code="actor.cancel" var="cancel" />
	
	<form:form id="form" action="${requestURI}" modelAttribute="actor" >
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="userAccount.authorities" />
		<form:hidden path="followers" />
		<form:hidden path="followeds" />
		<form:hidden path="ownerAdvertisement" />
		<form:hidden path="registersAdvertisement" />
		<form:hidden path="folders" />
		<form:hidden path="userAccount.banned" />
		

		<acme:textbox2 code="actor.userAccount.username" path="userAccount.username" />
		<br/>
		<acme:password2 code="actor.userAccount.password" path="userAccount.password" />
		<br/>
		<acme:textbox2 code="actor.name" path="name" />
		<br/>
		<acme:textbox2 code="actor.surname" path="surname" />
		<br/>
		<acme:textbox2 code="actor.email" path="email" />
		
		<br/>
		<div class="form-group" style="width: 55%;">
		<label> <spring:message code="actor.birthDate" />
			</label><br /> <input class="form-control"
				type="text" name="birthDate" placeholder="dd/MM/yyyy" />
			<form:errors cssClass="error" path="birthDate" />
	</div>
		<spring:message code="actor.terms.text" />
		<a href="welcome/terms.do" target="_blank"><spring:message code="actor.terms.link" /></a>
		<input type="checkbox" required="required"/>
		<br/>
		
		<input class="btn btn-warning" type="submit" name="save" value="${save}"/>&nbsp;
		<input onclick="window.location='welcome/index.do'"
			class="btn btn-warning" type="button" value="${cancel}" />
	
	</form:form>
</security:authorize>