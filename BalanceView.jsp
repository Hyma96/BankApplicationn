<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance</title>
<style>
  body {
    background-color: #f0f0f0; 
    font-family: Arial, sans-serif;
  }

  h1 {
    color: rgb(0, 123, 255); 
    text-align: center;
    padding-top: 50px;
  }

  .balance {
    font-size: 24px;
    color: rgb(34, 139, 34); 
    text-align: center;
  }

  .button-container {
    text-align: center;
    margin-top: 20px;
  }

  button {
    padding: 10px 20px;
    font-size: 16px;
    background-color: rgb(0, 123, 255);
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button:hover {
    background-color: rgb(0, 100, 220);
  }
</style>
</head>
<body>

<h1>Your Current Balance</h1>

<div class="balance">
<%
  session = request.getSession();
  out.println(session.getAttribute("bal"));
%>
</div>

<div class="button-container">
  <button onclick="location.href='Home.html'">Go Back to Home</button>
</div>

</body>
</html>
