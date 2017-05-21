<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer class="footer-bs container-fluid">
    <div class="row">
    	<div class="col-md-3 footer-brand animated fadeInLeft">
        	<a href="<c:url value="/Home"/>"><img class="img-responsive" src="<c:url value="/resources/img/logo.png"/>" alt="maroket"/></a>
            <p>2017 ENSAF PFA</p>
        </div>
    	<div class="col-md-7 footer-nav animated fadeInUp">
        	<div class="col-md-6">
                <ul class="list">
                    <li><a href="#">Etre un vendeur</a></li>
                    <li><a href="#">Publicité</a></li>
                </ul>
            </div>
        	<div class="col-md-6">
                <ul class="list">
                    <li><a href="#">A propos</a></li>
                    <li><a href="#">Contacts</a></li>
                    <li><a href="#">Terms And Condition</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">FAQ</a></li>
                </ul>
            </div>
        </div>
    	<div class="col-md-2 footer-social animated fadeInDown">
        	<h4>Follow Us</h4>
        	<ul>
            	<li><a href="#">Facebook</a></li>
            	<li><a href="#">Twitter</a></li>
            </ul>
        </div>
    </div>
</footer>