package service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class SignUp 
{
	public void user() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String userName = in.nextLine();
		
		System.out.println("Enter Email:");
		String userEmail = in.nextLine();
		
		System.out.println("Enter Password:");
		String userPass = in.nextLine();
		
		
		if(checkUser(userEmail) ) 
		{
			System.out.println("Useremail already exists...");
			System.out.println("Enter another email..");
			user();
		}
		else 
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");

				PreparedStatement ps = con.prepareStatement("insert into registeruser values(null,?,?,?)");

				ps.setString(1, userName);
				ps.setString(2, userEmail);
				ps.setString(3, userPass);

				int rs = ps.executeUpdate();
				
				if(rs>0)
				{
					System.out.println("You are successfully registered...");
				}

			} 
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		
	}

	private boolean checkUser(String userEmail) throws Exception
	{
		boolean st = false;
		try 
		{

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
			PreparedStatement ps = con.prepareStatement("select * from registeruser where email=?");
			ps.setString(1, userEmail);
			
			
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
