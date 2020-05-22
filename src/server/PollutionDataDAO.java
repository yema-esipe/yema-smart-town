package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


//import common.ConvertJSON;
import common.PollutionData;
//import common.Request;

public class PollutionDataDAO extends DAO<PollutionData> {
	
	//private ConvertJSON converter = new ConvertJSON();
	Logger LOGGER = Logger.getLogger(PollutionDataDAO.class.getName());
	
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

	public HashMap<String , Double> avgdistance(String date_debut, String date_fin,Connection connection) {
		
		HashMap<String , Double> map = new HashMap<String , Double>();
		Double  tempon  = 0.0D;
		
		try {
			Statement myRequest = connection.createStatement();
			
			ResultSet result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'pieton' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("pieton", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'velo' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("velo", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'moto' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("moto", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'voiture' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("voiture", tempon);
			}
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'bus' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("bus", tempon);
			}
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'metro' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("metro", tempon);
			}
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'tram' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("tram", tempon);
			}
			
			result = myRequest.executeQuery("SELECT AVG(distance) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'train' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getDouble(1);
				map.put("train", tempon);
			}
			
			
			result.close();
			myRequest.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "erreur de selection de distance moyenne");
		}
		return map;
	}
		

		
	public HashMap<String , Integer> countnb(String date_debut, String date_fin,Connection connection) {
			
		HashMap<String , Integer> map = new HashMap<String ,  Integer>();
		int tempon = 0;
			
		try {
			
			Statement myRequest = connection.createStatement();
			
			ResultSet result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'pieton' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("pieton", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'velo' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("velo", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'moto' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("moto", tempon);
			} 
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'voiture' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("voiture", tempon);
			}
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'bus' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("bus", tempon);
			}
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'metro' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1); 
				map.put("metro", tempon);
			}
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'tram' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("tram", tempon);
			}
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM pollutiondata, typeoftravel WHERE pollutiondata.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'train' AND pollutiondata.date BETWEEN STR_TO_DATE('"+date_debut+"', '%Y-%m-%d') AND STR_TO_DATE('"+date_fin+"', '%Y-%m-%d') ");
			if(result.next()) {
				tempon = result.getInt(1);
				map.put("train", tempon);
			}
			
			
			result.close();
			myRequest.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "erreur de selection de nombre véhicule");
		}
		
		return map;
	}

	
}
