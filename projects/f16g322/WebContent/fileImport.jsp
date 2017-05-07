<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
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
    <li><a href="mainMenu.jsp" class="w3-padding-large w3-white">Home</a></li>
   <li class="w3-hide-small"><a href= "docs/Online test system UML.pdf"  class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide4.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
    <li class="w3-hide-small"><a href="logout.jsp" class="w3-padding-large w3-hover-white">Logout</a></li>
  </ul>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-hide w3-hide-large w3-hide-medium">
    <ul class="w3-navbar w3-left-align w3-large w3-black">
    <li><a href="mainMenu.jsp" class="w3-padding-large w3-white">Home</a></li>
 <li><a class="w3-padding-large" href= "docs/Programmers Guide.pdf" >Programmers Guide</a></li>
      <li><a class="w3-padding-large" href="userGuide.jsp" target="_blank">Users Guide </a></li>
      <li><a class="w3-padding-large" href="logout.jsp">Logout</a></li>
    </ul>
  </div>
</div>
<!-- Header -->
<h:form enctype="multipart/form-data" styleClass="align-center">
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">File Import</h1>
  <p class="w3-xlarge">Group: f16g322</p>
  <p class="w3-xlarge"> Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
  <center>

	
	<!-- <div class="login-card">-->
		
			
			<h:panelGrid columns="3" style="background-color: Amber">
				<!-- border-bottom-style: solid; 
				border-top-style: solid; 
				border-left-style: solid;
				 border-right-style: solid" -->
				 
					<h:outputLabel for="crn" value="CRN:"/>
					<h:inputText id="crn" value="#{actionBeanImport.crn}" size="60"  required="true" requiredMessage="A CRN is required!"/>
					<h:message for="crn" showDetail="false" showSummary="true" style="color:red"/>
					
					<h:outputLabel for="Course" value="Course:" />
					<h:inputText id="Course"  value="#{actionBeanImport.course}" size="60" required="true" requiredMessage="Course name is required!"/>
					<h:message for="Course" showDetail="false" showSummary="true" style="color:red"/>
					
					<h:outputLabel for="rosterOrTest" value="Select Task:" />
					<h:selectOneRadio value="#{actionBeanImport.rosterOrTest}">
   							<f:selectItem itemValue="roster" itemLabel="Upload Roster" />
  							<f:selectItem itemValue="test" itemLabel="Upload Test" />
   					</h:selectOneRadio>
   					<h:outputText value=""/>	
											
					<h:outputLabel for="fileUpload" value="Select a file to upload:" />
					<t:inputFileUpload id="fileUpload" storage="file"
						value="#{actionBeanImport.uploadedFile}" size="60"  />
					<h:outputText value=""/>
						
					<h:outputLabel for="fileLabel" value="File Label:" />
					<h:inputText id="fileLabel" value="#{actionBeanImport.fileLabel}" required="true" requiredMessage="File Label required!"
						size="60" />
					<h:message for="fileLabel" showDetail="false" showSummary="true" style="color:red"/>
					
					<h:outputLabel for="update" value="Please update the form:" />
   					<h:commandButton id="update" action="#{actionBeanImport.methodRosterOrTest}" 
   							 value="Update form" />
   					<h:outputText value=""/>	
						
					<h:outputLabel for="fileType" value="Select the extension:" />
						<h:selectOneRadio value="#{actionBeanImport.fileType}">
   							<f:selectItem itemValue="csv" itemLabel="*.csv" />
  							<f:selectItem itemValue="txt" itemLabel="*.txt" />
   					</h:selectOneRadio>
   					<h:outputText value=""/>
   						
					<h:outputLabel for="start_date" value="Start Date:"
					rendered="#{actionBeanImport.checkRosterOrTest }" />
					<h:inputText id="start_date" value="#{actionBeanImport.startDate}" required="true" requiredMessage="Start Date of the test is required!"
						size="60"
						rendered="#{actionBeanImport.checkRosterOrTest }" />
					<h:message for="start_date" showDetail="false" showSummary="true" style="color:red"/>
						
					<h:outputLabel for="end_date" value="End Date:" 
					rendered="#{actionBeanImport.checkRosterOrTest }"/>
					<h:inputText id="end_date"  value="#{actionBeanImport.endDate}" required="true" requiredMessage="End Date of the test is required!"
						size="60" rendered="#{actionBeanImport.checkRosterOrTest }"/>
					<h:message for="end_date" showDetail="false" showSummary="true" style="color:red"/>
					
											
					<h:outputLabel for="duration" value="Duration:"
					rendered="#{actionBeanImport.checkRosterOrTest }"/>
					<h:inputText id="duration" value="#{actionBeanImport.duration}" required="true" requiredMessage="Duration of the test is required!"
						size="60" rendered="#{actionBeanImport.checkRosterOrTest }"/>	
					<h:message for="duration" showDetail="false" showSummary="true" style="color:red"/>
						
					
					<h:outputText value=""/>
					<h:commandButton id="Upload" 
						action="#{actionBeanImport.processFileUpload}" 
							 value="Upload File" />
					<h:outputText value=""/>	
						
				
				</h:panelGrid>
		
			<h:outputLabel rendered="#{actionBeanImport.fileImport}"
				value="Number of records imported: " />
			<h:outputText rendered="#{actionBeanImport.fileImport}"
				value="#{actionBeanImport.numberRows}" />

			<h:outputText rendered="#{actionBeanImport.fileImportError}"
				value="#{messageBean.errorMessage}" />
				<h:outputText value="#{messageBean.errorMessage}" />
				<hr>
				
				
			</h:form>
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