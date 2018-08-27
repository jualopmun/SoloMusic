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

select{
    width:197px;
    border:1px solid #ccc;
}
input{
    width:195px;
    border:1px solid #ccc;
}

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

.pagelinks {
    display: yes;
}

</style>
</head>
<div style="width:70%; margin: auto;">

	
	<div style="overflow-x:auto;">
	<display:table style="border-collapse:inherit;" keepStatus="true" name="userspace"
		requestURI="${requestURI}" id="row" class="table table-hover">
		<spring:message code="userspace.profileimg" var="profileImg"/>
		
		<display:column title="${profileImg}" sortable="false">
		<jstl:if test="${fn:length(row.profileImg)>0}">
		
			<img style="max-width: 80px; max-height: 80px;" src="userspace/view/image.do?q=${row.id}"/>
			</jstl:if>
		</display:column>
		
		<spring:message code="userspace.title" var="title"/>
		<display:column property="title" title="${title}"
			sortable="false" />
		
		<spring:message code="userspace.description" var="description"/>
		<display:column property="description" title="${description}"
			sortable="false" />
			
		<spring:message code="userspace.genre" var="genre"/>
		<display:column property="genre.genre" title="${genre}"
			sortable="false" />
		
		<spring:message code="userspace.contact" var="contact"/>		
		<display:column property="contact" title="${contact}"
			sortable="false" />
	   <spring:message code="userspace.viewer" var="viewer"/>	
	   
		<spring:url var="userSpaceUrl" value="/userspace/user/spaceview.do">
			<spring:param name="q" value="${row.id}" />
		</spring:url>
		<spring:message code="userspace.view" var="view" />
		<display:column sortable="false">
			<input onclick="window.location='${userSpaceUrl}'"	class="btn btn-danger" type="button" value="${view}" />
		</display:column>
	
	</display:table>
	</div>
</div>


<script>
var search="<spring:message code="userspace.search"/>";
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