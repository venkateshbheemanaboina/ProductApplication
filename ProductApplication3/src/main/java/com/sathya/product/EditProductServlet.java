package com.sathya.product;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditProductServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 16) // 5 MB as an example; adjust as needed
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve proId from the form
        String proId = request.getParameter("proId");

        // Update the product in the database based on proId
        ProductDao dao = new ProductDao();
        Product existingProduct = dao.getProductById(proId);
		if (existingProduct != null) {
		    // Convert the image data to Base64
		    byte[] imageBytes = existingProduct.getProImage();
		    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

		    // Set product and image data as request attributes
		    request.setAttribute("existingProduct", existingProduct);
		    request.setAttribute("base64Image", base64Image);

		    // Forward to the JSP page
		    RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		    dispatcher.forward(request, response);
		} else {
		    response.getWriter().println("Error: Product not found");
		}
    }
}
