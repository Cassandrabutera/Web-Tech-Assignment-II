package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet{
	String email, password;
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		email = req.getParameter("email");
		password = req.getParameter("password");
		
		if (email !=null && password !=null) {
			res.sendRedirect(req.getContextPath() + "/Login.html");
			
		}else {
			PrintWriter out = res.getWriter();
			out.println("Enter Values");
		}
	}

}
