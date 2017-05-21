<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  	<title>| Profil</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/style/bootstrap3.3.7.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/style/footer.css"/>" />
    <style type="text/css">
		.validationError{
			color : #a50616;
			margin-left : 15px;
			text-align : justify;
			font-size : 10px;
		}
    </style>
<body>
	<c:import url="../header.jsp" />
	<div class="container-fluid">
		<hr />
		<div class="row">
			<c:import url="seller_menu.jsp"/>
			<div class="col-md-10">
				<h3>Mes addresses</h3>
				<div class="col-md-8 col-md-offset-1">
					<c:if test="${!empty seller.getPerson().getAdresses() }" >
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Ville</th>
									<th colspan="2">Adresse</th>
									<th>code postale</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${seller.getPerson().getAdresses() }" var="addresse">
									<tr>
										<td>${addresse.getCity() }</td>
										<td colspan="2">${addresse.getAdress() }</td>
										<td>${addresse.getZipCode()}</td>
										<td><a href="<c:url value="/addresse/update?id=${addresse.getId()}"/>" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<div>
						<c:if test="${seller.getPerson().getAdresses().size() eq 0 }">
							<a class="btn btn-primary"  href="<c:url value="/addresse/add"/>">
								<span class="glyphicon glyphicon-plus"></span> Ajouter une nouvelle addresse
							</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div style="height : 300px"></div>
	</div>



	<c:import url="../footer.jsp" />
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	
</body>
</html>