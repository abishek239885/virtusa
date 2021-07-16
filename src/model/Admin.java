package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import service.IssueProcessor;

public class Admin 
{
	byte choice = 1;
	public void view() throws Exception
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
			Statement ps = con.createStatement();
			
			ResultSet rs = ps.executeQuery("select * from issue where status='new'");
			
			System.out.println("-------------------------NEW  ISSUES-------------------------");
			System.out.println(" IssueId | UserId |      Issue      | IssueLocation | Status ");
			System.out.println("-------------------------------------------------------------");
			
			while (rs.next()) 
			{
				System.out.println(rs.getInt("issueid")+" | "+rs.getInt("userid")+" | "+rs.getString("issue")+" | "+rs.getString("issuelocation")+" | "+rs.getString("status"));
			}
			
			
			rs.close();
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			Scanner scan = new Scanner(System.in);
				
			System.out.println("Enter 1 to continue\nEnter 2 to exit");
			choice = scan.nextByte();
			
			if(choice==1)
			{
				System.out.println("Enter the issueid to process:");
				IssueProcessor proc = new IssueProcessor(scan.nextInt());
				
				proc.process();	
				view();
			}
		}
	}
}