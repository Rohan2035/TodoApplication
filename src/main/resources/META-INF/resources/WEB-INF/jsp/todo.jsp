<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Add todo</title>
	<%@ include file="common/header.jspf" %>
	<link rel="stylesheet" href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.css">
</head>
<body>
	
	<%@ include file="common/navigation.jspf"%>
	
	<div class="container mx-auto">
	
		<form:form method="POST" modelAttribute="todo">
			
			<fieldset class="mb-2">
				<label for="description"><strong>Description:</strong></label>
				<form:input type="text" name="description" id="description" path="description"/>
				<form:errors path="description" cssClass="text-warning" />
			</fieldset>
			
			<fieldset class="mb-2">
				<label for="targetDate"><strong>Target Date:</strong></label>
				<form:input type="text" name="targetDate" id="targetDate" path="targetDate"/>
				<form:errors path="targetDate" cssClass="text-warning" />
			</fieldset>
			
			
			<form:input type="hidden" path="id" />
			<form:input type="hidden" path="done" />
			
			<input type="submit" class="btn btn-success">
		
		</form:form>
	
	</div>
	
	<%@ include file="common/footer.jspf" %>
	<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
	
	<script type="text/javascript">
		
		$('#targetDate').datepicker({
	    	format: 'yyyy-mm-dd',
		});
	
	</script>
		
</body>
</html>