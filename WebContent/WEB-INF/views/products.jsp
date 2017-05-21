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
	<ol class="breadcrumb">
	<c:forEach items="${ requestScope.categorie }" var="categorie" varStatus="boucle">
	<li class="active">${ categorie.getName() }</li>
	</c:forEach>
	</ol>
	 <div class="container">
        <div class="row">
	       <div class="col-md-9">
		       <div class="row">
			      <c:forEach items="${ requestScope.products }" var="product" varStatus="boucle">
		          <div class="col-sm-4 col-lg-4 col-md-4">
		              <div class="thumbnail">
		                  <img src=${product.image } alt="${ product.name }">
		                  <div class="caption">
		                      <h4 class="pull-right">${ product.price }</h4>
		                      <h4><a href='<c:url value="/p/${ product.getUrl() }"/>'>${ product.name }</a>
		                      </h4>
		                  </div>
		                  <div class="ratings">
		                      <p class="pull-right">15 reviews</p>
		                      <p>
		                          <span class="glyphicon glyphicon-star"></span>
		                          <span class="glyphicon glyphicon-star"></span>
		                          <span class="glyphicon glyphicon-star"></span>
		                          <span class="glyphicon glyphicon-star"></span>
		                          <span class="glyphicon glyphicon-star"></span>
		                      </p>
		                  </div>
		              </div>
		          </div>
			      </c:forEach>
		      </div>
	      </div>
      </div>
    </div>
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
