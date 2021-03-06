<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h4>Information sur l'entreprise</h4>
<div class="form-group">
        <label for="sellerName" class="col-sm-2 control-label" >Nom de l'entreprise</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="sellerName" name="sellerName" placeholder="company" value="<c:out value="${seller.getSeller_name()}"/>" >
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("sellerName")}'/></label>
      </div>

<div class="form-group">
        <label for="firstName" class="col-sm-2 control-label" >Url</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="sellerUrl" name="sellerUrl" placeholder="url" value="<c:out value="${seller.getInfo().getSeller_url()}"/>" >
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("sellerUrl")}'/></label>
      </div>
      
<hr/>
<h4>Information du responsable</h4>
      <!--First Name-->
      <div class="form-group">
        <label for="firstName" class="col-sm-2 control-label" >Prenom</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="firstName" name="firstName" placeholder="prenom" value="<c:out value="${seller.getPerson().getFirstname()}"/>" >
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("firstName")}'/></label>
      </div>

      <!--Last Name-->
      <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">Nom</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="lastName" name="lastName" placeholder="nom" value="<c:out value="${seller.getPerson().getLastname()}"/>">
        </div>
        <label class=" validationError" ><c:out value='${form.getErrors().get("lastName")}'/></label>
      </div>

      <!--Gendre-->
      <div class="form-group">
        <label for="gendre" class="col-sm-2 control-label">sexe</label>
        <div class="col-sm-4">
          <select name="gendre" id="gendre" class="form-control">
            <option value="Male" ${seller.getPerson().getGender() == 'Male' ? 'selected="selected"' : ''}>Male</option>
            <option value="Female" ${seller.getPerson().getGender() == 'Female' ? 'selected="selected"' : ''}>Female</option>
          </select>
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("gendre")}'/></label>
      </div>

      <!--Email-->
      <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-4">
          <input type="email" class="form-control" id="email" name="email" placeholder="email" value="<c:out value="${seller.getEmail()}"/>">
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("email")}'/></label>
      </div>

      <!--Birth Date-->
      <div class="form-group">
        <label for="birthDate" class="col-sm-2 control-label">Date de Naissance</label>
        <div class="col-sm-4">
          <input type="text" id="datePicker" class="form-control" id="birthDate" name="birthDate" value="<fmt:formatDate value="${seller.getPerson().getBirth() }" pattern="yyyy-MM-dd" />">
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("birthDate")}'/></label>
      </div>

      <!--Password-->
      <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-4">
          <input type="password" class="form-control" name="password" id="password" placeholder="Password" value="<c:out value="${seller.getPassword()}"/>">
        </div>
        <label class="validationError" ><c:out value='${form.getErrors().get("password")}'/></label>
      </div>

      <!--Telephone-->
      <div class="form-group">
        <label for="phone" class="col-sm-2 control-label">Telephone</label>
        <div class="col-sm-4">
          <input type="text" class="form-control" name="phone" id="phone" placeholder="+212" value="<c:out value="${seller.getPhone()}"/>">
        </div>
        <label class="control-label validationError" ><c:out value='${form.getErrors().get("phone")}'/></label>
      </div>
