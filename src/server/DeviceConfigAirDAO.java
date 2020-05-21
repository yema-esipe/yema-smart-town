package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.DeviceConfigAir;
/**
 * DeviceConfigAirDAO do alle the database operation from clients or sensors requests
 * @author elisa
 *
 */
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
			preparedStatement = connection.prepareStatement("UPDATE deviceconfigair SET co2 = ?, carbonMonoxide = ?, finesParticules = ?, sulfurDioxide = ?, nitrogenDioxide = ?, ozone = ? WHERE idDeviceAir = ?");
			
			preparedStatement.setInt(1, config.getCo2());
			preparedStatement.setInt(2, config.getCarbonMonoxide());
			preparedStatement.setInt(3, config.getFinesParticules());
			preparedStatement.setInt(4, config.getSulfurDioxide());
			preparedStatement.setInt(5, config.getNitrogenDioxide());
			preparedStatement.setInt(6, config.getOzone());
			preparedStatement.setInt(7, config.getIdDevice());
			
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
				config.setCo2(result.getInt(3));
				config.setCarbonMonoxide(result.getInt(4));
				config.setFinesParticules(result.getInt(5));
				config.setSulfurDioxide(result.getInt(6));
				config.setNitrogenDioxide(result.getInt(7));
				config.setOzone(result.getInt(8));
				
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
				config.setCo2(result.getInt(3));
				config.setCarbonMonoxide(result.getInt(4));
				config.setFinesParticules(result.getInt(5));
				config.setSulfurDioxide(result.getInt(6));
				config.setNitrogenDioxide(result.getInt(7));
				config.setOzone(result.getInt(8));
				
				String json = converter.ConfigToJson(config);
				list.add(json);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}	}

}
