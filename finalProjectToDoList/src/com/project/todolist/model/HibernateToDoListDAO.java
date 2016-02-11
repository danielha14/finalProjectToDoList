package com.project.todolist.model;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * The Class HibernateToDoListDAO.
 */
public class HibernateToDoListDAO implements IToDoListDAO, IToDoListUser {
	static final Logger logger = Logger.getLogger(HibernateToDoListDAO.class);
	/** The factory. */
	private SessionFactory factory = null;

	/** The instance. */
	private static HibernateToDoListDAO instance = null;

	/**
	 * Constructor HibernateToDoListDAO.
	 */
	private HibernateToDoListDAO() {
		logger.info("----------->HibernateToDoListDAO() constructor");

		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	/**
	 * Gets the single instance of HibernateToDoListDAO.
	 *
	 * @return single instance of HibernateToDoListDAO
	 */
	public static HibernateToDoListDAO getInstance() {
		logger.info("----------->getInstance() ");
		if (instance == null) {
			instance = new HibernateToDoListDAO();
		}
		return instance;
	}

	/**
	 * addUser
	 * add new user to the database.
	 *
	 * @param userToAdd the user to add
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	@Override
	public void addUser(User user) throws TodoListPlatformException {
		logger.info("----------->addUser()");
		Session session = factory.openSession();
		try {

			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with addUser method");
			}
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with addItem method");
				}

			}
		}
	}

	/**
	 * Check if user exist.
	 *
	 * @param userEmail the user email
	 * @return true, if successful
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public boolean CheckIfUserExist(String userEmail) throws TodoListPlatformException {
		logger.info("----------->CheckIfUserExist()");
		Session session = factory.openSession();
		boolean isExist = false;
		try {

			session.beginTransaction();
			if (session.get(User.class, userEmail) != null)
				
			{
				isExist = true;
			}
		}
		catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException(e.getMessage());
			}
		}
		finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException(e.getMessage());
				}

			}
		}
		return isExist;
	}
	
	
	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	@Override
	public User getUserByEmail(String email) throws TodoListPlatformException {
		logger.info("----------->getUserByEmail()");
		Session session = factory.openSession();
		User user = null;
		try {
			session.beginTransaction();
			if (CheckIfUserExist(email)) {
				user = (User) session.get(User.class, email);
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getUserById method");
			}
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getUserById  method");
				}

			}
		}
		return user;
	}

	
	/**
	 * Authenticate user.
	 *
	 * @param userEmai the user email
	 * @param password the password
	 * @return true, if successful
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	@Override
	public boolean authenticateUser(String userEmail, String password) throws TodoListPlatformException {
		logger.info("----------->authenticateUser()");
		boolean isAuthenticateUser = false;
		User user = getUserByEmail(userEmail);
		if (user != null && user.getEmail().equals(userEmail) && user.getPassword().equals(password)) {
			isAuthenticateUser = true;
		}
		return isAuthenticateUser;
	}

	/**
	 * deleteUser
	 * delete user from the  database.
	 *
	 * @param userToDelete the user to delete
	 * @return true, if successful
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	@Override
	public boolean deleteUser(User user) throws TodoListPlatformException {
		logger.info("----------->deleteUser()");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with deteteUser method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with deleteUser method");
				}

			}
		}
		return true;

	}

	/**
	 * updateUser
	 * update existing  user in the database.
	 *
	 * @param userToUpdate the user to update
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	@Override
	public void updateUser(User userToUpdate) throws TodoListPlatformException {
		logger.info("----------->updateUser()");
		Session session = factory.openSession();
		try {

			session.beginTransaction();
			session.update(userToUpdate);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with updateUser method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with updateUser method");
				}

			}
		}
	}

	/**
	 * addItem
	 * add item to the items database.
	 *
	 * @param item the item
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	@Override
	public void addItem(ToDoItem itemFromUserToAdd) throws TodoListPlatformException {
		logger.info("----------->addItem()");
		Session session = factory.openSession();
		try {

			session.beginTransaction();
			session.save(itemFromUserToAdd);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with addItem method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with addItem method");
				}

			}
		}
	}

	/**
	 * deleteItem
	 * delete item from the  database.
	 * @param item the item
	 * @return true, if successful
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	@Override
	public boolean deleteItem(ToDoItem itemFromUserToDelete) throws TodoListPlatformException {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.delete(itemFromUserToDelete);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with deleteItem method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with deleteItem method");
				}

			}
		}
		return true;

	}


	/**
	 * updateItem
	 * update existing item in the database.
	 *
	 * @param item the item
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public void updateItem(ToDoItem itemFromUserToUpdate) throws TodoListPlatformException {
		logger.info("----------->updateItem()");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.update(itemFromUserToUpdate);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with updateItem method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with updateItem method");
				}

			}
		}
	}


	/**
	 * Gets the users.
	 *
	 * @return  list of all the users
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	@Override
	public List<User> getUsers() throws TodoListPlatformException {
		logger.info("----------->getUsers()");
		Session session = factory.openSession();
		List<User> items = null;
		try {
			session.beginTransaction();
			items = (List<User>)session.createQuery("from User").list();

		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getItems method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getItems method");
				}

			}
			return items;
		}
	}

	/**
	 * Gets the user items.
	 *
	 * @param userId the user id
	 * @return the user items
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	@SuppressWarnings("finally")
	@Override
	public List<ToDoItem> getUserItems(String email) throws TodoListPlatformException {
		logger.info("----------->getUserItems()");
		Session session = factory.openSession();
		List<ToDoItem> items = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ToDoItem where USERID like '%" + email + "%'");
			// query.setParameter("user_email", email);
			items = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getItems method");
			}

		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getItems method");
				}

			}
			return items;
		}
	}
	/**
	 * Gets the item by id.
	 *
	 * @param itemId the item id
	 * @return the items by id
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	@Override
	public ToDoItem getItemById(int itemId) throws TodoListPlatformException {
		logger.info("----------->getItemById()");
		Session session = factory.openSession();
		ToDoItem item = null;
		try {

			session.beginTransaction();
			if ((session.get(ToDoItem.class, itemId) != null)) {

				session.getTransaction().commit();
				item = (ToDoItem) session.get(ToDoItem.class, itemId);
			}
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getUserById method");
			}
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getUserById  method");
				}

			}
		}
		return item;

	}

}
