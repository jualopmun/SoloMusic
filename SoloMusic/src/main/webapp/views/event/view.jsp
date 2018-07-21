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
</jstl:if>
</security:authorize>



<br />

<jstl:forEach var="p" items="${event}">

	<tr>
		<td><spring:message code="event.title" /></td>
		<td><jstl:out value="${p.title}" /></td>
	</tr>
	<br />

	<tr>
		<td><spring:message code="event.description" /></td>
		<td><jstl:out value="${p.description}" /></td>
	</tr>
	<br />

	<tr>
		<td><spring:message code="event.locationUrl" /></td>
		<td><a href="<jstl:out value="${p.locationUrl}" />"><jstl:out value="${p.locationUrl}" /></a></td>
	</tr>
	<br />
	<tr>
		<td><spring:message code="event.startDate" /></td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.startDate}"/>

		  <br /></td>

	</tr>
	<br />
<security:authorize access="hasRole('USER')">
<jstl:if test="${actor.userSpace==userSpace}">
<spring:message code="event.delete" var="actorDelete"/>
<input onclick="window.location='event/user/delete.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorDelete}"/>
<br/>
<br/>
<spring:message code="event.edit" var="actorEdit"/>
<input onclick="window.location='event/user/edit.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorEdit}"/>
<br/>
</jstl:if>
</security:authorize>


</jstl:forEach>





