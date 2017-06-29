package com.ensa.forms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ensa.models.Categorie;
import com.ensa.models.Product;
import com.ensa.models.Product_attributes;
import com.ensa.models.Product_options_value;
import com.ensa.service.CategorieService;
import com.ensa.service.ProductService;
import com.ensa.service.Product_attributesService;
import com.ensa.service.Product_optionsService;
import com.ensa.util.Cons;
import com.ensa.util.Validation;

public class ProductForm extends Form{
	
	private ProductService ps;
	private CategorieService cs;
	private Product_optionsService pos;
	private Product_attributesService pas;
	
	private Pattern reg = Pattern.compile("^(\\/)([0-9]+)(_)([a-zA-Z-0-9_]+)$");
	private Pattern reg1 = Pattern.compile("^(option_)([0-9]+)$");
	//private Pattern sreg = Pattern.compile("^(\\/)([0-9]+)(_)([a-zA-Z-_]+)$");
	//private Pattern creg = Pattern.compile("^(\\/)([0-9]+)(_)([a-zA-Z-_]+)$");
	//private String creg1 = "(\\/)([0-9]+)(_)([a-zA-Z-_]+)$";
	
	public ProductForm(ProductService ps,CategorieService cs,Product_optionsService pos,Product_attributesService pas) {
		this.ps=ps;
		this.cs=cs;
		this.pos=pos;
		this.pas=pas;
	}
	
	public int getProducts(HttpServletRequest request){
		Matcher smatch = reg.matcher(request.getPathInfo());
		int i=-1;
		try{
			if(smatch.find()){
				Validation.validateId(smatch.group(2));
				i= Integer.parseInt(smatch.group(2));
			}
		}catch(Exception e){
			setError("sourceid",e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else{
			result="false";
		}
		return i;
	}
	
	public Product getProduct(HttpServletRequest request){
		Product product=null;
		Matcher match = reg.matcher(request.getPathInfo());
		try{
			if(match.find()){
				product=ps.find(Integer.parseInt(match.group(2)));
			}
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,"lien invalid");
		}
		
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return product;
	}
	
	public Product getProduct1(HttpServletRequest request){
		String id = request.getQueryString();
		Product product=null;
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
			System.out.println("hereee");
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product;
	}
	public Product addProduct1(HttpServletRequest request,Product product) {
		try{
			Part part=request.getPart(Cons.PRODUCT_IMAGE_FIELD);
			try (InputStream input = part.getInputStream()) {
			    BufferedImage inputImage = ImageIO.read(input);

			    File uploads = new File("C:\\webapps\\resources\\img\\"+product.getId()+"_"+product.getDate_added().getTime()+".jpg");
			    ImageIO.write(inputImage, "jpg", uploads);
			    product.setImage(request.getContextPath()+"/img/"+uploads.getName());

			}
			catch (Exception e) {
				setError(Cons.PRODUCT_IMAGE_FIELD,e.getMessage());
			}
		} catch (Exception e) {
			setError(Cons.PRODUCT_IMAGE_FIELD,e.getMessage());
		}
		product.setUrl(product.getId()+"_"+product.getName().replace(' ','_').trim());
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product;
	}
	
	public Product addProduct(HttpServletRequest request) {
		String name 			= getValue(request,Cons.PRODUCT_NAME_FIELD);
		String description 		= getValue(request,Cons.PRODUCT_DESCRIPTION_FIELD);
		String quantity	 		= getValue(request,Cons.PRODUCT_QUANTITY_FIELD);
		String price 			= getValue(request,Cons.PRODUCT_PRICE_FIELD);
		String date_available 	= getValue(request,Cons.PRODUCT_DATE_AVAILABLE_FIELD);
		String categorie_id 	= getValue(request,Cons.CATEGORIE_ID_FIELD);
		
		ArrayList<String> values_id			=new ArrayList<String>();
		ArrayList<String> values_price		=new ArrayList<String>();
		Enumeration<String> options 		= request.getParameterNames();
		
		Product product						=new Product();
		Matcher match1=null;
		for(;options.hasMoreElements();){
			String element=options.nextElement();
			match1 = reg1.matcher(element);
			if(match1.find()){
				System.out.println("match         "+match1.group());
				String[] values=getValues(request,match1.group());
				for(int i=0;i<values.length;i++){
					values_id.add(values[i]);
					values_price.add(getValue(request,"price_"+values[i]));
				}
			}
			match1=null;
		}
		Product_attributes pa=new Product_attributes();

		List<Product_attributes> list1=new ArrayList<Product_attributes>();
		for(int i=0;i!=values_id.size();i++){	
			try{
				Validation.validateId(values_id.get(i));
				Validation.validateMoney(values_price.get(i));
				Product_options_value pov=pos.findValue(Integer.parseInt(values_id.get(i)));
				System.out.println("pov         "+pov.getName());
				pa.setProduct_options_value(pov);
				pa.setOptions_values_price(Integer.parseInt(values_price.get(i)));
				pa.setProduct(product);
				list1.add(pa);				
			}catch(Exception e){
				System.out.println("options"+e.getMessage());
				setError(Cons.OPTION_FIELD,e.getMessage());
			}
			pa=new Product_attributes();
		}
		product.setProducts_attributes(list1);
		Date date=null;
		product.setDate_added(new Date());
		
		product.setLast_modified(new Date());
		
		try{
			Validation.validateName(name);
		} catch(Exception e){
			System.out.println("name"+e.getMessage());
			setError(Cons.PRODUCT_NAME_FIELD,e.getMessage());
		}
		product.setName(name);

		try{
			Validation.validateText(description);
		} catch(Exception e){
			System.out.println("descrip"+e.getMessage());
			setError(Cons.PRODUCT_DESCRIPTION_FIELD,e.getMessage());
		}
		product.setDescription(description);

		try{
			Validation.validateQuantity(quantity);
			product.setQuantity(Integer.parseInt(quantity));
		} catch(Exception e){
			System.out.println("quantity"+e.getMessage());
			setError(Cons.PRODUCT_QUANTITY_FIELD,e.getMessage());
		}
	
		try{
			Validation.validateMoney(price);
			product.setPrice(Float.parseFloat(price));
		} catch(Exception e){
			System.out.println("monry"+e.getMessage());
			setError(Cons.PRODUCT_PRICE_FIELD,e.getMessage());
		}
		
		try{
			date = convertToDate(date_available);
		}catch(Exception e){
			System.out.println("date available"+e.getMessage());
			setError(Cons.PRODUCT_DATE_AVAILABLE_FIELD,e.getMessage());
		}
		product.setDate_available(date);
		
		try{
			Validation.validateId(categorie_id);
			product.setCategorie(cs.find(Integer.parseInt(categorie_id)));
		}catch(Exception e){
			System.out.println("categorieid"+e.getMessage());
			setError(Cons.CATEGORIE_ID_FIELD,e.getMessage());
		}
		
		if(errors.isEmpty()){
			
			result="true";
		}else
			result="false";
		return product;
	}
	
	private Date convertToDate(String date) throws Exception{
		Date dateV = null;
		String[] values = date.split("-");
		if(values.length == 3){
			try{
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(values[0]));
				cal.set(Calendar.MONTH, Integer.parseInt(values[1])-1);
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[2])-1);
				
				dateV = cal.getTime();
			}catch(Exception e){
				throw new Exception("Valeur date incorrecte.");
			}
		}else{
			throw new Exception("Format date ill?gale.");
		}
		
		return dateV;
	}

	public Product updateProduct(HttpServletRequest request){
		String name = getValue(request,Cons.PRODUCT_NAME_FIELD);
		String description = getValue(request,Cons.PRODUCT_DESCRIPTION_FIELD);
		String quantity = getValue(request,Cons.PRODUCT_QUANTITY_FIELD);
		String price = getValue(request,Cons.PRODUCT_PRICE_FIELD);
		String date_available = getValue(request,Cons.PRODUCT_DATE_AVAILABLE_FIELD);
		String id = getValue(request,Cons.PRODUCT_ID_FIELD);
		
		Product product=null;
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
			
			try{
				Part part=request.getPart(Cons.PRODUCT_IMAGE_FIELD);
				try (InputStream input = part.getInputStream()) {
				    BufferedImage inputImage = ImageIO.read(input);
				    File uploads = new File(request.getServletContext().getRealPath("/resources/img/")+"\\"+product.getId()+"_"+product.getDate_added().getTime()+".jpg");
				    ImageIO.write(inputImage, "jpg", uploads);
				    product.setImage(request.getContextPath()+"/resources/img/"+uploads.getName());
				}
				catch (Exception e) {
					setError(Cons.PRODUCT_IMAGE_FIELD,e.getMessage());
				}
			} catch (Exception e) {
				setError(Cons.PRODUCT_IMAGE_FIELD,e.getMessage());
			}
			
			try{
				Validation.validateName(name);
				product.setName(name);
				product.setUrl(product.getId()+"_"+name);
			} catch(Exception e){
				setError(Cons.PRODUCT_NAME_FIELD,e.getMessage());
			}
			
	
			try{
				Validation.validateText(description);
				product.setDescription(description);
			} catch(Exception e){
				setError(Cons.PRODUCT_DESCRIPTION_FIELD,e.getMessage());
			}
			
			
			try{
				Validation.validateQuantity(quantity);
				product.setQuantity(Integer.parseInt(quantity));
			} catch(Exception e){
				setError(Cons.PRODUCT_QUANTITY_FIELD,e.getMessage());
			}
	
			try{
				Validation.validateMoney(price);
				product.setPrice(Float.parseFloat(price));
			} catch(Exception e){
				setError(Cons.PRODUCT_PRICE_FIELD,e.getMessage());
			}
			
			try{
				Date date = convertToDate(date_available);
				product.setDate_available(date);
			}catch(Exception e){
				setError(Cons.PRODUCT_DATE_AVAILABLE_FIELD,e.getMessage());
			}
			
			product.setLast_modified(new Date());
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		
		return product;
	}

	public Product deleteProduct(HttpServletRequest request) {
		String id = getValue(request,Cons.PRODUCT_ID_FIELD);
		Product product=null;
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product;
	}

	public Product blockProduct(HttpServletRequest request) {
		String id = request.getQueryString();
		
		Product product=null;
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
			product.setStatus(false);
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product;
	}
	
	/*public source getSource(HttpServletRequest request) {
		System.out.println(request.getPathInfo());
		Matcher smatch = sreg.matcher(request.getPathInfo());
		Matcher cmatch = creg.matcher(request.getPathInfo());
		source s=new source("",0);
		try{
			if(smatch.find()){
				Validation.validateId(smatch.group(2));
				s=new source("seller",Integer.parseInt(smatch.group(2)));
			}
			if(cmatch.find()){
				System.out.println(cmatch.group(2));
				Validation.validateId(cmatch.group(2));
				s=new source("categorie",Integer.parseInt(cmatch.group(2)));
			}
		}catch(Exception e){
			setError("sourceid",e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else{
			result="false";
		}
		return s;
	}
	public class source{
		public String s;
		public int id;
		public source(String s,int id){
			this.id=id;
			this.s=s;
		}
	}*/
	public Product updateCategorieProduct(HttpServletRequest request) {
		String id = getValue(request,Cons.PRODUCT_ID_FIELD);
		String categorie_id = getValue(request,Cons.CATEGORIE_ID_FIELD);
		Product product=null;
		Categorie categorie=null;
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
			try{
				Validation.validateId(categorie_id);
				categorie=cs.find(Integer.parseInt(categorie_id));
				product.setCategorie(categorie);
			}catch(Exception ee){
				setError(Cons.CATEGORIE_ID_FIELD,ee.getMessage());
			}
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}
		else
			result="false";
		return product;
	}

	public Product addAttributesProduct(HttpServletRequest request) {

		String id = getValue(request,Cons.PRODUCT_ID_FIELD);
		Product product=null;
		
		ArrayList<String> values_id			=new ArrayList<String>();
		ArrayList<String> values_price		=new ArrayList<String>();
		Enumeration<String> options 		= request.getParameterNames();
		try{
			Validation.validateId(id);
			product=ps.find(Integer.parseInt(id));
			Matcher match1=null;
			for(;options.hasMoreElements();){
				String element=options.nextElement();
				match1 = reg1.matcher(element);
				if(match1.find()){
					System.out.println("match         "+match1.group());
					String[] values=getValues(request,match1.group());
					for(int i=0;i<values.length;i++){
						values_id.add(values[i]);
						values_price.add(getValue(request,"price_"+values[i]));
					}
				}
				match1=null;
			}
			Product_attributes pa=new Product_attributes();

			List<Product_attributes> list1=product.getProducts_attributes();
			if(list1.equals(null))
				list1=new ArrayList<Product_attributes>();
			for(int i=0;i!=values_id.size();i++){	
				try{
					Validation.validateId(values_id.get(i));
					Validation.validateMoney(values_price.get(i));
					Product_options_value pov=pos.findValue(Integer.parseInt(values_id.get(i)));
					System.out.println("pov         "+pov.getName());
					pa.setProduct_options_value(pov);
					pa.setOptions_values_price(Float.parseFloat(values_price.get(i)));
					pa.setProduct(product);
					list1.add(pa);				
				}catch(Exception e){
					System.out.println("options"+e.getMessage());
					setError(Cons.OPTION_FIELD,e.getMessage());
				}
				pa=new Product_attributes();
			}
			product.setProducts_attributes(list1);
		}catch(Exception e){
			setError(Cons.PRODUCT_ID_FIELD,e.getMessage());
		}
		
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return product;
	}

	public Product_attributes updateAttributesProduct(HttpServletRequest request) {
		String attribute_id = getValue(request,Cons.ATTRIBUTE_ID_FIELD);
		String price = getValue(request,Cons.VALUES_PRICE_FIELD);
		Product_attributes attribute=null;
		try{
			
			Validation.validateId(attribute_id);
			attribute=pas.find(Integer.parseInt(attribute_id));
			try{
				Validation.validateMoney(price);
				attribute.setOptions_values_price(Float.parseFloat(price));
			}catch(Exception eee){
				setError(Cons.VALUES_PRICE_FIELD,eee.getMessage());
				System.out.println(eee.getMessage());
			}
		}catch(Exception ee){
			System.out.println(ee.getMessage());
			setError(Cons.ATTRIBUTE_ID_FIELD,ee.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return attribute;
	}

	public Product_attributes deleteAttributesProduct(HttpServletRequest request) {
		String attribute_id = getValue(request,Cons.ATTRIBUTE_ID_FIELD);
		Product_attributes attribute=null;
		try{
			Validation.validateId(attribute_id);
			attribute=pas.find(Integer.parseInt(attribute_id));
		}catch(Exception ee){
			setError(Cons.ATTRIBUTE_ID_FIELD,ee.getMessage());
		}
		if(errors.isEmpty()){
			result="true";
		}else
			result="false";
		return attribute;
	}
}
