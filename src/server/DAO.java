package server;

import java.sql.Connection;
import java.util.ArrayList;


/**
 *  This generic class make the link between the data access layer and the business layer of our application
 *  
 *  Each method return a Response object 
 *  @param request is client request transfers thanks to request object 
 */ 

public abstract class DAO<T> {
	
      public DAO() {}
       
      /**
       * insert -> return Response object with the result of the request (problems or not)
       */	
       public abstract boolean insert(T obj, Connection connection);

       /**
       * delete -> return Response object with the result of the request (problems or not)
       */
       public abstract boolean delete(T obj, Connection connection);

       /**
       * update -> return Response object with the result of the request (problems or not)
       */
       public abstract boolean update(T obj, Connection connection);

       /**
       * select -> return Response object with the result of the request (problems or not)
       */
       public abstract ArrayList<T> select(Connection connection);
       
}