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
.classBar{
	
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
}
	
.classBar	li {
	    float: left;
	    padding-top : 40px;
	    padding-bottom : 10px;
	}
	
}
</style>
</head>

<body>

	<c:import url="header.jsp" />
	
	<div class="container-fluid">
		<div style="height:20px"></div>
		<ol class="breadcrumb">
		<c:forEach items="${ requestScope.categorie }" var="categorie"
			varStatus="boucle">
			<li><span><a href="<c:url value="/c/${categorie.getId()}_${categorie.getName() }"/>" >${ categorie.getName() }</a></span></li>
		</c:forEach>
		</ol>
		<div class="row">
		<div class="col-md-12">
			
		<h3>${ product.getName() }</h3>
		
		<form action='<c:url value="/cart/add"/>' method="POST">
			<input type="hidden" name="product_id" value="${ product.getId() }" />
			<div class="row">
				<div class="col-sm-9 col-md-10  main"
					style="border-left: 1px #ddd solid">
					<hr />
					<div class="row">
						<div class="col-sm-4">
							<img class="img-responsive"  alt="${ product.getName() }" src="${ product.getImage() }">
							<hr />
						</div>
						<div class="col-sm-8">
							<div>
								<div class="row">
									<div class="col-md-4">
										<div   id="rating_product"></div>		  
									</div>
									<div class="col-md-8">
										<a href="#"><span class="glyphicon glyphicon-user">
										</span> ${ product.getReviews().size() - nbFalse} commentaires client</a>
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
	<div>
		<div class="col-md-12">
		<c:if test="${! empty product.getReviews() }" >
			<h4>Commentaires</h4>
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th colspan="3"></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${product.getReviews() }" var="review">
						<c:if test="${ review.getStatus() eq true }">
							<tr>
								<td>
									<div   id="rating_${review.getId()}"></div>		    
								</td>
								<td colspan="2">${review.getText() }</td>
								<td>crée par <a href=""> ${review.getClient().getPerson().getFirstname() }</a></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	</div>
	</div>
	<hr/>
	<div  style="background : #545760 ; color : #ffffff ;padding : 15px">
         <h4>Ajouter Commentaire</h4>
         <hr/>
         <form class="form-horizontal" method="Post" action="<c:url value="/review/add?id=${product.getId() }"/>">

       <!--rating -->
       <div class="form-group">
         <label for="rating" class="col-sm-2 control-label">rating :</label>
         <div class="col-sm-4">
          <div   id="newrating"></div>
          <input type="number" style="visibility:hidden"  step="0.5"  name="rating" id="ratingValue" value="<c:out value="${review.getRating()}"/>"/>
         </div>
         <label class="validationError" ><c:out value='${form.getErrors().get("rating")}'/></label>
       </div>
       

	<!-- comment -->
		<div class="form-group">
         <label for="comment" class="col-sm-2 control-label">Commentaire :</label>
         <div class="col-md-10">
           <textarea class="form-control" name="comment" id="comment" placeholder="votre commentaire ..."><c:out value="${review.getText()}"/></textarea>
         </div>
         <label class="validationError" ><c:out value='${form.getErrors().get("comment")}'/></label>
      	</div>
       
       
       <div class="form-group">
         <div class="col-sm-offset-2 col-sm-10">
           <button type="submit" class="btn btn-success">Ajouter Commentaire</button>
         </div>
       </div>
     
			</form>
   		
   	</div>
   	<div style="height:100px"></div>
	</div>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/rating-svg.js"/>"></script>
			<script>
				$("#rating_product").starRating({
					initialRating: ${ratio},
					strokeColor: '#894A00',
				    readOnly: true,
				    starSize: 35,
					strokeWidth: 2
				})	    
			
				$("#newrating").starRating({
				    initialRating: '<c:out value="${review.getRating()}"/>',
				    starSize: 35,
					strokeWidth: 2,
				    disableAfterRate: false,
				    onHover: function(currentIndex, currentRating, $el){
				      $('#ratingValue').val(currentIndex);
				    },
				    onLeave: function(currentIndex, currentRating, $el){
				      $('#ratingValue').val(currentRating);
				    }
				 });
				 
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


