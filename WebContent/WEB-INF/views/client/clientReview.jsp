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
    <link rel="stylesheet" href="<c:url value="/resources/style/rating-svg.css"/>" />
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
			<c:import url="client_menu.jsp"/>
			<div class="col-md-10">
				<h3>Mes Commentaires</h3>
				<div class="col-md-8 col-md-offset-1">
					<c:if test="${! empty client.getReviews() }" >
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Note</th>
									<th colspan="2">Commentaire</th>
									<th>Produit</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${client.getReviews() }" var="review">
									
									<tr>
									
										<td>
											<div   id="rating_${review.getId()}"></div>		    
										</td>
										<td colspan="2">${review.getText() }</td>
										<td><a href="<c:url value="/p/${review.getProduct().getId()}_${review.getProduct().getName() }"/>">${review.getProduct().getName() }</a></td>
										<td>
											<c:choose>
												<c:when test = "${ review.getStatus() eq false }">
													<span class="label label-danger">bloquer</span>
												</c:when>
												<c:otherwise>
													<span class="label label-success">verifier</span>
												</c:otherwise>
											</c:choose>
										</td>
										<td><a href="<c:url value="/review/update?id=${review.getId()}"/>" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
		<div style="height : 300px"></div>
	</div>



	<c:import url="../footer.jsp" />
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/rating-svg.js"/>"></script>
	<c:if test="${!empty client.getReviews() }" >
		<script type="text/javascript">
		<c:forEach items="${client.getReviews() }" var="review">
			
				$("#rating_${review.getId()}").starRating({
					initialRating: '<c:out value="${review.getRating()}"/>',
					strokeColor: '#894A00',
				    readOnly: true,
				    starSize: 35,
					strokeWidth: 2
				})	    
			
		</c:forEach>
		</script>
		
	</c:if>
</body>
</html>