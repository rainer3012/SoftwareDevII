package org.ehrhardt;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDoItem extends ToDoFacade implements Comparable<Object> {

	// Variables
	@Id
	private int taskID;
	private Date dueDate;
	private String taskName;

	// Constructor
	public ToDoItem(Date dueDate, String taskName, int taskID) {
		this.dueDate = dueDate;
		this.taskName = taskName;
		this.taskID = taskID;
	}

	// Default Constructor
	public ToDoItem() {
	}

	@Override
	public int compareTo(Object o) {
		return getDueDate().compareTo(((ToDoItem) o).getDueDate());
	}

	// Getters and Setters
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	// toString()
	public String toString() {

		String itemDueDate = dueDate.toString().substring(0, 10);

		return (taskID + "\t" + itemDueDate + "\t" + taskName);
	}

}
