<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Database Access</title>
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
    <li><a class="w3-padding-large w3-white" href= "mainMenu.jsp">Home</a></li>
    <li class="w3-hide-small"><a href="docs/Programmers Guide.pdf" class="w3-padding-large w3-hover-white">Programmers Guide</a></li>
    <li class="w3-hide-small"><a href="userGuide5.jsp" target="_blank" class="w3-padding-large w3-hover-white">Users Guide</a></li>
    <li class="w3-hide-small"><a href= "logout.jsp"  class="w3-padding-large w3-hover-white">Logout</a></li>
    
  </ul>
</div>
	<h:form>
	<!-- Header -->
<header class="w3-container w3-amber w3-center w3-padding-128">
  <h1 class="w3-margin w3-jumbo">Database Access</h1>
  <p class="w3-xlarge">Group: f16g322</p>
    <p class="w3-xlarge"> Welcome <h:outputText value="#{user.userName}"/></p> 
  <hr>
	<center>
	<div class="w3-container">
	<div class="btn-group-justified">

   
	 <h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.displayTableName }" value="Table List">
	 </h:commandButton> &nbsp;&nbsp;&nbsp;
	
<h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.displayColumns }" value="Columns List">
	 </h:commandButton> &nbsp;&nbsp;&nbsp;
	
	 <h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.displayTables }" value="Display Table">
	 </h:commandButton>&nbsp;&nbsp;&nbsp;
	 
	<h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.displaySelectedColumns }" value="Display Selected Columns">
	</h:commandButton>&nbsp;&nbsp;&nbsp;
	<c:if test="${user.user==1 }">  
	<h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.dropSelectedTable }" value="Drop Selected Table">
	</h:commandButton>&nbsp;&nbsp;&nbsp;
	 </c:if> 
	<h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.executeUserQuery }" value="Execute User Query">
	</h:commandButton>&nbsp;&nbsp;&nbsp;
	 <c:if test="${user.user==1 }">
	<h:commandButton styleClass="w3-btn btn-primary" action="#{actionBeanDatabaseAccess.createTables }" value="Create Tables">
	</h:commandButton>
	</c:if>
	  </div>
	  </div>
	  <hr>
	 </center>	
	 	<h:selectOneListbox	size="10" styleClass="selectOneListbox_mono"
		value="#{actionBeanDatabaseAccess.tableName}"
		rendered="#{actionBeanDatabaseAccess.tableListRendered}">
		<f:selectItems value="#{actionBeanDatabaseAccess.tableViewList}" />
		</h:selectOneListbox>
		&nbsp;&nbsp;&nbsp;
		<h:selectManyListbox
						size="10" styleClass="selectManyListbox"
						value="#{actionBeanDatabaseAccess.columnNamesSelected}"
						rendered="#{actionBeanDatabaseAccess.columnListRendered}">
			<f:selectItems value="#{actionBeanDatabaseAccess.columnNames}" />
			</h:selectManyListbox>
			&nbsp;&nbsp;&nbsp;
			<h:inputTextarea value="#{actionBeanDatabaseAccess.userQuery}" cols="30" rows="10" >
			</h:inputTextarea>

<div style= "background-attachment: scroll; overflow:auto; height:400px;
background-repeat: repeat">
	
	<hr>
	<h:panelGrid columns="2" >
	<h:outputText value="Query" rendered="#{actionBeanDatabaseAccess.renderQuery }" />
	<h:outputText value="#{dbmsUserBean.query}" rendered="#{actionBeanDatabaseAccess.renderQuery }"></h:outputText>
	<h:outputText value="Number of Columns" />
	<h:outputText value="#{actionBeanDatabaseAccess.numberColumns}"></h:outputText>
	<h:outputText value="Number of Rows" />
	<h:outputText value="#{actionBeanDatabaseAccess.numberRows}"></h:outputText>
	<h:outputText value="" />
	<h:outputText value="#{messageBean.responseMessage}"></h:outputText>
	<h:outputText value="" />
	<h:outputText value="#{messageBean.errorMessage}"></h:outputText>
	</h:panelGrid>
	
	<hr>
	<t:dataTable
			value="#{actionBeanDatabaseAccess.result}"
			var="row"
			rendered="#{actionBeanDatabaseAccess.renderSet}" >
			<t:columns		
					var="col"
					value="#{actionBeanDatabaseAccess.columnNames}">
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
					
			
					
					<t:dataTable
			value="#{actionBeanDatabaseAccess.result}"
			var="row"
			rendered="#{actionBeanDatabaseAccess.renderCols}" >
			<t:columns		
					var="col"
					value="#{actionBeanDatabaseAccess.columnNamesSelected}">
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
					
					<t:dataTable
			value="#{actionBeanDatabaseAccess.result}"
			var="row"
			rendered="#{actionBeanDatabaseAccess.renderUserQuery}" >
			<t:columns		
					var="col"
					value="#{actionBeanDatabaseAccess.columnNames}">
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
	</div>
	
	
	</h:form>
	
	 
		
 </f:view>
</body>
</html>