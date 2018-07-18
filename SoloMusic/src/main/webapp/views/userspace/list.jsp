<%--
 * index.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>







<display:table  keepStatus="true" name="userspace"
	requestURI="${requestURI}" id="row" class="table table-over" pagesize="12">
	<spring:message code="userspace.profileimg" var="profileImg"/>
	<display:column title="${profileImg}" sortable="false">
		<img style="max-width: 80px; max-height: 80px;"
			src="<jstl:out value="${row.profileImg}"/>">
	</display:column>
	
	
	<spring:message code="userspace.title" var="title"/>
	<display:column property="title" title="${title}"
		sortable="false" />
	
	<spring:message code="userspace.description" var="description"/>
	<display:column property="description" title="${description}"
		sortable="false" />
	
	<spring:message code="userspace.contact" var="contact"/>		
	<display:column property="contact" title="${contact}"
		sortable="false" />
   <spring:message code="userspace.view" var="view"/>
   <spring:message code="userspace.viewer" var="viewer"/>	
   <display:column title="${view}" sortable="false">
			
				<a href="userspace/user/spaceview.do?q=${row.id}"><jstl:out value="${viewer}" /></a>
			
		</display:column>

</display:table>




