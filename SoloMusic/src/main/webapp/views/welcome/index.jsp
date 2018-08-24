<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
<!--
.carousel-control.right {
    right: 0;
    left: auto;
    background-image: -webkit-linear-gradient(left,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    background-image: -o-linear-gradient(left,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    background-image: -webkit-gradient(linear,left top,right top,from(rgba(0,0,0,.0000)),to(rgba(0,0,0,.0)));
    background-image: linear-gradient(to right,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00000000', endColorstr='#80000000', GradientType=1);
    background-repeat: repeat-x;
}

.carousel-control.left {
    left: 0;
    right: auto;
    background-image: -webkit-linear-gradient(left,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    background-image: -o-linear-gradient(left,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    background-image: -webkit-gradient(linear,left top,right top,from(rgba(0,0,0,.0000)),to(rgba(0,0,0,.0)));
    background-image: linear-gradient(to right,rgba(0,0,0,.0000) 0,rgba(0,0,0,.0) 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00000000', endColorstr='#80000000', GradientType=1);
    background-repeat: repeat-x;
}

.carousel-control {
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    width: 10%;
    font-size: 20px;
    color: red;
    text-align: center;
    background-color: transparent;
    filter: alpha(opacity=0);
    opacity: 0;
}

.glyphicon {
    color:red;
}

.container {
    padding-right: 0px;
    padding-left: 0px;
    margin-right: 0px;
    margin-left: 0px;
    margin-top: 0px;
}

.container {
    width: auto;
    height: auto;
}

.carousel-inner{
    background-position: center center;
    width: auto;
    height: auto;

}

.imagen1 {
	background-image: url("images/logo2.png");  
	height: 100vh;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
}

.imagen2 {
	background-image: url("images/bass.jpg");  
	height: 100vh;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
}

.imagen3 {
	background-image: url("images/hucha.jpg");  
	height: 100vh;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
}

.imagen4 {
	background-image: url("images/concert.jpg");  
	height: 100vh;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
}




.carousel-inner{
 	padding-right: 0px;
    padding-left: 0px;
    margin-right: 0%;
    margin-left: 0%;
}


.carousel-indicators li {
 
    background-color: red;
    border: 1px solid black;
}

.carousel-indicators {
    top: 20px;
}

.carousel-caption {
	position: absolute;
	top: 75%;
	margin-left: 30%;
	width: 70%;
    z-index: 3;
    -webkit-transform: translate3d(-50%,-50%,0);
    -moz-transform: translate3d(-50%,-50%,0);
    -ms-transform: translate3d(-50%,-50%,0);
    -o-transform: translate3d(-50%,-50%,0);
    transform: translate3d(-50%,-50%,0);

}

.carousel-caption2 {
	position: absolute;
	top: 90%;
	margin-left: 60%;
	width: 70%;
    z-index: 3;
    -webkit-transform: translate3d(-50%,-50%,0);
    -moz-transform: translate3d(-50%,-50%,0);
    -ms-transform: translate3d(-50%,-50%,0);
    -o-transform: translate3d(-50%,-50%,0);
    transform: translate3d(-50%,-50%,0);
}

-->
</style>

<div class="container">  
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
   
    <div class="carousel-inner" >
      <div class="item active">
      <div class="imagen1"></div>
      <div class="carousel-caption">
        <h1 style="font-weight:bold; color:black; font-size:3.5vw;"><spring:message code="welcome.greeting"/></h1>
      </div>
	</div>

      <div class="item">
      <div class="imagen2"></div>
      <div class="carousel-caption2">
        <h1 style="font-weight:bold; color:white; font-size:3.5vw;"><spring:message code="welcome.upload"/></h1>
      </div>
      </div>
    
       <div class="item">
      <div class="imagen3"></div>
      <div class="carousel-caption2">
        <h1 style="font-weight:bold; color:black; font-size:3.5vw;"><spring:message code="welcome.contribute"/></h1>
      </div>
      </div>
    
       <div class="item">
      <div class="imagen4"></div>
      <div class="carousel-caption2">
        <h1 style="font-weight:bold; color:white; font-size:3.5vw;"><spring:message code="welcome.succeed"/></h1>
      </div>
      </div>
    </div> 

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

<%-- <p><spring:message code="welcome.greeting.prefix" /> ${name}<spring:message code="welcome.greeting.suffix" /></p>
<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> --%>
