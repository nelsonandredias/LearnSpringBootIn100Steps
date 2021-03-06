<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
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
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${todo.targetDate}" /></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-todo">Add a todo</a>
			<p>${sessionName} is logged in!!!</p>
		</div>

	</div>
<%@ include file="common/footer.jspf" %>