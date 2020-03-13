package client;

import java.util.Scanner;

import common.Crud;

public class ClientMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Crud crud = new Crud();
		
		Scanner enter = new Scanner(System.in);
		Scanner enter2 = new Scanner(System.in);
		String reqClient;
		int choice;
		
		System.out.println("********************************************************************************");
		System.out.println("************************* WELCOME TO CRUD CLIENT MENU **************************");
		System.out.println("********************************************************************************");
		
		System.out.println("\n");
		
		System.out.println("1- Select operation");
		System.out.println("2- Insert operation");
		System.out.println("3- Update operation");
		System.out.println("4- Delete operation");
		
		System.out.println("\n");
		
		System.out.print("choose an option: ");
		choice = enter.nextInt();
		
		System.out.println("write your request: ");
		reqClient = enter2.nextLine();
	
		switch (choice) {
		case 1:
						
			System.out.println("1- Select operation");
			System.out.println(crud.executeQuery(reqClient));
			break;
		case 2:
						
			System.out.println("2- Insert operation");
			crud.executeUpdate(reqClient);
			break;
		case 3:
			
			System.out.println("3- Update operation");
			crud.executeUpdate(reqClient);
			break;
		case 4:
			
			System.out.println("4- Delete operation");
			crud.executeUpdate(reqClient);
			break;

		default:
			break;
		}
		
	}
}


