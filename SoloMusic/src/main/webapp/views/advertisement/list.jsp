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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
<style>

.table-hover {
    border-collapse:separate;
    border: solid #ccc 1px;
    border-radius: 25px;
    overflow: hidden
}
	
.table-hover thead {
	border: 1px solid;
	background-color: #c9302c;
	color: white;
}

.table-hover tbody tr:hover td, .table-hover tbody tr:hover th {
  background-color: #a6a6a6;
}
	
	</style>
	
</head>


<security:authorize access="hasRole('USER')">

	<div style="width:70%; margin: auto;">
		<spring:message code="advertisement.create" var="create" />
		<spring:message code="advertisement.owned" var="owned" />
		<spring:message code="advertisement.registered" var="registered" />
		<spring:message code="advertisement.premium" var="mustPremium" />
		<spring:message code="actor.dobepremium" var="dopremium" />
		<jstl:if test="${actor.isPremium==true }">
		<input onclick="window.location='advertisement/create.do'"
			class="btn btn-danger" type="button" value="${create}" />
	   </jstl:if>
	  
	   <jstl:if test="${actor.isPremium==false }">
	   <input onclick="window.location='actor/premium.do'"
			class="btn btn-danger" type="button" value="${dopremium}" />
		
	   </jstl:if>
		<br/>
		<br/>
				
		<jstl:if test="${requestURI != 'advertisement/list.do'}">
			<a href="advertisement/user/list.do?q=0"><jstl:out value="${owned}"/></a>&nbsp
			<a href="advertisement/user/list.do?q=1"><jstl:out value="${registered}"/></a>
			<br/>
			<br/>
		</jstl:if>
	</div>
</security:authorize>
	
<security:authorize access="permitAll">
	
	<div style="width:70%; margin: auto;">
	
		<spring:message code="advertisement.title" var="title" />
		<spring:message code="advertisement.price" var="price" />
		<spring:message code="advertisement.view" var="view" />
		<spring:message code="advertisement.edit" var="edit" />
		<spring:message code="advertisement.registered" var="registered" />
		<spring:message code="userspace.profileimg.upload" var="editImage" />
			
		<div style="overflow-x:auto;">
		<display:table style="border-collapse:inherit;" keepStatus="true" name="advertisements"
			requestURI="${requestURI}" id="row" class="table table-hover" pagesize="12">
			
			<display:column sortable="false">
			<jstl:if test="${fn:length(row.mainImg)>0}">
				<img style="max-width: 80px; max-height: 80px;" src="advertisement/view/image.do?q=${row.id}" alt="" />
				</jstl:if>
			</display:column>
			
			<display:column property="title" title="${title}" sortable="false" />
			
			<display:column property="price" title="${price}" sortable="false" />
			
		  	<display:column sortable="false">
		  	
			  	<spring:url var="viewUrl" value="/advertisement/view.do" >
					<spring:param name="q" value="${row.id}" />
				</spring:url>
		  		<input onclick="window.location='${viewUrl}'"	class="btn btn-danger" type="button" value="${view}" />
		  		
			</display:column>
			
			<display:column sortable="false">
				<jstl:if test="${actor.ownerAdvertisement.contains(row)}">
				<spring:url var="registeredUrl" value="/actor/advertisement/list.do" >
					<spring:param name="q" value="${row.id}" />
				</spring:url>
				<input onclick="window.location='${registeredUrl}'"	class="btn btn-danger" type="button" value="${registered}" />
				</jstl:if>
			</display:column>
			
			
			<jstl:if test="${requestURI == 'advertisement/user/list.do?q=0'}">
			<jstl:if test="${actor.isPremium==true}">
				<display:column sortable="false">
					<spring:url var="editUrl" value="/advertisement/edit.do" >
						<spring:param name="q" value="${row.id}" />
					</spring:url>
			  		<input onclick="window.location='${editUrl}'"	class="btn btn-danger" type="button" value="${edit}" />
				</display:column>
				<display:column sortable="false">
				
					<spring:url var="editImageUrl" value="/advertisement/image/upload.do?q=${row.id}" >
						
					</spring:url>
					
			  		<input onclick="window.location='${editImageUrl}'"	class="btn btn-danger" type="button" value="${editImage}" />
				</display:column>
				</jstl:if>
				<jstl:if test="${actor.isPremium==false}">
				<display:column sortable="false">
					<jstl:out value="${mustPremium}" />
				</display:column>
				</jstl:if>
			</jstl:if>
			
		</display:table>
		</div>
	</div>
</security:authorize>


<script>
var search="<spring:message code="advertisement.search"/>";
var show="<spring:message code="advertisement.show"/>";

$(document).ready(function() {
    $('#row').DataTable({
    	  "language": {
    		    "paginate": { "previous": "&lt", "next": "&gt"},
    		    "lengthMenu": show+" _MENU_",
    		    "search": search+" "
    		  }
    		}
    );
} );

</script>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>