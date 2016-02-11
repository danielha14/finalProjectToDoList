<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>welcome</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="bootstrap/js/appjs.js"></script>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h1>
				ToDo List <span class="glyphicon glyphicon-leaf"></span>
			</h1>
	
		</div>
		<!--jumbotron-->
		<%@ include file="navbar.jsp" %>
		

		<h1>
			<span class="glyphicon glyphicon-leaf"></span> Hello ! Welcome to
			your TodoList App!
		</h1>
		<!--welcome titel-->
		<p>
			Todo is useful when you have a deadline, need to focus, prioritize
			and <br>get things done quickly like home or school projects or
			dozens of detailed work tasks.
		</p>
		<div class="row">
			<div class="col-md-6">
				<h2>
					<span class="glyphicon glyphicon-pencil"></span> List management
				</h2>
				<!--welcome titel-->
				<p>Create and organize lists to help you stay on top of
					everything.</p>
				<h2>
					<span class="glyphicon glyphicon-comment"></span> Comments
				</h2>
				<!--welcome titel-->
				<p>Add comments and get updates, All in one place, synced
					everywhere.</p>
			</div>
			<!--left paragrph-->

			<div class="col-md-6">
				<h2>
					<span class="glyphicon glyphicon-list-alt"></span> Notes!
				</h2>
				<!--welcome titel-->
				<p>
					Add notes to your tasks to make sure you have everything <br>you
					need to complete the task..
				</p>

				<h2>
					<span class="glyphicon glyphicon-dashboard"></span> Daily Planner
				</h2>
				<!--welcome titel-->
				<p>The Any.do "Moment" feature is the daily planner you've been
					looking for.</p>
			</div>
			<!--right paragrph-->
		</div>
		<!-- paragrph-->

	</div>
	<!--container-->

</body>
</html>