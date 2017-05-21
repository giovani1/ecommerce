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
	<!--<style type="text/css">
    	.carousel-inner > .item > img,
	  	.carousel-inner > .item > a > img {
	      width: 30%;
	      margin: auto;
	  	}
    	col-centered{
		    float: none;
		    margin: 0 auto;
		}


		.footer-bs {
		    background-color: #101010;
			padding: 60px 40px;
			color: rgba(255,255,255,1.00);
		}
		.footer-bs .footer-brand, .footer-bs .footer-nav, .footer-bs .footer-social, .footer-bs .footer-ns { padding:10px 25px; }
		.footer-bs .footer-nav, .footer-bs .footer-social, .footer-bs .footer-ns { border-color: transparent; }
		.footer-bs .footer-brand h2 { margin:0px 0px 10px; }
		.footer-bs .footer-brand p { font-size:12px; color:rgba(255,255,255,0.70); }

		

		.footer-bs .footer-nav ul.list { list-style:none; padding:0px; }
		.footer-bs .footer-nav ul.list li { padding:5px 0px;}
		.footer-bs .footer-nav ul.list a { color:rgba(255,255,255,0.80); }
		.footer-bs .footer-nav ul.list a:hover { color:rgba(255,255,255,0.60); text-decoration:none; }

		.footer-bs .footer-social ul { list-style:none; padding:0px; }
		.footer-bs .footer-social h4 {
			font-size: 11px;
			text-transform: uppercase;
			letter-spacing: 3px;
		}
		.footer-bs .footer-social li { padding:5px 4px;}
		.footer-bs .footer-social a { color:rgba(255,255,255,1.00);}
		.footer-bs .footer-social a:hover { color:rgba(255,255,255,0.80); text-decoration:none; }

		.footer-bs .footer-ns h4 {
			font-size: 11px;
			text-transform: uppercase;
			letter-spacing: 3px;
			margin-bottom:10px;
		}
		.footer-bs .footer-ns p { font-size:12px; color:rgba(255,255,255,0.70); }

		@media (min-width: 768px) {
			.footer-bs .footer-nav, .footer-bs .footer-social, .footer-bs .footer-ns { border-left:solid 1px rgba(255,255,255,0.10); }
		}


		
    </style>-->

	<c:import url="header.jsp" />
	<div class="container-fluid">
		<c:forEach items="${ requestScope.categorie }" var="categorie"
			varStatus="boucle">
			<span>${ categorie.getName() }</span>
		</c:forEach>
		<h3>${ product.getName() }</h3>
		<!-- <h6><a href="/product?seller_name=${ product.getSeller().getName() }"></a></h6> -->
		<form action='<c:url value="/cart/add"/>' method="POST">
			<input type="hidden" name="product_id" value="${ product.getId() }" />
			<div class="row">
				<div class="col-sm-9 col-md-10  main"
					style="border-left: 1px #ddd solid">
					<hr />
					<div class="row">
						<div class="col-sm-4">
							<img alt="${ product.getName() }" src="${ product.getImage() }">
							<hr />
						</div>
						<div class="col-sm-8">
							<div>
								<div class="row">
									<div class="col-md-4" id="star-rating">
										<input type="radio" name="example" class="rating" value="1" />
										<input type="radio" name="example" class="rating" value="2" />
										<input type="radio" name="example" class="rating" value="3" />
										<input type="radio" name="example" class="rating" value="4" />
										<input type="radio" name="example" class="rating" value="5" />
									</div>
									<div class="col-md-8">
										<a href="#"><span class="glyphicon glyphicon-user">
										</span> 8664 commentaires client</a>
									</div>
								</div>
							</div>
							<hr />
							<div>
								<p>${ product.getDescription() }</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-3 col-md-2 sidebar">
					<div class="list-group">
						<div class="list-group-item list-group-item-success"
							style="font-size: 1.6em;">${ product.getPrice() }</div>
						<div class="list-group-item">
							<label>Qté : </label> <input type="number" class="form-control"
								minimum="1" width="50px" min="1" max="100" name="quantity">
						</div>
						<div class="list-group-item list-group-item-warning">
							<button type="submit" class="btn btn-warning">ajouter au panier</button>
						</div>
						
					</div>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-10 col-offset-md-2">
					<div class=" panel panel-default">
						<div class="panel-heading">Product details</div>
						<div class="panel-body">
							<table class="table">
								<c:forEach items="${ product.getProducts_attributes() }"
									var="attributes" varStatus="boucle">
									<tr>
										<td><input type="radio"
											name="attributes_${ attributes.getProduct_options_value().getProduct_options().getId() }"
											value="${ attributes.getId() }"> <span>${ attributes.getProduct_options_value().getProduct_options().getName() }</span></td>
										<td><span>${ attributes.getProduct_options_value().getName() }</span>
											<span>${ attributes.getOptions_values_price() }</span></td>
									</tr>
								</c:forEach>
								<tr>
									<td>Date de mise en ligne</td>
									<td>${ product.getDate_available() }</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>


