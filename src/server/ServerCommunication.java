package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

import common.ConvertJSON;
import common.Request;
import common.Response;
/**
 * ServerCommunication implements server socket connection with clients
 * it can have multiple client connection thanks to multi-threading 
 * */
public class ServerCommunication {
	private ServerSocket serverSocket;
	
/**
 * start establish the connection with clients
 * The server is pending a new client with a thread
 */
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {}

		while (true) {
			System.out.println("Serveur à l'écoute");
			new ThreadClient(serverSocket.accept()).start();
		}
	}

/**
 * stop close the server socket 
 */
	public void stop() throws IOException {
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
		private Factory factory = new Factory();
		private ConvertJSON converter;
		@SuppressWarnings("unused")
		private Request req;

		public ThreadClient(Socket socket) {
			this.clientSocket = socket;
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
		@SuppressWarnings("static-access")
		public void run()  {
			running.set(true);
			while (running.get()) {
			try {	
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				converter = new ConvertJSON();
				req = new Request();
				String jsonRequest;

				while ((jsonRequest = in.readLine()) != null) {
					Request req = converter.JsontoRequest(jsonRequest);
					Response resp;
					String jsonResponse;
					System.out.println("Treatment of " + req.getClient() + " for a " + req.getOperation_type() + " request");
					
					if (req.getOperation_type().equals("end")) {
						resp = new Response();
						resp.setResponse_type("end");
						resp.setResponse_state("Bye " + req.getClient());
						
						jsonResponse = converter.ResponseToJson(resp);
						System.out.println("End of " + req.getClient() + " communication");
						out.println(jsonResponse);
						break;
					}
					
					
						if (req.getOperation_type().equals("insert")) {					
							resp = factory.getData(req.getTarget()).insert(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("delete")) {
							DAOUser u = new DAOUser();
							resp = u.delete(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("update")) {
							DAOUser u = new DAOUser();
							resp = u.update(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("select")) {
							DAOUser u = new DAOUser();
							resp = u.select(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}
					
					
					
					
					/*
					if (req.getTarget().equals("users")) {
						if (req.getOperation_type().equals("insert")) {
							DAOUser u = new DAOUser();
							resp = u.insert(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("delete")) {
							DAOUser u = new DAOUser();
							resp = u.delete(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("update")) {
							DAOUser u = new DAOUser();
							resp = u.update(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}

						if (req.getOperation_type().equals("select")) {
							DAOUser u = new DAOUser();
							resp = u.select(req.getRequest());

							jsonResponse = converter.ResponseToJson(resp);
							out.println(jsonResponse);
						}
					}
					*/
				}

				in.close();
				out.close();
				clientSocket.close();
				
				this.currentThread().interrupt();
				System.out.println("Thread was interrupted");
				
			} catch (IOException e) {}	
			} //end while 
			running.set(true);
			
		}
	}
}
