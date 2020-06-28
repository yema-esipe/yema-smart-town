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
		
		else if(target.equals("pollutiondata"))
			return new PollutionDataDAO();
		
		else if(target.equals("typeoftravel"))
			return new TypeOfTravelDAO();
		
		else if(target.equals("deviceconfignbcar"))
			return new DeviceConfigNbCarDAO();
		
		else if(target.equals("retractablebollard"))
			  return new BollardDAO();
		
		else if (target.equals("vehiclesensor"))
				return new VehicleSensorDAO();
		
		else if (target.contentEquals("car"))
				return new CarDAO();
		else if (target.contentEquals("infotraffic"))
			return new infotrafficDAO();
		else
				return null; 
	}
}
