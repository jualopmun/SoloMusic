<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div style="width:70%; margin: auto;">

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