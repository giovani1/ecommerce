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
<style type="text/css">
.validationError {
	color: #a50616;
	margin-left: 15px;
	text-align: justify;
	font-size: 10px;
}
.classBar{
	
	    list-style-type: none;
	    margin: 0;
	    padding: 0;
}
	
.classBar	li {
	    float: left;
	    padding-top : 40px;
	    padding-bottom : 10px;
	}
	

	
	 .img-responsive {
		    margin: 0 auto;
		}
    
</style>
</head>

<body>
	<c:import url="header.jsp" />
	<div class="container-fluid">
	 <c:if test="${ ! empty categories }">
		<div class="col-sm-3 col-md-2 sidebar">
          <h4>Catégories</h4>
	          <ul class="nav nav-sidebar">
	          	<c:forEach items="${requestScope.categories }" var="categorie">
		            <li><a href="<c:url value="/c/${categorie.getId()}_${categorie.getName() }"/>" >${categorie.getName()  }</a></li>
	            </c:forEach>
	          </ul>
        </div>
     </c:if>
     <c:if test="${ ! empty sellers }">
		<div class="col-sm-3 col-md-2 sidebar">
          <h4>Vendeurs</h4>
	          <ul class="nav nav-sidebar">
	          	<c:forEach items="${requestScope.sellers }" var="seller">
		            <li><a href="<c:url value="/s/${ seller.getId() }_${seller.getSeller_name() }"/>">${seller.getSeller_name() }</a></li>
	            </c:forEach>
	          </ul>
        </div>
     </c:if>
        <div class="col-sm-9 col-md-10 ">
		<div class="row" >
			<c:if test="${ !empty categorie }">
			<div style="height:20px"></div>
			<ol class="breadcrumb">
			<c:forEach items="${ requestScope.categorie }" var="categorie" varStatus="boucle">
			<li class="active"><a href="<c:url value="/c/${categorie.getId()}_${categorie.getName() }"/>" >${ categorie.getName() }</a></li>
			</c:forEach>
			</ol>
			</c:if>
			<c:if test="${ !empty sellerOf }">
				<h2><a href="<c:url value="/s/${ sellerOf.getId() }_${sellerOf.getSeller_name() }"/>">${sellerOf.getSeller_name() }</a></h2>
			</c:if>			
		</div>
		
      	<div class="row">
      	
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
	   </div>
	   
	   <div style="height:100px"></div>
	   </div>
		
	</div>
    
	<c:import url="footer.jsp" />

	<script src="<c:url value="/resources/js/jquery1.12.4.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap3.3.7.min.js"/>"></script>
</body>
</html>
