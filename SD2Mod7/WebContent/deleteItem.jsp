<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<link href="${pageContext.request.contextPath}/style.css"
	rel="stylesheet">
<title>To-Do List Delete Task</title>
</head>
<style type="text/css">
.content {
	max-width: 500px;
	margin: auto;
}
</style>
<body>
	<br>
	<h1>Delete Task</h1>
	<br>
	<div class="content" style="align: center;">
		<form action="${pageContext.request.contextPath}/DelServlet"
			method="post">


				<table border="1" style="width: 100%">
					<tr>
						<th>Task</th>
						<th>Due Date</th>
						<th>Delete</th>
					</tr>
					<%
						try {
						Class.forName("com.mysql.jdbc.Driver");
						String url = "jdbc:mysql://localhost:3306/todo_list?characterEncoding=utf8";
						String username = "Chris";
						String password = "Password123";
						String query = "select * from todoitem";
						Connection conn = DriverManager.getConnection(url, username, password);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						while (rs.next()) {
					%>

					<tr>
						<td><%=rs.getString("taskName")%></td>
						<td><%=rs.getString("dueDate").substring(0, 10)%></td>

						<td><input type="radio" name="taskID"
							value="<%=rs.getString("taskID")%>"></td>
					</tr>

					<%
						}
					%>
					<%
						rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					%>
				</table>

			<br> <input type="reset" value="Clear" name="clear" /> <input
				type="submit" value="Delete" name="submit" />


		</form>
				<br>
		<h3 style=" text-align:center;">
			<a href="addItem.jsp">Add Tasks</a>
			<a href="showItems.jsp">Show Tasks</a> 

		</h3>
	</div>
</body>
</html>
