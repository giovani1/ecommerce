<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<link rel="stylesheet" href="<c:url value="/resources/style/jquery-ui1.12.1.min.css"/>">
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
	<form method="POST" action='<c:url value="/product/add"/>'
		enctype="multipart/form-data">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Choisir la categorie</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<select name="categorie_id" class="form-control">
						<option value="">«Sélectionnez»</option>
						<c:forEach items="${applicationScope.categories}"
							var="mapCategorie" varStatus="boucle">
							<option value="${ mapCategorie.key }">${ mapCategorie.value.getName() }</option>
						</c:forEach>
					</select> <span class="erreur">${form.errors['categorie_id']}</span>
				</div>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Choisir les options et leurs prix</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>Option</th>
							<th colspan="4">valeurs</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ applicationScope.options }" var="mapOption"
							varStatus="boucle">
							<tr>
								<td>
									<label class="control-label" for="option_${ mapOption.key }">${ mapOption.value.getName() }</label>
								</td>
								<td>
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>prix additional pour cette option</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${ mapOption.value.getProduct_options_value() }"
												var="value" varStatus="boucle">
												<tr>
													<td>
														<label><input type="checkbox" 
															name="option_${ mapOption.key }" value="${ value.getId() }">
															${ value.getName() }
														</label>
													</td>
													<td>						
														<input class="form-control" type="text" class="form-control"
															name="price_${ value.getId() }" />
														<span class="erreur">${form.errors['option']}</span>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Inserer les informations du produit</h3>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label class="control-label" for="name">le titre <span class="requis">*</span></label> <input
						type="text" id="name" name="name" class="form-control"
						value="<c:out value="${ product.getName() }"></c:out>" size="30"
						maxlength="30" /> <span class="erreur">${form.errors['name']}</span>
				</div>
				<div class="form-group">
					<label class="control-label" for="description">details <span class="requis">*</span></label>
					<input class="form-control" type="text" id="description" name="description"
						value="<c:out value="${ product.getDescription() }"></c:out>" /> <span
						class="erreur">${form.errors['description']}</span>
				</div>
				<div class="form-group">
					<label class="control-label" for="quantity">la quantité de produit <span
						class="requis">*</span></label> <input class="form-control" type="text" id="quantity"
						name="quantity"
						value="<c:out value="${ product.getQuantity() }"></c:out>" /> <span
						class="erreur">${form.errors['quantity']}</span>
				</div>
				<div class="form-group">
					<label class="control-label" for="image">l'image de produit <span class="requis">*</span></label>
					<input class="form-control" type="file" name="image" /><br />
					<p class="help-block">image du produit,taille XxX format jpeg</p>
					<span class="erreur">${form.errors['image']}</span>
				</div>
				<div class="form-group">
					<label class="control-label" for="price">le prix de produit <span class="requis">*</span></label>
					<input class="form-control" type="text" id="price" name="price"
						value="<c:out value="${ product.getPrice() }"></c:out>" /> <span
						class="erreur">${form.errors['price']}</span>
				</div>
				<div class="form-group">
					<label class="control-label" for="date_available">le date de diponibilite du
						produit <span class="requis">*</span>
					</label> <input class="form-control" type="date" id="date_available" name="date_available"
						value="<fmt:formatDate value="${ product.getDate_available() }" pattern="yyyy-MM-dd" />" />
					<span class="erreur">${form.errors['date_available']}</span>
				</div>
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Ajouter</button>  
			</div>
		</div>
	</form>
	</div>
	</div>
	</div>
	<c:import url="../footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-ui1.12.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script type="text/javascript">
		$( "#date_available" ).datepicker({
				 dateFormat: 'yy-mm-dd'	
			});
		
	</script>
</body>
</html>
