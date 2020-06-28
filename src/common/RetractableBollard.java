package common;





public class RetractableBollard {
	
	private int id;
	private String address;
	private boolean isActive;
	private boolean state;// in case the state is on true that will mean the bollard is leveled up 
	private boolean way;
	
	public boolean isWay() {
		return way;
	}
	public void setWay(boolean way) {
		this.way = way;
	}
	@Override
	public String toString() {
		return "RectactableBollard [address=" + address + "]";
	}
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
	public boolean isState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	public void OpenUpBollard() {
		this.state = true;
	}
	public RetractableBollard() {} 

}
