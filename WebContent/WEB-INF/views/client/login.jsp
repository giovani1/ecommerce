<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> | Se connecter </title>
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
	<c:import url="../header.jsp" />
	
    <div class="container-fluid">
     	<h3><span class="glyphicon glyphicon-user" > Se Connecter</span></h3>
     	<hr/>
	  <div class="col-sm-offset-2 col-sm-8">
      <form class="form-horizontal" method="Post" action="<c:url value="/login/client"/>">
      
        <!--Email-->
        <div class="form-group">
          <label for="email" class="col-sm-2 control-label">Email</label>
          <div class="col-sm-4">
            <input type="email" class="form-control" id="email" name="email" placeholder="email" value="<c:out value="${client.getEmail()}"/>">
          </div>
          <label class="control-label validationError" ><c:out value='${form.getErrors().get("email")}'/></label>
        </div>

        <!--Password-->
        <div class="form-group">
          <label for="password" class="col-sm-2 control-label">Password</label>
          <div class="col-sm-4">
            <input type="password" class="form-control" name="password" id="password" placeholder="Password">
          </div>
          <label class="validationError" ><c:out value='${form.getErrors().get("password")}'/></label>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Se Connecter</button>
          </div>
        </div>
        <hr/>
      </form>
      <div style="height : 150px"></div>
	</div>	
      
    </div>
    
    <!-- footer -->
    <c:import url="../footer.jsp"/> 
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
  
  </body>
</html>

