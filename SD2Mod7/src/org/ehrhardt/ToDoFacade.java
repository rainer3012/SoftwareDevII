package org.ehrhardt;

/**
* CEN-4025c 
* Module 5 Assignment
* @author Christopher Ehrhardt
* @since 5/12/2020
* @version 2
* 
* Version 2 notes
* For Module 5 I have added Hibernate integration allowing the program to store persistant data into
* a MySQL database, there was some challenges getting the items to play nice with the arraylist however
* it seems to be functioning nicely.
* 
* Version 1 notes
*  For this assignment I setup a simple menu-driven command line application to support ease of use
*  if unnecessary the toDoMenu.java could be omitted for portability. I added date support because 
*  for me it is required in a to-do program, however for maximum portability those sections of code 
*  could be removed and the program simply store an array of string objects.
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ToDoFacade {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	static ToDoList toDoList = new ToDoList(); // create toDoList Object
	static int storedObjects = 0; // existing DB entries

	// loadPersistance method connects to the DB via Hibernate and loads the DB
	// objects into the toDoList
	public static void loadPersistance() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration con = new Configuration().configure().addAnnotatedClass(ToDoItem.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Query<?> query = session.createQuery("select max(taskID) from ToDoItem todoitem");

		if (query.uniqueResult() != null) {
			storedObjects = (int) query.uniqueResult();
			toDoList.setTaskIDCounter(storedObjects); // update the task id counter to account for existing entries
		}

		// only create new objects and add them to the ArrayList if there are existing
		// database entries
		if (storedObjects > 0) {
			for (int i = 1; i <= storedObjects; i++) {
				ToDoItem item = session.get(ToDoItem.class, i);
				ToDoList.addExistingTask(item);
			}
		}
	}

	public static void main() throws ParseException {

		loadPersistance();
		System.out.println("Welcome to Christopher's To-Do Program");
		if (toDoList.getTaskIDCounter() > 0) {
			System.out.println("Existing records imported.");
		}
		toDoMenu.mainMenu();
	}

}
