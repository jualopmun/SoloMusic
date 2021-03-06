 <%--
 * login.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<div class="container">
	<div style="width: 300%; margin: auto; display: inline-block;">
		<form:form action="j_spring_security_check" modelAttribute="credentials">
			<%--  
			<form:label path="username">
				<spring:message code="security.username" />
			</form:label>
			<form:input path="username" />	
			<form:errors class="error" path="username" />
			<br />
		
			<form:label path="password">
				<spring:message code="security.password" />
			</form:label>
			<form:password path="password" />	
			<form:errors class="error" path="password" />
			<br />
		--%>
			<jstl:if test="${showError == true}">
				<acme:errors code="security.login.failed" />
			</jstl:if>
			<acme:inputText code="security.username" path="username"
				placeholder="security.username" />
			<acme:inputPassword code="security.password" path="password"
				placeholder="security.password" />
		
			<br />
			
			<input class="btn btn-danger" style="width: 25%" type="submit"
				value="<spring:message code="security.login" />" />
		
		</form:form>
	</div>
</div>