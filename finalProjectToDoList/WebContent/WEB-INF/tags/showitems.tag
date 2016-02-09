<%@ tag language="java" pageEncoding="windows-1255" import="java.util.*,com.project.todolist.model.*"%>

<%@ attribute name="count" required="false" %>

<div class="panel-group" id="accordion">
		<%
			List<ToDoItem> items = (ArrayList<ToDoItem>) session.getAttribute("listOfUserItems");
			int i = 1;
			if (items != null) {
				for (ToDoItem item : items) {
		%>
		<div class="panel panel-default template">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapse<%out.print(i);%>"> <%
 	out.println(item.getTitle());
 %>
					</a>
				</h4>
			</div>
			<%
				out.println("<div id=" + '"' + "collapse" + i + '"' + "class=" + '"' + "panel-collapse collapse"
								+ '"' + " >");
						out.println("<div class=" + '"' + "panel-body" + '"' + ">Title : " + item.getTitle().toString()
								+ "</div>");
						out.println("<div class=" + '"' + "panel-body" + '"' + ">Description :"
								+ item.getDescription().toString() + "</div>");
						out.println("<div class=" + '"' + "panel-footer" + '"' + ">");
						i++;
			%>
			<form action="delete" method="post">
				<input type="hidden" name="itemToDelete"
					value="<%out.print(item.getId());%>">
				<button class="btn btn-danger" type="submit">delete</button>
			</form>
				
				<button type="button" class="btn btn-success" data-whatever="<%out.print(item.getId());%>" data-toggle="modal" data-target="#Update">Update</button>
		</div>
	</div>

	<%
		}
		}
	%>
<%
	String item = request.getParameter("submit_id");
	session.setAttribute("itemId",item );
%>