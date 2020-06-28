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
import common.DeviceAir;
import common.RetractableBollard;

public class BollardDAO extends DAO<RetractableBollard>{
	private ConvertJSON converter = new ConvertJSON();
	@Override
	public boolean insert(String device, Connection connection) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		RetractableBollard bollard  = converter.JsonToBollard(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO retractablebollard (id, address, isActive, state, way) VALUES(?,?, ?, ?, ?)");
			preparedStatement.setInt(1, bollard.getId());
			preparedStatement.setString(2, bollard.getAddress());
			preparedStatement.setBoolean(3,bollard.isActive());
			preparedStatement.setBoolean(4, bollard.isState());
			preparedStatement.setBoolean(5,bollard.isWay());
			

			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		
		
		return false;
	       }
		}

	@Override
	public boolean delete(String device, Connection connection) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		RetractableBollard bollard = converter.JsonToBollard(device);
		
		try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM retractablebollard WHERE id = ?");
			
			preparedStatement.setInt(1, bollard.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(String device, Connection connection) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		RetractableBollard bollard = converter.JsonToBollard(device);

		try {
			preparedStatement = connection.prepareStatement("UPDATE retractablebollard SET address = ?, isActive = ?, state = ?,way = ? WHERE id = ?");
			
			preparedStatement.setInt(1, bollard.getId());
			preparedStatement.setString(2, bollard.getAddress());
			preparedStatement.setBoolean(3,bollard.isActive());
			preparedStatement.setBoolean(4, bollard.isState());
			preparedStatement.setBoolean(5,bollard.isWay());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		return false;
	       }
		}
	public boolean updateWay(int id, boolean state, Connection connection ) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("UPDATE retractablebollard SET state = ? WHERE id = ?");
			preparedStatement.setBoolean(1, state);
			preparedStatement.setInt(2, id);

			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
	}
	}

	@Override
	public ArrayList<String> select(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM retractablebollard");
			
			while(result.next()) {
				RetractableBollard bollard = new RetractableBollard();
				
				bollard.setId(result.getInt(1));
				bollard.setAddress(result.getString(2));
				bollard.setActive(result.getBoolean(3));
				bollard.setState(result.getBoolean(4));
				bollard.setWay(result.getBoolean(5));
				
				String json = converter.BollardToJson(bollard);
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