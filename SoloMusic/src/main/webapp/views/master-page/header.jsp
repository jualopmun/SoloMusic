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
	
<head>
 <title>Bootstrap Example</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/customIndex.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 
</head>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"  aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="security/login.do">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div style="width: 100%">
	<nav class="navbar navbar-inverse" style="margin-bottom: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
			 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"  aria-expanded="false"></button>
				<ul class="nav navbar-nav">
					<security:authorize access="isAnonymous()">
						<li><a class="fNiv" href="security/login.do"><spring:message
									code="master.page.login" /></a></li>
						<li><a class="fNiv" href="actor/create.do"><spring:message
									code="master.page.signup" /></a></li>
					   
					</security:authorize>
					
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
								<li><a class="fNiv" href="advertisement/user/list.do?q=0"><spring:message
									code="master.page.user.advertisement.list" /></a></li>
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
						<li><a class="fNiv" href="advertisement/list.do"><spring:message
							code="master.page.advertisement.list" /></a></li>
							<li><a class="fNiv" href="actor/premium.do"><spring:message
							code="master.page.actor.premium" /></a></li>
					</security:authorize>
				</ul>
			</div>
			
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<security:authorize access="permitAll">
						<li class="fNiv"><a href="welcome/index.do">
							<img src="images/home-icon-silhouette.png"/></a>
					</security:authorize>
				</ul>
			</div>
		</div>
	</nav>

	<a href="?language=en"> <img src="images/flag_en.png" /></a>
	<a href="?language=es"> <img src="images/flag_es.png" /></a>

</div>
