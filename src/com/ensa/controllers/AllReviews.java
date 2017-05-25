package com.ensa.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensa.models.Review;
import com.ensa.models.Seller;
import com.ensa.service.ReviewService;

/**
 * Servlet implementation class AllReviews
 */
@WebServlet(value={"/AllReviews","/AllReviews/switch_status"})
public class AllReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ALL_REVIEWS ="/WEB-INF/views/admin/allReviews.jsp";   
	

    @EJB
    ReviewService rs;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllReviews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Review> reviews= rs.getAllReviews();
		System.out.println("SIZE -------------- "+reviews.size());
		System.out.println(""+ reviews.get(0).getClient().getPerson().getFirstname());
		request.setAttribute("reviews",reviews);
		this.getServletContext().getRequestDispatcher(ALL_REVIEWS).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/AllReviews/switch_status")){
			int reviewId = Integer.parseInt(request.getParameter("review_id"));
			Review review = rs.getReviewId(reviewId);
			if(review.getStatus())
				review.setStatus(false);
			else
				review.setStatus(true);

			rs.update(review);
			
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllReviews");
		}else{
			response.sendRedirect(this.getServletContext().getContextPath()+"/AllReviews");
		}
	}

}
