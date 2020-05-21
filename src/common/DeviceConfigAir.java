package common;

/**
 * DeviceConfigAir is a business object, presents in the database
 * @author elisa
 * it represents the configuration specific to one device
 */
public class DeviceConfigAir {
	private int id;
	private int idDevice;
	private int co2;
	private int carbonMonoxide;
	private int finesParticules;
	private int sulfurDioxide;
	private int nitrogenDioxide;
	private int ozone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdDevice() {
		return idDevice;
	}
	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
	public int getCo2() {
		return co2;
	}
	public void setCo2(int co2) {
		this.co2 = co2;
	}
	public int getCarbonMonoxide() {
		return carbonMonoxide;
	}
	public void setCarbonMonoxide(int carbonMonoxide) {
		this.carbonMonoxide = carbonMonoxide;
	}
	public int getFinesParticules() {
		return finesParticules;
	}
	public void setFinesParticules(int finesParticules) {
		this.finesParticules = finesParticules;
	}
	public int getSulfurDioxide() {
		return sulfurDioxide;
	}
	public void setSulfurDioxide(int sulfurDioxide) {
		this.sulfurDioxide = sulfurDioxide;
	}
	public int getNitrogenDioxide() {
		return nitrogenDioxide;
	}
	public void setNitrogenDioxide(int nitrogenDioxide) {
		this.nitrogenDioxide = nitrogenDioxide;
	}
	public int getOzone() {
		return ozone;
	}
	public void setOzone(int ozone) {
		this.ozone = ozone;
	}
}
