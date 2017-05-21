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
			<div class="col-md-10">
			<div class="col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Ajouter une categorie</h3>
					</div>
					<div class="panel-body">
						<form action='<c:url value="/categorie/add"/>' method="post"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="categorie_name" class="control-label">le nom <span class="requis">*</span></label>
								<input type="text" class="form-control" name="categorie_name"
									value="<c:out value="${categorie.name}"></c:out>" />
									<span class="erreur">${form.errors['categorie_name']}</span>
							</div>
							<div class="form-group">
								<label for="categorie_image" class="control-label">Icon de la categorie <span
									class="requis">*</span></label> <input type="file" name="categorie_image" class="form-control" />
								<p class="help-block">image de categorie</p>
								<span class="erreur">${form.errors['categorie_image']}</span>
							</div>
							<div class="form-group">
								<label for="parent_id" class="control-label">le parent </label> <select name="parent_id"
									class="form-control">
									<option value="">«Sélectionnez»</option>
									<c:forEach items="${applicationScope.categories}"
										var="mapCategorie" varStatus="boucle">
										<option value="${ mapCategorie.key }">${ mapCategorie.value.name }</option>
									</c:forEach>
								</select> <span class="erreur">${form.errors['parent_id']}</span>
							</div>
							<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Ajouter</button>
							    
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Categories</h3>
					</div>
					<div class="panel-body">
						<table class="table">
						<thead>
							<tr>
								<th>categorie</th>
								<th>icon</th>
								<th>parent</th>
								<th></th>
								<th></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${applicationScope.categories}" var="mapCategorie"
								varStatus="boucle">
								<tr>
									<th>${ mapCategorie.value.getName() }</th>
									<td><img src="${ mapCategorie.value.getImage() }" /></td>
									<td>${ mapCategorie.value.getParent().getName() }</td>
									<td>
									<a class="btn btn-warning" href='<c:url value="/categorie/update?${ mapCategorie.key }"/>' role="button"><span class="glyphicon glyphicon-edit"></span></a>
									</td>
									<td>
										<form action='<c:url value="categorie/delete"/>' method="post">
											<input type="hidden" name="categorie_id"
												value="${ mapCategorie.key }" />
												<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button>
										</form>
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
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
