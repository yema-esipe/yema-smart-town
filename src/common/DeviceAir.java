package common;

import java.util.ArrayList;
import airsensor.YamlFileReader;

public class DeviceAir {
	private int id;
	private String address;
	private boolean isActive;
	private int frequency;
	private int nbStatement;
	private boolean onAlert;
	private ArrayList<DataAir> datas;
	
	public DeviceAir() {
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
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
		this.setNbStatement();
	}
	public int getNbStatement() {
		return nbStatement;
	}
	public void setNbStatement() {
		this.nbStatement = 60 / frequency;
	}
	public boolean isOnAlert() {
		return onAlert;
	}
	public void setOnAlert(boolean onAlert) {
		this.onAlert = onAlert;
	}
	
	public ArrayList<DataAir> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<DataAir> datas) {
		this.datas = datas;
	}
	
	public void getValues(int id) {
		YamlFileReader yaml = new YamlFileReader();
		datas = yaml.initData(id);
	}

	public String toString() {
		return address;
	}

}
