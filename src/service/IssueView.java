package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueView 
{
	int val;

	public IssueView(int val) 
	{
		this.val = val;
	}

	public void show() throws Exception
	{
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
				PreparedStatement ps = con.prepareStatement("select * from issue where userid=?");
				ps.setInt(1, val);
				
				System.out.println();
				System.out.println("UserId : "+val);
				System.out.println();
			
				ResultSet rs = ps.executeQuery();
			
				System.out.println("----------------------------ISSUES----------------------------");
				System.out.println(" IssueId |         Issue         |  IssueLocation  |  Status  ");
				System.out.println("--------------------------------------------------------------");
			
				while(rs.next())
				{
					System.out.println(rs.getInt("issueid") + " | "  + rs.getString("issue") + " | "+ rs.getString("issuelocation") + " | " + rs.getString("status"));
				}
			
				rs.close();
			
	
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
}