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
				
				<div class="col-md-10">
					<h3>Informations de l'entreprise <a style="margin-left : 20px" href="<c:url  value="/seller/update"/>" class="btn btn-primary" type="button"><span class="glyphicon glyphicon-edit"></span></a></h3>
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><b>Nom d'entreprise</b></td>
								<td colspan="3">${seller.getSeller_name()}</td>
							</tr>
							<tr>
								<td><b>URL</b></td>
								<td colspan="3">${seller.getInfo().getSeller_url()}</td>
							</tr>							
						</tbody>
					</table>
				</div>
				
				<div class="col-md-10">
					<h3>Mes informations</h3>
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><b>Nom</b></td>
								<td colspan="3">${seller.getPerson().getLastname()}</td>
							</tr>
							<tr>
								<td><b>Prenom</b></td>
								<td colspan="3">${seller.getPerson().getFirstname()}</td>
							</tr>
							<tr>
								<td><b>Gendre</b></td>
								<td colspan="3"> ${seller.getPerson().getGender()}</td>
							</tr>
							<tr>
								<td><b>Email</b></td>
								<td colspan="3">${seller.getEmail() }</td>
							</tr>
							<tr>
								<td><b>Date De Naissance</b></td>
								<td colspan="3"><fmt:formatDate value="${seller.getPerson().getBirth() }" pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr>
								<td><b>N° Telephone</b></td>
								<td colspan="3">${ seller.getPhone() }</td>
							</tr>
							
						</tbody>
					</table>
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