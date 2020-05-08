package server;

public class Factory {
	@SuppressWarnings("rawtypes")
	public static DAO getData(String target){
		if(target.equals("deviceair"))
			return new DeviceAirDAO();
		
		else if(target.equals("dataair")) 
			return new DataAirDAO();
		
		else if(target.equals("alert")) 
			return new AlertDAO();
		
		else if(target.equals("deviceconfigair"))
			return new DeviceConfigAirDAO();
		else 
			return null; 
	}
}
