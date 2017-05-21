<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> | Inscription </title>
    <link rel="stylesheet" href="<c:url value="/resources/style/bootstrap3.3.7.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/style/jquery-ui1.12.1.min.css"/>">
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
     	<h3><span class="glyphicon glyphicon-briefcase" > Modifier</span></h3>
      <hr/>
      <div class="col-sm-offset-2 col-sm-8">
      <form class="form-horizontal" method="Post" action="<c:url value="/seller/update"/>">
		<c:import url="seller_form.jsp"/> 
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">Modifier</button>
          </div>
        </div>
      </form>
      <div style="height : 100px"></div>
      </div>
	
      
    </div>
    
    <!-- footer -->
    <c:import url="../footer.jsp"/> 
  
    <script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-ui1.12.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	<script type="text/javascript">
		$( "#datePicker" ).datepicker({
			 dateFormat: 'yy-mm-dd'	
		});
	</script>
  </body>
</html>

