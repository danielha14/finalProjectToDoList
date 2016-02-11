package com.project.todolist.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * the Interface IToDoListUser.
 */
public interface IToDoListUser {
	
	/**
	 * addUser
	 * add new user to the database.
	 *
	 * @param userToAdd the user to add
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public void addUser(User userToAdd) throws TodoListPlatformException ;

	/**
	 * deleteUser
	 * delete user from the  database.
	 *
	 * @param userToDelete the user to delete
	 * @return true, if successful
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public boolean deleteUser(User userToDelete) throws TodoListPlatformException ;

	/**
	 * updateUser
	 * update existing  user in the database.
	 *
	 * @param userToUpdate the user to update
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public void updateUser(User userToUpdate) throws TodoListPlatformException ;

	/**
	 * Gets the user by email.
	 *
	 * @param email the email
	 * @return the user by email
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public User getUserByEmail(String email) throws TodoListPlatformException ;

	/**
	 * Authenticate user.
	 *
	 * @param userEmai the user email
	 * @param password the password
	 * @return true, if successful
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public boolean authenticateUser(String userEmail, String password) throws TodoListPlatformException;

	/**
	 * Check if user exist.
	 *
	 * @param userEmail the user email
	 * @return true, if successful
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public boolean CheckIfUserExist(String userEmail) throws TodoListPlatformException ;
	
	/**
	 * Gets the users.
	 *
	 * @return  list of all the users
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public List<User> getUsers() throws TodoListPlatformException ;

}
