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
<%@ page trimDirectiveWhitespaces="true" %>


<div style="width:70%; margin: auto;">
<div class="container-fluid">

<security:authorize access="hasRole('USER')">
	<jstl:if test="${p==null and actor.userSpace==p}">
		<spring:message code="actor.new" var="actorNew" />
		<input onclick="window.location='userspace/user/create.do'"
			class="btn btn-danger" type="button" value="${actorNew}" />
	</jstl:if>
	<jstl:if test="${p!=null and actor.userSpace==p}">
		<spring:message code="actor.edit" var="actorEdit" />
		<input onclick="window.location='userspace/user/edit.do'"
			class="btn btn-danger" type="button" value="${actorEdit}" />
	</jstl:if>
</security:authorize>

<!-- NO BORRAR AUNQUE ESTE EN AMARILLO -->
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
				class="btn btn-danger" type="button" value="${followMsg}" /></td>
		</tr>
		<br />
		<br />
	</jstl:if>
</security:authorize>

<%-- Ver los eventos --%>
<jstl:if test="${!p.events.isEmpty() or p!=null}">
	<spring:message code="actor.video" var="actorVideo" />

	<input onclick="window.location='event/user/view.do?p=${p.id}'"
		class="btn btn-danger" type="button" value="${actorVideo}" />
</jstl:if>

<%-- Ver las actuaciones --%>
<jstl:if test="${!p.perfomances.isEmpty() or p!=null}">
	<spring:message code="actor.perfomance" var="actorPerfomance" />

	<input onclick="window.location='perfomance/user/view.do?p=${p.id}'"
		class="btn btn-danger" type="button" value="${actorPerfomance}" />
</jstl:if>
<br />
<br />
</div>

<%-- PLAYLIST Y TRACK --%>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
		<table style="width: 100%; border-collapse:inherit;">
			<tr>
				<td>
					<h2>
						<spring:message code="userspace.info" />
					</h2> <br /> <br />
					<table style="width: 100%; border-collapse:inherit;">
						<tr>
							<td><spring:message code="userspace.title" /></td>
							<td><jstl:out value="${p.title}" /></td>
						</tr>
						<tr>
							<td><spring:message code="userspace.description" /></td>
							<td><jstl:out value="${p.description}" /></td>
						</tr>
		
						<tr>
						<spring:message code="userspace.upload.archive" var="actoUpload"></spring:message>
						
							<td><spring:message code="userspace.profileimg" /></td>
							<td><img style="max-width: 80px; max-height: 80px;"
								src="userspace/view/image.do?q=${p.id}"></td>
									<jstl:if test="${actor.userSpace==p}">	
						    <td style="width: 20%;"><input onclick="window.location='userspace/dowload/upload.do'"
						class="btn btn-danger" type="button" value="${actoUpload}" /></td>
						</jstl:if>
						</tr>
						<tr>
							<td><spring:message code="userspace.contact" /></td>
							<td><jstl:out value="${p.contact}" /></td>
						</tr>
					</table>
				</td>
			<tr>
		</table>
		<br/>
				
		<h2>
			<spring:message code="playlist"></spring:message>
		</h2>
		<br/>
		
		<jstl:if test="${actor.userSpace==p}">
			<spring:message code="actor.new" var="actorNew" />
			<input onclick="window.location='playlist/user/create.do'"
				class="btn btn-danger" type="button" value="${actorNew}" />
		</jstl:if>
		
		<jstl:forEach var="play" items="${p.playLists}">
			<table style="border-collapse:inherit;">
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
						<td>
							<spring:message code="actor.delete" var="actorDelete" />
							<input onclick="window.location='playlist/user/delete.do?q=${play.id}'"
								class="btn btn-danger" type="button" value="${actorDelete}" />
						</td>
					</tr>
				</jstl:if>
				
				<tr>
					<td><h2><spring:message code="play.track" /></h2></td>
				</tr>
				
				<jstl:forEach var="track" items="${play.tracks}">
					<tr>
						<td><spring:message code="dona.title" /></td>
						<td><jstl:out value="${track.title}" /></td>
					</tr>
					<tr>
						<td><spring:message code="track.duration" /></td>
						<td><audio controls preload="none"  >
								<source src="userspace/user/play.do?q=${track.id}"
									type='audio/mpeg;codec="mp3"' onmouseout="true" media="all" >
								Your browser does not support the audio element.
							</audio></td>
					</tr>
					<jstl:if test="${actor.userSpace==p}">
					<tr>
					
					<td>
						<input onclick="window.location='track/user/delete.do?q=${track.id}'"
							class="btn btn-danger" type="button" value="x" />
					</td>
					
				</jstl:if>
			
				</jstl:forEach>
				<jstl:if test="${actor.userSpace==p}">
					<tr>
					<td><spring:message code="actor.new.track" var="actorNewTrack" />
						<input onclick="window.location='track/user/create.do?q=${play.id}'"
						class="btn btn-danger" type="button" value="${actorNewTrack}" /></td>
						</tr>
						
				</jstl:if>
			</table>
		</jstl:forEach>
	</div>
	
	<%-- DONACIONES --%>
		<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
		<table style="width: 50%; border-collapse:inherit;">
			<tr>
				<td>
					<h2>
						<spring:message code="donantions.titulo" />
					</h2>
					<br/>
					
					<%-- Crear donaciones --%>
					<security:authorize	access="hasRole('USER')">
						<jstl:if test="${p!=null and actor.userSpace==p}">
							<spring:message code="actor.new" var="actorNew" />
							<input onclick="window.location='donation/user/create.do'"
								class="btn btn-danger" type="button" value="${actorNew}" />
						</jstl:if>
						<br />
					</security:authorize>
				</td>
			</tr>
			
			<jstl:forEach var="dona" items="${p.donations}">
				<tr>
					<td style="padding:0"><hr class="hr"/></td>
					<td style="padding:0"><hr class="hr"/></td>
				</tr>
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
					<td id="pricePaypal"><jstl:out value="${dona.price}" /> Euros</td>
				</tr>
				<tr>
					<td>
						<div id="paypal-button"></div>
					</td>
				</tr>
			</jstl:forEach>
			
		</table>
		
		</div>
	</div>
	
	<!-- COMMENTS -->
	<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4" style="padding: 0;">
		<table style="width: 50%; border-collapse:inherit;">
				<tr>
					<td>
						<h2>
							<spring:message code="userspace.comment" />
						</h2>
						<br/>
				<jstl:forEach var="com" items="${p.comments}">
					<tr>
						<td><spring:message code="comment.date" /></td>
						<td><jstl:out value="${com.date}" /></td>
					<tr>
					<tr>
						<td><spring:message code="comment.autor" /></td>
						<td><jstl:out value="${com.actor.userAccount.username}" /></td>
					<tr>
					<tr>
						<td><spring:message code="comment.text" /></td>
						<td><jstl:out value="${com.text}" /></td>
					<tr>
					<tr>
						<td><spring:message code="comment.puntuation" /></td>
						<td><jstl:out value="${com.puntuacion}" /></td>
					</tr>
					<jstl:if test="${com.actor.id==actor.id}">
						<tr>
							<td>
								<spring:message code="actor.edit.comment" var="actorEditComment" />
									<input onclick="window.location='userspace/user/commentEdit.do?q=${com.id}'"
									class="btn btn-danger" type="button" value="${actorEditComment}" />
								<spring:message code="actor.delete.comment" var="actordeleteComment" />
									<input onclick="window.location='userspace/user/commentDel.do?q=${com.id}'"
									class="btn btn-danger" type="button" value="${actordeleteComment}" />
							</td>
						</tr>
					</jstl:if>
					<br/>
				</jstl:forEach>
				<tr>
					<security:authorize access="hasRole('USER')">
						<td>
							<spring:message code="actor.new.comment" var="actorNewComment" />
							<input onclick="window.location='userspace/user/comment.do?q=${p.id}'"
								class="btn btn-danger" type="button" value="${actorNewComment}" />
						</td>
					</security:authorize>
				</tr>
			</table>
		</div>
	</div>
	</jstl:if>
</div>

<script src="https://www.paypalobjects.com/api/checkout.js"></script>

<script>

$( document ).ready(function() {
    console.log( "ready!" );
    
    var price = document.getElementById("pricePaypal").textContent.split(" Euros")[0];
    paypal.Button.render({
    	  // Configure environment
    	  env: 'sandbox',
    	  client: {
    	    sandbox: 'AdFnVkjaV4QC1Vw9CcZ9Jb7AZRT34WocoAuDHQIw0ArPXRqk0wl8LksQh5ZTy0GTe9-04-UTQSWbrkht',
    	    production: 'demo_production_client_id'
    	  },
    	  // Customize button (optional)
    	  locale: 'en_ES',
    	  style: {
    	    size: 'small',
    	    color: 'gold',
    	    shape: 'pill',
    	  },
    	  // Set up a payment
    	  payment: function (data, actions) {
    	    return actions.payment.create({
    	      transactions: [{
    	        amount: {
    	          total: price,
    	          currency: 'EUR'
    	        }
    	      }]
    	    });
    	  },
    	  // Execute the payment
    	  onAuthorize: function (data, actions) {
    	    return actions.payment.execute()
    	      .then(function () {
    	        // Show a confirmation message to the buyer
    	        window.alert('Gracias por donar');
    	      });
    	  }
    	}, '#paypal-button');
});

</script>
