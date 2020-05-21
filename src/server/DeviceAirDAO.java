package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.DeviceAir;
/**
 * DeviceAirDAO do all the database operation from clients and sensors requests
 * @author elisa
 *
 */
public class DeviceAirDAO extends DAO<DeviceAir> {
	private ConvertJSON converter = new ConvertJSON();

	/** the insertion of a device insert a configuration too */
	public boolean insert(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceAir deviceAir = converter.JsontoDeviceAir(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO deviceair(id, address, isActive, frequency, nbStatement, onAlert, quality) VALUES(?, ?, ?, ?, ?, ?, ?)");

			preparedStatement.setInt(1, deviceAir.getId());
			preparedStatement.setString(2, deviceAir.getAddress());
			preparedStatement.setBoolean(3, deviceAir.isActive());
			preparedStatement.setInt(4, deviceAir.getFrequency());
			preparedStatement.setInt(5, deviceAir.getNbStatement());
			preparedStatement.setBoolean(6, deviceAir.isOnAlert());
			preparedStatement.setFloat(7, deviceAir.getQuality());

			preparedStatement.executeUpdate();
			
			DeviceConfigAirDAO dao = new DeviceConfigAirDAO();
			dao.insert(String.valueOf(deviceAir.getId()), connection);
		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** the deletion of a sensor needs to delete the applisave, the data and average statement and the configuration, specific to the device*/
	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		DeviceAir deviceAir = converter.JsontoDeviceAir(device);

		try {
			String id = String.valueOf(deviceAir.getId());
			
			AppliSaveDAO daoappli = new AppliSaveDAO();
			daoappli.delete(id, connection);
			
			DeviceConfigAirDAO daoconfig = new DeviceConfigAirDAO();
			daoconfig.delete(id, connection);

			DataAirDAO daodata = new DataAirDAO();
			daodata.delete(id, connection);
			
			DataAirAVGDAO daoavg = new DataAirAVGDAO();
			daoavg.delete(id, connection);
			
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
			preparedStatement = connection.prepareStatement("UPDATE deviceair SET address = ?, isActive = ?, frequency = ?, nbStatement = ?, quality = ? WHERE id = ?");

			preparedStatement.setString(1, deviceAir.getAddress());
			preparedStatement.setBoolean(2, deviceAir.isActive());
			preparedStatement.setInt(3, deviceAir.getFrequency());
			preparedStatement.setInt(4, deviceAir.getNbStatement());
			preparedStatement.setFloat(5, deviceAir.getQuality());
			preparedStatement.setInt(6,  deviceAir.getId());

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
				device.setQuality(result.getFloat(7));

				String json = converter.DeviceAirToJson(device);
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
			ResultSet result = myRequest.executeQuery("SELECT * FROM deviceair WHERE id = " + idDevice);

			while(result.next()) {
				DeviceAir device = new DeviceAir();

				device.setId(result.getInt(1));
				device.setAddress(result.getString(2));
				device.setActive(result.getBoolean(3));
				device.setFrequency(result.getInt(4));
				device.setOnAlert(result.getBoolean(6));
				device.setQuality(result.getFloat(7));


				String json = converter.DeviceAirToJson(device);
				list.add(json);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}
	
	public ArrayList<String> selectInformation(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT COUNT(*) FROM deviceair");

			while(result.next()) {
				String nbSensor = String.valueOf(result.getInt(1));
				list.add(nbSensor);
			}
			
			result = myRequest.executeQuery("SELECT COUNT(*) FROM deviceair WHERE isActive = true");

			while(result.next()) {
				String nbActivatedSensor = String.valueOf(result.getInt(1));
				list.add(nbActivatedSensor);
			}
			
			result = myRequest.executeQuery("SELECT AVG(quality) FROM deviceair WHERE isActive = true");

			while(result.next()) {
				String qualityAVG = String.valueOf(result.getInt(1));
				list.add(qualityAVG);
			}

			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
	}

}
