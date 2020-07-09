<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<link href="${pageContext.request.contextPath}/style.css"
	rel="stylesheet">
<title>To-Do List Show Tasks</title>
</head>
<style type="text/css">
.content {
	max-width: 500px;
	margin: auto;
}
</style>
<body>
	<div class="content" style="align: center;">
		<br>
		<h1>Task List</h1>
		<br>
		<form>


			<table border="1" style="width: 100%" class="center">
				<tr>
					<th>Task</th>
					<th>Due Date</th>

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


		</form>
		<br>
		<h3 style="text-align: center;">
			<a href="addItem.jsp">Add Tasks</a> <a href="deleteItem.jsp">Delete
				Tasks</a>
		</h3>
	</div>
</body>

</html>
