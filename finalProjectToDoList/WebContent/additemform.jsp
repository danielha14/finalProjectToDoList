<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>

<title>Add new item</title>
</head>
<body>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Add new item
</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add new item</h4>
				</div>
				<div class="modal-body">
					<form action="addItem" method="post">
						<div class="form-group">
							<label for="recipient-name" class="control-label">title:</label>
							<input type="text" class="form-control" name="title"
								placeholder="title">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">description:</label>
							<textarea class="form-control" placeholder="description" name="description"></textarea>
						</div>
						<div class="modal-footer">
							<button class="btn btn-large btn-primary" type="submit">Add
								Item</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>

			</div>
		</div>
</div>
</body>
</html>
