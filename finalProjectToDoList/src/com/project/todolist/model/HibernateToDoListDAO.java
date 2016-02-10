package com.project.todolist.model;


import java.util.Arrays;
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
public class HibernateToDoListDAO implements IToDoListDAO , IToDoListUser{
	static final Logger	logger	= Logger.getLogger(HibernateToDoListDAO.class);
	/** The factory. */
	private SessionFactory factory = null;

	/** The instance. */
	private static HibernateToDoListDAO instance = null;

	/**
	 * Constructor HibernateToDoListDAO.
	 */
	private HibernateToDoListDAO(){
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

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListUser#addUser(com.abelski.samples.hibernate.User)
	 */
	@Override
	public void addUser(User user) throws TodoListPlatformException {
		logger.info("----------->addUser()");
		Session session = factory.openSession();
		try{

			session.beginTransaction();
			//if(!(session.get(user.getClass(),user.getEmail()) != null))
			//{
			session.save(user);
			session.getTransaction().commit();
		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with addUser method");
			}
		}
		finally{
			try{session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with addItem method");
				}

			}}
	}

	public boolean CheckIfUserExist( String userEmail )
	{
		logger.info("----------->CheckIfUserExist()");
		Session session = factory.openSession();
		boolean isExist = false;
		if(session.get(User.class,userEmail) != null);
		{
			isExist = true;
		}
		return isExist;
	}
	@Override
	public User getUserByEmail(String email) throws TodoListPlatformException {
		logger.info("----------->getUserByEmail()");
		Session session = factory.openSession();
		User user = null;
		try{
			session.beginTransaction();
			if(CheckIfUserExist(email))
			{
			user = (User)session.get(User.class,email);
			session.getTransaction().commit();
			}
		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getUserById method");
			}
		}
		finally{
			try
			{
				session.close();
			}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getUserById  method");
				}

			}}
		return user;
	}
	
   @Override
	public boolean authenticateUser(String userEmail, String password) throws TodoListPlatformException {
	   logger.info("----------->authenticateUser()");
	   boolean isAuthenticateUser  = false;
		User user = getUserByEmail(userEmail);         
		if(user!=null && user.getEmail().equals(userEmail) && user.getPassword().equals(password))
		{
			isAuthenticateUser = true;
		}
		return isAuthenticateUser;
	}
	
	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListUser#deleteUser(com.abelski.samples.hibernate.User)
	 */
	@Override
	public boolean deleteUser(User user) throws TodoListPlatformException {
		logger.info("----------->deleteUser()");
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();


		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with deteteUser method");
			}

		}
		finally{
			try{ session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with deleteUser method");
				}

			}
		}
		return true;


	}

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListUser#updateUser(com.abelski.samples.hibernate.User)
	 */
	@Override
	public void updateUser(User userToUpdate) throws TodoListPlatformException{
		logger.info("----------->updateUser()");
		Session session = factory.openSession();
		try{

			session.beginTransaction();
			session.update(userToUpdate);
			session.getTransaction().commit();

		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with updateUser method");
			}

		}
		finally{
			try{session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with updateUser method");
				}

			}}
	}

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListDAO#addItem(com.abelski.samples.hibernate.ToDoItem)
	 */
	@Override
	public void addItem(ToDoItem itemFromUserToAdd) throws TodoListPlatformException {
		logger.info("----------->addItem()");
		Session session = factory.openSession();
		try{

			session.beginTransaction();
			session.save(itemFromUserToAdd);
			session.getTransaction().commit();

		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with addItem method");
			}

		}
		finally{
			try{session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with addItem method");
				}

			}}
	}

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListDAO#deleteItem(com.abelski.samples.hibernate.ToDoItem)
	 */
	@Override
	public boolean deleteItem(ToDoItem itemFromUserToDelete) throws TodoListPlatformException {
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.delete(itemFromUserToDelete);
			session.getTransaction().commit();
		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with deleteItem method");
			}

		}
		finally{
			try{ session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with deleteItem method");
				}

			}
		}
		return true;


	}

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListDAO#updateItem(com.abelski.samples.hibernate.ToDoItem)
	 */
	public void updateItem(ToDoItem itemFromUserToUpdate) throws TodoListPlatformException {
		logger.info("----------->updateItem()");
		Session session = factory.openSession();
		try{
			session.beginTransaction();
			session.update(itemFromUserToUpdate);
			session.getTransaction().commit();

		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with updateItem method");
			}

		}
		finally{
			try{session.close();}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with updateItem method");
				}

			}}
	}

	/* (non-Javadoc)
	 * @see com.abelski.samples.hibernate.IToDoListDAO#getItems()
	 */
	@SuppressWarnings("finally")
	@Override
	public List getItems() throws TodoListPlatformException {
		logger.info("----------->getItems()");
		Session session = factory.openSession();
		List items = null;
		try{
			session.beginTransaction();
			items = session.createQuery("from ToDoItem").list();

		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getItems method");
			}

		}
		finally{
			try{ session.close();  }
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getItems method");
				}

			}
			return items;
		}


	}

	@SuppressWarnings("finally")
	@Override
	public List<ToDoItem> getUserItems(String email) throws TodoListPlatformException {
		logger.info("----------->getUserItems()");
		Session session = factory.openSession();
		List items = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery("from ToDoItem where USERID like '%"+email+"%'");
			//query.setParameter("user_email", email);
			items = query.list();
			session.getTransaction().commit();
		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getItems method");
			}

		}
		finally{
			try{ session.close();  }
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getItems method");
				}

			}
			return items;
		}
	}
	
	@Override
	public ToDoItem getItemById(int itemId) throws TodoListPlatformException {
		logger.info("----------->getItemById()");
		Session session = factory.openSession();
		ToDoItem item = null;
		try{

			session.beginTransaction();
			if((session.get(ToDoItem.class,itemId)!= null))
			{

				session.getTransaction().commit();
				item =(ToDoItem) session.get(ToDoItem.class, itemId);
			}
		}
		catch(HibernateException e){
			if(session.getTransaction()!=null){

				session.getTransaction().rollback();
				throw new TodoListPlatformException("error with getUserById method");
			}
		}
		finally{
			try
			{
				session.close();
			}
			catch(HibernateException e){
				if(session.getTransaction()!=null){
					session.getTransaction().rollback();
					throw new TodoListPlatformException("error with getUserById  method");
				}

			}}
		return item;

	}





}

