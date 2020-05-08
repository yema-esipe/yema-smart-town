package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import common.Alert;
import common.ConvertJSON;

public class AlertDAO extends DAO<Alert> {
	private ConvertJSON converter = new ConvertJSON();

	public boolean insert(String alertAQS, Connection connection) {
		PreparedStatement preparedStatement = null;
		Alert alert = converter.JsontoAlert(alertAQS);
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss"));

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO alert(idDeviceAIr, date, co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setInt(1, alert.getIdDeviceAir());
			preparedStatement.setString(2, datetime);
			preparedStatement.setBoolean(3, alert.isCo2());
			preparedStatement.setBoolean(4, alert.isCarbonMonoxide());
			preparedStatement.setBoolean(5, alert.isFinesParticules());
			preparedStatement.setBoolean(6, alert.isSulfurDioxide());
			preparedStatement.setBoolean(7, alert.isNitrogenDioxide());
			preparedStatement.setBoolean(8, alert.isOzone());

			preparedStatement.executeUpdate();
			
			DeviceAirDAO dao = new DeviceAirDAO();
			dao.updateAlert(alert.getIdDeviceAir(), true, connection);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	public boolean delete(String alertAQS, Connection connection) {
		PreparedStatement preparedStatement = null;
		Alert alert = converter.JsontoAlert(alertAQS);

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM alert WHERE id = ?");
			
			preparedStatement.setInt(1, alert.getId());
			preparedStatement.executeUpdate();
			
			DeviceAirDAO dao = new DeviceAirDAO();
			dao.updateAlert(alert.getIdDeviceAir(), false, connection);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}

	public boolean update(String alertAQS, Connection connection) {
		PreparedStatement preparedStatement = null;
		Alert alert = converter.JsontoAlert(alertAQS);
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss"));

		try {
			preparedStatement = connection.prepareStatement("UPDATE alert SET date = ?, co2 = ?, carbonMonoxide = ?, finesParticules = ?, sulfurDioxide = ?, nitrogenDioxide = ?, ozone = ? WHERE id = ?");
			
			preparedStatement.setString(1, datetime);
			preparedStatement.setBoolean(2, alert.isCo2());
			preparedStatement.setBoolean(3, alert.isCarbonMonoxide());
			preparedStatement.setBoolean(4, alert.isFinesParticules());
			preparedStatement.setBoolean(5, alert.isSulfurDioxide());
			preparedStatement.setBoolean(6, alert.isNitrogenDioxide());
			preparedStatement.setBoolean(7, alert.isOzone());
			preparedStatement.setInt(8, alert.getId());

			
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
			ResultSet result = myRequest.executeQuery("SELECT * FROM Alert");
			
			while(result.next()) {
				Alert alert = new Alert();
				
				alert.setId(result.getInt(1));
				alert.setIdDeviceAir(result.getInt(2));
				alert.setDate(result.getString(3));
				
				alert.setCo2(result.getBoolean(4));
				alert.setCarbonMonoxide(result.getBoolean(5));
				alert.setFinesParticules(result.getBoolean(6));
				alert.setSulfurDioxide(result.getBoolean(7));
				alert.setNitrogenDioxide(result.getBoolean(8));
				alert.setOzone(result.getBoolean(9));
				
				String json = converter.AlertToJson(alert);									
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
			ResultSet result = myRequest.executeQuery("SELECT * FROM Alert WHERE idDeviceAir = " + idDevice);
			
			while(result.next()) {
				Alert alert = new Alert();
				
				alert.setId(result.getInt(1));
				alert.setIdDeviceAir(result.getInt(2));
				alert.setDate(result.getString(3));
				
				alert.setCo2(result.getBoolean(4));
				alert.setCarbonMonoxide(result.getBoolean(5));
				alert.setFinesParticules(result.getBoolean(6));
				alert.setSulfurDioxide(result.getBoolean(7));
				alert.setNitrogenDioxide(result.getBoolean(8));
				alert.setOzone(result.getBoolean(9));

				String json = converter.AlertToJson(alert);									
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		
	}
	

}
