<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link href="${pageContext.request.contextPath}/style.css"
	rel="stylesheet">
<title>To-Do List Add Task</title>
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
		<h1>Add Task</h1>
		<br>
		<form action="${pageContext.request.contextPath}/AddServlet"
			method="post">
			<table border="1" style="width: 100%" class="center">
				<tbody>
					<tr>
						<td>Task:</td>
						<td><input type="text" name="task" value="" size="75" /></td>
					</tr>
					<tr>
						<td>Date:</td>
						<td><input type="text" name="taskDate" value="" size="75" /></td>
					</tr>
				</tbody>
			</table>

			<br>
			<input type="reset" value="Clear" name="clear" /> <input
				type="submit" value="Submit" name="submit" />
		</form>
		<br>
		<h3 style=" text-align:center;">
			<a href="showItems.jsp">Show Tasks</a> 
			<a href="deleteItem.jsp">Delete Tasks</a>
		</h3>
	</div>
</body>
</html>