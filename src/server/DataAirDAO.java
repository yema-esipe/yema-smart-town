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

/**
 * DataAirDAO do all the operation of the database from clients or sensors requests
 * @author elisa
 *
 */
public class DataAirDAO extends DAO<DataAir> {
	private ConvertJSON converter = new ConvertJSON();


	public boolean insert(String data, Connection connection) {
		System.out.println("------------- NEW STATEMENT ------------ \n");
		PreparedStatement preparedStatement = null;
		DataAir dataAir = converter.JsontoData(data);
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss"));

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO dataair(date, co2, carbonMonoxide, finesParticules, sulfurDioxide, nitrogenDioxide, ozone, idDeviceAir) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setString(1, datetime);
			preparedStatement.setInt(2, dataAir.getCo2());
			preparedStatement.setInt(3, dataAir.getCarbonMonoxide());
			preparedStatement.setInt(4, dataAir.getFinesParticules());
			preparedStatement.setInt(5, dataAir.getSulfurDioxide());
			preparedStatement.setInt(6, dataAir.getNitrogenDioxide());
			preparedStatement.setInt(7, dataAir.getOzone());
			preparedStatement.setInt(8, dataAir.getIdDeviceAir());

			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}

	/** when data are deleted, the average of the same sensor are deleted too*/
	public boolean delete(String id, Connection connection) {
		PreparedStatement preparedStatement = null;
		int idDevice = Integer.valueOf(id);
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM dataair WHERE idDeviceAir = ?");

			preparedStatement.setInt(1, idDevice);
			preparedStatement.executeUpdate();

			DataAirAVGDAO dao = new DataAirAVGDAO();
			dao.delete(id, connection);
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
		return null;
	}

	/** it is important to order the select data */
	public ArrayList<String> selectID(String id, Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM dataair WHERE idDeviceAir = " + Integer.valueOf(id) + " ORDER BY date DESC LIMIT 10");


			while(result.next()) {
				DataAir data = new DataAir();

				data.setId(result.getInt(1));
				data.setDate(result.getString(2));
				data.setCo2(result.getInt(3));
				data.setCarbonMonoxide(result.getInt(4));
				data.setFinesParticules(result.getInt(5));
				data.setSulfurDioxide(result.getInt(6));
				data.setNitrogenDioxide(result.getInt(7));
				data.setOzone(result.getInt(8));
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

}
