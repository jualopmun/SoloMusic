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


<security:authorize access="hasRole('USER')">

<spring:message code="actor.premium" var="premium"></spring:message>
<spring:message code="actor.ispremium" var="isPremium"></spring:message>
<jstl:if test="${actor.isPremium==true }">
<p> <jstl:out value="${isPremium}"></jstl:out></p>
</jstl:if>
<br/>

<p> <jstl:out value="${premium}"></jstl:out></p>



<spring:message code="event.back" var="back" />
<spring:message code="actor.premiumGo" var="premiumGo" />
<spring:message code="actor.premiumnoGo" var="premiumnoGo" />
<jstl:if test="${actor.isPremium==false }">
<input
	onclick="window.location='actor/user/premium.do'"
	class="btn btn-danger" type="button" name="premium" value="${premiumGo}" />
</jstl:if>

<jstl:if test="${actor.isPremium==true }">
<input
	onclick="window.location='actor/user/nopremium.do'"
	class="btn btn-danger" type="button" name="premium" value="${premiumnoGo}" />
</jstl:if>
<input
	onclick="window.location='welcome/index.do'"
	class="btn btn-danger" type="button" name="cancel" value="${back}" />
	</security:authorize>
	
