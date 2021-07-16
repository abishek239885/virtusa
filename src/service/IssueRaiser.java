package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class IssueRaiser 
{
	public IssueRaiser(int val) throws Exception
	{
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Enter your issue:");
	String issue = scan.nextLine();
	
	System.out.println("Enter the issue location:");
	String issueLocation = scan.nextLine();
	
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
	
			PreparedStatement ps = con.prepareStatement("insert into issue values(null,?,?,?,?)");
			
			ps.setInt(1, val);
			ps.setString(2, issue);
			ps.setString(3, issueLocation);
			ps.setString(4, "new");
	
			int rs = ps.executeUpdate();
			
			if(rs>0) 
			{
				System.out.println("Your Issue will be Processed and Solved quickly...");
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
