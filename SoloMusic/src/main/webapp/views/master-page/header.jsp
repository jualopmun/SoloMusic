<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
	
<head>
 <title>Bootstrap Example</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/customIndex.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet"/>
<style>
/* Define what each icon button should look like */
.button {
  color: white;
  display: inline-block; /* Inline elements with width and height. TL;DR they make the icon buttons stack from left-to-right instead of top-to-bottom */
  position: relative; /* All 'absolute'ly positioned elements are relative to this one */
  padding: 2px 5px; /* Add some padding so it looks nice */
}

/* Make the badge float in the top right corner of the button */
.button__badge {
  background-color: #fa3e3e;
  border-radius: 2px;
  color: white;
 
  padding: 1px 3px;
  font-size: 10px;
  
  position: absolute; /* Position the badge within the relatively positioned button */
  top: 0;
  right: 0;
}

</style>

</head>


<div style="width: 100%">
	<nav class="navbar navbar-inverse" style="margin-bottom: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"  aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<security:authorize access="isAnonymous()">
						<li><a class="fNiv" href="security/login.do"><spring:message
									code="master.page.login" /></a></li>
						<li><a class="fNiv" href="actor/create.do"><spring:message
									code="master.page.signup" /></a></li>
					   
					</security:authorize>
					
					<li><a class="fNiv" href="welcome/index.do"><spring:message
							code="master.page.user.home" /></a></li>
					
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.id" var="id" />
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><security:authentication
									property="principal.username" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a class="fNiv" href="actor/list.do?q=followers"><spring:message
									code="master.page.user.followers" /></a></li>
								<li><a class="fNiv" href="actor/list.do?q=followeds"><spring:message
									code="master.page.user.followeds" /></a></li>
								
								<li><a href="j_spring_security_logout"><spring:message
											code="master.page.logout" /> </a></li>
							</ul>
						</li>
					</security:authorize>
					
					<security:authorize access="hasRole('USER')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><spring:message
									code="master.page.userspace" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="userspace/user/view.do"><spring:message
									code="master.page.user.userspace" /></a></li>
					   			<li><a class="fNiv" href="userspace/user/list.do"><spring:message
									code="master.page.user.userspace.list" /></a></li>
							</ul>
					  	</li>
					</security:authorize>
					
					<security:authorize access="hasRole('USER')">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><spring:message
									code="master.page.advertisement.list" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a class="fNiv" href="advertisement/user/list.do?q=0"><spring:message
									code="master.page.user.advertisement.list" /></a></li>
								<li><a class="fNiv" href="advertisement/list.do"><spring:message
									code="master.page.advertisement.list" /></a></li>
							</ul>
						</li>

						<li><a class="fNiv" href="actor/premium.do"><spring:message
							code="master.page.actor.premium" /></a></li>
					</security:authorize>
					
					
					<security:authorize access="hasRole('USER')">
						
						<li>
						
						<a class="fNiv" href="notification/list.do">
						 <div class="button">
    						<i class="fa fa-bell"></i>
    						<jstl:if test="${notifications.size()>0}">
   							 <span class="button__badge">${notifications.size()}</span>
   							 </jstl:if>
 						 </div>
						</a>
						</li>
	
					</security:authorize>

				</ul>
			</div>
			
			
		</div>
		</div>
	</nav>

	<a href="javascript:setParam('language', 'en');"> <img src="images/flag_en.png" /></a>
	<a href="javascript:setParam('language', 'es');"> <img src="images/flag_es.png" /></a>


<script> 
    function setParam(name, value) {
        var l = window.location;

        /* build params */
        var params = {};        
        var x = /(?:\??)([^=&?]+)=?([^&?]*)/g;        
        var s = l.search;
        for(var r = x.exec(s); r; r = x.exec(s))
        {
            r[1] = decodeURIComponent(r[1]);
            if (!r[2]) r[2] = '%%';
            params[r[1]] = r[2];
        }

        /* set param */
        params[name] = encodeURIComponent(value);

        /* build search */
        var search = [];
        for(var i in params)
        {
            var p = encodeURIComponent(i);
            var v = params[i];
            if (v != '%%') p += '=' + v;
            search.push(p);
        }
        search = search.join('&');

        /* execute search */
        l.search = search;
    }
</script>

</div>
