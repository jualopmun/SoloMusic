<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div style="width:70%; margin: auto;">
	<display:table style="border-collapse:inherit;" keepStatus="true" name="actors"
		requestURI="${requestURI}" id="row" class="table table-over" pagesize="12">
		
		<display:column sortable="false">
		<jstl:if test="${row.userSpace.profileImg!=null}">
			<img style="max-width: 80px; max-height: 80px;"	src="userspace/view/image.do?q=${row.userSpace.id}">
			</jstl:if>
		</display:column>
		
		<display:column property="name" title="${name} ${surname}" sortable="false" />
		
		<spring:url var="userSpaceUrl" value="/userspace/user/spaceview.do">
			<spring:param name="q" value="${row.userSpace.id}" />
		</spring:url>
		<spring:message code="actor.userSpace" var="userSpaceMsg" />
		<display:column sortable="false">
			<input onclick="window.location='${userSpaceUrl}'"	class="btn btn-danger" type="button" value="${userSpaceMsg}" />
		</display:column>
		
		<jstl:if test="${requestURI == 'actor/list.do'}">
		
				<jstl:if test="${!principal.followeds.contains(row)}">
					<spring:url var="followUrl" value="/actor/follow.do">
						<spring:param name="q" value="${row.id}" />
					</spring:url>
					<spring:message code="userspace.follow" var="followMsg" />
				</jstl:if>
		
			
				<jstl:if test="${principal.followeds.contains(row)}">
					<spring:url var="followUrl" value="/actor/unfollow.do">
						<spring:param name="q" value="${row.id}" />
					</spring:url>
					<spring:message code="userspace.unfollow" var="followMsg" />
					</jstl:if>
				
			
	
			<display:column sortable="false">
				<input onclick="window.location='${followUrl}'"	class="btn btn-danger" type="button" value="${followMsg}" />
			</display:column>
		</jstl:if>
		
	</display:table>
</div>