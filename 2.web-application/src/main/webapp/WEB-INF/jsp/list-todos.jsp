<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Todo's for ${sessionName}</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<table class="table">
			<caption>Your todos are:</caption>
			<thead>
				<th>Description</th>
				<th>Date</th>
				<th>Is it Done?</th>
				<th>Update</th>
				<th>Delete</th>
			</thead>
			<tbody>
				<c:forEach items="${mTodosList}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success" href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-todo">Add a todo</a>
			<p>${sessionName} is logged in!!!</p>
		</div>
		
	</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>