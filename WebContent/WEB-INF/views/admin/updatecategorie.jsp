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
<body>
	<div class="container-fluid">
		<hr />
		<div class="row">
		<c:import url="admin_menu.jsp" />
	<div class="col-md-6 col-md-offset-2">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">ajouter une categorie</h3>
		</div>
		<div class="panel-body">
	<form action='<c:url value="/categorie/update"/>' method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="categorie_id" value="${ categorie.getId() }" />
		<div class="form-group">
		<label for="categorie_name" class="control-label">le nom </label> <input type="text" class="form-control"
			name="categorie_name"
			value="<c:out value="${ categorie.getName() }"></c:out>" /> <span
			class="erreur">${form.errors['categorie_name']}</span>
			</div>
		<div class="form-group">
		<label class="control-label"
			for="categorie_image">l'image de categorie</label> <input type="file" class="form-control"
			name="categorie_image" /><br /> <span class="erreur">${form.errors['categorie_image']}</span>
		</div>
		<div class="form-group">
		<label for="parent_id" class="control-label">le parent </label> 
		<select name="parent_id" class="form-control">
			<option value="0">«Sélectionnez»</option>
			<c:forEach items="${applicationScope.categorie}" var="mapCategorie"
				varStatus="boucle">
				<option value="${ mapCategorie.key }">${ mapCategorie.value.getName() }</option>
			</c:forEach>
		</select> <span class="erreur">${form.errors['parent_id']}</span><br> <br />
		</div>
		
		<div class="form-group">
			    <div class="col-sm-offset-2">
			      <button type="submit" class="btn btn-warning">modifier</button>
			    </div>
			 </div>
	</form>
	</div>
	</div>
	</div>
	</div></div>
	<c:import url="../footer.jsp" />
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
