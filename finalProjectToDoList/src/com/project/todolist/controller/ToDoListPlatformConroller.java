package com.project.todolist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.log4j.Logger;

import com.project.todolist.model.HibernateToDoListDAO;
import com.project.todolist.model.IToDoListDAO;
import com.project.todolist.model.ToDoItem;
import com.project.todolist.model.TodoListPlatformException;
import com.project.todolist.model.User;
import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

/**
 * Servlet implementation class ToDoListPlatformConroller
 */
@WebServlet("/controller/*")
public class ToDoListPlatformConroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger	logger	= Logger.getLogger(ToDoListPlatformConroller.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ToDoListPlatformConroller() 
	{
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		logger.info("----------->doGet() start");
		PrintWriter writer = response.getWriter();
		StringBuffer sb = request.getRequestURL();
		String url = sb.toString();
		RequestDispatcher dispatcher = null;
		if (url.endsWith("home"))
		{
			dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		} 
		else
			if (url.endsWith("login"))
			{
				logger.info("----------->login page");
				dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
			else
				if (url.endsWith("toregister"))
				{
					logger.info("----------->register page");
					dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
					dispatcher.forward(request, response);
				}
				else
					if (url.endsWith("addItem")) {
						logger.info("----------->adding item page");
						HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
						List listOfUserItems = null;
						String title = request.getParameter("title");
						String description = request.getParameter("description");
						User user = (User) request.getSession().getAttribute("user");
						String userEmail = user.getEmail();					
						ToDoItem item = new ToDoItem(8, title, description,userEmail);
						try {
							model.addItem(item);
							listOfUserItems = model.getUserItems(userEmail);
							request.getSession().setAttribute("listOfUserItems", listOfUserItems);							
							dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
							dispatcher.forward(request, response);
						}
						catch (TodoListPlatformException e) 
						{
							dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
							dispatcher.forward(request, response);
						}

					} 
					else
						if (url.endsWith("signin")) {
							logger.info("----------->signin page");
							HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
							String userEmail = request.getParameter("email");
							String userPassword = request.getParameter("password");
							boolean foundCookie = false;
							try {				
								boolean result = model.authenticateUser(userEmail, userPassword);
								if (result) {
									User user = model.getUserByEmail(userEmail);
									request.getSession().setAttribute("user", user);
									//check if the cookie is exist and set its value to proper user name
									Cookie[] cookies = request.getCookies();
									for (Cookie cooki : cookies) {
										if(cooki.getName().equals("userName")){
											cooki.setValue(user.getUserName());
											foundCookie = true;
											break;
										}
									}
									if(!foundCookie){
										Cookie userNameCooki  = new Cookie("userName", user.getUserName());
										userNameCooki.setMaxAge(2000);
										response.addCookie(userNameCooki);
									}

									dispatcher = getServletContext().getRequestDispatcher("/welcom.jsp");
									dispatcher.forward(request, response);
								}
								else 
								{

									dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
									dispatcher.forward(request, response);
								}
							}
							catch (TodoListPlatformException e) 
							{
								dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
								dispatcher.forward(request, response);
							}
						}
						else
							if(url.endsWith("useritems"))
							{
								logger.info("----------->useritems page");
								HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
								User user = (User)request.getSession().getAttribute("user");
								List listOfUserItems = null;
								try {	
									listOfUserItems = model.getUserItems(user.getEmail());
									request.getSession().setAttribute("listOfUserItems", listOfUserItems);
									request.getSession().setAttribute("user", user);
									dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
									dispatcher.forward(request, response);
								}
								catch (TodoListPlatformException e) 
								{
									dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
									dispatcher.forward(request, response);

								}
							}
							else
								if (url.endsWith("UpdateItem"))
								{
									logger.info("----------->UpdateItem button");
									HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
									List<ToDoItem> listOfUserItems = null;
									String title = request.getParameter("title");
									String description = request.getParameter("description");
									User user = (User) request.getSession().getAttribute("user");	
									int itemid = Integer.parseInt(request.getParameter("submit_id"));
									String userId = user.getEmail();
									ToDoItem newItem = new ToDoItem(itemid, title, description, userId);
									try 
									{
										model.updateItem(newItem);
										listOfUserItems = model.getUserItems(userId);
										request.getSession().setAttribute("listOfUserItems", listOfUserItems);
										request.getSession().setAttribute("userId", userId);
										dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
										dispatcher.forward(request, response);

									}
									catch (TodoListPlatformException e) 
									{
										dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
										dispatcher.forward(request, response);			
									}
								}
								else 
									if (url.endsWith("delete")) 
									{
										logger.info("----------->delete button");
										HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
										List listOfUserItems = null;
										User user = (User)request.getSession().getAttribute("user");
										int itemId = Integer.parseInt((String)request.getParameter("itemToDelete"));
										try 
										{
											ToDoItem item = model.getItemById(itemId);
											model.deleteItem(item);
											listOfUserItems = model.getUserItems(user.getEmail());
											request.getSession().setAttribute("listOfUserItems", listOfUserItems);
											request.getSession().setAttribute("userId", user.getEmail());
											dispatcher = getServletContext().getRequestDispatcher("/useritems.jsp");
											dispatcher.forward(request, response);
										}
										catch (TodoListPlatformException e) {

											dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
											dispatcher.forward(request, response);
										}
									}
									else
										if (url.endsWith("register"))
										{
											logger.info("----------->register page");
											HibernateToDoListDAO model = HibernateToDoListDAO.getInstance();
											String userName = request.getParameter("userName");
											String userEmail = request.getParameter("email");
											String userPassword = request.getParameter("password");
											User user = new User(userName,userEmail,userPassword);


											try 
											{
												model.addUser(user);
												dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
												dispatcher.forward(request, response);

											}
											catch (TodoListPlatformException e) {
												dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
												dispatcher.forward(request, response);
											}
										}
										else
											if (url.endsWith("logout"))
											{
												logger.info("----------->logout page");
												//remove session
												request.getSession().removeAttribute("user");
												request.getSession().removeAttribute("listOfUserItems");
												//remove cookie
												Cookie[] cookies = request.getCookies();
												for (Cookie cooki : cookies) {
													if(cooki.getName().equals("userName")){
														cooki.setMaxAge(0);	
														response.addCookie(cooki);
													}									
												}
											
												dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
												dispatcher.forward(request, response);

											}
											else 
												if (url.endsWith("toUpdate"))
												{
													dispatcher = getServletContext().getRequestDispatcher("/edititem.jsp");
													dispatcher.forward(request, response);

												}
												else
												{
													dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
													dispatcher.forward(request, response);
												}
		logger.info("----------->doGet() terminate");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
