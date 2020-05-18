package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.DeviceConfigAir;

public class DeviceConfigAirDAO extends DAO<DeviceConfigAir> {
	private ConvertJSON converter = new ConvertJSON();

	public boolean insert(String id, Connection connection) {
		PreparedStatement preparedStatement = null;
		int idDevice = Integer.valueOf(id);
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO deviceconfigair(idDeviceAir, co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone) VALUES( ?, 0, 0, 0, 0, 0, 0)");
			
			preparedStatement.setInt(1, idDevice);
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean delete(String id, Connection connection) {
		PreparedStatement preparedStatement = null;
		int idDevice = Integer.valueOf(id);
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM deviceconfigair WHERE idDeviceAir = ?");
			
			preparedStatement.setInt(1, idDevice);
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean update(String configAir, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceConfigAir config = converter.JsontoConfig(configAir);

		try {
			preparedStatement = connection.prepareStatement("UPDATE deviceconfigair SET co2 = ?, carbonMonoxide = ?, finesParticules = ?, sulfurDioxide = ?, nitrogenDioxide = ?, ozone = ? WHERE id = ?");
			
			preparedStatement.setFloat(1, config.getCo2());
			preparedStatement.setFloat(2, config.getCarbonMonoxide());
			preparedStatement.setFloat(3, config.getFinesParticules());
			preparedStatement.setFloat(4, config.getSulfurDioxide());
			preparedStatement.setFloat(5, config.getNitrogenDioxide());
			preparedStatement.setFloat(6, config.getOzone());
			preparedStatement.setInt(7, config.getId());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM deviceconfigair");
			
			while(result.next()) {
				DeviceConfigAir config = new DeviceConfigAir();
				
				config.setId(result.getInt(1));
				config.setIdDevice(result.getInt(2));
				config.setCo2(result.getFloat(3));
				config.setCarbonMonoxide(result.getFloat(4));
				config.setFinesParticules(result.getFloat(5));
				config.setSulfurDioxide(result.getFloat(6));
				config.setNitrogenDioxide(result.getFloat(7));
				config.setOzone(result.getFloat(8));
				
				String json = converter.ConfigToJson(config);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
	
	public ArrayList<String> selectID(String id, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		int idDevice = Integer.valueOf(id);
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM deviceconfigair WHERE idDeviceAir = " + idDevice);
			
			while(result.next()) {
				DeviceConfigAir config = new DeviceConfigAir();
				
				config.setId(result.getInt(1));
				config.setIdDevice(result.getInt(2));
				config.setCo2(result.getFloat(3));
				config.setCarbonMonoxide(result.getFloat(4));
				config.setFinesParticules(result.getFloat(5));
				config.setSulfurDioxide(result.getFloat(6));
				config.setNitrogenDioxide(result.getFloat(7));
				config.setOzone(result.getFloat(8));
				
				String json = converter.ConfigToJson(config);
				list.add(json);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}	}

}
