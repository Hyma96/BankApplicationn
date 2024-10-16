package com.BOB.controller;

import java.io.IOException;
import com.BOB.model.Model;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int accno = (Integer) session.getAttribute("accno");
        
        String samt = request.getParameter("amt");
        int amt = Integer.parseInt(samt);
        String sraccno = request.getParameter("raccno");
        int raccno = Integer.parseInt(sraccno);

        // Get the purpose parameter from the request
        String purpose = request.getParameter("purpose");

        try {
            Model m = new Model();
            m.setAccno(accno);
            m.setRaccno(raccno);
            m.setBal(amt);
            m.setPurpose(purpose); // Set the purpose in the model
            
            boolean b = m.transfer();
            
            if (b) {
                response.sendRedirect("/BOBBankingApplication/TransferSuccess.html");
            } else {
                response.sendRedirect("/BOBBankingApplication/TransferFail.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
