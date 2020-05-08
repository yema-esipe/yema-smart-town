package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import common.ConvertJSON;
import common.DataAir;
import common.DeviceAir;


public class DataAirDAO extends DAO<DataAir> {
	private ConvertJSON converter = new ConvertJSON();


	public boolean insert(String data, Connection connection) {
		System.out.println("----------------- NEW VALUE --------------");

		PreparedStatement preparedStatement = null;
		DataAir dataAir = converter.JsontoData(data);
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss"));

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO dataair(date, co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone, idDeviceAir) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, datetime);
			preparedStatement.setFloat(2, dataAir.getCo2());
			preparedStatement.setFloat(3, dataAir.getCarbonMonoxide());
			preparedStatement.setFloat(4, dataAir.getFinesParticules());
			preparedStatement.setFloat(5, dataAir.getSulfurDioxide());
			preparedStatement.setFloat(6, dataAir.getNitrogenDioxide());
			preparedStatement.setFloat(7, dataAir.getOzone());
			preparedStatement.setInt(8, dataAir.getIdDeviceAir());


			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean delete(String data, Connection connection) {
		PreparedStatement preparedStatement = null;
		DataAir dataAir = converter.JsontoData(data);

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM dataair WHERE id = ?");
			
			preparedStatement.setInt(1, dataAir.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	public boolean update(String obj, Connection connection) {
		return false;
	}

	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM dataair");
			
			while(result.next()) {
				DataAir data = new DataAir();
				
				data.setId(result.getInt(1));
				data.setDate(result.getString(2));
				data.setCo2(result.getFloat(3));
				data.setCarbonMonoxide(result.getFloat(4));
				data.setFinesParticules(result.getFloat(5));
				data.setSulfurDioxide(result.getFloat(6));
				data.setNitrogenDioxide(result.getFloat(7));
				data.setOzone(result.getFloat(8));
				data.setIdDeviceAir(result.getInt(9));
				
				String json = converter.DataToJson(data);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

	public ArrayList<String> selectAVG(String deviceAir, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		DeviceAir device = converter.JsontoDeviceAir(deviceAir);
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT idDeviceAir, AVG(co2), AVG(carbonMonoxide), AVG(finesParticules), AVG(sulfurDioxide), AVG(nitrogenDioxide), AVG(ozone) FROM (SELECT * FROM dataair WHERE idDeviceAir = " + device.getId() + " ORDER BY date DESC LIMIT " + device.getNbStatement() + ") as newTable");
			
			while(result.next()) {
				DataAir data = new DataAir();
				
				data.setIdDeviceAir(result.getInt(1));
				data.setCo2(result.getFloat(2));
				data.setCarbonMonoxide(result.getFloat(3));
				data.setFinesParticules(result.getFloat(4));
				data.setSulfurDioxide(result.getFloat(5));
				data.setNitrogenDioxide(result.getFloat(6));
				data.setOzone(result.getFloat(7));
				
				String json = converter.DataToJson(data);

				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
