package com.sathya.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve login credentials
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        // Create an instance of UserDao
        UserDao userDao = new UserDao();

        // Validate credentials
        try {
            if (userDao.validateUser(userId, userPassword)) {
            	 response.sendRedirect("Home.html");
            } else {
            	 response.sendRedirect("./LoginFaild.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
