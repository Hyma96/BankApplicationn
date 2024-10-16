package com.BOB.model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Model {
	private String name;
	private String custid;
	private int accno;
	private String pwd;
	private int bal;
	private String email;
	private int raccno;
	private String purpose;
	private ResultSet res;
	public ArrayList al=new ArrayList();
	public ArrayList sal=new ArrayList();
	public ArrayList ral=new ArrayList();
	
	private Connection con;
	private PreparedStatement pstmt;
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCustId() {
		return custid;
	}
	public void setCustId(String custid) {
		this.custid = custid;
	}
	
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	
	public Model() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");//Loading the Driver
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapplication","root","Hymareddy@96");
		System.out.println("Loading the driver and establishing the connection is completed");
	}
	
	
	public boolean register()throws SQLException {
		String s="insert into abc values (?,?,?,?,?,?)"; //Incomplete query
		
		pstmt=con.prepareStatement(s);
		pstmt.setString(1, name);
		pstmt.setString(2, custid);
		pstmt.setInt(3, accno);
		pstmt.setString(4, pwd);
		pstmt.setInt(5, bal);
		pstmt.setString(6, email);

		int x = pstmt.executeUpdate();

		if(x>0) {
			return true;
		}
		return false;
	}
	
	public boolean login() throws SQLException {
		String s="select * from abc where custid=? and pwd=?";
		
		pstmt=con.prepareStatement(s);
		pstmt.setString(1,custid);
		pstmt.setString(2,pwd);
		res=pstmt.executeQuery();
		
		while(res.next()==true) {
			accno=res.getInt("accno");
			return true;
		}
		return false;
	}
	public boolean checkBalance() throws SQLException {
		String s="select bal from abc where accno=?";
		pstmt=con.prepareStatement(s);
		pstmt.setInt(1,accno);
		res=pstmt.executeQuery();
		while(res.next()==true) {
			bal=res.getInt("bal");
			return true;
		}
		return false;
	}

	public boolean changePwd() throws SQLException {
		String s="update abc set pwd=? where accno=?";
		pstmt=con.prepareStatement(s);
		pstmt.setString(1,pwd);
		pstmt.setInt(2, accno);
		int x=pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public boolean transfer() throws SQLException {
	    // Check if the account has sufficient balance first
	    String s1 = "Select bal from abc where accno=?";
	    pstmt = con.prepareStatement(s1);
	    pstmt.setInt(1, accno);
	    res = pstmt.executeQuery();

	    if (res.next()) {
	        int currentBalance = res.getInt("bal");
	        
	        if (currentBalance < bal) {
	            System.out.println("Insufficient balance!");
	            return false;
	        }
	    }

	    // Proceed with the transfer
	    String s2 = "update abc set bal=bal-? where accno=?";
	    pstmt = con.prepareStatement(s2);
	    pstmt.setInt(1, bal);
	    pstmt.setInt(2, accno);
	    int y1 = pstmt.executeUpdate();

	    if (y1 > 0) {
	        // Check if receiving account exists
	        String s3 = "Select bal from abc where accno=?";
	        pstmt = con.prepareStatement(s3);
	        pstmt.setInt(1, raccno);
	        res = pstmt.executeQuery();

	        if (res.next()) {
	            String s4 = "update abc set bal=bal+? where accno=?";
	            pstmt = con.prepareStatement(s4);
	            pstmt.setInt(1, bal);
	            pstmt.setInt(2, raccno);
	            int y2 = pstmt.executeUpdate();

	            if (y2 > 0) {
	                // Log the transfer in the statement with purpose
	                String s5 = "insert into getstatement (accno, raccno, bal, purpose) values (?, ?, ?, ?)";
	                pstmt = con.prepareStatement(s5);
	                pstmt.setInt(1, accno);
	                pstmt.setInt(2, raccno);
	                pstmt.setInt(3, bal);
	                pstmt.setString(4, purpose);  // Adding purpose to the transaction
	                int y = pstmt.executeUpdate();

	                if (y > 0) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

	public ArrayList<ArrayList<String>> getStatement() throws SQLException {
	    // Create an ArrayList to store all transactions
	    ArrayList<ArrayList<String>> transactions = new ArrayList<>();

	    // Prepare the SQL query to retrieve all transactions for a specific account
	    String s = "select * from getstatement where accno=?";
	    pstmt = con.prepareStatement(s);
	    pstmt.setInt(1, accno);
	    res = pstmt.executeQuery();

	    // Iterate through the result set and gather transaction details
	    while (res.next()) {
	        // Create an ArrayList to store details of each transaction
	        ArrayList<String> transaction = new ArrayList<>();
	        
	        // Add account number, recipient account number, balance, and purpose to the list
	        transaction.add(String.valueOf(res.getInt("accno")));   // Account number
	        transaction.add(String.valueOf(res.getInt("raccno")));  // Recipient account number
	        transaction.add(String.valueOf(res.getInt("bal")));     // Balance
	        transaction.add(res.getString("purpose"));              // Purpose of the transaction
	        
	        // Add this transaction to the list of all transactions
	        transactions.add(transaction);
	    }

	    // Return the complete list of transactions
	    return transactions;
	}


	public boolean applyLoan() throws SQLException {
		String s="select * from abc where accno=?";
		pstmt=con.prepareStatement(s);
		pstmt.setInt(1, accno);
		res=pstmt.executeQuery();
		while(res.next()==true) {
			name=res.getString("name");
			email=res.getString("email");
			return true;
		}
		return false;
	}
}
