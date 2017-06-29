<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>commande</title>
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
	<div class="col-md-10">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">les produits</h3>
		</div>
		<div class="panel-body">
		<div class="row">
			<table class="table">
			<thead>
				<tr>
					<th>le produit</th>
					<th>prix unitaire</th>
					<th>Quantité</th>
					<th>total</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ requestScope.cart }" var="carts" varStatus="boucle1">
				<tr>
					<td>
					<a href='<c:url value="/p/${ carts.getProduct().getUrl() }"/>'>${ carts.getProduct().name }</a>
					<c:forEach items="${carts.getCart_attributes() }" var="cart_attributes" varStatus="boucle2">
					<br>
					<span>${ cart_attributes.getProduct_options_value().getProduct_options().getName() }:
						${ cart_attributes.getProduct_options_value().getName() }</span>
					</c:forEach>
					</td>
					<td>${ carts.getProduct().getPrice() }</td>
					<td>${ carts.getCart_quantity() }</td>
					<td>${ carts.getFinal_price() }</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
	</div>
	<form method="POST" action='<c:url value="/order/add/2"/>' >
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Choisir l'adresse de destination</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<select name="delivery_adress" class="form-control">
						<option value="">«Sélectionnez»</option>
						<c:forEach items="${client.getPerson().getAdresses()}"
							var="addresse" varStatus="boucle">
							<option value="${ addresse.getId() }">${ addresse.getCity() } ${ addresse.getAdress() } ${addresse.getZipCode() } </option>
						</c:forEach>
					</select> <span class="erreur">${form.errors['adresse_id']}</span>
				</div>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Inserer les informations de livraison</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label class="control-label" for="name">le nom de destinataire<span class="requis">*</span></label> <input
						type="text" id="name" name="delivery_name" class="form-control"
						value="<c:out value="${ product.getName() }"></c:out>" size="30"
						maxlength="30" /> <span class="erreur">${form.errors['delivery_name']}</span>
				</div>
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Ajouter</button>  
			</div>
		</div>
	</form>
	</div>
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>