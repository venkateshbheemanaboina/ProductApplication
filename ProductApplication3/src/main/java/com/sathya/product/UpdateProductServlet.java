package com.sathya.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/UpdateProductServlet")
@MultipartConfig(maxFileSize = 16177215) // 16 MB

public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String proId = request.getParameter("proId");
        String updatedProductName = request.getParameter("proName");
        double updatedProductPrice = Double.parseDouble(request.getParameter("proCost"));
        String updatedProductBrand = request.getParameter("proBrand");
        String updatedProductMadeIn = request.getParameter("proMadeIn");
        Date updatedProductMfgDate = Date.valueOf(request.getParameter("proMfgDate"));
        Date updatedProductExpDate = Date.valueOf(request.getParameter("proExpDate"));
         
	     Part filePart = request.getPart("proImage");
	        InputStream inputStream = filePart.getInputStream();
	        byte[] proImage = inputStream.readAllBytes();
	       
	        Product product = new Product();
		     product.setProId(proId);
		     product.setProName(updatedProductName);
		     product.setProCost(updatedProductPrice);
		     product.setProBrand(updatedProductBrand);
		     product.setProMadeIn(updatedProductMadeIn);
		     product.setProMfgDate(updatedProductMfgDate);
		     product.setProExpDate(updatedProductExpDate);
		     product.setProImage(proImage);

		     
		     ProductDao dao = new ProductDao();
  
	     int result = dao.updateProduct(product);
	
	     if(result == 1)
	     {  	RequestDispatcher dispatcher = request.getRequestDispatcher("ListOfProduct.jsp");
	        	dispatcher.forward(request, response);
	     }    
	}

}
