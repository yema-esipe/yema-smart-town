package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.DeviceAir;

public class DeviceAirDAO extends DAO<DeviceAir> {
	private ConvertJSON converter = new ConvertJSON();

	public boolean insert(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceAir deviceAir = converter.JsontoDeviceAir(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO deviceair(address, isActive, frequency, nbStatement, onAlert) VALUES(?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, deviceAir.getAddress());
			preparedStatement.setBoolean(2, deviceAir.isActive());
			preparedStatement.setInt(3, deviceAir.getFrequency());
			preparedStatement.setInt(4, deviceAir.getNbStatement());
			preparedStatement.setBoolean(5, deviceAir.isOnAlert());

			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceAir deviceAir = converter.JsontoDeviceAir(device);
		
		try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM deviceair WHERE id = ?");
			
			preparedStatement.setInt(1, deviceAir.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceAir deviceAir = converter.JsontoDeviceAir(device);

		try {
			preparedStatement = connection.prepareStatement("UPDATE deviceair SET address = ?, isActive = ?, frequency = ?, onAlert = ? WHERE id = ?");
			
			preparedStatement.setString(1, deviceAir.getAddress());
			preparedStatement.setBoolean(2, deviceAir.isActive());
			preparedStatement.setInt(3, deviceAir.getFrequency());
			preparedStatement.setBoolean(4, deviceAir.isOnAlert());
			preparedStatement.setInt(5,  deviceAir.getId());

			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateAlert(int id, boolean state, Connection connection) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("UPDATE deviceair SET onAlert = ? WHERE id = ?");
			preparedStatement.setBoolean(1, state);
			preparedStatement.setInt(2, id);

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
			ResultSet result = myRequest.executeQuery("SELECT * FROM deviceair");
			
			while(result.next()) {
				DeviceAir device = new DeviceAir();
				
				device.setId(result.getInt(1));
				device.setAddress(result.getString(2));
				device.setActive(result.getBoolean(3));
				device.setFrequency(result.getInt(4));
				device.setOnAlert(result.getBoolean(6));
				
				String json = converter.DeviceAirToJson(device);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
