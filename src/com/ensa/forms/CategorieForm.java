package com.ensa.forms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ensa.models.Categorie;
import com.ensa.service.CategorieService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class CategorieForm extends Form{
	
	
	
	///private Pattern reg = Pattern.compile("^(\\/)([0-9]+)(_)([a-zA-Z]+)([a-zA-Z-]*)$");
	
	private CategorieService cs;
	
	public CategorieForm(CategorieService cs) {
		this.cs = cs;
	}

	/*public Categorie getCategorie(HttpServletRequest request){
		int id = 0;
		Matcher match = reg.matcher(request.getPathInfo());
		if(match.find()){
			id=Integer.parseInt(match.group(2));
		}else{
			setError(CATEGORIE_ID_FIELD,"lien invalid");
		}
		Categorie categorie=null;
		//categorie=cs.find(id);
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return categorie;
	}*/
	
	public Categorie getCategorie(HttpServletRequest request){
		String id = request.getQueryString();
		Categorie categorie=null;
		try{
			Validation.validateId(id);
			categorie=cs.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.CATEGORIE_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else{
			result="false";
		}
		return categorie;
	}
	
	public Categorie addCategorie(HttpServletRequest request){
		String name = getValue(request,Cons.CATEGORIE_NAME_FIELD);
		String parent_id = getValue(request,Cons.CATEGORIE_PARENT_ID_FIELD);
		
		Categorie categorie=new Categorie();
		
		try{
			Validation.validateName(name);
		} catch(Exception e){
			setError(Cons.CATEGORIE_NAME_FIELD,e.getMessage());
		}
		categorie.setName(name);
		
		try{
			Validation.validateId(parent_id);
			categorie.setParent(cs.find(Integer.parseInt(parent_id)));
		}catch(Exception e){
			//setError(Cons.CATEGORIE_PARENT_ID_FIELD,e.getMessage());
			categorie.setParent(null);
		}
		
		categorie.setDate_added(new Date());
		categorie.setLast_modified(new Date());
		if(errors.isEmpty()){
			result="true";
		}else{
			result="false";
		}
		return categorie;
	}
	
	public Categorie addCategorie1(HttpServletRequest request, Categorie categorie) {
		try {
			Part part=request.getPart(Cons.CATEGORIE_IMAGE_FIELD);
			File uploads = new File(request.getServletContext().getRealPath("/resources/img/")+"\\"+categorie.getId()+"_"+categorie.getDate_added().getTime()+".jpg");
			try (InputStream input = part.getInputStream()) {
			    BufferedImage inputImage = ImageIO.read(input);
			    ImageIO.write(inputImage, "jpg", uploads);
			    categorie.setImage(request.getContextPath()+"/resources/img/"+uploads.getName());
			}catch (Exception e) {
				setError(Cons.CATEGORIE_IMAGE_FIELD,e.getMessage());
			}
		} catch (Exception e) {
			setError(Cons.CATEGORIE_IMAGE_FIELD,e.getMessage());
		}
		return categorie;
	}
	
	public Categorie updateCategorie(HttpServletRequest request){
		String name = getValue(request,Cons.CATEGORIE_NAME_FIELD);
		String parent_id = getValue(request,Cons.CATEGORIE_PARENT_ID_FIELD);
		String id=getValue(request,Cons.CATEGORIE_ID_FIELD);
		Categorie categorie=null;
		try{
			Validation.validateId(id);
			categorie=cs.find(Integer.parseInt(id));
			System.out.println("hollllllll");
		}catch(Exception e){
			setError(Cons.CATEGORIE_ID_FIELD,e.getMessage());
		}
		try{
			Validation.validateName(name);
		} catch(Exception e){
			setError(Cons.CATEGORIE_NAME_FIELD,e.getMessage());
		}
		categorie.setName(name);
		
		try {
			Part part=request.getPart(Cons.CATEGORIE_IMAGE_FIELD);
			System.out.println(categorie.getImage());
			File uploads = new File(categorie.getImage());
			try (InputStream input = part.getInputStream()) {
			    BufferedImage inputImage = ImageIO.read(input);
			    ImageIO.write(inputImage, "jpg", uploads);
			}catch(Exception e){
				e.printStackTrace();
				setError(Cons.CATEGORIE_IMAGE_FIELD,e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError(Cons.CATEGORIE_IMAGE_FIELD,e.getMessage());
		}
		
		try{
			Validation.validateId(parent_id);
			categorie.setParent(cs.find(Integer.parseInt(parent_id)));
		}catch(Exception e){
			setError(Cons.CATEGORIE_PARENT_ID_FIELD,e.getMessage());
		}
		categorie.setLast_modified(new Date());
		if(errors.isEmpty()){
			result="true";
		}else{
			result="false";
		}

		return categorie;
	}

	public Categorie deleteCategorie(HttpServletRequest request) {
		String id = getValue(request,Cons.CATEGORIE_NAME_FIELD);
		Categorie categorie=null;
		try{
			Validation.validateId(id);
			categorie=cs.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.CATEGORIE_NAME_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else{
			result="false";
		}
		return categorie;
	}

	
}
