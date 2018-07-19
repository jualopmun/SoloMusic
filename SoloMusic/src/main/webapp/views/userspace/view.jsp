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
<spring:message code="actor.new" var="actorNew"/>
<input onclick="window.location='userspace/user/create.do'" class="btn btn-warning" type="button"  value="${actorNew}"/>
</jstl:if>

<jstl:if test="${p!=null and actor.userSpace==p}">
<spring:message code="actor.edit" var="actorEdit"/>
<input onclick="window.location='userspace/user/edit.do'" class="btn btn-warning" type="button"  value="${actorEdit}"/>
</jstl:if>
<br/>





</security:authorize>
<!-- Ver los videos de youtube -->
<spring:message code="actor.video" var="actorVideo"/>
<input onclick="window.location='event/user/view.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorVideo}"/>
<br/>
<br/>
<tr>
		<td>
			<spring:message code="userspace.title" />
		</td>
		
		
			<jstl:out value="${p.title}"/>
		<td>
		<br/>
		<br/>	
			
		
		</td>
	</tr>
	
	<tr>
		<td>
			<spring:message code="userspace.description" />
		</td>
		<jstl:out value="${p.description}"/>
		<td>
		<br/>	
			
			
		
		</td>
	</tr>
	
	<tr>
		<td>
			<spring:message code="userspace.profileimg" />
		</td>
		<img style="max-width: 80px; max-height: 80px;" src="<jstl:out value="${p.profileImg}"/>">
		<td>
		<br/>
			
			
		
		</td>
	</tr>

	<tr>
		<td>
			<spring:message code="userspace.contact" />
					<jstl:out value="${p.contact}"/>
		</td>
		<td>
		<br/>

		
		<br />
		</td>
		
	</tr>
	
	<tr>
		<td>
			<jstl:choose>
				<jstl:when test="${followed eq false}">
					<spring:url var="followUrl" value="/actor/follow.do">
						<spring:param name="varId" value="${p.id}" />
					</spring:url>
					<spring:message code="userspace.follow" var="followMsg"/>
				</jstl:when>
				
				<jstl:otherwise>
					<spring:url var="followUrl" value="/actor/unfollow.do">
						<spring:param name="varId" value="${p.id}" />
					</spring:url>
					<spring:message code="userspace.unfollow" var="followMsg"/>
				</jstl:otherwise>
			</jstl:choose>

			<a href="${followUrl}"><jstl:out value="${followMsg}"/></a>
		</td>
		<td>
		<br/>

		
		<br />
		</td>
		
	</tr>
