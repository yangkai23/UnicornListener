package com.parker.unicornListener.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
//import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parker.unicornListener.dto.User;
import com.parker.unicornListener.repo.UserRepository;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		System.out.println("LoginServlet Constructor");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
//		System.out.println(config.getServletContext().getServletContextName());
		System.out.println("LoginServlet init");
		super.init(config);
	}

	@Override
	public void destroy() {
		System.out.println("LoginServlet Destroyed");
		super.destroy();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletConfig servletConfig = getServletConfig();
		String servletName = servletConfig.getServletName();
		log("Servlet Name : " + servletName);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		log("Current User : " + email);
		boolean isValid = UserRepository.isValidUser(email, password, this);
		Optional<User> optionalUser;
		if (isValid) {
			optionalUser = UserRepository.getUser();
			Cookie emailCookie = new Cookie("email", email);
			Cookie passCookie = new Cookie("pass", password);
			response.addCookie(passCookie);
			response.addCookie(emailCookie);
		} else
			optionalUser = Optional.empty();
		String projName = getServletContext().getInitParameter("projectName");
		String url = "/SuccessLogin.jsp";
		request.setAttribute("projectName", projName);
		request.setAttribute("user", optionalUser.orElseGet(() -> null));
		log("Redirecting to ..." + url.substring(1));
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
