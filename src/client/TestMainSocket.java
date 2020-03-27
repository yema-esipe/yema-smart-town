package client;

import java.io.IOException;
import java.util.Scanner;

import common.Request;

public class TestMainSocket {
	public static void main(String[] args) {
		
		try {
		
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 50014);
			Request req = new Request();
			
			
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
			
			//System.out.println("Voici la table USERS");
			/*
			req.setOperation_type("update");
			req.setTarget("users");
			req.setRequest("update users set Nom = 'KONE' where Prenom = 'Yaya'");
			
			client.sendMessage(req);
			
			req.setOperation_type("end");
			req.setTarget("");
			req.setRequest("");
			client.sendMessage(req);
			
			client.stopConnection();
			*/
			boolean fin = false;
			
			
			while (!fin) {
				System.out.println("Que souhaitez-vous faire ? Lecture/Modification");
				String rep1;
				rep1 = enter.nextLine();
			
				if (rep1.equals("Lecture")) {
					System.out.println("Très bien, veuillez préciser quelle colonne vous intéresse ?");
					
					rep1 = enter2.nextLine();
					System.out.println("Voici votre requête : ");
					
					req.setOperation_type("select");
					req.setTarget("users");
					req.setRequest("select * from users");
			
					client.sendMessage(req);
									
					System.out.println("Avez-vous fini ? Y/N");
					String r = enter7.nextLine();

					if (r.equals("Y")) {
						fin = true;
						
						req.setOperation_type("end");
						req.setTarget("");
						req.setRequest("");
						client.sendMessage(req);
						
						client.stopConnection();

					}
				} else if (rep1.equals("Modification")) {
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
						
						req.setOperation_type("insert");
						req.setTarget("users");
						req.setRequest("insert into users (Nom, Prenom, Age) values ('" + rep3 + "', '" + rep4 + "', " + rep5 + ")");
			
						client.sendMessage(req);
										
					}
					
					System.out.println("Avez-vous fini ? Y/N");
					String r = enter7.nextLine();
					
					if (r.equals("Y")) {
						fin = true;
						req.setOperation_type("end");
						req.setTarget("");
						req.setRequest("");
						client.sendMessage(req);
						
						client.stopConnection();;

					}
				} else {
					req.setOperation_type("end");
					req.setTarget("");
					req.setRequest("");
					client.sendMessage(req);
					
					client.stopConnection();
					break;
				}					
			}	
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
