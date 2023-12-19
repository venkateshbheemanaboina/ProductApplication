<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Failed</title>
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
        <h3>Login Failed</h3>
        <p>Invalid username or password. Please try again.</p>
    </div>

    <button onclick="goBack()" class="btn btn-secondary">Go Back</button>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>
