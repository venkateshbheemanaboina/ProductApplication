<%@ page import="com.sathya.product.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sathya.product.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product Information</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 20px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            text-align: right;
        }

        button {
            margin-top: 20px;
        }

        #error-message {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }

        input[type="text"],
        input[type="number"],
        input[type="date"],
        input[type="file"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type="submit"],
        a.btn {
            padding: 10px 20px;
            font-size: 16px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        a.btn {
            background-color: #6c757d;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
    </style>
    <script>
        function validateForm() {
            var proId = document.getElementById('proId').value;
            var proName = document.getElementById('proName').value;
            var proCost = document.getElementById('proCost').value;
            var proBrand = document.getElementById('proBrand').value;
            var proMadeIn = document.getElementById('proMadeIn').value;
            var proMfgDate = document.getElementById('proMfgDate').value;
            var proExpDate = document.getElementById('proExpDate').value;
            var proImage = document.getElementById('proImage').value;

            var errorMessage = '';

            // Check if proId is alphanumeric
            if (!/^[a-zA-Z0-9]+$/.test(proId)) {
                errorMessage += 'Product ID must be alphanumeric.<br>';
            }

            if (proName.trim() === '') {
                errorMessage += 'Product Name is required.<br>';
            }

            if (proCost.trim() === '' || isNaN(proCost)) {
                errorMessage += 'Product Cost must be a valid number.<br>';
            }

            if (proBrand.trim() === '') {
                errorMessage += 'Brand is required.<br>';
            }

            // Check if proMfgDate is not greater than proExpDate
            if (proMfgDate > proExpDate) {
                errorMessage += 'Manufacturing Date cannot be greater than Expiry Date.<br>';
            }

            if (proExpDate.trim() === '') {
                errorMessage += 'Expiry Date is required.<br>';
            }

            if (proImage.trim() === '') {
                errorMessage += 'Product Image is required.<br>';
            }

            if (errorMessage !== '') {
                document.getElementById('error-message').innerHTML = errorMessage;
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <form id="productForm" action="./UpdateProductServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div id="error-message"></div>

        <div class="form-group row">
            <label for="proId" class="col-sm-4 col-form-label">Product ID:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="proId" name="proId" required value="${existingProduct.proId}" readonly>
            </div>
        </div>

        <div class="form-group row">
            <label for="proName" class="col-sm-4 col-form-label">Product Name:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="proName" name="proName" required value="${existingProduct.proName}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proCost" class="col-sm-4 col-form-label">Product Cost:</label>
            <div class="col-sm-8">
                <input type="number" step="0.01" class="form-control" id="proCost" name="proCost" required value="${existingProduct.proCost}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proBrand" class="col-sm-4 col-form-label">Brand:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="proBrand" name="proBrand" required value="${existingProduct.proBrand}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proMadeIn" class="col-sm-4 col-form-label">Made In:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="proMadeIn" name="proMadeIn" required value="${existingProduct.proMadeIn}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proMfgDate" class="col-sm-4 col-form-label">Manufacturing Date:</label>
            <div class="col-sm-8">
                <input type="date" class="form-control" id="proMfgDate" name="proMfgDate" required value="${existingProduct.proMfgDate}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proExpDate" class="col-sm-4 col-form-label">Expire Date:</label>
            <div class="col-sm-8">
                <input type="date" class="form-control" id="proExpDate" name="proExpDate" required value="${existingProduct.proExpDate}">
            </div>
        </div>

        <div class="form-group row">
            <label for="proImage" class="col-sm-4 col-form-label">New Product Image:</label>
            <div class="col-sm-8">
                <input type="file" class="form-control-file" id="proImage" name="proImage" value="${existingProduct.proImage}" accept="image/*">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-4"></div>
            <div class="col-sm-8">
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="Home.html" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </form>
</body>
</html>
