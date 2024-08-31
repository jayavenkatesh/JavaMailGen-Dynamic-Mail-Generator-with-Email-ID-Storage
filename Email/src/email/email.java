package email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class email {
	private String fname;
	private String lname;
	private String password;
	private String dep;
	private String email;
	private String alemail;
	private int capacity;
	private String comp= "sap.com";
	
	
	//String s="select name from stud where id=12";
	
	String url="jdbc:postgresql://localhost:5432/demo";
	String username="postgres";
	String pasw="0000";
	
	
	//System.out.println(name);
	//con.close();
	
	
	email(String fname,String lname){
		this.fname=fname;
		this.lname=lname;
		System.out.println("email created: "+ fname+" "+lname);
		
		this.dep=setdep();
		System.out.println("Department :"+ dep);
		
		this.password=setpass(8);
		System.out.println("password :"+ password);
		
		email=fname+'.'+lname+'@'+dep+'.'+comp;
		System.out.println("Your mail id is "+email);
		
		savemaildb();
	}

	private void savemaildb() {
		Connection conn=null;
		PreparedStatement st=null;
		
		try {
			conn=DriverManager.getConnection(url,username,pasw);
			
			
			String s="insert into emp (fname, lname, email, dep, password) VALUES (?, ?, ?, ?, ?)";
			
			st=conn.prepareStatement(s);
			//ResultSet rs=st.executeQuery(s);
			//rs.next();
			 st.setString(1, this.fname);
	         st.setString(2, this.lname);
	         st.setString(3, this.email);
	         st.setString(4, this.dep);
	         st.setString(5, this.password);
			//String name=rs.getString(1);
	         
	         st.executeUpdate();
	         System.out.println("Email saved to database successfully.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
                if (st != null) st.close();
                if (conn != null) conn.close();
            } 
			catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}

	private String setpass(int len) {
		String str="abcdefghijklmnopqrstuvwxyz0123456789@#$";
		char[] arr=new char[len];
		for(int i=0;i<len;i++) {
			int rand=(int) (Math.random()* str.length());
			arr[i]=str.charAt(rand);
		}
		return new String(arr);
		
		
		
		
	}

	private String setdep() {
		System.out.println("Select the department CODE \n1 for sales \n2 for dev \n3 for accounting \nEnter the Code :");
		Scanner sc=new Scanner(System.in);
		int c=sc.nextInt();
		if(c==1) { return "sales"; }
		else if(c==2) { return "dev"; }
		else if(c==3) { return "accounting"; }
		else {
			return "";
		}
		
	}
	
	//to change password
	public void setPassword(String pass) {
		this.password=pass;
	}
	
	//to set alternate mail id
	public void setAltEmail(String alEmail) {
		this.alemail=alEmail;
	}
	public void setCapacity(int cap) {
		this.capacity=cap;
	}
	
	public int getCapacity() {
		System.out.println("capacity :"+capacity);
		return capacity;
	}
	public String getPassword() {
		System.out.println("password :"+password);
		return password;
		
	}
	public String getAltMail() {
		System.out.println("Altmailid :"+alemail);
		return alemail;
	}
}
