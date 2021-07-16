package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IssueProcessor 
{
	int id;
	public IssueProcessor(int id) 
	{
		this.id=id;
	}
	public void process() throws Exception
	{
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
			
				PreparedStatement ps = con.prepareStatement("select issue,issuelocation,status from issue where issueid=?");
				ps.setInt(1, id);
				
				System.out.println();
				System.out.println("IssueId : "+ id);
				System.out.println();
			
				ResultSet rs = ps.executeQuery();
			
				System.out.println("----------------------------ISSUES----------------------------");
				System.out.println("           Issue           |     IssueLocation     |  Status  ");
				System.out.println("--------------------------------------------------------------");
			
				while(rs.next())
				{
					System.out.println(rs.getString("issue") + "  |  "+ rs.getString("issuelocation") + "  |  " + rs.getString("status"));
				}
			
				rs.close();
				
				Statement qs = con.createStatement();
				qs.execute("update issue set status='processing' where issueid='"+id+"'");
				
	
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
		finally
		{
			IssueSolver solve = new IssueSolver(id);	
		}
	}
	

}
