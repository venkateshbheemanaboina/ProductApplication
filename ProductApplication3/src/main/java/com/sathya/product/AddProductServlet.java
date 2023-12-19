package com.sathya.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddProductServlet")
@MultipartConfig(maxFileSize = 16177215) // 16 MB
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String proId = request.getParameter("proId");
        String proName = request.getParameter("proName");
        String proCostStr = request.getParameter("proCost");
        double proCost = Double.parseDouble(proCostStr); // Convert to double
        String proBrand = request.getParameter("proBrand");
        String proMadeIn = request.getParameter("proMadeIn");
        Date proMfgDate = Date.valueOf(request.getParameter("proMfgDate"));
        Date proExpDate = Date.valueOf(request.getParameter("proExpDate"));

        // Process image file
        Part filePart = request.getPart("proImage");
        InputStream inputStream = filePart.getInputStream();
        byte[] proImage = inputStream.readAllBytes();

        // Create a Product object
        Product product = new Product(proId, proName, proCost, proBrand, proMfgDate, proExpDate, proMadeIn, proImage);

        // Call ProductDAO to insert the product into the database
        ProductDao dao = new ProductDao();
        int result = dao.insertProduct(product);

        // Check the result and forward or redirect accordingly
        if (result > 0) {
            // Successful insertion, you can forward or redirect to a success page
            response.sendRedirect("success.jsp");
        } else {
            // Insertion failed, handle accordingly (e.g., forward or redirect to an error page)
            response.sendRedirect("error.jsp");
        }
    }
}
