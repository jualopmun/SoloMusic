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
	<br />

</security:authorize>

<jstl:if test="${p!=null}">
<security:authorize access="hasRole('USER')">

	<jstl:if test="${isPrincipal eq false}">
		<tr>
			<td><jstl:choose>
					<jstl:when test="${followed eq false}">
						<spring:url var="followUrl" value="/actor/follow.do">
							<spring:param name="q" value="${a.id}" />
						</spring:url>
						<spring:message code="userspace.follow" var="followMsg" />
					</jstl:when>

					<jstl:otherwise>
						<spring:url var="followUrl" value="/actor/unfollow.do">
							<spring:param name="q" value="${a.id}" />
						</spring:url>
						<spring:message code="userspace.unfollow" var="followMsg" />
					</jstl:otherwise>
				</jstl:choose> <input onclick="window.location='${followUrl}'"
				class="btn btn-warning" type="button" value="${followMsg}" /></td>
		</tr>
		<br />
		<br />
	</jstl:if>
</security:authorize>

<%-- Ver los eventos --%>
<jstl:if test="${!p.events.isEmpty() or p!=null}">
	<spring:message code="actor.video" var="actorVideo" />

	<input onclick="window.location='event/user/view.do?p=${p.id}'"
		class="btn btn-warning" type="button" value="${actorVideo}" />
</jstl:if>
<%-- Ver las actuaciones --%>
<jstl:if test="${!p.perfomances.isEmpty() or p!=null}">
	<spring:message code="actor.perfomance" var="actorPerfomance" />

	<input onclick="window.location='perfomance/user/view.do?p=${p.id}'"
		class="btn btn-warning" type="button" value="${actorPerfomance}" />
</jstl:if>
<br />
<br />

<%-- PLAYLIST Y TRACK --%>




<table style="width: 75%;">
	<tr>
		<td>
			<h2>
				<spring:message code="userspace.info" />
			</h2> <br /> <br />
			<table style="width: 100%;">
				<tr>
					<td><spring:message code="userspace.title" /></td>
					<td><jstl:out value="${p.title}" /></td>
				</tr>
				<tr>
					<td><spring:message code="userspace.description" /></td>
					<td><jstl:out value="${p.description}" /></td>
				</tr>

				<tr>
					<td><spring:message code="userspace.profileimg" /></td>
					<td><img style="max-width: 80px; max-height: 80px;"
						src="<jstl:out value="${p.profileImg}"/>"></td>
				</tr>
				<tr>
					<td><spring:message code="userspace.contact" /></td>
					<td><jstl:out value="${p.contact}" /></td>
				</tr>
			</table>
		</td>
		<%-- DONACIONES --%>
		<td>
			<h2>
				<spring:message code="donantions.titulo" />
			</h2> <%-- Crear donaciones --%> <security:authorize
				access="hasRole('USER')">
				<jstl:if test="${p!=null and actor.userSpace==p}">
					<spring:message code="actor.new" var="actorNew" />
					<input onclick="window.location='donation/user/create.do'"
						class="btn btn-warning" type="button" value="${actorNew}" />
				</jstl:if>
				<br />
				<br />
			</security:authorize> <jstl:forEach var="dona" items="${p.donations}">
				<table style="width: 100%;">
					<tr>
						<td><spring:message code="dona.title" /></td>
						<td><jstl:out value="${dona.title}" /></td>
					<tr>
					<tr>
						<td><spring:message code="dona.description" /></td>
						<td><jstl:out value="${dona.description}" /></td>
					</tr>
					<tr>
						<td><spring:message code="dona.price" /></td>
						<td><jstl:out value="${dona.price}" /> Euros</td>

						<%-- Editar donacion o borrarlo 
						<security:authorize access="hasRole('USER')">
							<td><jstl:if test="${actor.userSpace==p}">
									<spring:message code="actor.edit" var="actorEdit" />
									<input
										onclick="window.location='donation/user/edit.do?q=${dona.id}'"
										class="btn btn-warning" type="button" value="${actorEdit}" />
								</jstl:if> <jstl:if test="${actor.userSpace==p}">
									<spring:message code="actor.delete" var="actorDelete" />
									<input
										onclick="window.location='donation/user/delete.do?q=${dona.id}'"
										class="btn btn-warning" type="button" value="${actorDelete}" />
									<br />
									<br />
								</jstl:if></td>
						</security:authorize>--%>
				</table>
				<br />
			</jstl:forEach>
		</td>
	<tr>
</table>

<h2>
	<spring:message code="playlist"></spring:message>
</h2>

<jstl:if test="${actor.userSpace==p}">
	<spring:message code="actor.new" var="actorNew" />
	<input onclick="window.location='playlist/user/create.do'"
		class="btn btn-warning" type="button" value="${actorNew}" />

</jstl:if>

<table>
	<jstl:forEach var="play" items="${p.playLists}">
		<tr>
			<td><spring:message code="play.title" /></td>
			<td><jstl:out value="${play.title}" /></td>
		<tr>
		<tr>
			<td><spring:message code="dona.description" /></td>
			<td><jstl:out value="${play.description}" /></td>
		</tr>
		<br/>
		<br/>
		<jstl:if test="${actor.userSpace==p}">
			<tr>

				<td><spring:message code="actor.delete" var="actorDelete" /> <input
					onclick="window.location='playlist/user/delete.do?q=${play.id}'"
					class="btn btn-warning" type="button" value="${actorDelete}" /></td>

			</tr>
		</jstl:if>
		
		<tr>
			<td>
				<h2>
					<spring:message code="play.track" />
				</h2>
			</td>
		</tr>
		<jstl:forEach var="track" items="${play.tracks}">
			<tr>
				<td><spring:message code="dona.title" /></td>
				<td><jstl:out value="${track.title}" /></td>
			</tr>

			<tr>
				<td><spring:message code="track.duration" /></td>
				<td><audio controls controlsList="nodownload">
						<source src="userspace/user/play.do?q=${track.id}"
							type="audio/mp3" onmouseout="true">
						Your browser does not support the audio element.
					</audio></td>
			</tr>
			<jstl:if test="${actor.userSpace==p}">
			<tr>
			
			<td><spring:message code="actor.delete.track"
					var="actorDeleteTrack" /> <input
				onclick="window.location='track/user/delete.do?q=${track.id}'"
				class="btn btn-warning" type="button" value="${actorDeleteTrack}" />
			</td>
			
		</jstl:if>
	
		</jstl:forEach>
		<jstl:if test="${actor.userSpace==p}">
			<tr>
			<td><spring:message code="actor.new.track" var="actorNewTrack" />
				<input onclick="window.location='track/user/create.do?q=${play.id}'"
				class="btn btn-warning" type="button" value="${actorNewTrack}" /></td>
				</tr>
				
		</jstl:if>
			<br />
		<br />
	</jstl:forEach>
	

</table>
</jstl:if>