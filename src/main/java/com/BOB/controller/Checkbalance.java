package com.BOB.controller;

import java.io.IOException;

import com.BOB.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int accno=(int) session.getAttribute("accno");
		try {
			Model m=new Model();
			m.setAccno(accno);
			boolean b=m.checkBalance();
			if(b==true) {
				session.setAttribute("bal", m.getBal());
				response.sendRedirect("/BOBBankingApplication/BalanceView.jsp");
				
			}
			else {
				response.sendRedirect("BOBBankingApplication/BalanceFail.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
