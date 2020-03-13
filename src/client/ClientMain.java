package client;

import java.util.Scanner;

import common.Crud;

public class ClientMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//JDBCConnectionPool jdbc = new JDBCConnectionPool(); not necessary
		
		Crud crud = new Crud();
		
		Scanner enter = new Scanner(System.in);
		String reqClient;
		int choice;
		
		System.out.println("*******************************************************************************************");
		System.out.println("****************************** BIENVENUE AU MENU CRUD CLIENT ******************************");
		System.out.println("*******************************************************************************************");
		
		System.out.println("\n");
		
		System.out.println("1- Select operation");
		System.out.println("2- Insert operation");
		System.out.println("3- Update operation");
		System.out.println("4- Delete operation");
		
		System.out.println("\n");
		
		System.out.print("choose an option: ");
		choice = enter.nextInt();
		
		System.out.println("writte your request: ");
		reqClient = enter.nextLine();
		
		switch (choice) {
		case 1:
			//select test 
			System.out.println("1- Select operation");
			System.out.println(crud.executeQuery(reqClient));
			//System.out.println(crud.executeQuery("Select prenom from users"));
			break;
		case 2:
			System.out.println("2- Insert operation");
			System.out.println(crud.executeUpdate(reqClient));
			break;
		case 3:
			System.out.println("3- Update operation");
			System.out.println(crud.executeUpdate(reqClient));
			break;
		case 4:
			System.out.println("4- Delete operation");
			System.out.println(crud.executeUpdate(reqClient));
			break;

		default:
			break;
		}
	
		
		//update test 
		//crud.executeUpdate("Update users set nom = 'KOONE' Where prenom = 'Yaya'");		
		
		//insert test 
		//crud.executeUpdate("Insert into users(nom, prenom, mdp, age) values ('De Lima', 'Mathieu', 'mama', '20')");
		
		//delete test
		//crud.executeUpdate("Delete from users where nom = 'De Lima'");
		
		
	}
}


