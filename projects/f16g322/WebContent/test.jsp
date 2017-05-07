<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Students View Tests Page</title>
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
    <li><a class="w3-padding-large w3-white" href= "mainMenu.jsp">Home</a></li>
    <li class="w3-hide-small"><a href= "docs/Programmers Guide.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide11.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
    <li class="w3-hide-small"><a href="logout.jsp" class="w3-padding-large w3-hover-white">Logout</a></li>
  </ul>
</div>
<!-- Header -->
<h:form>
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">View Test Page</h1>
  <p class="w3-xlarge">Group: f16g322</p>
  <p class="w3-xlarge"><h:outputText value="#{user.userName}"/></p> 
  <hr>
	<center>

 
<h:panelGrid columns="2" >
<h:outputText value=""/>
<h:outputText value="#{messageBean.responseMessage }" rendered="#{actionBeanStudentTest.submitTesterror }"/>
<h:outputText value="Click To see available Tests"/>
<h:commandButton type="submit" value="View Tests" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top" action="#{actionBeanStudentTest.showTests}"/>
</h:panelGrid>
  <t:dataTable value="#{actionBeanStudentTest.result}"
			var="row" 	rendered="#{actionBeanStudentTest.renderTests}" >
			<t:columns		
					var="col"
					value="#{actionBeanStudentTest.columnNames}">
					<f:facet name="header">	
						<t:outputText
							styleClass="outputHeader"
							value="#{col}" />
					</f:facet>
					<t:outputText
					styleClass="outputText"
					value="#{row[col]}" />
					</t:columns>
		</t:dataTable> 		
<hr>
<h:panelGrid columns="2" >
<h:selectOneMenu value="#{actionBeanStudentTest.testId}"> 
<f:selectItems value="#{actionBeanStudentTest.testid_list}" />
</h:selectOneMenu>
<h:commandButton type="submit" value="View Feedbacks" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top" action="#{actionBeanStudentTest.showFeedBack}"/>	
</h:panelGrid>
<h:outputText value="#{messageBean.errorMessage }" rendered="#{actionBeanStudentTest.feedBackerror }"  /> <br/><br/>

		 <t:dataTable value="#{actionBeanStudentTest.feedbackresult}"
			var="row" 	rendered="#{actionBeanStudentTest.renderFeedBack}" >
			<t:columns		
					var="col"
					value="#{actionBeanStudentTest.feedBackcolumnNames}">
					<f:facet name="header">	
						<t:outputText
							styleClass="outputHeader"
							value="#{col}" />
					</f:facet>
					<t:outputText
					styleClass="outputText"
					value="#{row[col]}" />
					</t:columns>
		</t:dataTable> 				 	
<hr>
<h:panelGrid columns="2" >

<h:selectOneMenu value="#{actionBeanStudentTest.testId}"> 
<f:selectItems value="#{actionBeanStudentTest.testid_list}" />
</h:selectOneMenu>

<h:commandButton type="submit" value="Start Test" styleClass="w3-btn w3-padding-16 w3-small w3-margin-top" action="#{actionBeanStudentTest.takeTest}"/>
<h:outputText value=""/>	 

</h:panelGrid>

<h:outputText value="#{messageBean.errorMessage }" rendered="#{actionBeanStudentTest.startTesterror }" /> <br/><br/>

		 
		  				 
				
		 
				
		

</h:form>

</f:view>
</body>
</html>