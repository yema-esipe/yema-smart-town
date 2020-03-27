package server;

import common.Response;
import connection.DataSource;

/* Cette classe va  faire le lien entre la couche d'accès aux données 
 * et la couche métier de notre application
 * */ 

public abstract class DAO<T> {
	
	protected DataSource source = null;
	protected Response resp = new Response();

      public DAO(){
        @SuppressWarnings("unused")
		DataSource source = new DataSource();
      }
       
      /**
      * Méthode de création
      * @param obj
      * @return boolean 
      */
      public abstract Response insert(String request);
     
 

      /**
      * Méthode pour effacer
      * @param obj
      * @return boolean 
      */
      public abstract Response delete(String request);

 

      /**
      * Méthode de mise à jour
      * @param obj
      * @return boolean
      */
      public abstract Response update(String request);

 
      /**
      * Méthode de recherche de toutes les informations
      * @return T
     */
      public abstract Response select(String request);
}