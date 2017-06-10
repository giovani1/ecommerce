<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-2">
	<div>
		<img class="center-block"
			src="<c:url value="/resources/img/user.png"/>" height="150px" />
	</div>
	<ul class="nav nav-sidebar">
		<li class="active"><a href="<c:url value="/seller"/>">Mes informations</a></li>
		<li><a href="<c:url value="/addresse"/>">Mes adresses</a>
		<li><a href="#">Mes commandes</a></li>
		<li><a href="<c:url value="/products"/>">Mes produits</a>
	</ul>
</div>
