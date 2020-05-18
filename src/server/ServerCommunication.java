package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.databind.ObjectMapper;

//import common.ConvertJSON;
//import common.Request;
//import common.Response;
import connection.DataSource;

public class ServerCommunication {
	private ServerSocket serverSocket;
	protected static DataSource source;
	
	public ServerCommunication(){
		
	}
	/**
	 * start establish the connection with clients
	 * The server is pending a new client with a thread
	 */
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		source = new DataSource();
		System.out.println("test2");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {}

		while (true) {
			System.out.println("Serveur à l'écoute");
			ThreadClient t = new ThreadClient(serverSocket.accept(), source);
			t.start();
			
		}
	}

	/**
	 * stop close the server socket 
	 */
	@SuppressWarnings("static-access")
	public void stop() throws IOException {
		source.closeAllConnection();
		serverSocket.close();
	}

	/**
	 * this static class ThreadClient allow to the server to treat several request client
	 * to each client connection, a thread is starting
	 */
	private static class ThreadClient extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;
		private AtomicBoolean running = new AtomicBoolean(false);
		private Connection connection;
		//private Factory factory = new Factory();

		//private ConvertJSON converter = new ConvertJSON();
		//@SuppressWarnings("unused")
		//private Request req = new Request();

		@SuppressWarnings("static-access")
		public ThreadClient(Socket socket, DataSource source) {
			this.clientSocket = socket;
			connection = source.giveConnection();
		} 

		/**
		 * close properly the thread when run is finish
		 */
		public void interrupt() {
			running.set(false);
		}

		/**
		 * run establish the client request treatment 
		 * Here we just have one DAO (user), all the treatments are on the run
		 * After, when we will have several DAO, we will use factory method 
		 * run sends the response to the client, it uses converter object to do the conversion between json-request and json-response
		 */
		@SuppressWarnings({ "static-access", "unchecked" })
		public void run()  {
			running.set(true);
			while (running.get()) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					out = new PrintWriter(clientSocket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String jsonRequest = "";
					

					while ((jsonRequest = in.readLine()) != null) {
						//Request req = converter.JsontoRequest(jsonRequest);
						//Response resp = new Response();
						HashMap<String, String> request = mapper.readValue(jsonRequest, HashMap.class);
						String jsonResponse;
						//System.out.println("Treatment of " + req.getSource() + " for a " + req.getOperation_type() + " request \n request : " + jsonRequest);

						/*if (req.getOperation_type().equals("end")) {
							resp.setResponse_type("BYE " + req.getSource());
							resp.setResponse_state(true);

							jsonResponse = converter.ResponseToJson(resp);
							System.out.println("End of " + req.getSource() + " communication");
							out.println(jsonResponse);
							break;
						}

						if (req.getOperation_type().equals("insert")) {					
							boolean result = factory.getData(req.getTarget()).insert(req.getObj(), connection);
							resp.setResponse_type("insert");
							resp.setResponse_state(result);
							jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);

							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("delete")) {					
							boolean result = factory.getData(req.getTarget()).delete(req.getObj(), connection);
							resp.setResponse_type("delete");
							resp.setResponse_state(result);

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("update")) {					
							boolean result = factory.getData(req.getTarget()).update(req.getObj(), connection);
							resp.setResponse_type("update");
							resp.setResponse_state(result);

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("select")) {					
							ArrayList<String> result = factory.getData(req.getTarget()).select(connection);
							resp.setResponse_type("select");
							resp.setResponse_state(true);
							resp.setValues(result);

							jsonResponse = converter.ResponseToJson(resp);  System.out.println(jsonResponse);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("selectAVG")) {	//Possible de mettre dans le DAO si requete similaire
							DataAirDAO dao = new DataAirDAO();
							ArrayList<String> result = dao.selectAVG(req.getObj(), connection);
							resp.setResponse_type("selectAVG");
							resp.setResponse_state(true);
							resp.setValues(result);

							jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("selectID")) { //Possible de mettre dans le DAO si requete similaire
							AlertDAO dao = new AlertDAO();
							ArrayList<String> result = dao.selectID(req.getObj(), connection); 
							resp.setResponse_type("selectID");
							resp.setResponse_state(true);
							resp.setValues(result);

							jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
							out.println(jsonResponse);
						}
						
						if (req.getOperation_type().equals("selectID")) { //Possible de mettre dans le DAO si requete similaire
							AlertDAO dao = new AlertDAO();
							ArrayList<String> result = dao.selectID(req.getObj(), connection); 
							resp.setResponse_type("selectID");
							resp.setResponse_state(true);
							resp.setValues(result);

							jsonResponse = converter.ResponseToJson(resp); System.out.println(jsonResponse);
							out.println(jsonResponse);
						}*/
						
						/* pour les requetes de calcul EC*/
						
						if (request.get("operation_type").equals("avgdistance")) {
							PollutionDataDAO dao = new PollutionDataDAO(); 
							HashMap<String , Double> result = dao.avgdistance(request.get("date_debut"), request.get("date_fin"), connection);
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "avgdistance");
							response.put("values", result);
							 
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println(jsonResponse);
							out.println(jsonResponse);
							
						}
						
						if (request.get("operation_type").equals("countnb")) {
							PollutionDataDAO dao = new PollutionDataDAO(); 
							HashMap<String , Integer> result = dao.countnb(request.get("date_debut"), request.get("date_fin"), connection);
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "countnb");
							response.put("values", result);
							
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println(jsonResponse);
							out.println(jsonResponse);
						}
						
						if (request.get("operation_type").equals("selectco2")) {
							TypeOfTravelDAO dao = new TypeOfTravelDAO(); 
							HashMap<String , Double> result = dao.selectco2(connection);
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "selectco2");
							response.put("values", result);
							
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println(jsonResponse);
							out.println(jsonResponse);
						}
						
						if (request.get("operation_type").equals("selectnbpassengeravg")) {
							TypeOfTravelDAO dao = new TypeOfTravelDAO(); 
							HashMap<String , Integer> result = dao.selectnbpassengeravg(connection);
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "selectnbpassengeravg");
							response.put("values", result);
							
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println(jsonResponse);
							out.println(jsonResponse);
						}
						
						if (request.get("operation_type").equals("selectnbcarmax")) {
							DeviceConfigNbCarDAO dao = new DeviceConfigNbCarDAO(); 
							HashMap<String , Integer> result = dao.selectnbcarmax(connection);
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "selectnbcarmax");
							response.put("values", result);
							
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println(jsonResponse);
							out.println(jsonResponse);
						}
						
						if ((request.get("operation_type")).equals("end")) {
							
							HashMap<String , Object> response = new HashMap<String , Object>();
							response.put("response_type", "end");
							jsonResponse = mapper.writeValueAsString(response);
							System.out.println("fin de traitement !");
							out.println(jsonResponse);
							break;	
						}
						
						/*Fin calculEC*/

					} 

					source.returnConnection(connection);

					in.close();
					out.close();
					clientSocket.close();

					this.currentThread().interrupt();
					System.out.println("Thread was interrupted");
					System.out.println("\n");

				} catch (IOException e) {}	
			} //end while 
			running.set(true);

		}
	}
}