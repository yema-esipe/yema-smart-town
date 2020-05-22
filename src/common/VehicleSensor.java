package common;




public class VehicleSensor {
	private int id ;
	private String address;
	private boolean isActive;
	private int NbVehicle ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getNbVehicle() {
		return NbVehicle;
	}
	@Override
	public String toString() {
		return "VehicleSensor [address=" + address + "]";
	}
	public void setNbVehicle(int nbVehicle) {
		NbVehicle = nbVehicle;
	}
	
	
	

}
