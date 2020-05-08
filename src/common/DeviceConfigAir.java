package common;

public class DeviceConfigAir {
	private int id;
	private float co2;
	private float carbonMonoxide;
	private float finesParticules;
	private float sulfurDioxide;
	private float nitrogenDioxide;
	private float ozone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getCo2() {
		return co2;
	}
	public void setCo2(float co2) {
		this.co2 = co2;
	}
	public float getCarbonMonoxide() {
		return carbonMonoxide;
	}
	public void setCarbonMonoxide(float carbonMonoxide) {
		this.carbonMonoxide = carbonMonoxide;
	}
	public float getFinesParticules() {
		return finesParticules;
	}
	public void setFinesParticules(float finesParticules) {
		this.finesParticules = finesParticules;
	}
	public float getSulfurDioxide() {
		return sulfurDioxide;
	}
	public void setSulfurDioxide(float sulfurDioxide) {
		this.sulfurDioxide = sulfurDioxide;
	}
	public float getNitrogenDioxide() {
		return nitrogenDioxide;
	}
	public void setNitrogenDioxide(float nitrogenDioxide) {
		this.nitrogenDioxide = nitrogenDioxide;
	}
	public float getOzone() {
		return ozone;
	}
	public void setOzone(float ozone) {
		this.ozone = ozone;
	}
}
