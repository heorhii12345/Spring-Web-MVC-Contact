<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="./fragments/head.jsp"%>
</head>
<body style="background-color: #E9ECEB;">
<%@include file="./fragments/menu.jsp"%>
<div class="container m-2" style="background-color: #FCFCFC;">
	<div class="container-sm">
		<div class="row">
			<div class="col-md-12">
				<div class="container text-left mb-3">
                    <a href="create-contact" class="btn btn-primary btn-sm m-2"><i class="bi bi-plus-lg"></i> Add Contact</a><br>
                </div>
				<table class="table">
					<thead class="table-light">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Surname</th>
							<th scope="col">Phone</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contacts}" var="b">
							<tr>
								<th scope="row">${b.id}</th>
								<td>${b.name}</td>
								<td>${b.surname}</td>
								<td>${b.phone}</td>
								<td>
								    <a class="btn btn-warning btn-sm" href="update-contact/${b.id}" role="button">Update</a>
								    &nbsp;&nbsp;
								    <a class="btn btn-danger btn-sm" href="delete-contact/${b.id}" role="button">Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@include file="./fragments/js_inc.jsp"%>
</body>
</html>