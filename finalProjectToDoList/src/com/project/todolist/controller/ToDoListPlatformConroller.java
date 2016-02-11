package com.project.todolist.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.project.todolist.model.HibernateToDoListDAO;
import com.project.todolist.model.ToDoItem;
import com.project.todolist.model.TodoListPlatformException;
import com.project.todolist.model.User;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ToDoListPlatformConroller.
 */
@WebServlet("/controller/*")
public class ToDoListPlatformConroller extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant logger. */
	static final Logger logger = Logger.getLogger(ToDoListPlatformConroller.class);

	/**
	 * Instantiates a new to do list platform conroller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public ToDoListPlatformConroller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("----------->doGet() start");
		StringBuffer sb = request.getRequestURL();
		String url = sb.toString();
		RequestDispatcher dispatcher = null;
		if (url.endsWith("home")) {
			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} else if (url.endsWith("login")) {
			logger.info("----------->login page");
			dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else if (url.endsWith("toregister")) {
			logger.info("----------->register page");
			dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
			dispatcher.forward(request, response);
		} else if (url.endsWith("addItem")) {
			try {
				logger.info("----------->adding item page");
				HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
				List<ToDoItem> listOfUserItems = null;
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				// check if the title and the description not empty then add the
				// item
				if (!title.equals("") && !description.equals("")) {
					// get the user from the session to add his email to the
					// item that belong to him
					User user = (User) request.getSession().getAttribute("user");
					String userEmail = user.getEmail();
					ToDoItem item = new ToDoItem(title, description, userEmail);
					// add the item
					model.addItem(item);
					// get the list of the user items
					listOfUserItems = model.getUserItems(userEmail);
					request.getSession().setAttribute("listOfUserItems", listOfUserItems);
					dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
					dispatcher.forward(request, response);
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
					dispatcher.forward(request, response);
				}
			} catch (TodoListPlatformException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}

		} else if (url.endsWith("signin")) {
			try {
				logger.info("----------->signin page");
				HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
				String userEmail = request.getParameter("email");
				String userPassword = request.getParameter("password");
				boolean foundCookie = false;

				if (!userEmail.equals("") && !userPassword.equals("")) {

					boolean result = model.authenticateUser(userEmail, userPassword);
					if (result) {
						User user = model.getUserByEmail(userEmail);
						request.getSession().setAttribute("user", user);
						// check if the cookie is exist and set its value to
						// proper user name

						Cookie[] cookies = request.getCookies();
						for (Cookie cooki : cookies) {
							if (cooki.getName().equals("userName")) {
								cooki.setValue(user.getUserName());
								foundCookie = true;
								break;
							}
						}
						//create cookie if not exist
						if (!foundCookie) {
							Cookie userNameCooki = new Cookie("userName", user.getUserName());
							userNameCooki.setMaxAge(2000);
							response.addCookie(userNameCooki);
						}

						dispatcher = getServletContext().getRequestDispatcher("/welcom.jsp");
						dispatcher.forward(request, response);
					} else {//if the user not exist in the data base

						dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
						dispatcher.forward(request, response);
					}
				}
				else
				{//if the input is not valid
					dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
					dispatcher.forward(request, response);
				}
			} catch (TodoListPlatformException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (url.endsWith("useritems")) {
			try {
				logger.info("----------->useritems page");
				HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
				//get the user from sesstion
				User user = (User) request.getSession().getAttribute("user");
				List listOfUserItems = null;
				//get all the user items
				listOfUserItems = model.getUserItems(user.getEmail());
				request.getSession().setAttribute("listOfUserItems", listOfUserItems);
				dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
				dispatcher.forward(request, response);
			} catch (TodoListPlatformException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);

			}
		} else if (url.endsWith("UpdateItem")) {
			try {
				logger.info("----------->UpdateItem button");
				HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
				List<ToDoItem> listOfUserItems = null;
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				User user = (User) request.getSession().getAttribute("user");
				int itemid = Integer.parseInt(request.getParameter("itemid"));
				if(itemid > 0)//check if the id of the item is bigger than 0
				{
					String userId = user.getEmail();
					ToDoItem newItem = new ToDoItem(itemid, title, description, userId);
					//update the proper item
					model.updateItem(newItem);
					//get the user items
					listOfUserItems = model.getUserItems(userId);
					request.getSession().setAttribute("listOfUserItems", listOfUserItems);
					request.getSession().setAttribute("userId", userId);
					dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
					dispatcher.forward(request, response);

				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
					dispatcher.forward(request, response);
				}
				
			} catch (TodoListPlatformException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (url.endsWith("delete")) {
			try {
				logger.info("----------->delete button");
				HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
				List<ToDoItem> listOfUserItems = null;
				//get the user from the sesstion to remove his item
				User user = (User) request.getSession().getAttribute("user");
				int itemId = Integer.parseInt((String) request.getParameter("itemToDelete"));
				if(itemId >0)//check if the id of the item is bigger than 0
				{
					//get item by its id
				ToDoItem item = model.getItemById(itemId);
				model.deleteItem(item);
				listOfUserItems = model.getUserItems(user.getEmail());
				request.getSession().setAttribute("listOfUserItems", listOfUserItems);
				request.getSession().setAttribute("userId", user.getEmail());
				dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
				dispatcher.forward(request, response);
				}
				else//item id less than 0
				{
					dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
					dispatcher.forward(request, response);
				}
			}catch (TodoListPlatformException e) {

				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		} else 
			if (url.endsWith("register")) {
				try {
					logger.info("----------->register page");
					HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();

					String userName = request.getParameter("userName");
					String userEmail = request.getParameter("email");
					String userPassword = request.getParameter("password");
					//check if the input is valid
					if(!userName.equals("") && !userEmail.equals("") && !userPassword.equals("") )
					{
						//create user
						User user = new User(userName, userEmail, userPassword);
						//check if the user exist
						if (!model.CheckIfUserExist(user.getEmail())) {
							//add user
							model.addUser(user);
							dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
							dispatcher.forward(request, response);
						} else {//the user is exist in the DB
							dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
							dispatcher.forward(request, response);
						}
					}
					else//invalid input
					{
						dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
						dispatcher.forward(request, response);

					}
					
			} catch (TodoListPlatformException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (url.endsWith("logout")) {
			logger.info("----------->logout page");
			// remove session
			request.getSession().invalidate();
			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);

		} else if (url.endsWith("welcom")) {
			dispatcher = getServletContext().getRequestDispatcher("/welcom.jsp");
			dispatcher.forward(request, response);

		} else {
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
		logger.info("----------->doGet() terminate");
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
