<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
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
  </head>

  <body>
  
	<!-- header -->
	<c:import url="../header.jsp"/>
	
	
    <div class="container-fluid">
      <hr/>
      <div class="row">
  	    <div class="col-md-2">
            <div style="border: 1px solid black">
              <img class="center-block" src="<c:url value="/resources/img/user.png"/>" height="150px" />
            </div>
  		      <ul class="nav nav-sidebar">
              <li class="active"><a href="#">Mes informations</a></li>
              <li><a href="#">Mes commandes</a></li>
              <li><a href="#">Immeubles</a></li>
              <li><a href="#">Misc</a></li>
            </ul>
  	    </div>
        <div class="col-md-10">
            <h3>Commentaire</h3>
            <hr/>
            <form class="form-horizontal" method="Post" action="<c:url value="/review/update?id=${sessionScope.reviewId }"/>">

		        <!--rating -->
		        <div class="form-group">
		          <label for="rating" class="col-sm-2 control-label">rating :</label>
		          <div class="col-sm-4">
			          <div   id="rating"></div>
			          <input type="number" style="visibility:hidden"  step="0.5"  name="rating" id="ratingValue" value="<c:out value="${review.getRating()}"/>"/>
		          </div>
		          <label class="validationError" ><c:out value='${form.getErrors().get("rating")}'/></label>
		        </div>
		        
		
				<!-- comment -->
				<div class="form-group">
		          <label for="comment" class="col-sm-2 control-label">Commentaire :</label>
		          <div class="col-md-4">
		            <textarea class="form-control" name="comment" id="comment" placeholder="votre commentaire ..."><c:out value="${review.getText()}"/></textarea>
		          </div>
		          <label class="validationError" ><c:out value='${form.getErrors().get("comment")}'/></label>
		        </div>
		        
		        
		        <div class="form-group">
		          <div class="col-sm-offset-2 col-sm-10">
		            <button type="submit" class="btn btn-success">Modifer Commentaire</button>
		          </div>
		        </div>
        
   			</form>
      		<div style="height : 250px"></div>
      	</div>
      </div>
      </div>
	
	<!-- footer -->
	<c:import url="../footer.jsp"/> 
    
    <script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/rating-svg.js"/>"></script>
	<script type="text/javascript">
		$("#rating").starRating({
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
	</script>
  </body>
</html>

