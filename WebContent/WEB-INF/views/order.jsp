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
	<c:import url="header.jsp" />
	<table class="table table-striped table-hover">

		<tr>
			<td>le nom de destinataire</td>
			<td>${ order.getDelivery_name() }"</td>
		</tr>
		<tr>
			<td>l'adresse de destinataire</td>
			<td>${ order.getDelivery_adress() }"</td>
		</tr>
		<tr>
			<td>zipcode</td>
			<td>${ order.getDelivery_zipCode() }"</td>
		</tr>
		<tr>
			<td>city</td>
			<td>${ order.getDelivery_city() }"</td>
		</tr>
		<tr>
			<td>date d'achat</td>
			<td>${ order.getDate_purchased() }"</td>
		</tr>
		<tr>
			<td>date de reception</td>
			<td>${ order.getDate_finished() }"</td>
		</tr>
		<tr>
			<td>status</td>
			<td>${ order.getStatus() }"</td>
		</tr>
		<tr>
			<c:forEach items="${ order.getOrder_products() }" var="product"
				varStatus="boucle">
				<tr>
					<td><a
						href='<c:url value="${ product.getProduct().getUrl() }"/>'>${ product.getProduct_name() }</a>
						<c:forEach items="${ product.getOrder_products_attributes() }"
							var="attributes" varStatus="boucle">
							<span>${ attributes.getOptions() }</span>
							<span>${ attributes.getOptions_values() }</span>
						</c:forEach></td>
					<td>le prix unitaire :${ product.getProduct_price() }</td>
					<td>le prix total :${ product.getFinal_price() }</td>
					<td>la quantité :${ product.getProduct_quantity() }</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
