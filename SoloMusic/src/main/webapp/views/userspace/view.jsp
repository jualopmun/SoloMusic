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


<security:authorize access="hasRole('USER')">
	<jstl:if test="${p==null and actor.userSpace==p}">
		<spring:message code="actor.new" var="actorNew" />
		<input onclick="window.location='userspace/user/create.do'"
			class="btn btn-warning" type="button" value="${actorNew}" />
	</jstl:if>

	<jstl:if test="${p!=null and actor.userSpace==p}">
		<spring:message code="actor.edit" var="actorEdit" />
		<input onclick="window.location='userspace/user/edit.do'"
			class="btn btn-warning" type="button" value="${actorEdit}" />
	</jstl:if>
	<br />





</security:authorize>
<!-- Ver los eventos -->
<spring:message code="actor.video" var="actorVideo" />
<input onclick="window.location='event/user/view.do?p=${p.id}'"
	class="btn btn-warning" type="button" value="${actorVideo}" />
<br />
<br />
<!-- Ver las actuaciones -->

<spring:message code="actor.perfomance" var="actorPerfomance" />
<input onclick="window.location='perfomance/user/view.do?p=${p.id}'"
	class="btn btn-warning" type="button" value="${actorPerfomance}" />
<br />
<br />
<tr>
	<td><spring:message code="userspace.title" /></td>


	<jstl:out value="${p.title}" />
	<td><br /> <br /></td>
</tr>

<tr>
	<td><spring:message code="userspace.description" /></td>
	<jstl:out value="${p.description}" />
	<td><br /></td>
</tr>

<tr>
	<td><spring:message code="userspace.profileimg" /></td>
	<img style="max-width: 80px; max-height: 80px;"
		src="<jstl:out value="${p.profileImg}"/>">
	<td><br /></td>
</tr>

<tr>
	<td><spring:message code="userspace.contact" /> <jstl:out
			value="${p.contact}" /></td>
	<td><br /> <br /></td>

</tr>

<tr>
	<td><jstl:choose>
			<jstl:when test="${followed eq false}">
				<spring:url var="followUrl" value="/actor/follow.do">
					<spring:param name="varId" value="${p.id}" />
				</spring:url>
				<spring:message code="userspace.follow" var="followMsg" />
			</jstl:when>

			<jstl:otherwise>
				<spring:url var="followUrl" value="/actor/unfollow.do">
					<spring:param name="varId" value="${p.id}" />
				</spring:url>
				<spring:message code="userspace.unfollow" var="followMsg" />
			</jstl:otherwise>
		</jstl:choose> <a href="${followUrl}"><jstl:out value="${followMsg}" /></a></td>
	<td><br /> <br /></td>

</tr>
<br />
<br />

<h1>
	<spring:message code="donantions.titulo" />
</h1>
<br />
<br />

<!-- Crear donaciones -->
<security:authorize access="hasRole('USER')">
	<jstl:if test="${actor.userSpace==p}">
		<spring:message code="actor.new" var="actorNew" />
		<input onclick="window.location='donation/user/create.do'"
			class="btn btn-warning" type="button" value="${actorNew}" />
	</jstl:if>
	<br />
	<br />
</security:authorize>


<jstl:forEach var="dona" items="${p.donations}">


	<tr>
		<td><spring:message code="dona.title" /></td>
		<jstl:out value="${dona.title}" />
		<td><br />
	<tr>
		<td><spring:message code="dona.description" /></td>
		<jstl:out value="${dona.description}" />
		<td><br />
	<tr>
		<td><spring:message code="dona.price" /></td>
		<jstl:out value="${dona.price}" /> Euros
		 <!-- Editar donacion o borrarlo -->
		<td><br /> <br /> <security:authorize access="hasRole('USER')">
				<jstl:if test="${actor.userSpace==p}">
					<spring:message code="actor.edit" var="actorEdit" />
					<input onclick="window.location='donation/user/edit.do?q=${dona.id}'"
						class="btn btn-warning" type="button" value="${actorEdit}" />

					<br />
					<br />
				</jstl:if>
				
				<jstl:if test="${actor.userSpace==p}">
					<spring:message code="actor.delete" var="actorDelete" />
					<input onclick="window.location='donation/user/delete.do?q=${dona.id}'"
						class="btn btn-warning" type="button" value="${actorDelete}" />

					<br />
					<br />
				</jstl:if>
				<br />
				<br />
			</security:authorize>
</jstl:forEach>

