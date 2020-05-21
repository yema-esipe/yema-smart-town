package common;
/**
 * Alert is a business object, from the database
 * @author elisa
 * this object allows to know which indicator is on alert, the date of the alert and the device in which the alert is in relation 
 */
public class Alert {
	private int id;
	private int idDeviceAir;
	private String date;
	private boolean co2;
	private boolean carbonMonoxide;
	private boolean finesParticules;
	private boolean sulfurDioxide;
	private boolean nitrogenDioxide;
	private boolean ozone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdDeviceAir() {
		return idDeviceAir;
	}
	public void setIdDeviceAir(int idDeviceAir) {
		this.idDeviceAir = idDeviceAir;
	}
	public boolean isCo2() {
		return co2;
	}
	public void setCo2(boolean co2) {
		this.co2 = co2;
	}
	public boolean isCarbonMonoxide() {
		return carbonMonoxide;
	}
	public void setCarbonMonoxide(boolean carbonMonoxide) {
		this.carbonMonoxide = carbonMonoxide;
	}
	public boolean isFinesParticules() {
		return finesParticules;
	}
	public void setFinesParticules(boolean finesParticules) {
		this.finesParticules = finesParticules;
	}
	public boolean isSulfurDioxide() {
		return sulfurDioxide;
	}
	public void setSulfurDioxide(boolean sulfurDioxide) {
		this.sulfurDioxide = sulfurDioxide;
	}
	public boolean isNitrogenDioxide() {
		return nitrogenDioxide;
	}
	public void setNitrogenDioxide(boolean nitrogenDioxide) {
		this.nitrogenDioxide = nitrogenDioxide;
	}
	public boolean isOzone() {
		return ozone;
	}
	public void setOzone(boolean ozone) {
		this.ozone = ozone;
	}
}
