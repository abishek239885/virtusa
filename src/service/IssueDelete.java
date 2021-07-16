package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IssueDelete 
{
	int issueId;
	public IssueDelete(int issueId) 
	{
		this.issueId = issueId;
	}

	public void delete() throws Exception
	{
		try 
		{
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
				Statement st = con.createStatement();
				st.executeUpdate("delete from issue where issueid="+issueId+"");
			}
			else 
			{
				System.out.println("Processsing or Solved issue cannot be deleted");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
