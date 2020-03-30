package client;

import java.io.IOException;
import java.util.Scanner;

import common.Request;

public class TestMainSocket {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
		
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 50026);
			Request req = new Request();
			
			Scanner enter = new Scanner(System.in);
			Scanner enterFin = new Scanner(System.in);
			
			Scanner enter2_1 = new Scanner(System.in);
			Scanner enter2_2 = new Scanner(System.in);
			Scanner enter2_3 = new Scanner(System.in);
			
			Scanner enter3_1 = new Scanner(System.in);
			Scanner enter3_2 = new Scanner(System.in);
			Scanner enter3_3 = new Scanner(System.in);
			Scanner enter3_4 = new Scanner(System.in);
			
			Scanner enter4_1 = new Scanner(System.in);
			Scanner enter4_2 = new Scanner(System.in);
			
			System.out.println("********************************************************************************");
			System.out.println("************************* WELCOME TO CLIENT MENU *******************************");
			System.out.println("********************************************************************************");
		
			boolean fin = false;
			
			while (!fin) {
				System.out.println("Que souhaitez-vous faire ? ");
				System.out.println("Select -> 1");
				System.out.println("Insert -> 2");
				System.out.println("Update -> 3");
				System.out.println("Delete -> 4");

				String rep1 = enter.nextLine();
			
				if (rep1.equals("1")) {
					System.out.println("Très bien, vous avez choisi SELECT, voici la table Users");
										
					req.setOperation_type("select");
					req.setTarget("users");
					req.setRequest("select * from users");
			
					client.sendMessage(req);
									
				} else if (rep1.equals("2")) {
					System.out.println("Très bien, vous avez choisi INSERT, veuillez nous préciser les informations suivantes :");
					String nom; String prenom; int age;
					
					System.out.println("Nom : ");
					nom = enter2_1.nextLine();
					 
					System.out.println("Prenom : ");
					prenom = enter2_2.nextLine();
					
					System.out.println("Age : ");
					age = enter2_3.nextInt();
						
					req.setOperation_type("insert");
					req.setTarget("users");
					req.setRequest("insert into users (Nom, Prenom, Age) values ('" + nom + "', '" + prenom + "', " + age + ")");
			
					client.sendMessage(req);
					
				} else if (rep1.equals("3")) {
					System.out.println("Très bien, vous avez choisi UPDATE, veuillez entrez les informations suivantes : ");
					
					System.out.print("Colonne à modifier : "); String col = enter3_1.nextLine();
					
					System.out.print("Nouvelle valeur : "); String val = enter3_2.nextLine();
					
					System.out.print("Colonne de référence : "); String colRef = enter3_3.nextLine();
					
					System.out.print("Valeur actuelle : "); String valRef = enter3_4.nextLine();
					 req.setOperation_type("update");
					 req.setTarget("users");
					 req.setRequest("update users set " + col + " = '" + val + "' where " + colRef + " = '" + valRef + "'");
					 
					 client.sendMessage(req);
					 
				} else if (rep1.equals("4")) {
					System.out.println("Très bien, vous avez choisi DELETE, veuillez entrez les informations suivantes : ");
					
					System.out.println("Nom du user à retirer : "); String nomD = enter4_1.nextLine();
					
					System.out.println("Prenom du user à retier : "); String prenomD = enter4_2.nextLine();
					
					req.setOperation_type("delete");
					req.setTarget("users");
					req.setRequest("delete from users where (Nom = '" + nomD + "' AND Prenom = '" + prenomD + "')");
					
					client.sendMessage(req);
				}
				
				System.out.println("Avez-vous fini ? Y/N");
				String r = enterFin.nextLine();
				
				if (r.equals("Y")) {
					fin = true;
					req.setOperation_type("end");
					req.setTarget("");
					req.setRequest("");
					client.sendMessage(req);
					
					client.stopConnection();

				}
			}	
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
