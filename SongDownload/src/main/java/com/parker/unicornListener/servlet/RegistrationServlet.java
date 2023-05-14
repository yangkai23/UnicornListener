package com.parker.unicornListener.servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.parker.unicornListener.dto.User;
import com.parker.unicornListener.repo.UserRepository;

public class RegistrationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log("inside Register Servlet..................");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Optional<User> optionalUser = UserRepository.insertUser(email, password, firstName, lastName, this);
		log("User Object fetched");
		request.setAttribute("user", optionalUser.get());
		
		String url = "/registerSuccess.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
