package service;

import java.util.Scanner;

public class SignUp_Or_SignIn 
{
	public int value() throws Exception 
	{
		System.out.println("Enter 1 for SignUp\nEnter 2 for SignIn\n");
		Scanner sc = new Scanner(System.in);
		
		byte choice = sc.nextByte();
		
		if(choice == 1) 
		{
			SignUp newuser = new SignUp();
			newuser.user();
		}
			
		SignIn olduser = new SignIn();
		return olduser.user();
	}
}
