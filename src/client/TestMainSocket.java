package client;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.Request;
import connection.PropertiesFileReader;

public class TestMainSocket {
	 
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Logger LOGGER = Logger.getLogger(TestMainSocket.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		try {
			
			System.out.println("\n");
			LOGGER.log(Level.INFO, "*** Client Program Beginning ***");
			System.out.println("\n");
			
			ClientCommunication client = new ClientCommunication();
			client.startConnection(SERVER_ADDRESS, SERVER_PORT);
			Request req = new Request();
			
			Scanner enter = new Scanner(System.in);
			Scanner enterName = new Scanner(System.in);
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
			System.out.print("Please, enter your name -> "); String user = enterName.nextLine();

			while (!fin) {
				System.out.println(user + ". What do you want to do ? ");
				
				System.out.println("Select -> 1");
				System.out.println("Insert -> 2");
				System.out.println("Update -> 3");
				System.out.println("Delete -> 4");

				String rep1 = enter.nextLine();
			
				if (rep1.equals("1")) {
					System.out.println("Fine, you chose SELECT, this is Users query");
										
					req.setOperation_type("select");
					req.setTarget("users");
					
			
					client.sendMessage(req);
									
				} else if (rep1.equals("2")) {
					System.out.println("Fine, you chose INSERT, please give us the following information :");
					String nom; String prenom; int age;
					
					System.out.println("Last name : ");
					nom = enter2_1.nextLine();
					 
					System.out.println("First name : ");
					prenom = enter2_2.nextLine();
					
					System.out.println("Age : ");
					age = enter2_3.nextInt();
						
					req.setOperation_type("insert");
					req.setTarget("users");
					

					client.sendMessage(req);
					
				} else if (rep1.equals("3")) {
					System.out.println("Fine, you chose UPDATE, please give us the following information : ");
					
					System.out.print("Field to change : "); String col = enter3_1.nextLine();
					
					System.out.print("New value : "); String val = enter3_2.nextLine();
					
					System.out.print("Reference field : "); String colRef = enter3_3.nextLine();
					
					System.out.print("Current value : "); String valRef = enter3_4.nextLine();
					 req.setOperation_type("update");
					 req.setTarget("users");
					

					 client.sendMessage(req);
					 
				} else if (rep1.equals("4")) {
					System.out.println("Fine, you chose DELETE, please give us the following information : ");
					
					System.out.print("Last name user to remove : "); String nomD = enter4_1.nextLine();
					
					System.out.print("First name user to remove : "); String prenomD = enter4_2.nextLine();
					
					req.setOperation_type("delete");
					req.setTarget("users");
					

					client.sendMessage(req);
				}
				
				System.out.println("Are you finished ? Y/N");
				String r = enterFin.nextLine();
				
				if (r.equals("Y")) {
					fin = true;
					req.setOperation_type("end");
					req.setTarget("");
					

					client.sendMessage(req);
					
					client.stopConnection();

				}
			}	
						
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("\n\n");
		LOGGER.log(Level.INFO, "*** End client program ***");
	}
}
