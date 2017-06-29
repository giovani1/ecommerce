package com.ensa.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensa.util.Name;

/**
 * Servlet Filter implementation class AdminAndSellerFilter
 */
@WebFilter({"/product/*","/products"})
public class AdminAndSellerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminAndSellerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestH = ((HttpServletRequest)request);
		HttpSession session = requestH.getSession();
		if(session.getAttribute(Name.ACCOUNT_TYPE) != null && (((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("admin") || ((String)session.getAttribute(Name.ACCOUNT_TYPE)).equals("seller") )){ 
			chain.doFilter(request, response);
		} else {
		      ((HttpServletResponse)response).sendRedirect(requestH.getServletContext().getContextPath()+"/Home");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
