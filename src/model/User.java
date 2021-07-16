package model;

import java.util.Scanner;

import service.IssueDelete;
import service.IssueRaiser;
import service.IssueUpdate;
import service.IssueView;

public class User 
{
	int userId;
	public User(int userId)
	{
		this.userId=userId;
	}
	public void issue() throws Exception
	{
		Scanner scan = new Scanner(System.in);
			
		System.out.println("Enter 1 to raise issue");
		System.out.println("Enter 2 to update previous issues");
		System.out.println("Enter 3 to delete issues");
		System.out.println("Enter 4 to View previous issues");
		
		byte choice = scan.nextByte();
		
		if(choice == 1)
		{
			IssueRaiser raise = new IssueRaiser(userId);
			issue();
		}
		else if(choice == 2)
		{
			IssueView view = new IssueView(userId);
			view.show();
			
			System.out.println("Enter the issue Id:");
			int issueId = scan.nextInt();
			
			IssueUpdate update = new IssueUpdate(issueId);
			update.update();
			
			issue();
		}
		else if(choice == 3)
		{
			IssueView view = new IssueView(userId);
			view.show();
			
			System.out.println("Enter the issue Id:");
			int issueId = scan.nextInt();
			
			IssueDelete delete = new IssueDelete(issueId);
			delete.delete();
			
			issue();
		}
		else
		{
			IssueView view = new IssueView(userId);
			view.show();
		}
		
	}
}
