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


<div class="btn-group btn-group-xs">
	<button onclick="javascript:location.href='folder/actor/create.do'"
		type="button" class="btn btn-default">
		<spring:message code="folder.new" />
	</button>
	<button
		onclick="javascript:location.href='mailmessage/actor/create.do'"
		type="button" class="btn btn-default">
		<spring:message code="folder.message.new" />
	</button>
</div>
<table style="border-collapse:inherit;" class="table table-over">
	<thead>
		<tr>
			<th><spring:message code="folder.folderName" /></th>
			<th><spring:message code="folder.messages" /></th>
		</tr>
	</thead>
	<jstl:forEach items="${folder}" var="e">
		<tr>
			<td><a href="folder/actor/listFolder.do?folder=${e.id}">${e.folderName}</a></td>

			<!-- Si tiene subcarpetas, no se muestra cuantos mensajes tiene dicha carpeta -->
			<jstl:if test="${fn:length(e.folderChildren) == 0}">
				<td><span class="badge">${fn:length(e.messages)}</span></td>
			</jstl:if>
			<jstl:if test="${fn:length(e.folderChildren) != 0}">
				<td><span class="badge"></span></td>
			</jstl:if>
			<td><jstl:if
					test="${e.folderName ne 'Inbox' and e.folderName ne 'Outbox' and e.folderName ne 'Trashbox' and e.folderName ne 'Spambox'}">
					<a href="folder/actor/edit.do?folder=${e.id}"><spring:message
							code="acme.edit" /></a>
				</jstl:if></td>
			<td><jstl:if
					test="${e.folderName ne 'Inbox' and e.folderName ne 'Outbox' and e.folderName ne 'Trashbox' and e.folderName ne 'Spambox' and fn:length(e.folderChildren) == 0}">
					<a href="folder/actor/delete.do?folder=${e.id}"><spring:message
							code="acme.delete" /></a>
				</jstl:if></td>
		</tr>
	</jstl:forEach>
</table>


