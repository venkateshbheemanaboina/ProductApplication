<%@ page import="java.util.List" %>
<%@ page import="com.sathya.product.ProductDao" %>
<%@ page import="com.sathya.product.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    ProductDao dao = new ProductDao();
    List<Product> productList = dao.displayListOfProducts();
    pageContext.setAttribute("productList", productList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Products</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }

        table {
            width: 200%; /* Increase table width */
            margin: 0 auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow-x: auto; /* Enable horizontal scrolling if needed */
        }

        th, td {
            text-align: center;
            vertical-align: middle;
            font-size: 16px;
            padding: 15px;
            border-bottom: 1px solid #dee2e6;
        }

        thead {
            background-color: #ADD8E6; /* Light Blue */
            color: white;
        }

        img {
            max-width: 80px;
            max-height: 80px;
            display: block;
            margin: 0 auto;
            border-radius: 5px;
        }

        .action-buttons {
            display: flex;
            justify-content: space-around;
        }

        .btn-primary, .btn-danger, .btn-warning {
            width: 100px;
            font-size: 14px;
            font-weight: bold;
            border-radius: 5px;
            transition: all 0.3s;
        }

        .btn-primary:hover, .btn-danger:hover, .btn-warning:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="container">
    <h1>List of Products</h1>
    <form action="Home.html">
        <button class="btn btn-primary mb-3" type="submit">Home</button>
    </form>
    
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Cost</th>
                    <th>Brand</th>
                    <th>Manufacture Date</th>
                    <th>Expire Date</th>
                    <th>Made In</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${productList}">
                    <tr class="${loop.index % 2 == 0 ? 'table-light' : 'table-dark'}">
                        <td>${product.proId}</td>
                        <td>${product.proName}</td>
                        <td>${product.proCost}</td>
                        <td>${product.proBrand}</td>
                        <td>${product.proMfgDate}</td>
                        <td>${product.proExpDate}</td>
                        <td>${product.proMadeIn}</td>
                        <td>
                            <img src="data:image/jpeg;base64,${product.base64Image}" alt="Product Image">
                        </td>
                        <td class="action-buttons">
                            <form method="post" action="DeleteServlet">
                                <input type="hidden" name="proId" value="${product.proId}" />
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form>
                            <form method="post" action="./EditProductServlet">
                                <input type="hidden" name="proId" value="${product.proId}" />
                                <button class="btn btn-warning" type="submit">Update</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
