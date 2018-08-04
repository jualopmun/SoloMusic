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

<security:authorize access="hasRole('USER')">

	<spring:message code="advertisement.create" var="create" />
	<spring:message code="advertisement.owned" var="owned" />
	<spring:message code="advertisement.registered" var="registered" />
	<spring:message code="advertisement.premium" var="mustPremium" />
	<spring:message code="actor.dobepremium" var="dopremium" />
	<jstl:if test="${actor.isPremium==true }">
	<input onclick="window.location='advertisement/create.do'"
		class="btn btn-warning" type="button" value="${create}" />
   </jstl:if>
  
   <jstl:if test="${actor.isPremium==false }">
   <input onclick="window.location='actor/premium.do'"
		class="btn btn-warning" type="button" value="${dopremium}" />
	
   </jstl:if>
	<br/>
	<br/>
			
	<jstl:if test="${requestURI != 'advertisement/list.do'}">
		<a href="advertisement/user/list.do?q=0"><jstl:out value="${owned}"/></a>&nbsp
		<a href="advertisement/user/list.do?q=1"><jstl:out value="${registered}"/></a>
		<br/>
		<br/>
	</jstl:if>
	
</security:authorize>


<security:authorize access="permitAll">

	<spring:message code="advertisement.title" var="title" />
	<spring:message code="advertisement.price" var="price" />
	<spring:message code="advertisement.view" var="view" />
	<spring:message code="advertisement.edit" var="edit" />
	<spring:message code="userspace.profileimg" var="editImage" />
	
	<display:table  keepStatus="true" name="advertisements"
		requestURI="${requestURI}" id="row" class="table table-over" pagesize="12">
		
		<display:column sortable="false">
			<img style="max-width: 80px; max-height: 80px;" src="advertisement/view/image.do?q=${row.id}" />
		</display:column>
		
		<display:column property="title" title="${title}" sortable="false" />
		
		<display:column property="price" title="${price}" sortable="false" />
		
	  	<display:column sortable="false">
			<a href="advertisement/view.do?q=${row.id}"><jstl:out value="${view}" /></a>
		</display:column>
		
		<spring:url var="registeredUrl" value="/actor/advertisement/list.do" >
			<spring:param name="q" value="${row.id}" />
		</spring:url>
		<spring:message code="advertisement.registered" var="registered" />
		<display:column sortable="false">
			<input onclick="window.location='${registeredUrl}'"	class="btn btn-warning" type="button" value="${registered}" />
		</display:column>
		
		<jstl:if test="${requestURI == 'advertisement/user/list.do?q=0'}">
		<jstl:if test="${actor.isPremium==true}">
			<display:column sortable="false">
				<a href="advertisement/edit.do?q=${row.id}"><jstl:out value="${edit}" /></a>
			</display:column>
			<display:column sortable="false">
				<a href="advertisement/image/upload.do?q=${row.id}"><jstl:out value="${editImage}" /></a>
			</display:column>
			</jstl:if>
			<jstl:if test="${actor.isPremium==false}">
			<display:column sortable="false">
				<jstl:out value="${mustPremium}" />
			</display:column>
			</jstl:if>
		</jstl:if>
		
	</display:table>

</security:authorize>