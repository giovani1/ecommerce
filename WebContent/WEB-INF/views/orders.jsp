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
	<div class="container">
		<hr />
	
	<div class="col-md-8 col-md-offset-2">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">les orders</h3>
		</div>
		<div class="panel-body">
		<div class="row">
	<table class="table">
	<thead>
		<tr>
			<th>order</th>
			<th>Date d'achat</th>
			<th>Statut de la commande</th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${requestScope.orders}" var="order"
			varStatus="boucle">
			<tr>
				<td><a href='<c:url value="/order?id=${order.getId() }"/>'>Commande</a></td>
				<td><span>${ order.getDate_purchased() }</span></td>
				<td>${ order.getStatus() }</td>
				<c:if test="${ !order.getStatus().equals('delivred') }">
				<td><a class="btn btn-primary" href='<c:url value="/order/confirm?id=${order.getId() }"/>'>Confirmer la réception</a></td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div></div></div></div></div>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
