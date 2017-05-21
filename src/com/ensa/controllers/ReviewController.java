package com.ensa.controllers;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.forms.ReviewForm;
import com.ensa.models.Client;
import com.ensa.models.Review;
import com.ensa.service.ClientService;
import com.ensa.service.ReviewService;
import com.ensa.util.Name;

/**
 * Servlet implementation class AddReview
 */
@WebServlet(value={"/review","/review/add","/review/update"})
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @EJB
    ReviewService rs;
    @EJB
    ClientService cs;
    
    public ReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute(Name.ACCOUNT) != null && ((String)request.getSession().getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
			if(request.getServletPath().equals("/review")){
				allReview(request,response);
			}else if(request.getServletPath().equals("/review/add")){
				addReviewGet(request,response);
			}else if(request.getServletPath().equals("/review/update")){
				updateReviewGet(request,response);
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
			}
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Name.ACCOUNT) != null && ((String)request.getSession().getAttribute(Name.ACCOUNT_TYPE)).equals("client")){
			if(request.getServletPath().equals("/review")){
				updateReviewPost(request,response);
			}else if(request.getServletPath().equals("/review/add")){
				addReviewPost(request,response);
			}else if(request.getServletPath().equals("/review/update")){
				updateReviewPost(request,response);
			}else{
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
			}
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
		}
		
	}
	
	private void allReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Name.CLIENT, (Client)request.getSession().getAttribute(Name.ACCOUNT));
		this.getServletContext().getRequestDispatcher(Name.ALL_CLIENT_REVIEWS).forward(request, response);
	}
	
	private void addReviewGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(Name.ADD_REVIEW_FORM).forward(request, response);
	}

	private void addReviewPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ReviewForm form = new ReviewForm();
		Review review = form.addReview(request);
		
		if(form.getResult().equals("true")){
			
			Client client = (Client)session.getAttribute(Name.ACCOUNT);
			review.setDate_added(new Date());
			review.setLast_modified(new Date());
			
			client = rs.addReviewToClient(review,client.getId());
			client = cs.getClientById(client.getId());
			request.getSession().setAttribute(Name.ACCOUNT,client);
//			//rs.addReviewToProduct(review.getId(), productId)
			//add review to product;
			//last seen here
			response.sendRedirect(this.getServletContext().getContextPath()+Name.REVIEWS);	
		}else{
			
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.REVIEW, review);
			this.getServletContext().getRequestDispatcher(Name.ADD_REVIEW_FORM).forward(request,response);
			
		}
	}


	private void updateReviewGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer reviewId;
		try{
			//get the id review from the url
			reviewId = Integer.parseInt(request.getParameter("id"));
			if( reviewId != null){
				Review review = (Review)rs.getReviewId(reviewId);
				
				if(review.getClient().getId() == ((Client)request.getSession().getAttribute(Name.ACCOUNT)).getId()){
					
					Review newReview = new Review();
					newReview.setClient(review.getClient());
					newReview.setDate_added(review.getDate_added());
					newReview.setId(review.getId());
					newReview.setLast_modified(review.getLast_modified());
					newReview.setProduct(review.getProduct());
					newReview.setRating(review.getRating());
					newReview.setText(review.getText());
					
					request.getSession().setAttribute(Name.ID_REVIEW_TO_UPDATE, reviewId);
					request.setAttribute(Name.REVIEW, review);
					this.getServletContext().getRequestDispatcher(Name.UPDATE_REVIEW_FORM).forward(request, response);
					
				}else{
					
					response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
				
				}
			}else{
				
				response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
			
			}
		}catch(Exception e){
			
			response.sendRedirect(this.getServletContext().getContextPath()+Name.HOME);	
		
		}
	}
	
	private void updateReviewPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewForm form = new ReviewForm();
		Review review = form.addReview(request);
		
		if(form.getResult().equals("true")){
			HttpSession session = request.getSession();
			
			Review tmpRev = rs.getReviewId((Integer)session.getAttribute(Name.ID_REVIEW_TO_UPDATE));
			tmpRev.setLast_modified(new Date());
			tmpRev.setText(review.getText());
			tmpRev.setRating(review.getRating());
			tmpRev = rs.update(tmpRev);
			
			session.setAttribute(Name.ACCOUNT,cs.getClientById(((Client)session.getAttribute(Name.ACCOUNT)).getId()));
			request.getSession().removeAttribute(Name.ID_REVIEW_TO_UPDATE);
			System.out.println("allthing here");
			
			response.sendRedirect(this.getServletContext().getContextPath()+Name.REVIEWS);
		}else{
			request.setAttribute(Name.FORM, form);
			request.setAttribute(Name.REVIEW, review);
			this.getServletContext().getRequestDispatcher(Name.UPDATE_REVIEW_FORM).forward(request,response);
		}
	}
}
