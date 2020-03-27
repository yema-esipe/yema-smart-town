package client;

import java.io.IOException;
import java.util.Scanner;

public class TestMainSocket {
	public static void main(String[] args) {
		
		try {
		
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 2330);
			@SuppressWarnings("resource")
			Scanner enter = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter2 = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter3 = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter4 = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter5 = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter6 = new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner enter7 = new Scanner(System.in);
						
			
			System.out.println("********************************************************************************");
			System.out.println("************************* WELCOME TO CLIENT MENU *******************************");
			System.out.println("********************************************************************************");
			System.out.println("Voici la table USERS");

			client.sendMessage("select Nom, Prenom, age from users"); //affichage de la table entière
			boolean fin = false;
			
			while (!fin) {
				System.out.println("Que souhaitez-vous faire ? Lecture/Modification");
				String reponse;
				reponse = enter.nextLine();
			
				if (reponse.equals("Lecture")) {
					System.out.println("Très bien, veuillez préciser quelle colonne vous intéresse ?");
					String rep;
					rep = enter2.nextLine();
					System.out.println("Voici votre requête : ");
					client.sendMessage("select " + rep + " from users");
				
					System.out.println("Avez-vous fini ? Y/N");
					String r = enter7.nextLine();

					if (r.equals("Y")) {
						fin = true;
						client.sendMessage("fin ");
						client.stopConnection();

					}
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
						client.sendMessage("insert into users (Nom, Prenom, Age) values ('" + rep3 + "', '" + rep4 + "', " + rep5 + ")");
				
					}
					
					System.out.println("Avez-vous fini ? Y/N");
					String r = enter7.nextLine();
					
					if (r.equals("Y")) {
						fin = true;
						client.sendMessage("fin ");
						client.stopConnection();

					}
				} else {
					client.sendMessage("fin ");
					client.stopConnection();
					break;
				}					
			}	
							
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
