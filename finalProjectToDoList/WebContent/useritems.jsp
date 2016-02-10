<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	import="java.util.*,com.project.todolist.model.*"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="bootstrap/js/appjs.js"></script>
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 40px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 700px;
}

.container-narrow>hr {
	margin: 30px 0;
}

/* Main marketing message and sign up button */
.jumbotron {
	margin: 60px 0;
	text-align: center;
}

.jumbotron h1 {
	font-size: 72px;
	line-height: 1;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}

/* Supporting marketing content */
.marketing {
	margin: 60px 0;
}

.marketing p+h4 {
	margin-top: 28px;
}
</style>
</head>

<body>
	<%
		User user = (User) session.getAttribute("user");
	%>

	<div class="container-narrow">

		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="toregister">register</a></li>
				<li><a href="login">login</a></li>
				<li><a href="about">about</a></li>
			</ul>
			<h3 class="muted">TodoLis</h3>
		</div>
		<div class="jumbotron">
			<h1>TodoList</h1>

			<a class="btn btn-large btn-success" href="#">watch the video</a>
		</div>
	</div>


	<h1>
		<%
			out.println(user.getUserName() + "'s");
		%>
		List of tasks
	</h1>

	

	<div class="row">
		<div class="col-sm-4">
			<h3>ToDoItem Title</h3>
			<p>Lorem ipsum dolor sit amet, consectetur ...</p>

		</div>
		<div class="col-sm-4">
			<h3>Description</h3>
			<p>Lorem ipsum dolor sit amet...</p>

		</div>
		<%@ include file="additemform.jsp"%>

	</div>
	 

	<%@ taglib tagdir="/WEB-INF/tags" prefix="abc"%>
	<abc:showitems></abc:showitems>


	<%@ include file="edititem.jsp"%>


</body>
</html>
