<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Failed</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
        }

        #error-message {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }

        button {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div id="error-message">
        <h3>Registration Failed</h3>
        <p>Sorry, we couldn't complete your registration. Please check your details and try again.</p>
    </div>

    <a href="UserForm.html" class="btn btn-primary">Go back to Registration</a>
</div>

</body>
</html>
