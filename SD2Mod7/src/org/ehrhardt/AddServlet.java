package org.ehrhardt;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loadTask = request.getParameter("task");
		String loadDate = request.getParameter("taskDate");
		ToDoFacade.loadPersistance();

		Date date;
		try {
			date = ToDoFacade.format.parse(loadDate);
			ToDoList.addTask(date, loadTask);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		response.sendRedirect("showItems.jsp");  
		
	}

}
