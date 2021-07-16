package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SignIn 
{
	int id;
	public int user() throws Exception 
	{
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Useremail:");
		String userEmail = in.nextLine();
		
		System.out.println("Enter Password:");
		String userPass = in.nextLine();
		
		
		
		if(userEmail.equalsIgnoreCase("admin@issuemanagement.com") && userPass.equals("1234"))
		{
			System.out.println("Welcome Admin...!!!");
			return 0;
			
		}
		else if(checkUser(userEmail,userPass)) 
		{
			System.out.println("Login Successfully...");
			return userId(userEmail);
		}
		else 
		{
			System.out.println("Enter the email and password correctly...");
			return user();
		}
		
	}
	
	private int userId(String userEmail) throws Exception
	{

		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
			PreparedStatement ps = con.prepareStatement("select userid from registeruser where email=?");
			
			ps.setString(1, userEmail);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				this.id = rs.getInt("userid");
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			return this.id;
		}
		
	}

	private boolean checkUser(String userEmail, String userPass) throws Exception 
	{
		boolean st = false;
		try 
		{

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
			PreparedStatement ps = con.prepareStatement("select * from registeruser where email=? and pass=?");
			
			ps.setString(1, userEmail);
			ps.setString(2, userPass);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				st = true;
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return st;
		
	}
	
}
