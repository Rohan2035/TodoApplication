<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Todo Application</title>
	<%@ include file="common/header.jspf" %>
</head>

<body>

	<%@ include file="common/navigation.jspf"%>
	<div class="container">
		<div><h3>Your Todos:</h3></div>
		
		<table class="table">
			
			<thead>
				
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Progress</th>
					<th>Remove</th>
					<th>Update</th>
				</tr>
			
			</thead>
			
			<tbody>
			
				<c:forEach items="${todos}" var="todo">
				
					<tr>
						
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="/delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
						<td><a href="/update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
					
					</tr>
				
				</c:forEach>
			
			</tbody>
			
		</table>
		
		<a href="/add-todo" class="btn btn-success">Add todo</a>
		
	</div>
	
	<%@ include file="common/footer.jspf" %>
		
</body>
</html>