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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<style>

@import url('https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i,900,900i|Roboto+Condensed:400,400i,700,700i');
section{
    padding: 100px 0;
}
.details-card {
	background: #ecf0f1;
}

.card-content {
	background: #ffffff;
	border: 4px;
	box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);
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
    transform: translate(-50%,-50%);
}
.card-img span h4{
        font-size: 12px;
        margin:0;
        padding:10px 5px;
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
.btn-card{
	background-color: #1ABC9C;
	color: #fff;
	box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);
    padding: .84rem 2.14rem;
    font-size: .81rem;
    -webkit-transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,-webkit-box-shadow .15s ease-in-out;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,-webkit-box-shadow .15s ease-in-out;
    -o-transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out,-webkit-box-shadow .15s ease-in-out;
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


<security:authorize access="permitAll">
<br>
<div class="container-fluid">
	<div class="row content">
            <div class="col-md-6">
                <div class="card-content">
                <div class="card-desc">
                    <div class="card-img">
                        <img src="advertisement/view/image.do?q=${advertisement.id}" alt="">
                       
                    </div>
                    
                        <h3><jstl:out value="${advertisement.title}" /></h3>
                        <br/>
                        <p><jstl:out value="${advertisement.description}" /></p>
                            <br/>
                            <spring:message code="advertisement.startDate.now" var="start" />
                            <spring:message code="advertisement.endDate.now" var="end" />
                            <fmt:parseDate pattern="yyyy-MM-dd" value="${advertisement.startDate}" var="parsedDate" />
                            <fmt:parseDate pattern="yyyy-MM-dd" value="${advertisement.endDate}" var="parsedDate2" />
							
                          <p><jstl:out value="${start}" />:<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" /> <jstl:out value="${end}" />:<fmt:formatDate value="${parsedDate2}" pattern="dd/MM/yyyy" /> </p>
                          <br/> 
                         
                            <spring:message code="event.locationUrl" var="location" />
                          <p><a href='advertisement/user/location.do?p=${advertisement.id}'><jstl:out value="${location}" /></a></p>
                            <security:authorize access="hasRole('USER')">
			
		</security:authorize>  
                       
                    </div>
                  
                </div>
            </div>
</div>
</div>




	<div style="width:20%; margin: auto;">
		<spring:message code="event.location" var="location"/>
		
		
		<%-- Registro --%>
		<security:authorize access="hasRole('USER')">
			<jstl:if test="${isOwner eq false}">
				<tr>
					<td>
					
					
							<jstl:if test="${advertisement.actorOwener!=actor and !advertisement.actorRegisters.contains(actor)}">
								<spring:url var="registerUrl" value="/advertisement/register.do">
									<spring:param name="q" value="${advertisement.id}" />
								</spring:url>
								<spring:message code="advertisement.register" var="registerMsg" />
							</jstl:if>
		
							
							<jstl:if test="${advertisement.actorOwener!=actor and advertisement.actorRegisters.contains(actor)}">
								<spring:url var="registerUrl" value="/advertisement/unregister.do">
									<spring:param name="q" value="${advertisement.id}" />
								</spring:url>
								<spring:message code="advertisement.unregister" var="registerMsg" />
								</jstl:if>
							
						
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