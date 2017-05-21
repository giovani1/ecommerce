package com.ensa.forms;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Gender;
import com.ensa.models.Person;
import com.ensa.models.Seller;
import com.ensa.models.SellerInfo;
import com.ensa.util.Validation;
import com.ensa.util.ValidationException;

public class SellerForm extends Form{

	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String FIRST_NAME_FIELD = "firstName";
	private static final String LAST_NAME_FIELD = "lastName";
	private static final String GENDRE_FIELD = "gendre";
	private static final String BIRTH_FIELD = "birthDate";
	private static final String PHONE_FIELD = "phone";
	private static final String SELLER_NAME_FIELD = "sellerName";
	private static final String SELLER_URL_FIELD = "sellerUrl";
	
	// this method is responsible of all the validation of the sign up form
	public Seller addSeller(HttpServletRequest request) {
		String email = getValue(request,EMAIL_FIELD);
		String password = getValue(request,PASSWORD_FIELD);
		String firstName = getValue(request,FIRST_NAME_FIELD);
		String lastName = getValue(request,LAST_NAME_FIELD);
		String gendre = getValue(request,GENDRE_FIELD);
		String birthDate = getValue(request,BIRTH_FIELD);
		String phoneNumber = getValue(request,PHONE_FIELD);
		String sellerName = getValue(request,SELLER_NAME_FIELD);
		String sellerUrl = getValue(request,SELLER_URL_FIELD);
		
		Seller seller = new Seller();
		Person person = new Person();
		SellerInfo info = new SellerInfo();
		seller.setInfo(info);
		
		Date date = null;
		
		//check the seller Name 
		try{
			Validation.validateName(sellerName);
		}catch(ValidationException e){
			setError(SELLER_NAME_FIELD,e.getMessage());
		}
		seller.setSeller_name(sellerName);
		
		//check the seller url
		try{
			Validation.validateURL(sellerUrl);
		}catch(ValidationException e){
			setError(SELLER_URL_FIELD,e.getMessage());
		}
		info.setSeller_url(sellerUrl);
		
		//check the password
		try{
			Validation.validatePassword(password);
		} catch(ValidationException e){
			setError(PASSWORD_FIELD,e.getMessage());
		}
		seller.setPassword(password);
		
		//check the email
		try{
			Validation.validateEmail(email);
		}catch(ValidationException e){
			setError(EMAIL_FIELD,e.getMessage());
		}
		seller.setEmail(email);
		
		//check the phone number
		try{
			Validation.validatePhoneNumber(phoneNumber);
		}catch(ValidationException e){
			setError(PHONE_FIELD,e.getMessage());
		}
		seller.setPhone(phoneNumber);
		
		//check the first name
		try{
			Validation.validateFirstName(firstName);
		}catch(ValidationException e){
			setError(FIRST_NAME_FIELD,e.getMessage());
		}
		person.setFirstname(firstName);
		
		//check the last name
		try{
			Validation.validateLastName(lastName);
		}catch(ValidationException e){
			setError(LAST_NAME_FIELD,e.getMessage());
		}
		person.setLastname(lastName);
		
		//check the gendre
		try{
			Validation.validateGender(gendre);
		}catch(ValidationException e){
			setError(GENDRE_FIELD,e.getMessage());
		}
		person.setGender(Gender.valueOf(gendre));
		
		//check the birth date
		try{
			Validation.validateDate(birthDate);
		}catch(ValidationException e){
			setError(BIRTH_FIELD,e.getMessage());
		}
		
		//convert birth to a date object
		try{
			date = convertToDate(birthDate);
		}catch(Exception e){
			setError(BIRTH_FIELD,e.getMessage());
		}
		person.setBirth(date);
		
		
		if ( errors.isEmpty() ) {
	        result = "true";
	    } else {
	    	
	        result = "false";
	    }
		seller.setInfo(info);
		seller.setPerson(person);
		
		return seller;
	}	
	
	//method to convert the birth string to a date Object
	private Date convertToDate(String date) throws Exception{
		Date dateV = null;
		String[] values = date.split("-");
		if(values.length == 3){
			try{
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(values[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(values[1])-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[2]));
				
				dateV = cal.getTime();
			}catch(Exception e){
				throw new Exception("Valeur date incorrecte.");
			}
		}else{
			throw new Exception("Format date illegale.");
		}
		
		return dateV;
	}
}
