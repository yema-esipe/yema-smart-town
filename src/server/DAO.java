package server;

import common.Response;
import connection.DataSource;

/**
 *  This generic class make the link between the data access layer and the business layer of our application
 *  
 *  Each method return a Response object 
 *  @param request is client request transfers thanks to request object 
 */ 

public abstract class DAO<T> {
	
	protected DataSource source = null;
	protected Response resp = new Response();

      public DAO(){
        @SuppressWarnings("unused")
		DataSource source = new DataSource();
      }
       
      /**
      * insert -> return Response object with the result of the request (problems or not)
      */	
      public abstract Response insert(String request);

      /**
      * delete -> return Response object with the result of the request (problems or not)
      */
      public abstract Response delete(String request);

      /**
      * update -> return Response object with the result of the request (problems or not)
      */
      public abstract Response update(String request);

      /**
      * select -> return Response object with the result of the request (problems or not)
      */
      public abstract Response select(String request);
}