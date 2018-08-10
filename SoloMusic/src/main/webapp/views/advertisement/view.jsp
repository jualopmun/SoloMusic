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

<security:authorize access="permitAll">

	<div style="width:20%; margin: auto;">
		<spring:message code="event.location" var="location"/>
		<table>
			<tr>
				<td><img style="max-width: 80px; max-height: 80px;" src="advertisement/view/image.do?q=${advertisement.id}"/></td>
				
				<td><jstl:out value="${advertisement.title}" /></td>
			</tr>
			<tr>
				<td><spring:message code="advertisement.description" /></td>
				<td><jstl:out value="${advertisement.description}" /></td>
			</tr>
			<tr>
				<tr >
					<td><spring:message code="event.locationUrl" /></td>
					<td><a href='advertisement/user/location.do?p=${advertisement.id}'><jstl:out value="${location}" /></a></td>
				</tr>
			</tr>
			<tr>
				<td><spring:message code="advertisement.price" /></td>
				<td><jstl:out value="${advertisement.price}" /></td>
			</tr>
		</table>
		
		<%-- Registro --%>
		<security:authorize access="hasRole('USER')">
			<jstl:if test="${isOwner eq false}">
				<tr>
					<td>
						<jstl:choose>
							<jstl:when test="${isRegistered eq false}">
								<spring:url var="registerUrl" value="/advertisement/register.do">
									<spring:param name="q" value="${advertisement.id}" />
								</spring:url>
								<spring:message code="advertisement.register" var="registerMsg" />
							</jstl:when>
		
							<jstl:otherwise>
								<spring:url var="registerUrl" value="/advertisement/unregister.do">
									<spring:param name="q" value="${advertisement.id}" />
								</spring:url>
								<spring:message code="advertisement.unregister" var="registerMsg" />
							</jstl:otherwise>
						</jstl:choose>
						<input style="margin-top: 2%;" onclick="window.location='${registerUrl}'"
							class="btn btn-danger" type="button" value="${registerMsg}" />
					</td>
					<td>
						<br />
						<br />
					</td>
				</tr>
			</jstl:if>
		</security:authorize>
	</div>
</security:authorize>