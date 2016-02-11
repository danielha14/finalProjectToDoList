package com.project.todolist.servies;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.List;
import com.project.todolist.model.HibernateToDoListDAO;
import com.project.todolist.model.ToDoItem;
import com.project.todolist.model.TodoListPlatformException;
import com.project.todolist.model.User;

import javax.ws.rs.Path;

@Path("/userlist")
public class UsersListJersey {

	@GET
	@Produces("text/html")
	public String getClichedMessage() {
		String result = "<h1>List of the Users</h1> <br/>";
		try {
			HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
			List<User> Users = (List<User>) model.getUsers();
            result += "<table border='6' style='width:30%'>"+ "<tr><th>Users</th></tr>";
			for (User user : Users) {
				result += "<tr>" + "<td>" + user.getUserName() + "</td>" + "</tr>";
			}
			result += "</table>";
		} catch (TodoListPlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}