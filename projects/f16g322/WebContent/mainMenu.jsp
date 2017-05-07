<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Menu</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-navbar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
</head>
<body>
<f:view>
<!-- Navbar -->
<div class="w3-top" >
  <ul class="w3-navbar w3-amber w3-card-2 w3-left-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-white w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
    <li><a class="w3-padding-large w3-white" href= "userLogin.jsp">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <c:if test="${user.user==1}">
    <li class="w3-hide-small"><a href="userGuide3.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li></c:if>
    <c:if test="${user.user==2}">
    <li class="w3-hide-small"><a href="userGuide9.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li></c:if>
    <c:if test="${user.user==3}">
    <li class="w3-hide-small"><a href="userGuide8.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li></c:if>
    <li class="w3-hide-small"><a href="logout.jsp" class="w3-padding-large w3-hover-white">Logout</a></li>
  </ul>
</div>
<!-- Header -->
<h:form>
<header class="w3-container w3-amber w3-center w3-padding-128">
<c:if test="${user.user==1}">
  <h1 class="w3-margin w3-jumbo">Admin Page</h1>
 <p class="w3-xlarge">Group: f16g322</p>
  <p class="w3-xlarge">Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
  </c:if>
  <c:if test="${user.user==2}">
  <h1 class="w3-margin w3-jumbo">Student Page</h1>
 <p class="w3-xlarge">Group: f16g322</p>
  <p class="w3-xlarge">Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
  </c:if>
   <c:if test="${user.user==3}">
  <h1 class="w3-margin w3-jumbo">Instructor Page</h1>
 <p class="w3-xlarge">Group: f16g322</p>
  <p class="w3-xlarge">Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
  </c:if>

<center>
<!--  <div class="w3-container w3-light-grey">-->
			
			<!-- <div class="login-card">--> 
			<div>
  <ul class="w3-navbar w3-light-grey w3-card-2 w3-center-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-amber w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
   	<c:if test="${user.user==3 }">
    <li class="w3-hide-small"><a href="fileImport.jsp" class="w3-padding-large w3-hover-amber">File Import</a></li></c:if>
       	<c:if test="${user.user==3 }">
    <li class="w3-hide-small"><a href="fileExport.jsp" class="w3-padding-large w3-hover-amber">File Export</a></li></c:if>
    <c:if test="${user.user==2}">
    <li class="w3-hide-small"><a href="test.jsp" class="w3-padding-large w3-hover-amber">View Available Tests</a></li></c:if>
    <c:if test="${user.user==1 || user.user==3}">
   	<li class="w3-hide-small"><a href="databaseAccess.jsp" class="w3-padding-large w3-hover-amber">Database Access</a></li></c:if>
   	<c:if test="${user.user==3 }">
   	<li class="w3-hide-small"><a href="viewStats.jsp" class="w3-padding-large w3-hover-amber">View Score Statistics</a></c:if> 
    <c:if test="${user.user==3 }">
    <li class="w3-hide-small"><a href="viewGraphics.jsp" class="w3-padding-large w3-hover-amber">View Score Graphs</a></c:if>
    </ul>
				</h:form>
				</div>
		</center>
		<script>
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>
</f:view>
</body>
</html>
</html>