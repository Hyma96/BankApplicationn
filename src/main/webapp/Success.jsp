<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction Statement</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: rgb(240, 240, 240);
        text-align: center;
        padding-top: 50px;
    }
    table {
        width: 80%;
        margin: auto;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid rgb(200, 200, 200);
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: rgb(0, 123, 255);
        color: white;
    }
    h3 {
        color: rgb(0, 123, 255);
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
    // Retrieve the transaction list from session
    ArrayList<ArrayList<String>> transactions = (ArrayList<ArrayList<String>>) session.getAttribute("transactions");

    if (transactions == null || transactions.isEmpty()) {
%>
        <h3>No transactions found.</h3>
<%
    } else {
%>
        <h3>Transaction Statement</h3>
        <table>
            <tr>
                <th>Account Number</th>
                <th>Recipient Account Number</th>
                <th>Balance</th>
                <th>Purpose</th>
            </tr>
<%
        // Iterate over each transaction and display its details
        for (ArrayList<String> transaction : transactions) {
%>
            <tr>
                <td><%= transaction.get(0) %></td> <!-- Account Number -->
                <td><%= transaction.get(1) %></td> <!-- Recipient Account Number -->
                <td><%= transaction.get(2) %></td> <!-- Balance -->
                <td><%= transaction.get(3) %></td> <!-- Purpose -->
            </tr>
<%
        }
%>
        </table>
<%
    }
%>

<div class="button-container">
    <a href="Home.html" class="home-button">Back to Home</a>
</div>

</body>
</html>
