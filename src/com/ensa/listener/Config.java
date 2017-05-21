package com.ensa.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ensa.models.City;

/**
 * Application Lifecycle Listener implementation class Config
 *
 */
@WebListener
public class Config implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Config() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent e)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	 event.getServletContext().setAttribute("cityValues", City.values());
    }
	
}
