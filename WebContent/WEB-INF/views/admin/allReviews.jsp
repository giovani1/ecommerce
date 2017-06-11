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
			<c:import url="admin_menu.jsp" />
			<div class="col-sm-10">
				<table class="table">
					<thead>
						<tr>
							<th>Createur</th>
							<th>Review</th>
							<th>Rating</th>
							<th>Product URL</th>
							<th>State</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${reviews}" var="review">
							<tr>
								<td>${ review.getClient().getPerson().getLastname()  } ${review.getClient().getPerson().getFirstname() }</td>
								<td>${ review.getText() }</td>
								<td>
									<div   id="rating_${review.getId()}"></div>	
								</td>
								<td>
									<a href="<c:url value="/p/${ review.getProduct().getUrl() }"/>">${ review.getProduct().getName() }</a>
								</td>
								<td>
									<form action='<c:url value="AllReviews/switch_status"/>' method="post">
										<input type="hidden" name="review_id"
											value="${ review.getId() }" />
											<c:choose>
												<c:when test = "${ review.getStatus() eq false }">
													<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-off"></span></button>
												</c:when>
												<c:otherwise>
													<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-off"></span></button>
												</c:otherwise>
											</c:choose>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp" />
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/rating-svg.js"/>"></script>
	<c:if test="${!empty reviews }" >
		<script type="text/javascript">
		<c:forEach items="${reviews}" var="review">
			
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
