package com.service;

import jakarta.servlet.Filter;
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpFilter;


/**
 * Servlet Filter implementation class SessionFilter
 */

@WebFilter("/login")
public class SessionFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SessionFilter() {
        super();
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
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        
     // Check if the request is for the login page
        
        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.endsWith("/Login.html") || requestURI.endsWith("/login"); 
        
        // If it's not the login page and user is not authenticated, redirect to login page
        if (!isLoginPage && (session == null || session.getAttribute("email") == null)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login.html");
            return;
        }

        // If it's the login page, validate username and password
        if (isLoginPage && httpRequest.getMethod().equalsIgnoreCase("POST")) {
            String email = httpRequest.getParameter("email");
            String password = httpRequest.getParameter("password");

            // Perform simple validation (you should use more secure methods in production)
            if ("cassandrabutera01@gmail.com".equals(email) && "cassy@5".equals(password)) {
                // Valid credentials, create session and redirect to home page
                session = httpRequest.getSession(true); // Create a new session if one doesn't exist
                session.setAttribute("email", email);
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/stuadd.html");
                return;
            } else {
                // Invalid credentials, redirect back to login page with error message
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login.html?error=invalid");
                return;
            }
        }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
