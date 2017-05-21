<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>| Accueil</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<c:url value="/resources/style/bootstrap3.3.7.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/style/footer.css"/>" />
<style type="text/css">
.validationError {
	color: #a50616;
	margin-left: 15px;
	text-align: justify;
	font-size: 10px;
}
</style>
</head>

<body>
	<c:import url="../header.jsp" />

	<div class="container-fluid">
		<hr />
		<div class="row">
			<c:import url="admin_menu.jsp" />

				<div class="col-sm-10">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Ajouter une option</h3>
						</div>
						<div class="panel-body">
							<form action='<c:url value="/options/add"/>' method="post">
								<div class="form-group">
									<input type="text" name="option_name" class="form-control"/>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Ajouter</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-sm-10">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Options</h3>
						</div>
						<div class="panel-body">
							<table class="table">
							<thead>
								<tr>
									<th>nom d'option</th>
									<th>ajouter valeurs</th>
									<th>modifier options</th>
									<th></th>
									<th>valeurs</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${applicationScope.options}" var="mapOptions">
									<tr>
										<td>${ mapOptions.value.getName() }</td>
										<td>
											<form action='<c:url value="/optionsvalue/add"/>' class="form-inline" method="post">
												<input type="hidden" name="option_id" value="${ mapOptions.key }" />
												<div class="form-group">
													<input type="text" name="value_name" class="form-control" placeholder="le nom de valeur"/>
												</div>
												<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span></button>
											</form>
										</td>
										<td>
											<form action='<c:url value="/options/update"/>' class="form-inline" method="post">
												<input type="hidden" name="option_id" value="${ mapOptions.key }" />
												<div class="form-group">
													<input type="text" name="option_name" class="form-control" value="${ mapOptions.value.getName() }" />
												</div>
												<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span></button>
											</form>
										</td>
										<td>
											<form action='<c:url value="/options/delete"/>' method="post">
												<input type="hidden" name="option_id"
													value="${ mapOptions.key }" />
												<div class="form-group">
													<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button>
												</div>
											</form>
										</td>
										<td>
											<table class="table">
											
											<tbody>
											<c:forEach items="${ mapOptions.value.getProduct_options_value() }" var="values">
											<tr>
												<td>${ values.getName() }</td>
												<td>
													<form action='<c:url value="/optionsvalue/update"/>' class="form-inline" method="post">
														<input type="hidden" name="value_id" value="${ values.getId() }" />
														<div class="form-group">
															<input type="text" name="value_name" class="form-control"
																value="${ values.getName() }" />
														</div>
														<button type="submit" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span></button>
													</form>
												</td>
												<td>
													<form action='<c:url value="/optionsvalue/delete"/>'
														method="post">
													<input type="hidden" name="value_id" class="form-control"
														value="${ values.getId() }" />
													<div class="form-group">
														<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button>
													</div>
													</form>
												</td>
											</tr>
											</c:forEach>
											</tbody>
											</table>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
		
		</div>
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
