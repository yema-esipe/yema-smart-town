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

	public boolean insert(String configAir, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceConfigAir config = converter.JsontoConfig(configAir);
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO deviceconfigair(co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone) VALUES(?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setFloat(1, config.getCo2());
			preparedStatement.setFloat(2, config.getCarbonMonoxide());
			preparedStatement.setFloat(3, config.getFinesParticules());
			preparedStatement.setFloat(4, config.getSulfurDioxide());
			preparedStatement.setFloat(5, config.getNitrogenDioxide());
			preparedStatement.setFloat(6, config.getOzone());

			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean delete(String configAir, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceConfigAir config = converter.JsontoConfig(configAir);

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM deviceconfigair WHERE id = ?");
			
			preparedStatement.setInt(1, config.getId());
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
				config.setCo2(result.getFloat(2));
				config.setCarbonMonoxide(result.getFloat(3));
				config.setFinesParticules(result.getFloat(4));
				config.setSulfurDioxide(result.getFloat(5));
				config.setNitrogenDioxide(result.getFloat(6));
				config.setOzone(result.getFloat(7));
				
				String json = converter.ConfigToJson(config);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
