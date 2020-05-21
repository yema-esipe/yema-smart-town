package common;

import java.util.HashMap;
/**
 * Index allows the quality operation for each device but also usefull for the user configuration (present in the IHM)
 * @author elisa
 * it directly has all the recording of the index for each indicator, this objet is used to read the yaml file called 'aqs-index.yaml'
 */
public class Index {
	private HashMap<Integer, Float> co2 = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> carbonMonoxide = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> fineParticules = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> sulfurDioxide = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> nitrogenDioxide = new HashMap<Integer, Float>();
	private HashMap<Integer, Float> ozone = new HashMap<Integer, Float>();
	
	public HashMap<Integer, Float> getCo2() {
		return co2;
	}
	public void setCo2(HashMap<Integer, Float> co2) {
		this.co2 = co2;
	}
	public HashMap<Integer, Float> getCarbonMonoxide() {
		return carbonMonoxide;
	}
	public void setCarbonMonoxide(HashMap<Integer, Float> carbonMonoxide) {
		this.carbonMonoxide = carbonMonoxide;
	}
	public HashMap<Integer, Float> getFineParticules() {
		return fineParticules;
	}
	public void setFineParticules(HashMap<Integer, Float> finesParticules) {
		this.fineParticules = finesParticules;
	}
	public HashMap<Integer, Float> getSulfurDioxide() {
		return sulfurDioxide;
	}
	public void setSulfurDioxide(HashMap<Integer, Float> sulfurDioxide) {
		this.sulfurDioxide = sulfurDioxide;
	}
	public HashMap<Integer, Float> getNitrogenDioxide() {
		return nitrogenDioxide;
	}
	public void setNitrogenDioxide(HashMap<Integer, Float> nitrogenDioxide) {
		this.nitrogenDioxide = nitrogenDioxide;
	}
	public HashMap<Integer, Float> getOzone() {
		return ozone;
	}
	public void setOzone(HashMap<Integer, Float> ozone) {
		this.ozone = ozone;
	}

}
