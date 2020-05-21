package common;

/**
 * DataAir is a business object, present in the database
 * @author elisa
 * for each statement, all the data are recorded in each attributes
 * there is the date and the device too
 */
public class DataAir {
	private int id;
	private String date;
	private int co2;
	private int carbonMonoxide;
	private int finesParticules;
	private int sulfurDioxide;
	private int nitrogenDioxide;
	private int ozone;
	private int idDeviceAir;

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
	public int getIdDeviceAir() {
		return idDeviceAir;
	}
	public void setIdDeviceAir(int idDeviceAir) {
		this.idDeviceAir = idDeviceAir;
	}

}
