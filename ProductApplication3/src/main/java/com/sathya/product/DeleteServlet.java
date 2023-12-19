package com.sathya.product;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the product ID to be deleted
        String proId = request.getParameter("proId");

        // Delete the product based on the product ID
        ProductDao dao = new ProductDao();
        dao.deleteProduct(proId);

        // Redirect back to the list of products after deletion
        response.sendRedirect("ListOfProduct.jsp");
    }
}

