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


<div id="map" style="width:100%;height:200px"></div>
<br/>
<br/>
		
		<script>
function myMap() {
  var local="${advertisement.locationUrl}"

   var  parsear= local.split("@");
   var parsear2=parsear[1].split(",");
  

  
  var myCenter = new google.maps.LatLng(parseFloat(parsear2[0]),parseFloat(parsear2[1]));
  var mapCanvas = document.getElementById("map");
  var mapOptions = {center: myCenter, zoom: 15};
  var map = new google.maps.Map(mapCanvas, mapOptions);
  var marker = new google.maps.Marker({position:myCenter});
  marker.setMap(map);

  // Zoom to 9 when clicking on marker
  google.maps.event.addListener(marker,'click',function() {
    map.setZoom(9);
    map.setCenter(marker.getPosition());
  });
}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC2N9vI0ME-rzwaAAVULoeJqSANvValRLM&callback=myMap"></script>


<spring:message code="event.back" var="back" />
<input
	 onClick="history.go(-1);"
	class="btn btn-danger" type="button" name="cancel" value="${back}" />
