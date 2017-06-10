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
	<link rel="stylesheet" href="<c:url value="/resources/style/rating-svg.css"/>" />
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
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			<li><a href='<c:url value="/product/block?${ product.getId() }"/>'>bloquer le produit</a></li>
			</ul>
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
					<li class="list-group-item">
					${ attribute.getProduct_options_value().getProduct_options().getName() } : ${ attribute.getProduct_options_value().getName() }
					<br>${ attribute.getOptions_values_price() }
					</li>
					</c:forEach>
				  </ul>
				</div>
				<fieldset>
					<legend>Commentaires</legend>
					<div>
						<c:if test="${! empty product.getReviews() }" >
							<table class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th colspan="3"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${product.getReviews() }" var="review">
										<tr>
											<td>
												<div   id="rating_${review.getId()}"></div>		    
											</td>
											<td colspan="3">${review.getText() }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/rating-svg.js"/>"></script>
	
	<script type="text/javascript">
	
		<c:forEach items="${product.getReviews() }" var="review">
		
			$("#rating_${review.getId()}").starRating({
				initialRating: '<c:out value="${review.getRating()}"/>',
				strokeColor: '#894A00',
			    readOnly: true,
			    starSize: 35,
				strokeWidth: 2
			})	    
		
		</c:forEach>  
	</script>
</body>
</html>
