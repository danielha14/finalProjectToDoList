<%@page import="java.nio.channels.SeekableByteChannel" errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	import="java.util.*,com.project.todolist.model.*"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>user items</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<jsp:useBean id="user" class="com.project.todolist.model.User" scope="session"/>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>
				<jsp:getProperty name="user" property="userName" />'s List of tasks
				
			</h1>

		</div>
		<div class="row">
				<div class="col-md-3">
					<a href="welcom" class="btn btn-primary" role="button"> <span
						class="glyphicon glyphicon-home"></span> Home
					</a>
				</div>

				<div class="col-md-3">
					<a href="useritems" class="btn btn-success" role="button"> <span
						class="glyphicon glyphicon-th-list"></span> TodoList
					</a>
				</div>

				
				<div class="col-md-3">
					<a href="logout" class="btn btn-warning" role="button"> <span
						class="glyphicon glyphicon-log-out"></span> Logout
					</a>
				</div>
				<div class="col-md-3">
					<%@ include file="additemform.jsp" %>

				</div>
			</div>

		
	</div>
	<!-- /row 1 -->
		
<%@ taglib tagdir="/WEB-INF/tags" prefix="abc" %>
<abc:showitems></abc:showitems>


<%@ include file="edititem.jsp" %>

<script type="text/javascript">
$("h3").hide().show(2000);
$('#Update').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) 
	var recipient = button.data('whatever') 
											
	var modal = $(this)
	
	modal.find('.modal-body #hidden').val(recipient)
});

</script>
</body>
</html>
