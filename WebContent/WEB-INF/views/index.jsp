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
    <style>
	 
.carousel .item {
  height: 380px;
}
.item img {
  	
    top: 0;
    left: 0;
    min-width:80%;
}
.img-responsive {
		    margin: 0 auto;
		}

    </style>
  </head>

  <body>
	<!-- header -->
	<c:import url="header.jsp" />
	
    <div class="container-fluid">
    
      <div class="row">
      	<div id="myCarousel" class="carousel slide" data-ride="carousel" style="width: 100%;height:380px; margin: 0 auto">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		    <li data-target="#myCarousel" data-slide-to="1"></li>
		    <li data-target="#myCarousel" data-slide-to="2"></li>
		  </ol>
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner" role="listbox">
		    <div class="item active">
		      <img src="<c:url value="/resources/img/b1.jpg"/>" alt="Chania" />
		    </div>

		    <div class="item">
		      <img src="<c:url value="/resources/img/b2.jpg"/>" alt="Chania">
		    </div>

		    <div class="item">
		      <img  src="<c:url value="/resources/img/b3.jpg"/>" alt="Flower" >
		    </div>
		  </div>

		  <!-- Left and right controls -->
		  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
      </div>
      
      <div class="row">
      
        <div class="col-sm-3 col-md-2 sidebar">
          <h3>Catégories</h3>
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Hi-Tec</a></li>
            <li><a href="#">Vehicules</a></li>
            <li><a href="#">Immeubles</a></li>
            <li><a href="#">Misc</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i> Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">Dropdown heading</li>
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li><a href="#">Separated link</a></li>
                <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i> Dropdown <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu">
	                <li class="dropdown-header">Dropdown heading</li>
	                <li><a href="#">Action</a></li>
	                <li><a href="#">Another action</a></li>
	                <li><a href="#">Something else here</a></li>
	                <li><a href="#">Separated link</a></li>
	                <li><a href="#">One more separated link</a></li>
	              </ul>
	            </li>
            </li>
                </li>
              </ul>
            </li>
            
          </ul>
        </div>
        <div class="col-sm-9 col-md-10  main" style="border-left: 1px #ddd solid">
        	<h3>Nouveaux Produits</h3>
        	<table>
        	<thead>
        		<tr>
        			<th></th>
        			<th></th>
        			<th></th>
        			<th></th>
        		</tr>
        	</thead>
        	<tbody>
        	<c:forEach items="${ requestScope.products }" var="product" varStatus="boucle">
        		<c:if test="${boucle.index mod 4 eq 0 }">
		        	<tr style="border-top : 1px solid #9fa0a5 ; padding : 5px ;">
		        </c:if>
		        	<td style=" padding : 5px " class="col-md-3">
	        			<div style="border-right : 1px solid #9fa0a5 ; padding : 5px ;">
					      <a href="<c:url value="/p/${product.getUrl() }"/>"><img width="100"  class="img-responsive" src="${product.getImage()}" /></a>
					      <div class="caption">
					        <h3>${ product.getName() }</h3>
					        <p><a href="<c:url value="/s/${ product.getSeller().getId() }_${product.getSeller().getSeller_name() }"/>">${product.getSeller().getSeller_name() }</a></p>
	        				<p class="label label-success ">${ product.getPrice() } MAD</p>
					      </div>
	    				</div>	
	        		</td>
		        <c:if test="${boucle.index+1 % 4 eq 0 or boucle.index eq requestScope.products.size()-1 }">
		        	</tr>
		        	
	        	</c:if>
        	</c:forEach>
        	</tbody>
        	</table>
        	<!-- 
        	<nav style="margin :0 auto; width: 70%;">
			  <ul class="pagination">
			    <li class="enabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			    <li ><a href="#">1 <span class="sr-only">(current)</span></a></li>
			    <li class="active"><a href="#">2 <span class="sr-only"></span></a></li>
			    <li ><a href="#">3 <span class="sr-only"></span></a></li>
			    <li ><a href="#">4 <span class="sr-only"></span></a></li>
			    <li ><a href="#">5 <span class="sr-only"></span></a></li>
			    <li ><a href="#">6 <span class="sr-only"></span></a></li>

			  </ul>
			</nav>
			-->
        </div>
      </div>
      <div style="height:100px"></div>
    </div>
    <!-- footer -->
    <c:import url="footer.jsp"/>
    
	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
	
  </body>
</html>

    