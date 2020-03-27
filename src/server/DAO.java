package server;

import common.Response;
import connection.DataSource;

/* Cette classe va  faire le lien entre la couche d'acc�s aux donn�es 
 * et la couche m�tier de notre application
 * */ 

public abstract class DAO<T> {
	
	protected DataSource source = null;
	protected Response resp = new Response();

      public DAO(){
        @SuppressWarnings("unused")
		DataSource source = new DataSource();
      }
       
      /**
      * M�thode de cr�ation
      * @param obj
      * @return boolean 
      */
      public abstract Response insert(String request);
     
 

      /**
      * M�thode pour effacer
      * @param obj
      * @return boolean 
      */
      public abstract Response delete(String request);

 

      /**
      * M�thode de mise � jour
      * @param obj
      * @return boolean
      */
      public abstract Response update(String request);

 
      /**
      * M�thode de recherche de toutes les informations
      * @return T
     */
      public abstract Response select(String request);
}