package com.ensa.forms;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Client;
import com.ensa.util.Validation;
import com.ensa.util.ValidationException;

public class LoginForm extends Form{
	private static final String EMAIL_FIELD = "email";
	private static final String PASSWORD_FIELD = "password";
	
	
	
	public Client loginClient(HttpServletRequest request) {
		String email = getValue(request,EMAIL_FIELD);
		String password = getValue(request,PASSWORD_FIELD);
		
		Client client = new Client();
		//check the password
		try{
			Validation.validatePassword(password);
		} catch(ValidationException e){
			setError(PASSWORD_FIELD,e.getMessage());
		}
		client.setEmail(email);
		//check the email
		try{
			Validation.validateEmail(email);
		}catch(ValidationException e){
			setError(EMAIL_FIELD,e.getMessage());
		}
		client.setPassword(password);

		if ( errors.isEmpty() ) {
	        result = "true";
	    } else {
	        result = "false";
	    }
		
		return client;
		
	}
}
