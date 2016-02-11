package com.project.todolist.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The interface IToDoListDAO.
 */
public interface IToDoListDAO {
	
	/**
	 * addItem
	 * add item to the items database.
	 *
	 * @param item the item
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public void addItem(ToDoItem item) throws TodoListPlatformException ;
	

	/**
	 * updateItem
	 * update existing item in the database.
	 *
	 * @param item the item
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public void updateItem(ToDoItem item) throws TodoListPlatformException ;
	
	
	/**
	 * deleteItem
	 * delete item from the  database.
	 * @param item the item
	 * @return true, if successful
	 * @exception TodoListPlatformException the todo list platform exception
	 */
	public boolean deleteItem(ToDoItem item) throws TodoListPlatformException ;

	
	/**
	 * Gets the user items.
	 *
	 * @param userId the user id
	 * @return the user items
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public List<ToDoItem> getUserItems(String userId) throws TodoListPlatformException ;
	
	/**
	 * Gets the item by id.
	 *
	 * @param itemId the item id
	 * @return the items by id
	 * @throws TodoListPlatformException the todo list platform exception
	 */
	public ToDoItem getItemById(int itemId)throws TodoListPlatformException ;

}
