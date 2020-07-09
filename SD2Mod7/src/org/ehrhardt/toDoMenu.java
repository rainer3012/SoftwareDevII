package org.ehrhardt;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class toDoMenu extends ToDoFacade {
	static Scanner sc = new Scanner(System.in);
	static Scanner taskScan = new Scanner(System.in);

	public static void mainMenu() throws ParseException {
		int selection;
		System.out.println("\nPlease make a selection:");
		System.out.println("1. Add a New Task");
		System.out.println("2. Remove a Task");
		System.out.println("3. List Pending Tasks");
		System.out.println("4. Quit");

		System.out.print("\nPlease enter your selection: ");
		selection = sc.nextInt();

		menuSelection(selection);

	}

	public static void menuSelection(int s) throws ParseException {

		switch (s) {
		case 1:
			addTask();
			mainMenu();
		case 2:
			removeTask();
			mainMenu();
		case 3:
			listTasks();
			mainMenu();
		case 4:
			quit();
			break;
		default:
			System.out.println("Invalid Selection.");
			mainMenu();
		}

	}

	public static void addTask() throws ParseException {
		System.out.println("Please enter name of new task: ");
		String taskName = taskScan.nextLine();

		System.out.println("Please enter due date of " + taskName + " (yyyy-MM-dd)");
		String dueDate = taskScan.nextLine();
		
		Date date = ToDoFacade.format.parse(dueDate);
		ToDoList.addTask(date, taskName);

	}

	public static void removeTask() throws ParseException {
		ToDoFacade.toDoList.showTasks();
		System.out.println("Which task should be removed?");
		int remTask = sc.nextInt();

		ToDoList.deleteTask(remTask);
	}

	public static void listTasks() throws ParseException {
		ToDoFacade.toDoList.showTasks();
		mainMenu();
	}

	public static void quit() {
		System.out.println("Thanks for using the To-Do Program");
		System.exit(0);
	}

}
