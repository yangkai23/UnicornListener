package com.parker.unicornListener.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
//import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
		System.out.println(config.getServletContext().getServletContextName());
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		PrintWriter printWriter = response.getWriter();
		boolean isValid = UserRepository.isValidUser(email, password);
		Optional<User> optionalUser;
		if (isValid)
			optionalUser = UserRepository.getUser();
		else
			optionalUser = Optional.empty();
		/*
		 * printWriter.write( "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" +
		 * "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n" +
		 * "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
		 * "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
		 * + "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n" +
		 * "    <h1 style=\"color: darkslategrey;\">Welcome " + user.getFirstName() +
		 * "</h1>\r\n" + "</body>\r\n" + "</html>"); } else { printWriter.write(
		 * "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" +
		 * "    <meta charset=\"UTF-8\">\r\n" +
		 * "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
		 * "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
		 * + "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n" +
		 * "    <h1 style=\"color: crimson;\">Please check the credentials</h1>\r\n" +
		 * "</body>\r\n" + "</html>");
		 */
		String url;
		if (optionalUser.isPresent()) {
			url = "/SuccessLogin.jsp";
			request.setAttribute("firstName", optionalUser.get().getFirstName());
		} else
			url = "/failedLogin.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
