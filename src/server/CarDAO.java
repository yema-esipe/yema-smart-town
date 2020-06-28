package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import common.Car;
import common.ConvertJSON;

public class CarDAO extends DAO<Car>{
	private ConvertJSON converter = new ConvertJSON();
	@Override
	public boolean insert(String device, Connection connection) {
		
		PreparedStatement preparedStatement = null;
		Car car = converter.JsonToCar(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO car(id, isInTheCity) VALUES(?, ?)");
			
			preparedStatement.setInt(1, car.getId());
			preparedStatement.setBoolean(2, car.getIsInTheCity());
			
			preparedStatement.executeUpdate();
			
			return true; 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		Car car  = converter.JsonToCar(device);
try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM car WHERE id = ?");
			
			preparedStatement.setInt(1, car.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}}

	@Override
	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		Car car  = converter.JsonToCar(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE car SET isInTheCity = ? WHERE id = ?");
			preparedStatement.setInt(1, car.getId());
			
			preparedStatement.setBoolean(2, car.getIsInTheCity());
			
			
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {e.printStackTrace();}
			
		return false;
	}

	@Override
	public ArrayList<String> select(Connection connection) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT * FROM car where isInTheCity = true ");
			
			while(result.next()) {
				Car car = new Car();
				
				car.setId(result.getInt(1));
				car.setIsInTheCity(result.getBoolean(2));
				
				
				String json = converter.CarToJson(car);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); }
			
		return list;
	}

	@Override
	public ArrayList<String> selectID(String id, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

}
