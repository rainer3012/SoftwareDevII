package org.ehrhardt;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ToDoList extends ToDoFacade {

	// Variables
	private static ArrayList<ToDoItem> toDoItemList = new ArrayList<ToDoItem>();
	private static int taskIDCounter = 0;

	// Default Constructor
	public void toDoList() {
	}

	// addTask Method
	public static void addTask(Date dueDate, String taskName) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration con = new Configuration().configure().addAnnotatedClass(ToDoItem.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		taskIDCounter++;
		int idNum = taskIDCounter;

		ToDoItem taskID = new ToDoItem(dueDate, taskName, idNum);

		toDoItemList.add(taskID);
		String itemDueDate = dueDate.toString().substring(0, 10);
		System.out.println("New To-Do task " + taskName + " due on " + itemDueDate + " added!");

		Transaction tx = session.beginTransaction();
		session.save(taskID);
		tx.commit();
		session.close();

		// Recreate ArrayList
		toDoItemList.clear();
		ToDoFacade.loadPersistance();

	}
		

	// This method loads existing database record into the toDoItemList ArrayList
	public static void addExistingTask(ToDoItem existingTask) {
		toDoItemList.add(existingTask);
	}

	// deleteTask Method
	public static void deleteTask(int taskID) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Configuration con = new Configuration().configure().addAnnotatedClass(ToDoItem.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		Object persistentInstance = session.load(ToDoItem.class, taskID);
		if (persistentInstance != null) {
			session.remove(persistentInstance);
		}
		tx.commit();
		session.close();

		toDoItemList.remove(taskID - 1);
		toDoItemList.clear();
		ToDoFacade.loadPersistance();

		System.out.println("To-Do task removed.");
	}

	// showTasks Method
	public void showTasks() {
		System.out.println("\nUpcoming To-Do Items: \n");
		System.out.println("ID\tDue Date\tTo-Do");
		System.out.println("--\t--------\t-----");

		toDoItemList.clear();
		ToDoFacade.loadPersistance();
		for (int i = 0; i < toDoItemList.size(); i++) {
			ToDoItem item = toDoItemList.get(i);
			if (item == null) {
			} else
				System.out.println(item.toString());
		}
	}
	
	public int getTaskIDCounter() {
		return taskIDCounter;
	}

	public void setTaskIDCounter(int taskIDCounter) {
		this.taskIDCounter = taskIDCounter;
	}

}