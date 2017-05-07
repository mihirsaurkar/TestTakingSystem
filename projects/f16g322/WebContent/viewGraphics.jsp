<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Score Graphics</title>
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
<f:view>
<!-- Navbar -->
<div class="w3-top">
  <ul class="w3-navbar w3-amber w3-card-2 w3-left-align w3-large">
    <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">
      <a class="w3-padding-large w3-hover-white w3-large w3-amber" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    </li>
    <li class="w3-hide-small"><a href="mainMenu.jsp" class="w3-padding-large w3-white">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide7.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
   <li class="w3-hide-small"><a href="logout.jsp" class="w3-padding-large w3-hover-white">Logout</a></li>
  </ul>
</div>
<!-- Header -->
<h:form>
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">View Statistics Page</h1>
  <p class="w3-xlarge">Group: f16g322</p>
    <p class="w3-xlarge"> Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
	
	
<center>
<h:panelGrid columns="2" >
<h:selectOneListbox value="#{actionBeanGraphics.exam}" size="1">
					<f:selectItem itemValue="exam01" />
					<f:selectItem itemValue="exam02" />
					<f:selectItem itemValue="exam03" />
				</h:selectOneListbox>
				
	<h:commandButton type="submit" value="Generate Series Chart" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top"
					action="#{actionBeanGraphics.generateGraph}"/>
	</h:panelGrid>
	</center>
					
	<h:graphicImage value="#{actionBeanGraphics.XYchartFile}"
		height="450" width="600"
		rendered="#{actionBeanGraphics.renderChart}"
		alt="xySeriesChart"/><br/>
	
	<hr>
	<center>
	<h:panelGrid columns="1" >
		<h:commandButton type="submit" value="Generate Bar Chart" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top"
					action="#{actionBeanGraphics.generateBarGraph}"/>
		
		
		</h:panelGrid>
		</center>
		<h:graphicImage value="#{actionBeanGraphics.barchartFile}"
			height="450" width="600"
			rendered="#{actionBeanGraphics.renderBarChart}"
			alt="xySeriesChart"/>
		<hr>
		<center>
	<h:panelGrid columns="2">
	<h:selectOneListbox value="#{actionBeanGraphics.exam}" size="1">
					<f:selectItem itemValue="exam01" />
					<f:selectItem itemValue="exam02" />
					<f:selectItem itemValue="exam03" />
					<f:selectItem itemValue="total"/>
				</h:selectOneListbox>
	<h:commandButton type="submit" value="Generate Pie Chart" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top"
					action="#{actionBeanGraphics.generatePieChart}"/>
	
	</h:panelGrid>
	</center>

		<h:graphicImage value="#{actionBeanGraphics.piechartFile}"
		height="450" width="600"
		rendered="#{actionBeanGraphics.renderPieChart}"
		alt="xySeriesChart"/><br/>
	
		
	
	</h:form>
	</f:view>
	
</body>
</html>