<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<title>| Accueil</title>
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
  </head>

  <body>
  
	<!-- header -->
	<c:import url="../header.jsp"/>
	
	
    <div class="container-fluid">
      <hr/>
      <div class="row">
  	    <c:import url="client_menu.jsp" />
  	    
        <div class="col-md-10">
            <h3>Modifier une Adresse</h3>
            <hr/>
            <form class="form-horizontal" method="Post" action="<c:url value="/addresse/update?id=${sessionScope.addresseId }"/>" >
    
		        <!--City-->
		        <div class="form-group">
		          <label for="city" class="col-sm-2 control-label">Ville : </label>
		          <div class="col-sm-4">
		            <select class="form-control" id="city" name="city">
		          		<c:forEach items="${cityValues}" var="city">
							<option value='<c:out value="${city}"/>' ><c:out value="${city}" /></option>
						</c:forEach>
		          	</select>
		          </div>
		          <label class="control-label validationError" ><c:out value='${form.getErrors().get("city")}'/></label>
		        </div>
		
		        <!--ZipCode-->
		        <div class="form-group">
		          <label for="zipCode" class="col-sm-2 control-label">Code Zip :</label>
		          <div class="col-sm-4">
		            <input type="text" class="form-control" name="zipCode" id="zipCode" placeholder="zip"  value='<c:out value="${adresse.getZipCode()}"/>'>
		          </div>
		          <label class="validationError" ><c:out value='${form.getErrors().get("zipCode")}'/></label>
		        </div>
		
				<!-- Adresse -->
				<div class="form-group">
		          <label for="adresse" class="col-sm-2 control-label">Adresse :</label>
		          <div class="col-sm-4">
		            <textarea class="form-control" name="adresse" id="adresse" placeholder="adresse">
		          		<c:out value="${adresse.getAdress()}"/>
		          	</textarea>
		          </div>
		          <label class="validationError" ><c:out value='${form.getErrors().get("adresse")}'/></label>
		        </div>
		        
		        
		        <div class="form-group">
		          <div class="col-sm-offset-2 col-sm-10">
		            <button type="submit" class="btn btn-success">Modifier l'adresse</button>
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
  </body>
</html>

