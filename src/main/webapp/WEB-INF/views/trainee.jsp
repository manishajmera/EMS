<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css
">
<% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Expires","0"); 
response.setDateHeader("Expires",-1); 
%> 
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div align="center">
	<span style="font-size:40px">Welcome ${designation} </span>
	</div>
		<div align="center">
		<p style="padding: 20px;"></p>
	<span style="font-size:20px">Your Account Details</span>
	</div>
	<table align="center" class="table table-hover" >
  	<tbody>
		<c:forEach items="${details}" var="employee">
    	<tr >
	         <td style="border:transparent ">Employee Id</td><td style="border:transparent"><c:out value="${employee.empId}"/></td></tr>
	         <tr > 
	         <td style="border:transparent">Employee Name</td><td style="border:transparent"><c:out value="${employee.name}"/></td></tr>
	         <tr >
	         <td style="border:transparent">Employee Password</td><td style="border:transparent"><c:out value="${employee.password}"/></td></tr>
	         <tr >
	         <td style="border:transparent">Employee Mentor</td><td style="border:transparent"><c:out value="${employee.supervisedBy}"/></td></tr>
	         <tr >
	         <td style="border:transparent">Employee Designation</td><td style="border:transparent"><c:out value="${employee.roleName}"/></td></tr>
	   		 <tr >
	         <td style="border:transparent">Employee Salary</td><td style="border:transparent"><c:out value="${employee.salary}"/></td></tr>
    		
	</c:forEach>
	</tbody>
</table>
<div align="center">
<a href="/"><button class="btn-primary" >Logout</button></a>
</div>


</div>

</body>
</html>