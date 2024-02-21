package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet {
	public void service ( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException{
		String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        if ("cassandrabutera01@gmail.com".equals(email) && "cassy@5".equals(password)) {
            try {
            	HttpSession session = req.getSession(true);
            	session.setAttribute("email", email);
            	res.sendRedirect(req.getContextPath() + "/stuadd.html");
            } catch (IOException e) {
                e.printStackTrace();
           }
        } else {
        	PrintWriter showmes = res.getWriter();
        	showmes.println("No values");
        }
	}
}
