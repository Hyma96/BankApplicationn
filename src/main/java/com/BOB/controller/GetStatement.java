package com.BOB.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.BOB.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        Integer str = (Integer) session.getAttribute("accno");
        int accno = Integer.valueOf(str);

        try {
            // Instantiate the model and set the account number
            Model m = new Model();
            m.setAccno(accno);
            
            // Retrieve the transaction history (list of ArrayLists)
            ArrayList<ArrayList<String>> transactions = m.getStatement();
            
            if (transactions.isEmpty()) {
                // No transactions found, redirect to a failure page
                response.sendRedirect("/BOBBankingApplication/StatementFail.html");
            } else {
                // Store the transaction list in the session
                session.setAttribute("transactions", transactions);
                
                // Redirect to the success page (e.g., JSP) to display the transactions
                response.sendRedirect("/BOBBankingApplication/Success.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
