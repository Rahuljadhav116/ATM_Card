package com.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class User{
	private String userID;
	private String userPIN;
	private double accountBalance;
	
	public User (String userID, String userPIN, double accountBalance) {
		this.userID=userID;
		this.userPIN = userPIN;
		this.accountBalance = accountBalance;
		
		
	}
	
	public String getuserID () {
		return userID;
		
	}
	
	public String getuserPIN() {
		return userPIN;
	}
	
	public double getaccountBalance() {
		return accountBalance;
	}
	
	public void setaccountBalance(double newBalance) {
		this.accountBalance = newBalance;
	}
	
}
 class ATMS {
	private Map<String, User> users;
	private User currentUser;
	
	public ATMS () {
		users = new HashMap<>();
		
		users.put("123456", new User("123456", "1234", 1000.0));
		users.put("789012", new User("789012", "5678", 2000.0));
	}
	
	public void start() {
		System.out.println("Welcome to the ATM!");
		authenticateUser();
		DisplayMenu();
		
	}
	
	
	private void authenticateUser() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the UserID ");
		String userID = scanner.next();
		
		System.out.println("Enter the PIN");
		String PIN = scanner.next();
		
		User user = users.get(userID);
		
		if (user != null && user.getuserPIN().equals (PIN)) {
			System.out.println("Authentication Successful !");
			currentUser = user;
		
		} else {
			System.out.println("Invalid Credentials Please Enter Valid Credentials");
			System.exit(0);
		}
		
		
	}

	private void DisplayMenu() {
		Scanner scanner = new Scanner(System.in);
		
		int choice ;
		
		do {
			System.out.println("\n1. check Balance");
			System.out.println("2. Withdraw Money ");
			System.out.println("3. Deposit Money");
			System.out.println("4. Exit");
			System.out.println("Enter your choice: ");
			
			choice= scanner.nextInt();
			switch (choice) {
			case 1:
				checkBalance();
				break;
			case 2:
				withdrawMoney();
				break;			
			case 3:
				depositeMoney();
			case 4:
				System.out.println("Exiting. Thank you! Visit Again ^-^ ");
				break;
			default:
			System.out.println("Invalid choice Please try again.");
			}
		} while (choice != 4);
		
	}

	public void checkBalance() {
	System.out.println("Your account balance is:" + currentUser.getaccountBalance());
		
	}
	
	public void withdrawMoney () {
		
		Scanner scanner= new Scanner(System.in);
		
		System.out.println("Enter the amount to withdraw:");
		double amount = scanner.nextDouble();
		
		if (amount > 0 && amount <= currentUser.getaccountBalance()) {
		Double newBalance =	currentUser.getaccountBalance() - amount ;
		currentUser.setaccountBalance(newBalance);
		System.out.println("Amount Withdraw Successful. Your Balance is: " + newBalance);
		
		}else {
			System.out.println("Invalid amount or Insufficient funds. Please Check the Fund");
		
		}
		
	
	}
	
	public void depositeMoney() {
		Scanner scanner = new Scanner(System.in );
		 System.out.println("Enter the amount to Deposit:");
		 double amount = scanner.nextDouble();
		 
		 if (amount > 0 ) {
			 double newBalance = currentUser.getaccountBalance() + amount;
			 currentUser.setaccountBalance(newBalance);
			 System.out.println("Amount Deposit Sucessfully. Your new Balance is:" + newBalance);
			 
		 }else {
			 System.out.println("Invalid amount. Please try again.");
		 }
		 
		
	}

}

public class ATM {
	public static void main(String[] args) {
		ATMS atm= new ATMS ();		
		atm.start();
	}
}
