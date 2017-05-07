<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Guide</title>
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
      <h5 class="w3-padding-32">About the Application</h5>
      <p class="w3-text-grey"> Online Test Taking System is a web application that keeps track of the information related to assignments,
       quizzes and exams taken by students, courses in which they are enrolled and professors teaching those courses. 
       It is an online assessment tools that can be accessed by admin users, professors and students. 
       Professors can upload and export student roster, upload assignments and view results. 
       Students can take online assessments and view their result.</p><br/>
       <!--  <img src="images/index.png" height="400" width="600">-->
       <p class="w3-text-grey">By clicking the "Login to Database" button, you can log into the test taking application.</p>
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