package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = (String) request.getParameter("username");
		String pass = (String) request.getParameter("password");

		credentialss c = new credentialss(user, pass);
		boolean var = c.verification();

		if (var) {
			String key = "sdyg3u4grt3u4g";
			String authenticationKey = key;
			// passing the key through the header
			response.setHeader("Authentication-Key", authenticationKey);
			c.updateDB(key, user);
			request.getSession().setAttribute("generatedKey", key);
			request.setAttribute("user", user);
			request.setAttribute("key", key);
			request.setAttribute("cred", c);
			request.getRequestDispatcher("/index.html").forward(request, response);
			// RequestDispatcher rd=(RequestDispatcher) request.getRequestDispatcher("")
		} else {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
