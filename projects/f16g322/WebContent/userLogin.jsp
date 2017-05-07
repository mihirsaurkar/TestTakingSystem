<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>
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
<--<div class="login-card">-->
<body>

<f:view>
<!-- Navbar -->
<div class="w3-top">
  <ul class="w3-navbar w3-amber w3-card-2 w3-left-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-white w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
    <li><a href="index.jsp" class="w3-padding-large w3-white">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf" class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide2.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
    <!--  <li class="w3-hide-small"><a href="docs/EAD - Online Test System ER diagram.pdf" class="w3-padding-large w3-hover-white">ER Diagram</a></li>-->
  </ul>
  
  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-hide w3-hide-large w3-hide-medium">
    <ul class="w3-navbar w3-left-align w3-large w3-black">
      <li><a class="w3-padding-large" href= "docs/Online test system UML.pdf" >Programmers Guide</a></li>
      <li><a class="w3-padding-large" href="userGuide.jsp" target="_blank">Users Guide </a></li>
      <!-- <li><a class="w3-padding-large" href="docs/EAD - Online Test System ER diagram.pdf">ER Diagram</a></li>-->
    </ul>
  </div>
</div>

<!-- Header -->
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">User Login</h1>
  <p class="w3-xlarge">Group: f16g322</p>
  <br/>
	<h:form>
			<br />
			<hr>
			<center>
		<h:panelGrid columns="3" >
				<h:outputText value="User Type:" />
				<h:selectOneListbox value="#{user.userType}" size="1">
				<f:selectItem itemValue="Administrator" itemLabel="DB Administrator" />
				<f:selectItem itemValue="Instructor" itemLabel="Instructor" />
				<f:selectItem itemValue="Student" itemLabel="Student" />
				</h:selectOneListbox>
				<h:outputText value="" />
				
				<h:outputText value="User name:"/>
				<h:inputText id="userName" value="#{user.userName}" required="true" requiredMessage="An Username is required!"/>
				<h:message for="userName" showDetail="false" showSummary="true" style="color:red"/>
				
				<h:outputText value="Password:"/>
				<h:inputSecret id="password" value="#{user.password}"  required="true" requiredMessage="A Password is required!"/>
				<h:message for="password" showDetail="false" showSummary="true" style="color:red"/>
				
				<h:outputText value="" />
				<h:commandButton type="submit" value="Login to System" styleClass="w3-btn w3-padding-16 w3-large w3-margin-top"
				action="#{actionBeanLoginLogout.processUserLogin}" />
				<h:outputText value="" />
				
				<h:outputText value="" />
				<h:outputText value="#{messageBean.errorMessage}" style="color:red"/>
				<h:outputText value="" />
				
								
		</h:panelGrid>
		</center>
	</h:form>
</f:view>
</body>
</div>
</html>