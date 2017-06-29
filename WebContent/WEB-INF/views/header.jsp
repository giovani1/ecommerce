<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header style="margin-bottom: 50px;">
 <nav class="navbar navbar-inverse navbar-fixed-top">
   <div class="container-fluid">
     <div class="navbar-header">
       <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
         <span class="sr-only">Toggle navigation</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </button>
       	<div class="navbar-brand" >
       		<a href="<c:url value="/Home"/>"><img src="<c:url value="/resources/img/logo.png"/>" style="margin-top: -35px;" width="200px" alt="maroket"/></a>
       	</div>
       </div>
       <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <form class="navbar-form navbar-left col-md-8">
	           <div class="input-group">            
		          <input type="text" class="form-control" placeholder="Search">
		          <span class="input-group-btn">
		            <button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-search"></span></button>
		          </span>
		        </div>
	         </form>
	        <c:if test="${(sessionScope.accountType eq 'client') or (empty sessionScope.account ) }">
	            <li><a href="<c:url value="/cart"/>"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span><span style="margin-left : 2px" class="label label-primary">
	            <c:choose>
			    	<c:when test="${! empty sessionScope.numbercart}">
			       		${ numbercart }
			    	</c:when>
			    	<c:otherwise>
			        	0
			    	</c:otherwise>
				 </c:choose>
	            </span></a></li>
	            <li class="dropdown">
	              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a>
		          <ul class="dropdown-menu">
		          	<c:choose>
				    	<c:when test="${! empty sessionScope.account}">
				       		<li><a href="<c:url value="/client"/>">Profil</a></li>
				       		<li><a href="<c:url value="/orders"/>">Commandes</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
				    	</c:when>
				    	<c:otherwise>
				        	<li><a href="<c:url value="/client/add"/>">S' Inscrire</a></li>
				            <li><a href="<c:url value="/login/client"/>">Se Connecter</a></li>
				    	</c:otherwise>
				   	</c:choose>    
		          </ul>
		        </li>
	        </c:if>
	        <c:if test = "${(sessionScope.accountType eq 'seller' ) or (empty sessionScope.account ) }">
		        <li class="dropdown">
	              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" ><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span></a>
		          <ul class="dropdown-menu">
		          	<c:choose>
				    	<c:when test="${! empty sessionScope.account}">
				       		<li><a href="<c:url value="/seller"/>">Profil</a></li>
				       		<li><a href="<c:url value="/products"/>">Products</a></li>
				            <li role="separator" class="divider"></li>
				            <li><a href="<c:url value="/Logout"/>">Deconnexion</a></li>
				    	</c:when>
				    	<c:otherwise>
				        	<li><a href="<c:url value="/seller/add"/>" >S' Inscrire</a></li>
				            <li><a href="<c:url value="/login/seller"/>">Se Connecter</a></li>
				    	</c:otherwise>
				   	</c:choose>    
		          </ul>
		        </li>
	        </c:if>
          </ul>
         
       </div>
     </div>
   </nav>
</header>