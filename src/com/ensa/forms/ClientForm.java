package com.ensa.forms;



import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Client;
import com.ensa.models.ClientInfo;
import com.ensa.models.Gender;
import com.ensa.models.Person;
import com.ensa.util.Validation;
import com.ensa.util.ValidationException;

import java.util.Calendar;
import java.util.Date;


// this object is responsable of sign up a new client into the web application
public class ClientForm extends Form{

	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	private static final String FIRST_NAME_FIELD = "firstName";
	private static final String LAST_NAME_FIELD = "lastName";
	private static final String GENDRE_FIELD = "gendre";
	private static final String BIRTH_FIELD = "birthDate";
	private static final String PHONE_FIELD = "phone";

	// this method is responsible of all the validation of the sign up form
	public Client addClient(HttpServletRequest request) {
		String email = getValue(request,EMAIL_FIELD);
		String password = getValue(request,PASSWORD_FIELD);
		String firstName = getValue(request,FIRST_NAME_FIELD);
		String lastName = getValue(request,LAST_NAME_FIELD);
		String gendre = getValue(request,GENDRE_FIELD);
		String birthDate = getValue(request,BIRTH_FIELD);
		String phoneNumber = getValue(request,PHONE_FIELD);
		
		Client client = new Client();
		ClientInfo info = new ClientInfo();
		Person person = new Person();
		Date date = null;
		//check the password
		try{
			Validation.validatePassword(password);
		} catch(ValidationException e){
			setError(PASSWORD_FIELD,e.getMessage());
		}
		client.setPassword(password);
		
		//check the email
		try{
			Validation.validateEmail(email);
		}catch(ValidationException e){
			setError(EMAIL_FIELD,e.getMessage());
		}
		client.setEmail(email);
		
		//check the phone number
		try{
			Validation.validatePhoneNumber(phoneNumber);
		}catch(ValidationException e){
			setError(PHONE_FIELD,e.getMessage());
		}
		client.setPhone(phoneNumber);
		
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
		client.setPerson(person);
		client.setInfo(info);
		return client;
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
