package controller;

import model.Admin;
import model.User;
import service.SignUp_Or_SignIn;

public class MainControl 
{
	public static void main(String[] args) throws Exception 
	{
		SignUp_Or_SignIn sign = new SignUp_Or_SignIn();
		
		int val = sign.value();
		
		if(val == 0)
		{
			Admin admin = new Admin();
			admin.view();
		}
		else
		{
			User user = new User(val);
			user.issue();
		}
		
		System.out.println("Thanks For using Issue Management...!!!");
		
	}

}
