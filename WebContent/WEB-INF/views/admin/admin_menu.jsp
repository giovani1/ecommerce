<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-2">
	
	<div>
		<img class="center-block"
			src="<c:url value="/resources/img/admin.png"/>" height="150px" />
	</div>
	<ul class="nav nav-sidebar">
		<li><a href="<c:url value="/options"/>">Options</a>
		<li><a href="<c:url value="/categorie"/>">Categories</a></li>
		<li><a href="<c:url value="/categorie/all"/>">Tous les produits</a>
		<li><a href="<c:url value="/AllClients"/>">Tous les clients</a>
		<li><a href="<c:url value="/AllSellers"/>">Tous les vendeurs</a>
		<li><a href="<c:url value="/AllReviews"/>">Tous les commentaires</a>			
	</ul>
	
</div>