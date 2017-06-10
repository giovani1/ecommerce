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
			<c:import url="seller_menu.jsp"/>
			<div class="col-md-10">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">choisir la categorie</h3>
				</div>
				<div class="panel-body">
					<form method="POST" action='<c:url value="/product/c/update"/>'>
						<div class="hidden">
							<input type="hidden" name="product_id" value="${ product.getId() }" />
						</div>
						<div class="form-group">
							<select name="categorie_id">
								<option value="">«Sélectionnez»</option>
								<c:forEach items="${ applicationScope.categories }"
									var="mapCategorie" varStatus="boucle">
									<c:choose>
										<c:when
											test="${ mapCategorie.key==product.getCategorie().getId() }">
											<option value="${ mapCategorie.key }" selected>${ mapCategorie.value.getName() }</option>
										</c:when>
										<c:otherwise>
											<option value="${ mapCategorie.key }">${ mapCategorie.value.getName()}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-warning">modifier</button>
					</form>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">ajouter des nouvelles options</h3>
				</div>
				<div class="panel-body">
					<form method="POST" action='<c:url value="/product/o/add"/>'>
						<div class="hidden">
							<input type="hidden" name="product_id" value="${ product.getId() }" />
						</div>
						<div class="form-group">
						<c:forEach items="${ applicationScope.options }" var="mapOption"
							varStatus="boucle">
							<label for="option_${ mapOption.key }">${ mapOption.value.getName() }</label>
							<c:forEach items="${ mapOption.value.getProduct_options_value() }"
								var="value" varStatus="boucle">
								<div class="checkbox">
									<label> <input type="checkbox"
										name="option_${ mapOption.key }" value="${ value.getId() }">
										${ value.getName() }
									</label>
								</div>
								<label for="price_${ value.getId() }">le prix ajouter de
									cette options:</label>
								<input type="text" class="form-control"
									name="price_${ value.getId() }" />
								<span class="erreur">${form.errors['option']}</span>
							</c:forEach>
						</c:forEach>
						</div>
						<button type="submit" class="btn btn-primary">ajouter</button>
					</form>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">modifier les options</h3>
				</div>
				<div class="panel-body">
					<table  class="table">
						<tr>
							<th>nom option</th>
							<th>nom de valeur d'option</th>
							<th>modifier le prix</th>
							<th>supprimer</th>
						</tr>
						<c:forEach items="${ product.getProducts_attributes() }"
							var="attributes" varStatus="boucle">
							<tr>
							<td>${ attributes.getProduct_options_value().getProduct_options().getName() }</td>
							<td>${ attributes.getProduct_options_value().getName() }</td>
							<td>
								<form method="post" action='<c:url value="/product/o/update"/>' class="form-inline">
									<input type="hidden" name="attribute_id"
										value="${ attributes.getId() }" /> 
										<div class="form-group">
									    <label class="sr-only" for="exampleInputAmount">le prix</label>
									    <div class="input-group">
									      <div class="input-group-addon">DH</div>
									      <input type="text"
										name="price" value="${ attributes.getOptions_values_price() }" />
									    </div>
									  </div>
										
									<button type="submit" class="btn btn-warning">modifier</button>
								</form>
							</td>
							<td>
								<form method="post" action='<c:url value="/product/o/delete"/>'>
									<input type="hidden" name="attribute_id"
										value="${ attributes.getId() }" /> 
										<button type="submit" class="btn btn-danger">supprimer</button>
								</form>
							</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">inserer les informations du produit</h3>
					</div>
					<div class="panel-body">
					<form method="POST" action='<c:url value="/product/update"/>'
					enctype="multipart/form-data" >
					<div class="hidden">
						<c:set var="product" value="${ requestScope.product }" />
						<input type="hidden" name="product_id" value="${ product.getId() }" />
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">le titre</label> <input type="text" id="name"
							name="name" value="<c:out value="${product.getName() }"></c:out>"
							size="30" maxlength="30"  class="form-control" /> <span class="erreur">${form.errors['name']}</span>
					</div>
					<div class="form-group">
						<label for="description" class="col-sm-2 control-label">details</label> <input type="text"
							id="description" name="description"
							value="<c:out value="${product.getDescription() }"></c:out>"  class="form-control"/> <span
							class="erreur">${form.errors['description']}</span>
					</div>
					<div class="form-group">
						<label for="quantity" class="col-sm-2 control-label">la quantité de produit</label> <input
							type="text" id="quantity" name="quantity"
							value="<c:out value="${product.getQuantity() }"></c:out>"  class="form-control"/> <span
							class="erreur">${form.errors['quantity']}</span>
					</div>
					<div class="form-group">
						<label for="image" class="col-sm-2 control-label">l'image de produit</label> <input type="file"
							name="image"  class="form-control"/> <span class="erreur">${form.errors['image']}</span>
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">le prix de produit</label> <input type="text"
							id="price" name="price"
							value="<c:out value="${product.getPrice() }"></c:out>"  class="form-control"/> <span
							class="erreur">${form.errors['price']}</span>
					</div>
					<div class="form-group">
						<label for="date_available" class="col-sm-2 control-label">le date de diponibilite du
							produit</label> <input type="date" id="date_available"
							name="date_available"
							value="<c:out value="${product.getDate_available() }"></c:out>"  class="form-control"/>
						<span class="erreur">${form.errors['date_available']}</span>
					</div>
					<button type="button" class="btn btn-primary">modifier</button>
					</form>
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
