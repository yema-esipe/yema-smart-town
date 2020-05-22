package server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.DeviceConfigNbCar;

 
public class DeviceConfigNbCarDAO extends DAO<DeviceConfigNbCar>{
	//private ConvertJSON converter = new ConvertJSON();
			Logger LOGGER = Logger.getLogger(DeviceConfigNbCarDAO.class.getName());
			
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
			

			public HashMap<String , Integer> selectnbcarmax(Connection connection) {
				
				HashMap<String , Integer> map = new HashMap<String ,  Integer>();
				int tempon = 0;
					
				try {
					
					Statement myRequest = connection.createStatement();
					
					ResultSet result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'pieton' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("pieton", tempon);
					} 
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'velo' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("velo", tempon);
					} 
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'moto' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("moto", tempon);
					} 
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'voiture' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("voiture", tempon);
					}
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'bus' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("bus", tempon);
					}
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'metro' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("metro", tempon);
					}
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'tram' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("tram", tempon);
					}
					
					result = myRequest.executeQuery("SELECT deviceconfignbcar.nbcarmax FROM deviceconfignbcar, typeoftravel WHERE deviceconfignbcar.idtypeoftravel = typeoftravel.idtypeoftravel AND typeoftravel.labeltype = 'train' ");
					if(result.next()) {
						tempon = result.getInt(1);
						map.put("train", tempon);
					}
					
					
					result.close();
					myRequest.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
					LOGGER.log(Level.WARNING, "erreur de selection de nombre véhicule max");
				}
				
				return map;
			}

			
}
