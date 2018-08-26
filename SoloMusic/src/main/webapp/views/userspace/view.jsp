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
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<head>

<style>

/* Set gray background color and 100% height */
.sidenav {
	background-color: #f1f1f1;
	height: 100%;
}

.card bg-primary text-white {
	height: auto;
	padding: 200px;
	float: left;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
.row.content {
	height: auto;
}

}
@import
	url('https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css')
	;

#team {
	background: #eee !important;
}

.btn-info:hover, .btn-info:focus {
	background-color: #007b5e;
	border-color: #007b5e;
	box-shadow: none;
	outline: none;
	
}

.btn-info {
	color: #fff;
	background-color: #007b5e;
	border-color: #007b5e;
	display: block;
	margin-left: auto;
    margin-right: auto;
}

section {
	padding: 60px 0;
}

section .section-title {
	text-align: center;
	color: #007b5e;
	margin-bottom: 50px;
	text-transform: uppercase;
}

#team .card {
	border: none;
	background: #ffffff;
}

#config{
    border: 2px solid #c9302c;
    border-radius: 20px;
    padding-bottom: 10px;
    padding-top: 5px;
}

#info{

	padding-left:0px;
	padding-right:0px;
    border: 2px solid black;
    border-radius: 20px;
    padding-bottom: 10px;
}

.nav > li > a:focus, .nav > li > a:hover {
    text-decoration: none;
    background-color: #e38582;
}

a {
    color: #337ab7;
    text-decoration: none;
    font-weight: bold;
}

#donations{
	margin-bottom: 20px;
	padding-left:5px;
}

.bg-primary{
	border: 2px solid #224f77;
    border-radius: 20px;
    padding-bottom: 5px;
    padding-left: 7px;
    margin-bottom: 5px;
    margin-top: 5px;
}

#paypaldiv{

	padding-left:0px;
}

#accordion{
	padding-top:20px;
}

#avatar{
	padding-left:5px;
	padding-right: 5px;
	padding-top: 8px;
}

#caratula{
	display: block;
	margin-left: auto;
    margin-right: auto;
    width: 65%;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    background-color: white;
}

audio{
width: 75%;
}

#Cabecera{
	
	background-color: #c9302c;
	margin-top:0px;
	margin-bottom:0px;
	padding-top:5px;
	padding-bottom:5px;
	border-radius: 19px 19px 0px 0px;
	padding-left:0px;
	border-bottom: 2px solid black;
}
#cabeceratext{
	font-size: 18px;
	color: black;
	text-align: center;
}

audio, canvas, progress, video {
    display: inline-block;
    vertical-align: baseline;
}
#borrartrack {
	display: inline-block;
    vertical-align: top;
    font-size: 18px;
	font-weight: bold;
    }
    
    
.panel-default > .panel-heading {
	color: #333;
	background-color: #bfbfbf;
	
	border-width: 5px;
}

.panel-group .panel {
    margin-bottom: 0;
    border-radius: 5px;
    border-color: black;
}

#tituloplaylist{
	text-align:center;
}

#comment{
	border: 2px solid black;
	border-style: inset;
	padding: 3px;
}

#newcomment{
	margin-top: 5px;
}

#avatarimg{
	padding-bottom: 5px;
}

label label-success {
    margin-bottom: 3px;
}


</style>
</head>
<br>

<jstl:if test="${p==null and actor.userSpace==p}">
	<div class="card bg-primary text-white">

								<div class="card-body">
								
									<spring:message code="userspace.newspace" var="spaceNew" />
								
									<p class="card-text">${spaceNew}</p>
									
									<spring:message code="actor.new" var="actorNew" />
									<input onclick="window.location='userspace/user/create.do'"
									class="btn btn-danger" type="button" value="${actorNew}" />
								</div>
		</div>
</jstl:if>

<!-- Configuración del perfil -->
<div class="container-fluid">
	<div class="row content">
		<div class="col-sm-2" style="margin-bottom: 10px" id="config">

			<security:authorize access="hasRole('USER')">

				<jstl:if test="${actor.userSpace!=p}">
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
			<ul class="nav nav-pills nav-stacked">
				<security:authorize access="hasRole('USER')">
					<jstl:if test="${p!=null and actor.userSpace==p}">
						<spring:message code="actor.edit" var="actorEdit" />
						<li><a href="userspace/user/edit.do">${actorEdit}</a></li>
					</jstl:if>
				</security:authorize>
				<jstl:if test="${p!=null}">
					<spring:message code="actor.video" var="actorVideo" />
					<li><a href="event/user/view.do?p=${p.id}">${actorVideo}</a></li>
				</jstl:if>
				<jstl:if test="${p!=null}">
					<spring:message code="actor.perfomance" var="actorPerfomance" />
					<li><a href="perfomance/user/view.do?p=${p.id}">${actorPerfomance}</a></li>
				</jstl:if>
			</ul>
			<br>
			<jstl:if test="${p!=null}">
			<jstl:if test="${fn:length(p.profileImg)>0}">
				
					<img src="userspace/view/image.do?q=${p.id}" class="img-rounded"
						alt="Avatar" width="100" id="avatarimg">
				</jstl:if>
				<jstl:if test="${p!=null and actor.userSpace==p}">
					<br />

					<spring:message code="userspace.upload.archive"
						var="actoUploadImage" />
					<input onclick="window.location='userspace/dowload/upload.do'"
						class="btn btn-danger" type="button" value="${actoUploadImage}" />
				</jstl:if>
			</jstl:if>
		</div>
		
	<!-- Container general -->
		
		<div class="col-sm-10" style="margin-bottom: 0px" >
		
		
			<div class="col-sm-8">
<!-- Información del perfil -->
				<div class="col-sm-12" id="info">
					<div class="col-sm-12" id="Cabecera">
					<spring:message code="userspace.info" var="spacetitle" />
					<jstl:if test="${p!=null}">
						<h4 id="cabeceratext">
							${spacetitle}
						</h4>
						
					</jstl:if>
					</div>
					<div class="col-sm-12">
					<h2>${p.title}</h2>
					<h5>
						<span class="label label-danger">${p.genre.genre}</span>
					</h5>
					<br>
					<p>${p.description}</p>
					<br>
					<spring:message code="userspace.contact" var="spaceContact" />
					<jstl:if test="${p!=null}">
						<p>${spaceContact} ${p.contact}</p>
						<hr>

					</jstl:if>
					</div>
				</div>

		<!-- Donations -->
				<div class="col-sm-12" id="donations">
				<spring:message code="donantions.titulo" var="userDonations" />
				<h4>
								<small> ${userDonations} </small>
				
				</h4>
					<security:authorize access="hasRole('USER')">
						<jstl:if test="${p!=null and actor.userSpace==p}">

							<spring:message code="actor.new" var="actorNew" />
							<input onclick="window.location='donation/user/create.do'"
								class="btn btn-danger" type="button" value="${actorNew}" />	
								
								<jstl:if test="${empty p.donations}">
								<spring:message code="userspace.donation.new" var="spaceNew" />
								<span class="label label-success">${spaceNew}</span>
							</jstl:if>
						</jstl:if>
						
					</security:authorize>
					<jstl:forEach var="dona" items="${p.donations}"  >

				<div class="col-sm-12" id="paypaldiv">
							

					<div class="card bg-primary text-white">

								<div class="card-body">
									<h5 class="card-title">${dona.title}</h5>
									<p class="card-text">${dona.description}</p>
									<br />
									<spring:message code="dona.price" var="donaprice" />
									<p class="card-text">${donaprice} ${dona.price} Euros</p>
									<div class="paypal" id="paypal-button">
										<p style="display: none;">${dona.price}</p>
									</div>
								</div>
							</div>
						</div>
					</jstl:forEach>
					<jstl:if test="${p!=null}">
					</jstl:if>
				</div>


		<!-- Comments -->
		<div class="col-sm-12">
				
					<jstl:forEach var="com" items="${p.comments}" varStatus="vs">
					<div class="col-sm-12" id="comment">
					<jstl:if test="${fn:length(com.actor.userSpace.profileImg)>0}">
					
					<div class="col-sm-2 text-center" id="avatar">
					
							<img src="userspace/view/image.do?q=${com.actor.userSpace.id}"
								class="img-circle" height="75" width="75" alt="Avatar">
					</div>
						</jstl:if>
						<div class="col-sm-10" id="comentario">
							<h4>${com.actor.name}
								<fmt:parseDate pattern="yyyy-MM-dd" value="${com.date}" var="parsedDate" />
								${" "} ${com.actor.surname}<small> <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" /></small>
							</h4>
							<spring:message code="comment.puntuation" />:${com.puntuacion}
							<br />
							<p>${com.text}</p>
							<br>
							<jstl:if test="${com.actor.id==actor.id}">
							<div class="container">

									<!-- Button to Open the Modal -->
									<button type="button" class="btn btn-danger"
										data-toggle="modal" data-target="#myModal${vs.index}"><spring:message code="event.delete"/></button>

									<!-- The Modal -->
									<div class="modal fade" id="myModal${vs.index}">
									
								
										<div class="modal-dialog">
											<div class="modal-content">
												
												<!-- Modal Header -->
												<div class="modal-header alert-danger">
													<h4 class="modal-title"><spring:message code="delete.comment"/></h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>

												<!-- Modal body -->
												<div class="modal-body alert-info"><spring:message code="delete.comment.message"/>
												
												
												</div>

												<!-- Modal footer -->
												<div class="modal-footer alert-success">
													<button type="button" class="btn btn-danger"
														onclick="window.location='userspace/user/commentDel.do?q=${com.id}'"
														data-dismiss="modal"><spring:message code="event.delete"/></button>
													<button type="button" class="btn btn-danger"
														onclick="window.location='userspace/user/spaceview.do?q=${p.id}'"
														data-dismiss="modal"><spring:message code="event.cancel"/></button>
												</div>

											</div>
										</div>
									</div>
									<input
									<spring:message code="actor.edit.comment" var="actorEditComment" />
								
									onclick="window.location='userspace/user/commentEdit.do?q=${com.id}'"
									class="btn btn-danger" type="button"
									value="${actorEditComment}" />

								</div> 
							
								
								<spring:message code="actor.delete.comment"
									var="actordeleteComment" />
								
							</jstl:if>
						</div>
						</div>
					</jstl:forEach>
					<div class="col-sm-12" id="newcomment">
					<security:authorize access="hasRole('USER')">
						<jstl:if test="${p!=null}">
							<spring:message code="actor.new.comment" var="actorNewComment" />
							<input
								onclick="window.location='userspace/user/comment.do?q=${p.id}'"
								class="btn btn-danger" type="button" value="${actorNewComment}" />
						</jstl:if>
					</security:authorize>
					</div>
				</div>
			</div>

	<!-- PlayList -->
			<div class="col-sm-4" >
				<spring:message code="playlist" var="userPlaylist"></spring:message>
				<jstl:if test="${p!=null}">
					<h4>
						<small> ${userPlaylist} </small>
					</h4>
					<hr>
				</jstl:if>
				<security:authorize access="hasRole('USER')">
					<jstl:if test="${p!=null}">
						<jstl:if test="${actor.userSpace==p}">
						

							<jstl:if test="${empty p.playLists}">
								<spring:message code="userspace.playlist.new" var="spaceNew" />
								<span class="label label-success">${spaceNew}</span>
							</jstl:if>
	
	
							<spring:message code="actor.new" var="actorNew" />
							<input onclick="window.location='playlist/user/create.do'"
								class="btn btn-danger" type="button" value="${actorNew}" />
								
	
						
						
						
						
						</jstl:if>
					</jstl:if>
				</security:authorize>
				
		<!-- Collapsable -->
		<div class="panel-group" id="accordion">
				<jstl:forEach var="play" items="${p.playLists}">
				<div class="panel panel-default">
   					 <div class="panel-heading">
  				<h2 id="tituloplaylist">${play.title}</h2>
  					<p>
						<img class=" img-fluid" src="images/playlist.png" alt="Caratula" height="175" width="200" id="caratula">
					</p>
				<h4>${play.description} </h4>
 					 <button type="button" class="btn btn-info" data-parent="#accordion" data-toggle="collapse" data-target="#${play.title}"><spring:message code="play.track"
								/></button>
				</div>
  					<div id="${play.title}" class="panel-collapse collapse">
  					<div class="panel-body">
  						<jstl:forEach var="track" items="${play.tracks}" varStatus="vs">
								
								<p class="card-text">"${track.title}"</p>
									<div class="card">
										<audio preload="none" controlsList="nodownload" controls="controls" runat="server">
												<source src="userspace/user/play.do?q=${track.id}"
														type='audio/mp3;codec="mp3"' onmouseout="true" media="all" >Your browser does not support the audio element.</audio>
									

									<jstl:if test="${actor.userSpace==p}">
										<input onclick="window.location='track/user/delete.do?q=${track.id}'"
										class="btn btn-danger" type="button" value="x" id="borrartrack"/>
									</jstl:if>
					
									</div>
						</jstl:forEach>
						<jstl:if test="${actor.userSpace==p}">
											<spring:message code="actor.new.track" var="actorNewTrack" />
											<input
												onclick="window.location='track/user/create.do?q=${play.id}'"
												class="btn btn-danger" type="button"
												value="${actorNewTrack}" />
						</jstl:if>
 					 
					</div>
					</div>
					</div>
				</jstl:forEach>
				</div>
			</div>

		</div>
	</div>
</div>

<script src="https://www.paypalobjects.com/api/checkout.js"></script>

<script>
	$(document).ready(function() {
		var elements = document.getElementsByClassName("paypal"); 
		
		for(var i=0; i<elements.length; i++){
			var price = elements[i].children[0].innerHTML;
			paypal.Button.render({
				// Configure environment
				env : 'sandbox',
				client : {
					sandbox : 'AdFnVkjaV4QC1Vw9CcZ9Jb7AZRT34WocoAuDHQIw0ArPXRqk0wl8LksQh5ZTy0GTe9-04-UTQSWbrkht',
					production : 'demo_production_client_id'
				},
				// Customize button (optional)
				locale : 'en_ES',
				style : {
					size : 'small',
					color : 'gold',
					shape : 'pill',
				},
				// Set up a payment
				payment : function(data, actions) {
					return actions.payment.create({
						transactions : [
							{
								amount : {
									total : price,
									currency : 'EUR'
								}
							}
						]
					});
				},
				// Execute the payment
				onAuthorize : function(data, actions) {
					return actions.payment.execute().then(function() {
						// Show a confirmation message to the buyer
						window.alert('Gracias por donar');
					});
				}
			}, elements[i]);
		}
		
		
	});
</script>
