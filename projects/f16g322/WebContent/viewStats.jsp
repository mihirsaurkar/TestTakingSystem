<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
 <%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Statistics Page</title>
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
<!-- There will be a data table  -->
<f:view>
<!--<div class="login-card">-->
<!-- Navbar -->
<div class="w3-top">
  <ul class="w3-navbar w3-amber w3-card-2 w3-left-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-white w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
    <li class="w3-hide-small"><a href="mainMenu.jsp" class="w3-padding-large w3-white">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide6.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
   <li class="w3-hide-small"><a href="logout.jsp" class="w3-padding-large w3-hover-white">Logout</a></li>
  </ul>
</div>
<!-- Header -->
<h:form enctype="multipart/form-data">
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">View Statistics Page</h1>
  <p class="w3-xlarge">Group: f16g322</p>
   <p class="w3-xlarge"> Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
	<center>

<center>

<h:panelGrid columns="2" >

<h:selectOneListbox value="#{actionBeanStatistics.exam}" size="1">
					<f:selectItem itemValue="exam01" />
					<f:selectItem itemValue="exam02" />
					<f:selectItem itemValue="exam03" />
				</h:selectOneListbox>
	<h:commandButton type="submit" value="Generate Stats" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top"
					action="#{actionBeanStatistics.generateStats}"/>
					</h:panelGrid>
			
	<h:panelGrid columns="2">
	<h:outputText  value="Minimum Value :"/>
	<h:outputText  value="#{actionBeanStatistics.minValue }"/>
	
	<h:outputText  value="q1 :"/>
	<h:outputText  value="#{actionBeanStatistics.q1 }"/>
	
	<h:outputText  value="q3 :"/>
	<h:outputText  value="#{actionBeanStatistics.q3 }"/>
	
	<h:outputText  value="Maximum Value :"/>
	<h:outputText  value="#{actionBeanStatistics.maxValue }"/>
	
	
	<h:outputText  value="Median :"/>
	<h:outputText  value="#{actionBeanStatistics.median }"/>
	
	<h:outputText  value="Mean :"/>
	<h:outputText  value="#{actionBeanStatistics.mean }"/>
	
	<h:outputText  value="Variance :"/>
	<h:outputText  value="#{actionBeanStatistics.variance }"/>
	
	<h:outputText  value="Standard Deviation :"/>
	<h:outputText  value="#{actionBeanStatistics.std }"/>
	
	<h:outputText  value="Range :"/>
	<h:outputText  value="#{actionBeanStatistics.range }"/>
	
	<h:outputText  value="IQR :"/>
	<h:outputText  value="#{actionBeanStatistics.iqr }"/>
</h:panelGrid>
<hr>
<h3> Please Select exam scores for Regression And ANOVA </h3>
<h:panelGrid columns="3" >

	<h:selectOneListbox value="#{actionBeanStatistics.regXam1}" size="1">
					<f:selectItem itemValue="exam01" />
					<f:selectItem itemValue="exam02" />
					<f:selectItem itemValue="exam03" />
				</h:selectOneListbox>
				
	
		<h:selectOneListbox value="#{actionBeanStatistics.regXam2}" size="1">
					<f:selectItem itemValue="exam01" />
					<f:selectItem itemValue="exam02" />
					<f:selectItem itemValue="exam03" />
				</h:selectOneListbox>

	

	<h:commandButton type="submit" value="Get Regression And Anova" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top"
					action="#{actionBeanStatistics.getRegression}"/>
					
			
	
	
	
	<h:outputText  value="Intercept :"/>
	<h:outputText  value="#{actionBeanStatistics.intercept }"/>
	<h:outputText value=""/>
		
	<h:outputText  value="Slope :"/>
	<h:outputText  value="#{actionBeanStatistics.slope }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="RSquare :"/>
	<h:outputText  value="#{actionBeanStatistics.rsq }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="Significance :"/>
	<h:outputText  value="#{actionBeanStatistics.significance }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="InterceptStdErr :"/>
	<h:outputText  value="#{actionBeanStatistics.interceptStdErr }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="SlopeStdErr :"/>
	<h:outputText  value="#{actionBeanStatistics.slopeStdErr }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="Equation :"/>
	<h:outputText  value="#{actionBeanStatistics.equation}"/>
	<h:outputText value=""/>
	
	<h:outputText  value="One Way ANOVA F value:"/>
	<h:outputText  value="#{actionBeanStatistics.fstatistics }"/>
	<h:outputText value=""/>
	
	<h:outputText  value="One Way ANOVA p value"/>
	<h:outputText  value="#{actionBeanStatistics.pValue }"/>
	<h:outputText value=""/>
</h:panelGrid>
</center>
</h:form>
</f:view>
</body>
</html>