package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class IssueSolver 
{
	public IssueSolver(int id) throws Exception
	{
		try
		{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter the options to forward the issue:");
		System.out.println("1.Fire Station");
		System.out.println("2.Ambulance Service");
		System.out.println("3.Police Station");
		System.out.println("4.Domestic Works");
		System.out.println("5.Others");
		
		byte code = scan.nextByte();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "root");
		
		
		Statement st = con.createStatement();
		st.execute("update issue set status='solved' where issueid='"+id+"'");
		
		}
		finally
		{
			System.out.println("Issue is forwarded...");
		}
	}
}
