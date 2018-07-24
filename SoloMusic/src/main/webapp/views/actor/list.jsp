<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table keepStatus="true" name="actors"
	requestURI="${requestURI}" id="row" class="table table-over" pagesize="12">
	
	<display:column title="${userSpace.profileImg}" sortable="false">
		<img style="max-width: 80px; max-height: 80px;"	src="<jstl:out value="${row.userSpace.profileImg}"/>">
	</display:column>
	
	<display:column property="name" title="${name} ${surname}" sortable="false" />
	
	<jstl:choose>
		<jstl:when test="${varid == 'followers'}">
			<spring:url var="followUrl" value="/actor/follow.do">
				<spring:param name="q" value="${actor.id}" />
			</spring:url>
			<spring:message code="userspace.follow" var="followMsg" />
		</jstl:when>

		<jstl:otherwise>
			<spring:url var="followUrl" value="/actor/unfollow.do">
				<spring:param name="q" value="${actor.id}" />
			</spring:url>
			<spring:message code="userspace.unfollow" var="followMsg" />
		</jstl:otherwise>
	</jstl:choose>

	<display:column sortable="false">
		<input onclick="window.location='${followUrl}'"	class="btn btn-warning" type="button" value="${followMsg}" />
	</display:column>
	
</display:table>

<!-- 
<table>
	<jstl:forEach var="actor" items="actors">
		<tr>
			<td>
				<img style="max-width: 80px; max-height: 80px;" src="<jstl:out value="${actor.userSpace.profileImg}"/>">
			</td>
			<td>
				<jstl:out value="${actor.name} ${actor.surname}"/>
			</td>
			<jstl:choose>
					<jstl:when test="${varid == 'followers'}">
						<spring:url var="followUrl" value="/actor/follow.do">
							<spring:param name="q" value="${actor.id}" />
						</spring:url>
						<spring:message code="userspace.follow" var="followMsg" />
					</jstl:when>

					<jstl:otherwise>
						<spring:url var="followUrl" value="/actor/unfollow.do">
							<spring:param name="q" value="${actor.id}" />
						</spring:url>
						<spring:message code="userspace.unfollow" var="followMsg" />
					</jstl:otherwise>
				</jstl:choose>
			<td>
				<input onclick="window.location='${followUrl}'"
					class="btn btn-warning" type="button" value="${followMsg}" />
			</td>
		</tr>
	</jstl:forEach>
</table>
 -->
