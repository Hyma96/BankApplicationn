<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Interest Confirmation</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: rgb(240, 240, 240); 
    padding: 50px;
    text-align: center;
  }

  h1 {
    color: rgb(0, 123, 255); 
  }

  p {
    font-size: 18px;
    color: rgb(34, 34, 34); 
  }

  .highlight {
    color: rgb(0, 102, 0); 
    font-weight: bold;
  }

  .button-container {
    margin-top: 20px;
  }

  .home-button {
    background-color: #007bff; 
    color: white; 
    padding: 10px 20px; 
    border: none; 
    border-radius: 4px; 
    cursor: pointer; 
    text-decoration: none;
    font-size: 16px;
  }

  .home-button:hover {
    background-color: #0056b3;
  }
</style>
</head>
<body>

<%
session = request.getSession();
String name = (String) session.getAttribute("name");
String email = (String) session.getAttribute("email");

out.println("<h1>Thank you for your interest in our loans!</h1>");
out.println("<p>Dear <span class='highlight'>" + name + "</span>,</p>");
out.println("<p>Thank you for showing your interest in the loans from ABC Bank. Our executive will contact you soon on the following email address:</p>");
out.println("<p class='highlight'>" + email + "</p>");
%>

<div class="button-container">
  <a href="Home.html" class="home-button">Go to Home</a>
</div>

</body>
</html>
