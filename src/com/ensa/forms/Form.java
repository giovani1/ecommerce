package com.ensa.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Form {
	
	protected String result;
	protected Map<String, String> errors = new HashMap<String, String>(); 
	// holds the errors encountred while processing the form

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	//Get values from the request
	protected static String getValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value.trim();
			
		}
	}
	protected static String[] getValues(HttpServletRequest request, String name) {
		String[] value = request.getParameterValues(name);
		if (value.equals(null) || value.length==0) {
			return null;
		} else {
			return value;
			
		}
	}
	//set errors occured 
	protected void setError(String fieldName,String message){
		errors.put(fieldName, message);
	}
}
