<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log In</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="appjs.js"></script>
</head>

<body>

<div class="container">
    <div class="jumbotron">
        <h1 align="center">Log In</h1>
    </div>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4" >
            <form role="form" action = "signin" method ="post" class="form-horizontal sign-in-form">
               <label for="email" class="sr-only">Email address</label>
                <div class="form-group col-sm-10">
                   Email Address <input type="email" id="email" class="form-control" name="email" placeholder="Email address" required autofocus>
                </div>
                <label for="password" class="sr-only">Password</label>
                <div class="form-group col-sm-10">
                    Password <input type="password" id="password" class="form-control" name="password" placeholder="Password"  required>
                </div>
                <div class="form-group col-sm-10">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                </div>
            </form>
        </div>
    </div>

</div><!-- /container -->

</body>
</html>
