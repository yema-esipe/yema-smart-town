package client;

import java.io.IOException;
import java.util.Scanner;

import common.Crud;

public class ClientMain {
 
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		Crud crud = new Crud();
		Scanner enter = new Scanner(System.in);
		Scanner enter2 = new Scanner(System.in);
		String reqClient;
		int choice;
		
		System.out.println("********************************************************************************");
		System.out.println("************************* WELCOME TO CLIENT MENU *******************************");
		System.out.println("********************************************************************************");
		
		System.out.println("\n");
		/*
		 Idée de scénario :
		 affiche la table
		 demande ce que le client veut faire
		 il n'ecrit plus directement les requetes elles seront ecrites directement en fonction de ce qu'il veut 
		 */
		System.out.println("1- Select operation");		
		System.out.println("2- Insert operation");
		System.out.println("3- Update operation");
		System.out.println("4- Delete operation");
		
		System.out.println("\n");
		 
		System.out.print("choose an option: ");  		//allows client to chose what he wants to do with the database
		choice = enter.nextInt();
		
		System.out.println("write your request: ");		//he cans write himself the request 
		reqClient = enter2.nextLine();
	
		
		//cette partie doit etre coté serveur
		switch (choice) {
		case 1:
						
			System.out.println("1- Select operation");
			System.out.println(crud.executeSelect(reqClient)); //on doit changer ces lignes en 'envoie' de cette requête dans la socket
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


