package server;

import java.io.*;
import java.net.*;

import common.ConvertJSON;
import common.Request;
import common.Response;

public class ServerCommunication {
	private ServerSocket serverSocket;

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {}

		while (true) {
			System.out.println("Serveur à l'écoute");
			new ThreadClient(serverSocket.accept()).start();
		}
	}

	public void stop() throws IOException {
		serverSocket.close();
	}

	private static class ThreadClient extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		private ConvertJSON converter;
		private Request req;

		public ThreadClient(Socket socket) {
			this.clientSocket = socket;
		} 

		public void run()  {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				converter = new ConvertJSON();
				req = new Request();
				String jsonRequest;

				while ((jsonRequest = in.readLine()) != null) { //est différent de 'fin'
					Request req = converter.JsontoRequest(jsonRequest);
					Response resp;
					String jsonResponse;

					if (req.getOperation_type().equals("end")) {
						resp = new Response();
						resp.setResponse_type("end");
						resp.setResponse_state("La communication est terminée");
						
						jsonResponse = converter.ResponseToJson(resp);
						out.println(jsonResponse);
						break;
					}
					
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
				}

				in.close();
				out.close();
				clientSocket.close();

			} catch (IOException e) {}
		}
	}
}
