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
	<div class="container">
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
					<th>modifer</th>
					<th>total</th>
					<th>Supprimer</th>
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
					<form action='<c:url value="cart/update"/>' method="post">
						
						<input type="hidden" name="cart_id"
							value="${ carts.getId() }" />
						<td>
						<div class="form-group">
						<input type="number" name="quantity" class="form-control"
							value="${ carts.getCart_quantity() }" />
						</div>
						</td>
						<td>
						<div class="form-group">
						<button type="submit" class="btn btn-primary">modifier la quantite</button>
						</div>
						</td>
					</form>
					<td>${ carts.getFinal_price() }</td>
					<td>
					<form action='<c:url value="cart/delete"/>' method="post">
						<input type="hidden" name="cart_id"
							value="${ carts.getId() }" />
							<div class="form-group">
							<button type="submit" class="btn btn-danger">supprimer</button>
							</div>
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
		<div style="height : 300px"></div>
	</div>
	
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
