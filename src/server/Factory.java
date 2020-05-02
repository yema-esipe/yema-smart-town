package server;

public class Factory {
	@SuppressWarnings("rawtypes")
	public static DAO getData(String target){
		if(target.equals("users"))
			return new DAOUser();
		/*else if(target == 1)
			return new C();
		else 
			return new D();*/ 
		return null;
	}
}
 