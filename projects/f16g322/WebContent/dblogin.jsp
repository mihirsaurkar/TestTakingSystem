<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Database Login</title>
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
<!--<div class="login-card">-->
<!-- Navbar -->
<div class="w3-top">
  <ul class="w3-navbar w3-amber w3-card-2 w3-left-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-white w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
    <li class="w3-hide-small"><a href="index.jsp" class="w3-padding-large w3-white">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide1.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
   <!--  <li class="w3-hide-small"><a href="docs/EAD - Online Test System ER diagram.pdf" class="w3-padding-large w3-hover-white">ER Diagram</a></li> -->
  </ul>
</div>
<!-- Header -->
	<h:form>
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">Database Login</h1>
  <p class="w3-xlarge">Group: f16g322</p>
   
  <hr>
	<center>
	
		
		
			<br />
			
			<center>
			<h:panelGrid columns="3" >
				<h:outputText value="User name:"/>
				<h:inputText id="userName" value="#{dbmsUserBean.userName}" required="true" requiredMessage="An Username is required!"/>
				<h:message for="userName" showDetail="false" showSummary="true" style="color:red"/>
				
				<h:outputText value="Password:" />
				<h:inputSecret id="password" value="#{dbmsUserBean.password}" required="true" requiredMessage="A Password is required!"/>
				<h:message for="password" showDetail="false" showSummary="true" style="color:red"/>
				
				<h:outputText value="Host:" />
				<h:selectOneListbox value="#{dbmsUserBean.dbmsHost}" size="1">
					<f:selectItem itemValue="131.193.209.54" itemLabel="server54" />
					<f:selectItem itemValue="131.193.209.57" itemLabel="server57" />
					<f:selectItem itemValue="localhost" />
				</h:selectOneListbox>
				<h:outputText value="" />

				<h:outputText value="RDBMS:" />
				<h:selectOneListbox value="#{dbmsUserBean.dbms}" size="1">
					<f:selectItem itemValue="MySQL" />
					<f:selectItem itemValue="DB2" />
					<f:selectItem itemValue="Oracle" />
				</h:selectOneListbox>
				<h:outputText value="" />
				
				<h:outputText value="Database schema:" />
				<h:inputText id="databaseSchema" required="true" requiredMessage="A Database Schema is required!" value="#{dbmsUserBean.dbSchema}" />
				<h:message for="databaseSchema" showDetail="false" showSummary="true" style="color:red"/>
				
			
			<h:outputText value="" />
			<h:commandButton type="submit" value="Login" styleClass="w3-btn w3-padding-16 w3-large w3-margin-top"
					action="#{actionBeanLoginLogout.processDBLogin}" />
			<h:outputText value="" />
				
			<h:outputText value="" />
			<h:outputText value="#{messageBean.errorMessage}" style="color:red"/>
			<h:outputText value="#{messageBean.responseMessage}" />
			
					
				
					

			</h:panelGrid>
			
			</center>
			
		</h:form>
		
		</div>
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