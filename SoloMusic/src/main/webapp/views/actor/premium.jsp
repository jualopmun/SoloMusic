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

	<spring:message code="actor.premium.one" var="premium1"></spring:message>
	<spring:message code="actor.premium.two" var="premium2"></spring:message>
	<spring:message code="actor.premium.three" var="premium3"></spring:message>
	<spring:message code="actor.ispremium" var="isPremium"></spring:message>
	
	<div style="width:60%; margin: auto;">
		<h2> <jstl:out value="${premium1}"></jstl:out></h2>
		<br/>
		<p> <jstl:out value="${premium2}"></jstl:out></p>
		<p> <jstl:out value="${premium3}"></jstl:out></p>
		<br/>
		
		<spring:message code="event.back" var="back" />
		<spring:message code="actor.premiumGo" var="premiumGo" />
		<spring:message code="actor.premiumnoGo" var="premiumnoGo" />
		<jstl:if test="${actor.isPremium==false }">
		<div id="paypal-button"></div>
		</jstl:if>
		
		<jstl:if test="${actor.isPremium==true }">
		<input
			onclick="window.location='actor/user/nopremium.do'"
			class="btn btn-danger" type="button" name="premium" value="${premiumnoGo}" />
		</jstl:if>
		<input
			onclick="window.location='welcome/index.do'"
			class="btn btn-danger" type="button" name="cancel" value="${back}" />
	</div>
	
</security:authorize>


<script src="https://www.paypalobjects.com/api/checkout.js"></script>

<script>

$( document ).ready(function() {
    console.log( "ready!" );
    
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
    	          total: '6',
    	          currency: 'EUR'
    	        }
    	      }]
    	    });
    	  },
    	  // Execute the payment
    	  onAuthorize: function (data, actions) {
    	    return actions.payment.execute()
    	      .then(function () {
    	    	  window.location='actor/user/premium.do';
    	      });
    	  }
    	}, '#paypal-button');
});

</script>
	
