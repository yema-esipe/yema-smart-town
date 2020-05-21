package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.AppliSave;
import common.ConvertJSON;

/**
 * AppliSaveDAO do all the database operation from clients or sensors requests
 * @author elisa
 *
 */
public class AppliSaveDAO extends DAO<AppliSave>{
	private ConvertJSON converter = new ConvertJSON();


	public boolean insert(String obj, Connection connection) {
		PreparedStatement preparedStatement = null;
		AppliSave save = converter.JsontoSave(obj);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO applisave(idDeviceAir, coordX, coordY) VALUES(?, ?, ?)");

			preparedStatement.setInt(1, save.getIdDeviceAir());
			preparedStatement.setInt(2, save.getCoordX());
			preparedStatement.setInt(3, save.getCoordY());

			preparedStatement.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String obj, Connection connection) {
		PreparedStatement preparedStatement = null;
		int idDevice = Integer.valueOf(obj);

		try {

			preparedStatement = connection.prepareStatement("DELETE FROM applisave WHERE idDeviceAir = ?");

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

	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM applisave");

			while(result.next()) {
				AppliSave save = new AppliSave();

				save.setId(result.getInt(1));
				save.setIdDeviceAir(result.getInt(2));
				save.setCoordX(result.getInt(3));
				save.setCoordY(result.getInt(4));
				
				String json = converter.SaveToJson(save);
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
		// TODO Auto-generated method stub
		return null;
	}

}
