package com.BOB.controller;

import java.io.IOException;

import com.BOB.model.Model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("name");
		String ccustid = request.getParameter("custid");
		
		
		String saccno = request.getParameter("accno");//Collects the data in form of string
      	int caccno=Integer.parseInt(saccno);
      	
		String cpwd = request.getParameter("pwd");
		
		String sbal = request.getParameter("bal");//Collects the data in form of string
		int cbal=Integer.parseInt(sbal);//Converts string data to int data
		
		String cemail = request.getParameter("email");
		
		try {
			Model m=new Model();
			
			m.setName(cname);
			m.setCustId(ccustid);
			m.setAccno(caccno);
			m.setPwd(cpwd);
			m.setBal(cbal);
			m.setEmail(cemail);
			
			boolean b = m.register();
			
			if(b == true) {
				response.sendRedirect("/BOBBankingApplication/SuccessReg.html");
			}
			else {
				response.sendRedirect("/BOBBankingApplication/FailureReg.html");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}


