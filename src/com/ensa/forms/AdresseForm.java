package com.ensa.forms;


import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Adresse;
import com.ensa.models.City;
import com.ensa.util.Validation;
import com.ensa.util.ValidationException;

public class AdresseForm extends Form{
	
	private static final String ADRESS_FIELD = "adresse";
	private static final String ZIPCODE_FIELD = "zipCode";
	private static final String CITY_FIELD = "city";
	

	
	public Adresse addAdresse(HttpServletRequest request){
		String adresse_street = getValue(request,ADRESS_FIELD);
		String zipCode = getValue(request,ZIPCODE_FIELD);
		String city = getValue(request,CITY_FIELD);
		
		Adresse adresse=new Adresse();
	
		try{
			Validation.validateText(adresse_street);
			adresse.setAdress(adresse_street);
		} catch(ValidationException e){
			setError(ADRESS_FIELD,e.getMessage());
		}
		
		try{
			Validation.validateZipCode(zipCode);
			
		} catch(ValidationException e){
			setError(ZIPCODE_FIELD,e.getMessage());
		}
		adresse.setZipCode(zipCode);
			
		try{
			Validation.validateName(city);
			
		} catch(ValidationException e){
			setError(CITY_FIELD,e.getMessage());
		}
		adresse.setCity(City.valueOf(city));

		if ( errors.isEmpty() ) {
	        result = "true";
	    } else {
	    	
	        result = "false";
	    }
		
		return adresse;
	}
}
