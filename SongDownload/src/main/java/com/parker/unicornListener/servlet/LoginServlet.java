package com.parker.unicornListener.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.core.impl.Log4jProvider;

import com.parker.unicornListener.dto.User;
import com.parker.unicornListener.repo.UserRepository;

public class LoginServlet extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		System.out.println("LoginServlet Constructor");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(config.getServletContext());
		System.out.println("LoginServlet init");
		super.init(config);
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet service");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PrintWriter printWriter = response.getWriter();
		if (UserRepository.isValidUser(email, password)) {
			Optional<User> optionalUser = UserRepository.getUser();
			User user = optionalUser.get();
			printWriter.write(
					"<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n"
							+ "    <h1 style=\"color: darkslategrey;\">Welcome " + user.getFirstName() + "</h1>\r\n"
							+ "</body>\r\n" + "</html>");
		} else {
			printWriter.write(
					"<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n"
							+ "    <h1 style=\"color: crimson;\">Please check the credentials</h1>\r\n" + "</body>\r\n"
							+ "</html>");
		}
	}

	@Override
	public void destroy() {
		System.out.println("LoginServlet Destroyed");
		super.destroy();
	}
}
