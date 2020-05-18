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
import common.DataAirAVG;
import common.DeviceAir;

public class DataAirAVGDAO extends DAO<DataAirAVG>{
	private ConvertJSON converter = new ConvertJSON();
	
	public boolean insert(String deviceAir, Connection connection) {
		PreparedStatement preparedStatement = null;
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss"));
		DeviceAir device = converter.JsontoDeviceAir(deviceAir);
		try { 

			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT COUNT(id) FROM dataair WHERE idDeviceAir = " + device.getId());
			int nbData = 0;
			while(result.next()) {nbData = result.getInt(1);}
			
			if (nbData >= device.getNbStatement()) {

				result = myRequest.executeQuery("SELECT idDeviceAir, AVG(co2), AVG(carbonMonoxide), AVG(finesParticules), AVG(sulfurDioxide), AVG(nitrogenDioxide), AVG(ozone) FROM (SELECT * FROM dataair WHERE idDeviceAir = " + device.getId() + " ORDER BY date DESC LIMIT " + device.getNbStatement() + ") as newTable");
				DataAirAVG dataAVG = new DataAirAVG();
				
				while(result.next()) {
					dataAVG.setIdDeviceAir(result.getInt(1));
					dataAVG.setCo2(result.getFloat(2));
					dataAVG.setCarbonMonoxide(result.getFloat(3));
					dataAVG.setFinesParticules(result.getFloat(4));
					dataAVG.setSulfurDioxide(result.getFloat(5));
					dataAVG.setNitrogenDioxide(result.getFloat(6));
					dataAVG.setOzone(result.getFloat(7));
				}

				//on insère

				preparedStatement = connection.prepareStatement("INSERT INTO dataairavg(idDeviceAir, date, co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

				preparedStatement.setInt(1, dataAVG.getIdDeviceAir());
				preparedStatement.setString(2, datetime);
				preparedStatement.setFloat(3, dataAVG.getCo2());
				preparedStatement.setFloat(4, dataAVG.getCarbonMonoxide());
				preparedStatement.setFloat(5, dataAVG.getFinesParticules());
				preparedStatement.setFloat(6, dataAVG.getSulfurDioxide());
				preparedStatement.setFloat(7, dataAVG.getNitrogenDioxide());
				preparedStatement.setFloat(8, dataAVG.getOzone());

				preparedStatement.executeUpdate();
			}
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
			preparedStatement = connection.prepareStatement("DELETE FROM dataairavg WHERE idDeviceAir = ?");

			preparedStatement.setInt(1, idDevice);
			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean update(String obj, Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> select(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> selectID(String id, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM dataairavg WHERE idDeviceAir = " + Integer.valueOf(id) + " ORDER BY date");


			while(result.next()) {
				DataAirAVG dataAVG = new DataAirAVG();

				dataAVG.setId(result.getInt(1));
				dataAVG.setIdDeviceAir(result.getInt(2));
				dataAVG.setDate(result.getString(3));
				dataAVG.setCo2(result.getFloat(4));
				dataAVG.setCarbonMonoxide(result.getFloat(5));
				dataAVG.setFinesParticules(result.getFloat(6));
				dataAVG.setSulfurDioxide(result.getFloat(7));
				dataAVG.setNitrogenDioxide(result.getFloat(8));
				dataAVG.setOzone(result.getFloat(9));

				String json = converter.DataAVGToJson(dataAVG);
				list.add(json);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
