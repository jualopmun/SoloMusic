<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

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


<div style="width:60%; margin: auto;">

	<spring:message code="notificacion.view" var="viewNot" />
	<spring:message code="notificacion.view.view" var="viewView" />
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col" colspan="2">
					<spring:message code="notification.message" />
				</th>
			 </tr>
		</thead>
		<tbody>
			<jstl:forEach items="${notifications}" var="notification">
				<jstl:if test="${notification.actor ne null}">
					<tr>
						<td>
							<spring:message code="notification.message.actor" /><jstl:out value="${notification.actor.name}" />
						</td>
						<td>
						<input onclick="window.location='notification/view.do?q=${notification.id}'"
							class="btn btn-success" type="button" value="${viewNot}" />
						<input onclick="window.location='userspace/user/spaceview.do?q=${notification.actor.userSpace.id}'"
							class="btn btn-info" type="button" value="${viewView}" />
						</td>
					</tr>
				</jstl:if>
				<jstl:if test="${notification.advertisement ne null}">
					<tr>
						<td>
							<spring:message code="notification.message.advertisement" /><jstl:out value="${notification.advertisement.title}" />
						</td>
						<td>
							<input onclick="window.location='notification/view.do?q=${notification.id}'"
								class="btn btn-success" type="button" value="${viewNot}" />
							<input onclick="window.location='actor/advertisement/list.do?q=${notification.advertisement.id}'"
								class="btn btn-info" type="button" value="${viewView}" />
						</td>
					</tr>
				</jstl:if>
			</jstl:forEach>
		</tbody>
	</table>
	
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