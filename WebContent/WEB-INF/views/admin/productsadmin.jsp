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
			<c:import url="admin_menu.jsp"/>
			<div class="col-md-10">
			
			<table class="table table-hover"e>
				<thead>
					<tr>
						<th>Image</th>
						<th>Produit</th>
						<th>Prix</th>
						<th>Vendeur</th>
					</tr>
				</thead>
				<tbody>
						
						<c:forEach items="${ requestScope.products }" var="product"
							varStatus="boucle">
							<tr style="height : 100px">
								<td>
									<img alt="${ product.name }" height="100" src="${product.getImage()}" />
								</td>
								<td>
									<a href="<c:url value="/p/${ product.getUrl() }"/>">${ product.getName() }</a>
								</td>
								<td>
									<span>${ product.getPrice() } MAD</span>
								</td>
								<td>
									<a href="<c:url value="/s/${ product.getSeller().getId() }_${product.getSeller().getSeller_name() }"/>">${product.getSeller().getSeller_name() }</a>
								</td>
							</tr>
						</c:forEach>
					
				</tbody>
			</table>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
