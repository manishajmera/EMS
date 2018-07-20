<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Expires","0"); 
response.setDateHeader("Expires",-1); 
%> 
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css
">


<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#name").keyup(function(){
	    var name = $("#name").val();
	    var flag = 0;
	    
	    for(var i=0;i<name.length;i++)
	    {
	        if( (name.charAt(i)>='a' && name.charAt(i)<='z') || (name.charAt(i)>='A' && name.charAt(i)<='Z') || name.charAt(i)==' '){
	        	$("#cross").removeClass().addClass("not_error");
	            $("#tick").removeClass().addClass("valid");
	            continue;
	        }
	        else{
	            $("#cross").removeClass().addClass("error");
	            $("#tick").removeClass().addClass("not_error");
	            break;
	        }
	    }
	    
	 });
	  $("#number").keyup(function(){
	    var number = $("#number").val();
	    if(number.length>10)
	    {
	    	$("#cross1").removeClass().addClass("error");
            $("#tick1").removeClass().addClass("not_error");
       }else{
	        for(var i=0;i<number.length;i++)
	        {
	            if( (number.charAt(i)>='1' && number.charAt(i)<='9') || number.charAt(i)=='0'){
	            	$("#cross1").removeClass().addClass("not_error");
	            	$("#tick1").removeClass().addClass("valid");
	                continue;
	            }
	            else{
	            	 $("#cross1").removeClass().addClass("error");
	            $("#tick1").removeClass().addClass("not_error");
	            break;
	            }
	        }
	     }
	    
	 });
	  $("#number1").keyup(function(){
		    var number = $("#number1").val();
		    if(number.length>10)
		    {
		    	$("#cross2").removeClass().addClass("error");
	            $("#tick2").removeClass().addClass("not_error");
	       }else{
		        for(var i=0;i<number.length;i++)
		        {
		            if( (number.charAt(i)>='1' && number.charAt(i)<='9') || number.charAt(i)=='0'){
		            	$("#cross2").removeClass().addClass("not_error");
		            	$("#tick2").removeClass().addClass("valid");
		                continue;
		            }
		            else{
		            	 $("#cross2").removeClass().addClass("error");
		            $("#tick2").removeClass().addClass("not_error");
		            break;
		            }
		        }
		     }
		    
		 });
	  
});



</script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

td {
	text-align: center;
}

th {
	text-align: center;
}
/* The Modal (background) */
.error {
	display: inline;
	color: red;
}

.not_error {
	display: none;
}

.valid {
	display: inline;
	color: green;
}

button {
	background-color: Transparent;
	background-repeat: no-repeat;
	border: none;
	cursor: pointer;
	overflow: hidden;
	outline: none;
}

.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.close1 {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close1:hover, .close1:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
<title></title>
</head>
<body>

	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close">&times;</span>
			<form class="form" action="add" method="post" name="addForm">
				<h3 align="center">Enter Employee Details</h3>
				<table class="table" align="center">
					<input type="hidden" hidden="true" value="${designation}" name="session_id" >

					<tr>
						<td style="border: transparent">Enter Employee Name :</td>
						<td style="border: transparent"><input type="text"
							name="name1" id="name" required><span class="not_error"
							id="tick">&#10004;</span> <span class="not_error" id="cross">
								X</span></td>
					</tr>
					<tr>
						<td style="border: transparent">Enter Employee Password :</td>
						<td style="border: transparent"><input type="password"
							name="password" required></td>
					</tr>
					<tr>
						<td style="border: transparent">Enter Employee Role-Id :</td>
						<td style="border: transparent"><select style="width: 180px;height:28px; background:white;" 
							name="roleName">
								<c:forEach items="${roles}" var="role">
									<option><c:out value="${role.roleName}" /></option>
								</c:forEach>

						</select></td>
					</tr>
					<tr>
						<td style="border: transparent">Enter Employee Supervisor's
							Id :</td>
						<td style="border: transparent"><input type="text"
							name="supervisorid" id="number" required> <span
							class="not_error" id="tick1">&#10004;</span> <span
							class="not_error" id="cross1"> X</span></td>
					</tr>
					<tr>
						<td style="border: transparent">Enter Employee Salary :</td>
						<td style="border: transparent"><input type="text"
							name="salary" id="number1" required> <span
							class="not_error" id="tick2">&#10004;</span> <span
							class="not_error" id="cross2"> X</span></td>
					</tr>
				</table>
				<div align="center">
					<button class="btn btn-primary">Submit
					</button>
					
				</div>

			</form>
		</div>

	</div>



	<!-- The Modal -->
	<div id="myModal1" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<span class="close1">&times;</span>
			<form class="form" action="update" method="post">
				<h3 align="center">Employee Details</h3>
				<table class="table" align="center">
					<input type="hidden" hidden="true" value="${designation}"
						name="session_id">
					<input type="hidden" value="" name="id" id="empId">
					<tr>
						<td style="border: transparent">Employee Name :</td>
						<td style="border: transparent"><input id="empName"
							type="text" name="name1" id="name" required><span
							class="not_error" id="tick">&#10004;</span> <span
							class="not_error" id="cross"> X</span></td>
					</tr>
					<tr>
						<td style="border: transparent">Employee Password :</td>
						<td style="border: transparent"><input id="password"
							type="password" name="password" required></td>
					</tr>
					<tr>
						<td style="border: transparent">Employee Designation :</td>
						<td style="border: transparent"><select style="width: 180px;height:28px; background:white;"
							name="roleName" id="roleName" >
								<c:forEach items="${roles}" var="role">
									<option><c:out value="${role.roleName}" /></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td style="border: transparent">Employee Supervisor's Id :</td>
						<td style="border: transparent"><input type="text"
							name="supervisorid" id="mentor" required> 
							
					</tr>
					<tr>
						<td style="border: transparent">Employee Salary :</td>
						<td style="border: transparent"><input type="text"
							name="salary" id="salary" required>
							
					</tr>
				</table>
				<div align="center">
					<button class="btn btn-primary">Update</button>
				</div>

			</form>
		</div>

	</div>
	<div class="container">


		<div align="center">
			<span style="font-size: 40px">Welcome ${designation}</span><br>
			<span style="color: red"> ${errormsg}</span>
			<span style="color: green"> ${success_msg}</span>
		</div>

		<div align="right">

			<button id="myBtn">
				<i class="fa fa-user-plus " style="font-size: 22px;"></i>
			</button>
			<a href="/"><button class="btn-primary">Logout</button></a>

		</div>
		<hr>
		<table align="center" class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Employee Id</th>
					<th scope="col">Employee Name</th>
					<th scope="col">Mentor Id</th>
					<th scope="col">Designation</th>
					<th scope="col">Delete Operation</th>
					<th scope="col">Promotion</th>
					<th scope="col">Edit Profile</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td style="border: transparent"><c:out
								value="${employee.empId}" /></td>
						<td style="border: transparent"><c:out
								value="${employee.name}" /></td>
						<td style="border: transparent"><c:out
								value="${employee.supervisedBy}" /></td>
						<td style="border: transparent"><c:out
								value="${employee.roleName}" /></td>
						<c:choose>
							<c:when test="${designation=='CEO' && employee.roleName=='HR'}">
								<td style="border: transparent">
									<form action="delete" method="post">
										<input type="hidden" hidden="true" value="${employee.empId}"
											name="id"> <input type="hidden" hidden="true"
											value="${designation}" name="session_id">

										<button type="submit"
											onclick="return confirm('Are you sure you want to delete this ')">
											<i class="fa fa-trash" aria-hidden="true"
												style="font-size: 22px; color: red;"></i>
										</button>
									</form>
								</td>
							</c:when>

							<c:when
								test="${employee.roleName=='Trainee' || employee.roleName=='Manager' || employee.roleName=='Director'}">
								<td style="border: transparent">
									<form action="delete" method="post">
										<input type="hidden" hidden="true" value="${employee.empId}"
											name="id"> <input type="hidden" hidden="true"
											value="${designation}" name="session_id">

										<button type="submit"
											onclick="return confirm('Are you sure you want to delete this employee')">
											<i class="fa fa-trash" aria-hidden="true"
												style="font-size: 22px; color: red;"></i>
										</button>

									</form>
								</td>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${(employee.roleId==3) || (employee.roleId==4) || (employee.roleId==5) }">
								<td style="border: transparent">
									<form action="promote" method="post">
										<input type="hidden" hidden="true" value="${designation}"
											name="session_id"> <input type="hidden" hidden="true"
											value="${employee.empId}" name="id"> <input
											type="hidden" hidden="true" value="${employee.roleId}"
											name="roleId">
										<button type="submit"
											onclick="return confirm('Are you sure you want to promote this employee')">
											<i class="fa fa-arrow-circle-up"
												style="font-size: 22px; color: green;"></i>
										</button>
									</form>
								</td>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${(employee.roleId==3) || (employee.roleId==4) || (employee.roleId==5)  }">
								<td style="border: transparent">

									<button
										onclick="myFunction(${employee.empId},'${employee.name}','${employee.password}','${employee.roleName}',${employee.supervisedBy}, ${employee.salary})"
										id="${employee.empId}">
										<i class="fa fa-edit" style="font-size: 22px; color: green;"></i>
									</button>


								</td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



	<script>
function myFunction(a,b,c,d,e,f) {
	/* alert(a);
	alert(b);
	alert(c);
	alert(d);
	alert(e);
	alert(f); */
    var modal1 = document.getElementById('myModal1');
	modal1.style.disply="block";
	document.getElementById('empId').value = a;

	document.getElementById('empName').value = b;
	document.getElementById('password').value = c;
	document.getElementById('roleName').value = d;
	document.getElementById('mentor').value = e;
	document.getElementById('salary').value = f;

	




    var span1 = document.getElementsByClassName("close1")[0];
    span1.onclick = function() {
        modal1.style.display = "none";
    }
        modal1.style.display = "block";
}

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");


// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
   
}
// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</body>
</html>