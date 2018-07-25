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

<security:authorize access="permitAll">

<table>
	<tr>
		<td><img style="max-width: 80px; max-height: 80px;" src="<jstl:out value="${advertisement.mainImg}"/>"></td>	
		<td><jstl:out value="${advertisement.title}" /></td>
	</tr>
	<tr>
		<td><spring:message code="advertisement.description" /></td>
		<td><jstl:out value="${advertisement.description}" /></td>
	</tr>
	<tr>
		<td><spring:message code="advertisement.locationUrl" /></td>
		<td><jstl:out value="${advertisement.locationUrl}" /></td>
	</tr>
	<tr>
		<td><spring:message code="advertisement.price" /></td>
		<td><jstl:out value="${advertisement.price}" /></td>
	</tr>
</table>

</security:authorize>