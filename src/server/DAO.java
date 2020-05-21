package server;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *  This generic class make the link between the data access layer and the business layer of our application
 *  Each method return the values or the state of the request 
 *  
 */ 

public abstract class DAO<T> {
	
      public DAO() {}
       
      /**
      * insert -> return boolean with the result of the request (problems or not)
      */	
      public abstract boolean insert(String obj, Connection connection);

      /**
      * delete -> return boolean the result of the request (problems or not)
      */
      public abstract boolean delete(String obj, Connection connection);

      /**
      * update -> return boolean with the result of the request (problems or not)
      */
      public abstract boolean update(String obj, Connection connection);

      /**
      * select -> return the selection of the request
      */
      public abstract ArrayList<String> select(Connection connection);
      
      /**
       * selectID -> return the selection of the request specific to the ID
       */
      public abstract ArrayList<String> selectID(String id, Connection connection);

}