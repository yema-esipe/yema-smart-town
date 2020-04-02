package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.Response;
import common.User;

/**
 * DAOUser extends DAO class
 * Each method get connection thanks to the connection pool and return that before to return the request result
 */
public class DAOUser extends DAO<User> {
	public DAOUser() {
		super();
	}
	
	 @SuppressWarnings("static-access")
	public Response insert(String request) {
		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			
			myRequest.executeUpdate(request);
			
			source.returnConnection(connection);
			source.closeAllConnection();
			
			resp.setResponse_type("INSERT");
			resp.setResponse_state("The operation is completed successfully");
			
			return resp;
			
		} catch (Exception e) {
			resp.setResponse_type("INSERT");
			resp.setResponse_state("ERROR - the operation isn't completed successfully");
			return resp;
		}
		 
	 }
     
	 
     @SuppressWarnings("static-access")
	public Response delete(String request) {
    	 try {
 			Connection connection = source.giveConnection();
 			Statement myRequest = connection.createStatement();
 			
 			myRequest.executeUpdate(request); //request
 			
 			source.returnConnection(connection);
 			source.closeAllConnection();
 		
 			resp.setResponse_type("DELETE");
			resp.setResponse_state("The operation is completed successfully");
			
 			return resp;
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			resp.setResponse_type("DELETE");
			resp.setResponse_state("ERROR - the operation isn't completed successfully");
 			return resp;
 		}
 		 
     }

  
     @SuppressWarnings("static-access")
	public Response update(String request) {
    	 try {
 			Connection connection = source.giveConnection();
 			Statement myRequest = connection.createStatement();
 			
 			myRequest.executeUpdate(request); //request
 			
 			source.returnConnection(connection);
 			source.closeAllConnection();
 			
 			resp.setResponse_type("UPDATE");
			resp.setResponse_state("The operation is completed successfully");
			
 			return resp;
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			resp.setResponse_type("UPDATE");
			resp.setResponse_state("ERROR - the operation isn't completed successfully");
 			return resp;
 		}
 		 
     }
    
   
     @SuppressWarnings("static-access")
	public Response select(String request) {
    	 try {
  			Connection connection = source.giveConnection();
  			Statement myRequest = connection.createStatement();
  			
  			ResultSet result = myRequest.executeQuery(request);
  			ArrayList<String> list = new ArrayList<String>();
  			
  			while (result.next()) {
  				String res = "id : " + result.getInt(1) + "; Nom : " + result.getString(2) + "; Prenom : " + result.getString(3) + "; Age : " + result.getInt(4) + '\n';
  				list.add(res);
  			}
  					
  			source.returnConnection(connection);
  			source.closeAllConnection();
  		
  			resp.setResponse_type("SELECT");
			resp.setResponse_state("The operation is completed successfully");
			resp.setValues(list); 
			
  			return resp;
  			
  		} catch (Exception e) {
  			e.printStackTrace();
  			resp.setResponse_type("SELECT");
			resp.setResponse_state("ERROR - the operation isn't completed successfully");
  			return resp;
  		}
  		 
     }
}
