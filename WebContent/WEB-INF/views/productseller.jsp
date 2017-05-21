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
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <li><a href='<c:url value="/product/add"/>'>ajouter produit</a></li>
      <li><a href='<c:url value="/product/update?${ product.id }"/>'>modifier le produit</a></li>
      </ul>
      <form class="navbar-form navbar-left">
        <input type="hidden" name="product_id" value="${ product.id }" />
        <button type="submit" class="btn btn-default">supprimer le produit</button>
      </form>
      </div>
	<ol class="breadcrumb">
	<c:forEach items="${ requestScope.categorie }" var="categorie" varStatus="boucle">
	<li class="active">${ categorie.getName() }</li>
	</c:forEach>
	</ol>
	<div class="panel panel-default">
	  <div class="panel-heading">product informations</div>
	  <ul class="list-group">
	    <li class="list-group-item"><label>reference</label>:${ product.getId() }</li>
	    <li class="list-group-item"><label>le nom de produit</label>:${ product.getName() }</li>
	    <li class="list-group-item"><label>les details</label>:${ product.getDescription() }</li>
	    <li class="list-group-item"><label>le nombre de vue</label>:${ product.getProduct_viewed() }</li>
	    <li class="list-group-item"><label>la quantité</label>:${ product.getQuantity() }</li>
	    <li class="list-group-item"><label>date d'ajoute du produit</label>:${ product.getDate_added() }</li>
	    <li class="list-group-item"><label>date de dernier modification</label>:${ product.getLast_modified() }</li>
	    <li class="list-group-item"><label>date de disponibilte du produit</label>:${ product.getDate_available() }</li>
	    <li class="list-group-item">
	    <label>disponible pour l'achat</label>:
		<c:choose>
			<c:when test="${ product.isStatus() }">
				<span>oui</span>
			</c:when>
			<c:otherwise>
				<span>non</span>
			</c:otherwise>
		</c:choose>
	    </li>
	  </ul>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">product informations</div>
	  <ul class="list-group">
	    <c:forEach items="${ product.getProducts_attributes() }" var="attribute" varStatus="boucle">
			<li>
			${ attribute.getProduct_options_value().getProduct_options().getName() } : ${ attribute.getProduct_options_value().getName() }
			<br>${ attribute.getOptions_values_price() }
			</li>
		</c:forEach>
	  </ul>
	</div>
	<fieldset>
		<legend>les reviews</legend>
		<c:forEach items="${ product.reviews }" var="review"
			varStatus="boucle">
			<label>${ review.client_name }</label>
			<br>
			<span>${ review.review_rating }</span>
			<p>${ review.review_text }</p>
			<br>
		</c:forEach>
	</fieldset>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
