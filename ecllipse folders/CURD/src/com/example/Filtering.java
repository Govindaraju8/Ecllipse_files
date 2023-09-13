package com.example;

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

/**
 * Servlet Filter implementation class Filtering
 */
@WebFilter("/Filtering")
public class Filtering implements Filter {

	/**
	 * Default constructor.
	 */
	public Filtering() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		// getting the err
		String authenticationKey = httpRequest.getHeader("Authentication-Key");
		// Console.log(authenticationKey);
		String storedKey = (String) session.getAttribute("generatedKey");

		// String providedKey = request.getParameter("key");
		if (authenticationKey != null && authenticationKey.equals(storedKey)) {
			// System.out.println("ghvxhzjvx");
			// Valid key, forward to employee.jsp
			chain.doFilter(request, response);
		} else {
			// HttpServletResponse httpResponse = (HttpServletResponse) response;
			// httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login.html");
			// Check if the requested resource exists
			if (request.getAttribute("javax.servlet.error.status_code") == null) {
				// Resource not found, redirect to custom 404 error page
				request.getRequestDispatcher("/error404.html").forward(request, response);
			} else {
				// Forward to the Login page for other errors
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login.html");
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
