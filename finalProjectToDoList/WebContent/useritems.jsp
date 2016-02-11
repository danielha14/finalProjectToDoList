<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	import="java.util.*,com.project.todolist.model.*"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="bootstrap/js/appjs.jsp"></script>
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
			<div class="col-sm-4">
				<h3>ToDoItem Title</h3>
				<p>Lorem ipsum dolor sit amet, consectetur ...</p>

			</div>
			<div class="col-sm-4">
				<h3>Description</h3>
				<p>Lorem ipsum dolor sit amet...</p>

			</div>
			<%@ include file="additemform.jsp" %>

		</div>
	</div>
	<!-- /row 1 -->
		
<%@ taglib tagdir="/WEB-INF/tags" prefix="abc" %>
<abc:showitems></abc:showitems>


<%@ include file="edititem.jsp" %>


</body>
</html>
