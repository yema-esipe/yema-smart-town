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
		else if(target.equals("applisave"))
			return new AppliSaveDAO();
		else if (target.equals("dataairavg"))
			return new DataAirAVGDAO();
		else
			return null; 
	}
}
