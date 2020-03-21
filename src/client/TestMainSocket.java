package client;

import java.io.IOException;
import java.util.Scanner;

import common.Crud;

public class TestMainSocket {
	public static void main(String[] args) {
		
		try {
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 2595);
			Crud crud = new Crud();
			Scanner enter = new Scanner(System.in);
			Scanner enter2 = new Scanner(System.in);
			Scanner enter3 = new Scanner(System.in);
			Scanner enter4 = new Scanner(System.in);
			Scanner enter5 = new Scanner(System.in);
			Scanner enter6 = new Scanner(System.in);


			//test dialogue
			
			System.out.println("********************************************************************************");
			System.out.println("************************* WELCOME TO CLIENT MENU *******************************");
			System.out.println("********************************************************************************");
			
			client.sendMessage("select Nom, Prenom, age from users"); //affichage de la table entière
			
			System.out.println("Voici la table USERS");
			System.out.println("Que souhaitez-vous faire ? Lecture/Modification");
			String reponse;
			reponse = enter.nextLine();
			
			if (reponse.equals("Lecture")) {
				System.out.println("Très bien, veuillez préciser quelle colonne vous intéresse ?");
				String rep;
				rep = enter2.nextLine();
				System.out.println("Voici votre requête : ");
				client.sendMessage("select " + rep + " from users");
				
			} else if (reponse.equals("Modification")) {
				System.out.println("Très bien, quelle opération voulez-vous ? Update/Insert/Delete");
				String rep2;
				rep2 = enter3.nextLine();
				if (rep2.equals("Insert")) {
					System.out.println("Veuillez entrez les informations suivantes : ");
					String rep3; String rep4; int rep5;
					
					System.out.println("Nom : ");
					rep3 = enter4.nextLine();
					
					System.out.println("Prenom : ");
					rep4 = enter5.nextLine();
					
					System.out.println("Age : ");
					rep5 = enter6.nextInt();
					client.sendMessage("insert into users(Nom, Prenom, Age) values (" + rep3 + ", " + rep4 + ", " + rep5 + ")");
				
				}
				
			}

			
			client.sendMessage(".");
			client.stopConnection();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
