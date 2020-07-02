package server;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConvertJSON;
import common.RetractableBollard;
import common.infotraffic;

public class infotrafficDAO extends DAO<infotraffic>{
	private ConvertJSON converter = new ConvertJSON();
	@Override
	public boolean insert(String device, Connection connection) {
		
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO infotraffic(id, alert, nbmaxcar) VALUES(?, ?,?)");
			
			preparedStatement.setBoolean(2, info.getAlert());
			preparedStatement.setInt(3, info.getNbmaxcar());
			preparedStatement.setInt(1, info.getId());
			
			
			return true; 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);

try {
			
			preparedStatement = connection.prepareStatement("DELETE FROM infotraffic WHERE id = ?");
			
			preparedStatement.setInt(1, info.getId());
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		return false;
	}}

	@Override
	public boolean update(String device, Connection connection) {
		PreparedStatement preparedStatement = null;
		infotraffic info = converter.JsonToinfotraffic(device);
		try {
			preparedStatement = connection.prepareStatement("UPDATE infotraffic SET alert = ?, nbcarmax = ?,  WHERE id = ?");
			preparedStatement.setInt(1, info.getId());
			preparedStatement.setInt(3, info.getNbmaxcar());
			preparedStatement.setBoolean(2, info.getAlert());
			
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {e.printStackTrace();}
			
		return false;
	}

	

	@Override
	public ArrayList<String> selectID(String id, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> select(Connection connection) {
		// TODO Auto-generated method stub
		return null; 
	}
	public ArrayList<String> selectnbmax(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT nbmaxcar FROM infotraffic");
			
			while(result.next()) {
				int nbmaxcar = result.getInt("nbmaxcar") ;
				
				// info.setNbmaxcar(result.getInt(1));
				
				
				String json = String.valueOf(nbmaxcar);
				list.add(json);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	}
		

}
	public ArrayList<String> selectalert(Connection connection) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT alert FROM infotraffic");
			
			while(result.next()) {
				Boolean  alert = result.getBoolean("alert");
				
				String json = String.valueOf(alert);
				list.add(json);
				
				
				
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		
		
	}
	}
}
	
	
	
