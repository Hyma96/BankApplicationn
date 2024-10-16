package com.BOB.controller;

import java.io.IOException;

import com.BOB.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private HttpSession session;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid=request.getParameter("custid");
		String pwd=request.getParameter("pwd");
		session=request.getSession(true);
		
		try {
			Model m=new Model();
			
			m.setCustId(custid);
			m.setPwd(pwd);
			
			boolean b = m.login();
			
			if(b==true) {
				session.setAttribute("accno", m.getAccno());
				response.sendRedirect("/BOBBankingApplication/Home.html");
			}
			else {
				response.sendRedirect("/BOBBankingApplication/Error.html");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
