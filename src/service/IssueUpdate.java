package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IssueUpdate 
{
	int issueId;
	
	public IssueUpdate(int issueId) 
	{
		this.issueId=issueId;
	}

	public void update() throws Exception
	{
		try 
		{
			Scanner scan = new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
			PreparedStatement ps = con.prepareStatement("select * from issue where issueid=?");
			ps.setInt(1, issueId);
			
			ResultSet rs = ps.executeQuery();
			String s = null;
			
			while(rs.next()) 
			{
				s = rs.getString("status");
			}
			
			if(s.equals("new"))
			{
				System.out.println("Enter your issue:");
				String issue = scan.nextLine();
				
				System.out.println("Enter the issue location:");
				String issueLocation = scan.nextLine();
				
				Statement st = con.createStatement();
				st.executeUpdate("update issue set issue='"+issue+"',issuelocation='"+issueLocation+"' where issueid="+issueId);
				
			}
			else 
			{
				System.out.println("Processsing or Solved issue cannot be updated");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
