<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div style="width:70%; margin: auto;">


	<table class="table table-hover">
		<thead>
			<tr scope="col"><spring:message code="notification.message" /> </tr>
		</thead>
		<tbody>
			<jstl:forEach items="${notifications}" var="notification">
				<jstl:if test="${notification.actor ne null}">
					<tr scope="row">
						<td>
							<spring:message code="notification.message.actor" /><jstl:out value="${notification.actor.name}" />
						</td>
					</tr>
				</jstl:if>
				<jstl:if test="${notification.advertisement ne null}">
					<tr scope="row">
						<td>
							<spring:message code="notification.message.advertisement" /><jstl:out value="${notification.advertisement.title}" />
						</td>
					</tr>
				</jstl:if>
			</jstl:forEach>
		</tbody>
	</table>

	
</div>