package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import common.ConvertJSON;


import common.VehicleSensor;

public class VehicleSensorDAO extends DAO<VehicleSensor> {
	private ConvertJSON converter = new ConvertJSON();
	
	public boolean insert(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		VehicleSensor sensor = converter.JsontoVehicleSensor(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO vehiclesensor(id,address, isActive) VALUES(?,?, ?)");
			preparedStatement.setInt(1, sensor.getId());
			preparedStatement.setString(2, sensor.getAddress());
			preparedStatement.setBoolean(3, sensor.isActive());
			
			preparedStatement.executeUpdate();
			
			return true; 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		VehicleSensor sensor = converter.JsontoVehicleSensor(device);
try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM vehiclesensor WHERE id = ?");
			
			preparedStatement.setInt(1, sensor.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}}

	@Override
	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		VehicleSensor sensor = converter.JsontoVehicleSensor(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE vehiclesensor SET address = ?, isActive = ? WHERE id = ?");
			
			preparedStatement.setString(2, sensor.getAddress());
			preparedStatement.setBoolean(3, sensor.isActive());
			
			preparedStatement.setInt(1, sensor.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}
		}

	@Override
	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM vehiclesensor");
			
			while(result.next()) {
				VehicleSensor sensor = new VehicleSensor();
				
				sensor.setId(result.getInt(1));
				sensor.setAddress(result.getString(2));
				sensor.setActive(result.getBoolean(3));
				
				
				String json = converter.VehicleSensortoJson(sensor);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	        } 
		}

	@Override
	
		public ArrayList<String> selectID(String id, Connection connection) {
			ArrayList<String> list = new ArrayList<String>();
			int idSensor = Integer.valueOf(id);
			try {
				Statement myRequest = connection.createStatement();
				ResultSet result = myRequest.executeQuery("SELECT * FROM vehiclesensor WHERE id = " + idSensor);
				
				while(result.next()) {
					VehicleSensor sensor = new VehicleSensor();
					
					sensor.setId(result.getInt(1));
					sensor.setAddress(result.getNString(2));
					sensor.setActive(result.getBoolean(3));
					
					

					String json = converter.VehicleSensortoJson(sensor);									
					list.add(json);
				}
				
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				return list;
			}
		
	}
}
	
	
