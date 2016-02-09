/**
 * Created by DANIEL on 09/12/2015.
 */

var elementId = 0;
var itemid = 0 ;
$(function(){
    var $template = $(".template");
    var hash = 2;

//$('#signinid').click(function(){
//	$.ajax({
//		url : 'controller/addItem',
//		data : {email:$('email').val(),
//			password:$('password').val()},
//			success : function(ressponse){
//				$('#ajaxGetUserServletResponse').text(responseText);
//			}
//	});
//});
  
//add todoItem
    $(".btn-add-panel").on("click",function(){
        itemid++;
       var todoItem =  $("<div>").attr({'class':'panel panel-default template','id':itemid})
        .append($('<div>').attr({'class':'panel-heading'})
        .append($('<h4>').attr({'class':'panel-title'})
                .append($('<a>ToDoItem</a>')
                    .attr({'data-toggle':'collapse','data-parent':'#accordion','href':'#col-'+itemid}))))
        .append($('<div>').attr({'id':'col-'+itemid,'class':'panel-collapse collapse'})
                .append($('<div>Title:</div>').attr({'class':'panel-body'}))
                .append($('<div> Description : </div>').attr({'class':'panel-body'}))
                .append($('<div>').attr({'class':'panel-footer'})
                    .append($('<a>Delete</a>')
                    .attr({'id':itemid,'type':'button','class':'btn item-delete btn-sm btn-primary'}))));

        $("#accordion").append(todoItem);
    });

//delete todoItem
     $(".container").on('click','.item-delete',function(){
        var itemId = $(this).attr("id");
         $("#"+itemId).remove("div,.panel");
        // $("#"+itemId).toggleClass('myClass');
         //alert(itemId);
    });

    $("h3").hide().show(2000);

    $('#exampleModal').on('show.bs.modal', function (event) {
    	  var button = $(event.relatedTarget) // Button that triggered the modal
    	  var recipient = button.data('whatever') // Extract info from data-* attributes
    	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    	  var modal = $(this)
    	  modal.find('.modal-title').text('New message to ' + recipient)
    	  modal.find('.modal-body input').val(recipient)
    	})
  	
});