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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<security:authorize access="hasRole('USER')">
	<jstl:if test="${actor.userSpace==userSpace}">
		<spring:message code="event.new" var="actorNew"/>
		<input onclick="window.location='event/user/create.do'" class="btn btn-warning" type="button"  value="${actorNew}"/>
		<br />
		<br />
	</jstl:if>
</security:authorize>

<table>
	<jstl:forEach var="p" items="${event}">
		<tr>
			<td><spring:message code="event.title" /></td>
			<td><jstl:out value="${p.title}" /></td>
		</tr>
		<tr>
			<td><spring:message code="event.description" /></td>
			<td><jstl:out value="${p.description}" /></td>
		</tr>
		<tr>
			<td><spring:message code="event.locationUrl" /></td>
			<td><a href="<jstl:out value="${p.locationUrl}" />"><jstl:out value="${p.locationUrl}" /></a></td>
		</tr>
		<tr>
			<td><spring:message code="event.startDate" /></td>
			<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.startDate}"/></td>
		</tr>
		<tr>
			<td>
				<security:authorize access="hasRole('USER')">
					<jstl:if test="${actor.userSpace==userSpace}">
						<spring:message code="event.delete" var="actorDelete"/>
						<input onclick="window.location='event/user/delete.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorDelete}"/>
						<spring:message code="event.edit" var="actorEdit"/>
						<input onclick="window.location='event/user/edit.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorEdit}"/>
					</jstl:if>
				</security:authorize>
			</td>
		</tr>
	</jstl:forEach>
</table>
<br/>
<br/>

<spring:message code="event.back" var="back"/>
<input onclick="window.location='userspace/user/view.do?p=${actor.userSpace.id}'" class="btn btn-warning" type="button" name="cancel" value="${back}"/>