package com.ensa.forms;

import javax.servlet.http.HttpServletRequest;

import com.ensa.models.Review;
import com.ensa.util.Validation;
import com.ensa.util.ValidationException;

public class ReviewForm extends Form{

	private static final String RATING = "rating";
	private static final String COMMENT = "comment";
	
	public Review addReview(HttpServletRequest request){
		String rating = getValue(request,RATING);
		String comment = getValue(request,COMMENT);
		
		Review review = new Review();
		
		try{
			review.setRating(Float.parseFloat(rating));
			Validation.validateRating(rating);
		}catch(ValidationException e){
			setError(RATING,e.getMessage());
		}catch(Exception e){
			review.setRating(Float.valueOf(0));
			
		}
		
		try{
			review.setText(comment.trim());
			Validation.validateComment(comment);  
			
		}catch(ValidationException e){
			setError(COMMENT,e.getMessage());
		}catch(NullPointerException e){
			setError(COMMENT,"Vous devez saisir un commentair !");
		}
		
		
		
		if( errors.isEmpty() ) {
	        result = "true";
	    } else {
	        result = "false";
	    }
		
		return review;
	}
}
