<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
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


<div style="width:70%; overflow-x:auto; margin: auto;">
	<display:table style="border-collapse:inherit;" keepStatus="true" name="actors"
		requestURI="${requestURI}" id="row" class="table table-hover" pagesize="12">
		<spring:message code="actor.name" var="name" />
		<spring:message code="actor.followers" var="followers" />
		<spring:message code="actor.followeds" var="followeds" />
		
		<display:column sortable="false">
		
		<jstl:if test="${fn:length(row.userSpace.profileImg)>0}">
			<img style="max-width: 80px; max-height: 80px;"	src="userspace/view/image.do?q=${row.userSpace.id}">
			</jstl:if>
		</display:column>
		
		<display:column property="name" title="${name} ${surname}" sortable="false" />
		
		<display:column sortable="false" title="${followers}">
		
		<jstl:out value="${row.followers.size()}"></jstl:out>
		</display:column>
		
		<display:column sortable="false" title="${followeds}">
		<jstl:out value="${row.followeds.size()}"></jstl:out>
		
		</display:column>
		
		
		<spring:url var="userSpaceUrl" value="/userspace/user/spaceview.do">
			<spring:param name="q" value="${row.userSpace.id}" />
		</spring:url>
		<spring:message code="actor.userSpace" var="userSpaceMsg" />
		<display:column sortable="false">
			<jstl:if test="${row.userSpace!=null}">
			<input onclick="window.location='${userSpaceUrl}'"	class="btn btn-danger" type="button" value="${userSpaceMsg}" />
			</jstl:if>
			<jstl:if test="${row.userSpace==null}">
			
			<p><spring:message code="actor.noUserSpace"></spring:message></p>
			
			</jstl:if>
		</display:column>
		
	</display:table>
</div>

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