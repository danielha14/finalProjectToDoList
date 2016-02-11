<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>

<div class="modal fade" id="Update" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">Update Item</h4>
      </div>
      <div class="modal-body">
        <form action="UpdateItem" method="post">
          <div class="form-group">
          	<input type="hidden" class="form-control" id="hidden"  name="itemid">
            <label for="recipient-name" class="control-label">Title</label>
            <input type="text" class="form-control" name="title">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Description</label>
            <textarea class="form-control" name="description"></textarea>
          </div>
                <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary">Save changes</button>
      </div>
        </form>
      </div>

    </div>
  </div>
</div>



	