<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
@import
	url('https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i,900,900i|Roboto+Condensed:400,400i,700,700i')
	;

section {
	padding: 100px 0;
}

.details-card {
	background: #ecf0f1;
}

.card-content {
	background: #ffffff;
	border: 4px;
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
}

.card-img {
	position: relative;
	overflow: hidden;
	border-radius: 0;
	z-index: 1;
}

.card-img img {
	width: 100%;
	height: auto;
	display: block;
}

.card-img span {
	position: absolute;
	top: 15%;
	left: 12%;
	background: #1ABC9C;
	padding: 6px;
	color: #fff;
	font-size: 12px;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-ms-border-radius: 4px;
	-o-border-radius: 4px;
	transform: translate(-50%, -50%);
}

.card-img span h4 {
	font-size: 12px;
	margin: 0;
	padding: 10px 5px;
	line-height: 0;
}

.card-desc {
	padding: 1.25rem;
}

.card-desc h3 {
	color: #000000;
	font-weight: 600;
	font-size: 1.5em;
	line-height: 1.3em;
	margin-top: 0;
	margin-bottom: 5px;
	padding: 0;
}

.card-desc p {
	color: #747373;
	font-size: 14px;
	font-weight: 400;
	font-size: 1em;
	line-height: 1.5;
	margin: 0px;
	margin-bottom: 20px;
	padding: 0;
	font-family: 'Raleway', sans-serif;
}

.btn-card {
	background-color: #1ABC9C;
	color: #fff;
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .16), 0 2px 10px 0
		rgba(0, 0, 0, .12);
	padding: .84rem 2.14rem;
	font-size: .81rem;
	-webkit-transition: color .15s ease-in-out, background-color .15s
		ease-in-out, border-color .15s ease-in-out, -webkit-box-shadow .15s
		ease-in-out;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, -webkit-box-shadow .15s ease-in-out;
	-o-transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	transition: color .15s ease-in-out, background-color .15s ease-in-out,
		border-color .15s ease-in-out, box-shadow .15s ease-in-out,
		-webkit-box-shadow .15s ease-in-out;
	margin: 0;
	border: 0;
	-webkit-border-radius: .125rem;
	border-radius: .125rem;
	cursor: pointer;
	text-transform: uppercase;
	white-space: normal;
	word-wrap: break-word;
	color: #fff;
}

.btn-card:hover {
	background: orange;
}

a.btn-card {
	text-decoration: none;
	color: #fff;
}
</style>




<security:authorize access="hasRole('USER')">



	<jstl:if test="${actor.userSpace==userSpace}">
		<div class="card bg-primary text-white">

								<div class="card-body">
								
									<spring:message code="event.text" var="spaceNew" />
								
									<p class="card-text">${spaceNew}</p>
									
									<spring:message code="event.new" var="actorNew" />
									<input onclick="window.location='event/user/create.do'"
									class="btn btn-danger" type="button" value="${actorNew}" />
								</div>
		</div>
		<br />
		<br />
	</jstl:if>
</security:authorize>
<jstl:forEach var="p" items="${event}" varStatus="vs">
		<div class="card-content">
			
			<div class="card-desc">
			<div class="container">
				<h3>
					<jstl:out value="${p.title}" />
				</h3>
				<p class="card-text">
					<jstl:out value="${p.description}" />
				</p>
				<br />
				<spring:message code="event.locationUrl" var="location" />
				<p class="card-text">
					<a href='event/user/location.do?p=${p.id}'><jstl:out
							value="${location}" /></a>
				</p>
				<br />
				<p class="card-text">
					<jstl:out value="${p.startDate}" />
				</p>
				</div>
				<security:authorize access="hasRole('USER')">
					<jstl:if test="${actor.userSpace==userSpace}">
						
						
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
													<h4 class="modal-title"><spring:message code="delete.event.title"/> <jstl:out value="${p.title}" /></h4>
													<button type="button" class="close" data-dismiss="modal">&times;</button>
												</div>

												<!-- Modal body -->
												<div class="modal-body alert-info"><spring:message code="delete.event.message"/>
												
												
												</div>

												<!-- Modal footer -->
												<div class="modal-footer alert-success">
													<button type="button" class="btn btn-danger"
														onclick="window.location='event/user/delete.do?p=${p.id}'"
														data-dismiss="modal"><spring:message code="event.delete"/></button>
													<button type="button" class="btn btn-danger"
														onclick="window.location='event/user/view.do?p=${userSpace.id}'"
														data-dismiss="modal"><spring:message code="event.cancel"/></button>
												</div>

											</div>
										</div>
									</div>
									<spring:message code="event.edit" var="actorEdit" /> <input
								onclick="window.location='event/user/edit.do?p=${p.id}'"
								class="btn btn-danger" type="button" value="${actorEdit}" />

								</div> 
								
						
						
					</jstl:if>

				</security:authorize>

			</div>

		</div>



</jstl:forEach>





