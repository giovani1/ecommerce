package com.ensa.util;


public final class Validation {
	
	//method validating the birth date
	public static void validateDate(String date) throws ValidationException{
		if(date != null){
			date = date.trim();
			if(!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
				throw new ValidationException("La valeur n'est pas correcte");
			}
		}else{
			throw new ValidationException("Merci de preciser votre date de naissance");
		}
	}

	
	//method validating the client gendre
	public static void validateGender(String gender) throws ValidationException{
		if(gender!= null){
			gender = gender.trim();
			String tmpG = gender.toLowerCase();
			if(!(tmpG.equals("male") || tmpG.equals("female"))){
				throw new ValidationException("Valeur non precise");
			}
		
		}else{
			throw new ValidationException("Merci de preciser votre sexe");
		}
	}
	
	//method validating the first name
	public static void validateFirstName(String firstName) throws ValidationException {
		 if ( firstName != null ) {
			 firstName = firstName.trim();
			 if(!firstName.matches("^([a-z]|[A-Z]){3,25}$")){
				 throw new ValidationException( "Votre Prenom doit contenir au moins 3 caracteres." );
			 }
		 } else {
			 throw new ValidationException("Merci de saisir votre prenom");
		 }
	}
	
	//method validating the  last name
	public static void validateLastName(String lastName) throws ValidationException {
		 if ( lastName != null ) {
			 lastName = lastName.trim();
			 if(!lastName.matches("^([a-z]|[A-Z]){3,25}$")){
				 throw new ValidationException( "Votre Nom doit contenir au moins 3 caractï¿½res." );
			 }
		 } else {
			 throw new ValidationException("Merci de saisir votre nom");
		 }
	}
	
	
	//method validating the phone number 
	public static void validatePhoneNumber(String phone) throws ValidationException{
		if( phone != null ){
			phone = phone.trim();
			if( !phone.matches("^0(5|6)[0-9]{8}$") ){
				throw new ValidationException("Merci de saisir un numero de telephone valide.");
			}
		} else {
			throw new ValidationException("Merci de saisir un numero de telephone");
		}
	}
	
	//method validating the client email
	public static void validateEmail( String email ) throws ValidationException {
	    if ( email != null ) {
	    	email = email.trim();
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new ValidationException( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new ValidationException( "Merci de saisir une adresse mail." );
	    }
	}
	
	//method validating the password
	public static void validatePassword(String password) throws ValidationException{
		if(password != null ){
			password = password.trim();
			if(!password.matches("^([a-z]|[A-Z]|[0-9]|_|-){8,25}$")){
				//password doesn't satisfy the critaria 
				throw new ValidationException("Votre mot de passe doit etre d'une longueur minmum de 8 caracteres , contenant les caracteres alphanumerique et des _,-");
			}
		} else {
			//empty field
			throw new ValidationException("Vous devez saisir votre mot de passe");
		}
	}
	
	//method validating name
	public static void validateName(String firstName) throws ValidationException {
		 if ( firstName != null ) {
			 firstName = firstName.trim();
			 if(!firstName.matches("^([a-zA-Z- ]){3,60}$")){
				 throw new ValidationException( "Votre nom doit contenir au moins 3 caracteres." );
			 }
		 } else {
			 throw new ValidationException("Merci de saisir votre nom");
		 }
	}
	
	//method validating the street adrees
	public static void validateText(String adresse_street) throws ValidationException{
		if ( adresse_street != null ) {
			adresse_street = adresse_street.trim();
			 if(!adresse_street.matches("^[A-Za-z0-9'\\.\\;\\-\\s\\,]{5,10000}$")){
				 throw new ValidationException( "votre text doit contenir au moins 5 caratere et pas des cartere speciaux" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisir le text");
		 }
	}

	//method validating the zip code
	public static void validateZipCode(String zipCode) throws ValidationException{
		if ( zipCode != null ) {
			 zipCode = zipCode.trim();
			 if(!zipCode.matches("^[1-9]{1}[0-9]{4}$")){
				 throw new ValidationException( "le zip code entre n'est pas valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisire un zip code ");
		 }
		
	}

	//method validating the url
	public static void validateURL(String url) throws ValidationException{
		String pattern = "^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]\\.[^\\s]{2,})$";
		if ( url != null ) {
		     pattern = pattern.trim();
			 if(!url.matches(pattern)){
				 throw new ValidationException( "l'url que vous avez entrez n'est pas valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisire un url");
		 }
	}

	//method validating the quantity
	public static void validateQuantity(String quantity) throws ValidationException{
		if ( quantity != null ) {
			quantity = quantity.trim();
			 if(!quantity.matches("[1-9]{1}[0-9]{0,18}")){
				 throw new ValidationException( "la quantite que vous avez entrez n'est pas valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisire une quantity");
		 }
	}

	//method validating the path of a file in server
	public static void validatePath(String path) throws ValidationException{
		if ( path != null ) {
			path = path.trim();
			 if(!path.matches("^\\w+(\\/\\w+){0,20}\\.[a-zA-Z]{2,5}$")){
				 throw new ValidationException( "le path entrez n'est pas valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisire un path");
		 }
	}

	//method validating the price of a product
	public static void validateMoney(String price) throws ValidationException {
		if ( price != null ) {
			 price = price.trim();
			 if(!price.matches("^[0-9]{1,9}.?[0-9]{0,9}$")){
				 throw new ValidationException( "le montant entrez n'est pas valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de saisire le prix");
		 }
	}

	//method validating a string if it's boolean
	public static void validateBool(String status) throws ValidationException{
		if ( status != null ) {
			 status = status.trim();
			 if(!status.equals("false") && !status.equals("true")){
				 throw new ValidationException( "la valeur du satuts n'est valide" );
			 }
		 } else {
			 throw new ValidationException("Merci de préciser l'etat du status");
		 }
		
	}


	public static void validateComment(String comment) throws ValidationException {
		if(comment != null){
			comment = comment.trim();
			if(!comment.matches("^([a-zA-z_0-9,-]*\\s[a-zA-Z0-9,-]*\\s?\\n?)*$")){
				throw new ValidationException("Votre Commentaire est invalide");
			}
		}else{
			throw new ValidationException("Merci de saisir votre commentaire");
		}
		
	}


	public static void validateRating(String rating) throws ValidationException{
		if(rating != null){
			rating = rating.trim();
			if(!rating.matches("^[0-5]$")){
				throw new ValidationException("Le ration precise est invalide (0-5)");
			}
		}else{
			throw new ValidationException("Merci de preciser le ration sur ce produit");
		}
	}


	public static void validateId(String id) throws ValidationException {
		if ( id != null ) {
			 id = id.trim();
			 if(!id.matches("^([0-9])+$")){
				 throw new ValidationException( "l'identificateur est incorrecte" );
			 }
		 } else {
			 throw new ValidationException("aucun identifiant");
		 }
		
	}


	public static void validateCity(String delivery_city) {
		// TODO Auto-generated method stub
		
	}
}
