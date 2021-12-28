<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<div class="w3-row-padding w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-twothird">
      <h1>Users Guide</h1>
      <h5 class="w3-padding-32">Database Access Page</h5>
      <p class="w3-text-grey"> The admin user can perform the following functionalities in the Database Access Page:  </p>
      <h5 class="w3-padding-32">1) Table List</h5>
      <img src="images/dbAccess.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">Clicking on "Table List" button displays all the tables in the particular database schema.</p>
      	<h5 class="w3-padding-32">2) Column List</h5>
      <img src="images/dbAccess1.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">Clicking on "Column List" button displays all the columns of the table selected, from the list of tables. </p>
      	<h5 class="w3-padding-32">3) Display Table</h5>
      <img src="images/dbAccess2.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">Displays all the columns and rows of the table selected from the list of tables. Also, shows the query executed and number 
      	 of rows and columns affected by executing the SQL query.  </p>
      	<h5 class="w3-padding-32">4) Display Selected Columns</h5>
      <img src="images/dbAccess3.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">Displays only the columns that have been selected of a particular table.  </p>
      	<h5 class="w3-padding-32">5) Drop Selected Table</h5>
      <img src="images/dbAccess4.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">A particular table can be dropped from the database schema by clicking the "Drop Selected Table" button </p>
      	<h5 class="w3-padding-32">6) Execute User Query</h5>
      <img src="images/dbAccess5.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">The user can write write an appropriate SQL query and execute it by clicking the "Execute User Query" button. The result of 
      		the query executed is displayed at the bottom of the page  </p>
      		<h5 class="w3-padding-32">7) Create Table</h5>
      <img src="images/dbAccess6.png" height="400" width="650"><br/>
      	<p class="w3-text-grey">Creates tables like APP_USERS, course, TEST_TABLE, Roster etc if they do not already exist in the database </p>
      
    </div>
    
    <br/>
    

    <div class="w3-third w3-center">
      <i class="fa fa-edit w3-padding-64 w3-text-aqua" style="font-size:100px;color:aqua"></i>
    </div>
  </div>
</div>
</f:view>
</body>
</html>