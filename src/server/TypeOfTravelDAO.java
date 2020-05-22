package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


import common.TypeOfTravel;

public class TypeOfTravelDAO extends DAO<TypeOfTravel>{
	//private ConvertJSON converter = new ConvertJSON();
		Logger LOGGER = Logger.getLogger(TypeOfTravelDAO.class.getName());
		
		public boolean insert(String data, Connection connection) {
			return false;	
		}

		public boolean delete(String data, Connection connection) {
			return false;
		} 

		public boolean update(String obj, Connection connection) {
			return false;
		}

		public ArrayList<String> select(Connection connection) {
			ArrayList<String> list = new ArrayList<String>();
			return list;
		}
		
		public ArrayList<String> selectID(String id, Connection connection) {
			ArrayList<String> list = new ArrayList<String>();
			return list;
		}
		
		public HashMap<String , Double> selectco2 (Connection connection) {
			
			HashMap<String , Double> map = new HashMap<String , Double>();
			Double  tempon  = 0.0D;
			
			try {
				Statement myRequest = connection.createStatement();
				
				ResultSet result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'pieton'");
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("pieton", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'velo'");				
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("velo", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'moto'");
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("moto", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'voiture'");
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("voiture", tempon);
				}
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'bus'");				
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("bus", tempon);
				}
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'metro'");				
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("metro", tempon);
				}
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'tram'");				
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("tram", tempon);
				}
				
				result = myRequest.executeQuery("SELECT co2 FROM typeoftravel WHERE  typeoftravel.labeltype = 'train'");				
				if(result.next()) {
					tempon = result.getDouble(1);
					map.put("train", tempon);
				}
				
				
				result.close();
				myRequest.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.log(Level.WARNING, "erreur de selection de distance co2");
			}
			return map;
		}
			

			
		public HashMap<String , Integer> selectnbpassengeravg(Connection connection) {
				
			HashMap<String , Integer> map = new HashMap<String ,  Integer>();
			int tempon = 0;
				
			try {
				
				Statement myRequest = connection.createStatement();
				
				ResultSet result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'pieton'");
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("pieton", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'velo'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("velo", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'moto'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("moto", tempon);
				} 
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'voiture'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("voiture", tempon);
				}
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'bus'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("bus", tempon);
				}
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'metro'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("metro", tempon);
				}
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'tram'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("tram", tempon);
				}
				
				result = myRequest.executeQuery("SELECT nbpassengeravg FROM typeoftravel WHERE  typeoftravel.labeltype = 'train'");				
				if(result.next()) {
					tempon = result.getInt(1);
					map.put("train", tempon);
				}
				
				
				result.close();
				myRequest.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.log(Level.WARNING, "erreur de selection de nombre de passenger moyen");
			}
			
			return map;
		}



}
