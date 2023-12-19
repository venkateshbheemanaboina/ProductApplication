package com.sathya.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user details from the form
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String userMail = request.getParameter("userMail");
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        // Create a User object with the retrieved details
        User user = new User(userName, userPhone, userMail, userId, userPassword);
        int result=0;
        // Call UserDao to register the user into the database
        UserDao dao = new UserDao();
        try {
		result =dao.registerUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if(result==1)
        response.sendRedirect("RegistrationSuccess.jsp");
        else
        {
        	response.sendRedirect("RegistrationFailed.jsp");
        }
    }
}
