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


<security:authorize access="hasRole('USER')">
	<jstl:if test="${actor.userSpace==userSpace}">
		<spring:message code="performance.new" var="actorNew"/>
		<input onclick="window.location='perfomance/user/create.do'" class="btn btn-warning" type="button"  value="${actorNew}"/>
		<br />
	</jstl:if>
</security:authorize>
<br/>

<jstl:forEach var="p" items="${perfomance}">
	<table>
			<tr>
				<td><spring:message code="perfomance.title" /></td>
				<td><jstl:out value="${p.title}" /></td>
			</tr>
			<tr>
				<td><spring:message code="perfomance.description" /></td>
				<td><jstl:out value="${p.description}" /></td>
			</tr>
	</table>

	<spring:message code="perfomance.videoUrl" />
	<input type="text" id="video" value="${p.videoUrl}"	style="display: none">
	<%-- Script para los videos de yotube --%>
	<div class="youtube" id='<jstl:out value="${p.videoUrl}" />' style="width:560px; height: 315px;">
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	
	<security:authorize access="hasRole('USER')">
		<jstl:if test="${actor.userSpace==userSpace}">
			<spring:message code="event.delete" var="actorDelete"/>
			<input onclick="window.location='perfomance/user/delete.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorDelete}"/>
			<spring:message code="event.edit" var="actorEdit"/>
			<input onclick="window.location='perfomance/user/edit.do?p=${p.id}'" class="btn btn-warning" type="button"  value="${actorEdit}"/>
		</jstl:if>
	</security:authorize>
</jstl:forEach>

<spring:message code="performance.back" var="back"/>
<input onclick="window.location='userspace/user/view.do?p=${actor.userSpace.id}'" class="btn btn-warning" type="button" name="cancel" value="${back}"/>
	
<script> 

// Find all the YouTube video embedded on a page
var videos = document.getElementsByClassName("youtube"); 

for (var i=0; i<videos.length; i++) {
  
  var youtube = videos[i];
  
  // Based on the YouTube ID, we can easily find the thumbnail image
  var img = document.createElement("img");
  img.setAttribute("src", "http://i.ytimg.com/vi/" 
                          + youtube.id + "/hqdefault.jpg");
  img.setAttribute("class", "thumb");
  

  // Overlay the Play icon to make it look like a video player
  var circle = document.createElement("div");
  circle.setAttribute("class","circle");  
  
  youtube.appendChild(img);
  youtube.appendChild(circle);
  
  // Attach an onclick event to the YouTube Thumbnail
  youtube.onclick = function() {

    // Create an iFrame with autoplay set to true
    var iframe = document.createElement("iframe");
    iframe.setAttribute("src",
          "https://www.youtube.com/embed/" + this.id
        + "?&fs=1&autoplay=1&autohide=1&border=0&wmode=opaque&enablejsapi=1"); 
    
    // The height and width of the iFrame should be the same as parent
    iframe.style.width  = this.style.width;
    iframe.style.height = this.style.height;
    iframe.add=true
    // Replace the YouTube thumbnail with YouTube HTML5 Player
    this.parentNode.replaceChild(iframe, this);
    

  }; 
}

</script>


