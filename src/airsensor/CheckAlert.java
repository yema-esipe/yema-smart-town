package airsensor;

import java.util.ArrayList;

import common.Alert;
import common.DataAirAVG;
import common.DeviceAir;
import common.DeviceConfigAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
import connection.PropertiesFileReader;
/**
 * 
 * @author elisa
 * CheckAlert is a class to check if there is an alert for each data which is send to the server
 * nbAlertMax is a value recorded in resources file 'aqs-config.properties' -> matches with the number of average we need before to launch an alert
 */

public class CheckAlert {
	private Alert alert;
	private int nbAlertMax;
	private PropertiesFileReader file = new PropertiesFileReader();
	private DeviceAir device;	
	private RequestsAqsClient request;
	private final String source;

	public CheckAlert(DeviceAir device, CommunicationWithServer communication) {
		this.device = device;
		request = new RequestsAqsClient(communication);
		source = "sensor " + device.getId();
		file.initSensor();
		nbAlertMax = Integer.parseInt(file.getProperty("nbAlertMax"));
	}

	/** 
	 * checkAlert is the main method which called all the others. 
	 * @param dataAVG -> is a list with all average calculated (from the database)
	 * checkAlert change the sensor alert status if it is necessary and send the change to the server (it can be an update/delete/insert)
	 */
	public void checkAlert(ArrayList<DataAirAVG> dataAVG) {
		if(device.isOnAlert()) alert = (Alert) request.selectOne(device.getId(), source, "alert");
		else alert = new Alert();

		if (dataAVG.size() >= nbAlertMax) {  // it is useless to check alert if the number of average isn't enough 
			DeviceConfigAir config = (DeviceConfigAir) request.selectOne(device.getId(), source, "deviceconfigair");
			int indic = this.checkCo2(alert, config, dataAVG) + this.checkCarbonMonoxide(alert, config, dataAVG) + this.checkFinesParticules(alert, config, dataAVG) + this.checkSulfurDioxide(alert, config, dataAVG) + this.checkNitrogenDioxide(alert, config, dataAVG) + this.checkOzone(alert, config, dataAVG);

			if (device.isOnAlert()) {
				if (!(alert.isCo2()) && !(alert.isCarbonMonoxide()) && !(alert.isFinesParticules()) && !(alert.isSulfurDioxide()) && !(alert.isNitrogenDioxide()) && !(alert.isOzone())) {
					device.setOnAlert(false);
					request.delete(alert.getIdDeviceAir(), source, "alert");
				} else { request.update(alert, source, "alert"); }
			} else {
				if (indic > 0) {
					alert.setIdDeviceAir(device.getId());
					device.setOnAlert(true);
					request.insert(alert, source, "alert");
				}
			}
		}
	}
	
	/**
	 * checkCo2 and all the others below have the same function, only the indicator change
	 * @param alert -> can be a new alert (if the sensor don't be onAlert) or the existent alert (sensor onAlert)
	 * @param config -> is the indicators configuration specific to the device
	 * @param dataAVG -> the list of average calculated by the sensor
	 * @return 1 if it is for an insertion and 0 for an update or delete operation
	 */
	public int checkCo2(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) { //dataAVG list is ordered by the more recent date
			if (device.isOnAlert()) {
				if (config.getCo2() > dataAVG.get(i).getCo2()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getCo2() < dataAVG.get(i).getCo2()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setCo2(false);
		if (cptAlert == nbAlertMax) {alert.setCo2(true); return 1;}
		return 0;
	}

	public int checkCarbonMonoxide(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getCarbonMonoxide() > dataAVG.get(i).getCarbonMonoxide()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getCarbonMonoxide() < dataAVG.get(i).getCarbonMonoxide()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setCarbonMonoxide(false);
		if (cptAlert == nbAlertMax) {alert.setCarbonMonoxide(true); return 1;}
		return 0;
	}

	public int checkFinesParticules(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getFinesParticules() > dataAVG.get(i).getFinesParticules()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getFinesParticules() < dataAVG.get(i).getFinesParticules()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setFinesParticules(false);
		if (cptAlert == nbAlertMax) {alert.setFinesParticules(true); return 1;}
		return 0;
	}

	public int checkSulfurDioxide(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getSulfurDioxide() > dataAVG.get(i).getSulfurDioxide()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getSulfurDioxide() < dataAVG.get(i).getSulfurDioxide()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setSulfurDioxide(false);
		if (cptAlert == nbAlertMax) {alert.setSulfurDioxide(true); return 1;}
		return 0;
	}

	public int checkNitrogenDioxide(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getNitrogenDioxide() > dataAVG.get(i).getNitrogenDioxide()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getNitrogenDioxide() < dataAVG.get(i).getNitrogenDioxide()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setNitrogenDioxide(false);
		if (cptAlert == nbAlertMax) {alert.setNitrogenDioxide(true); return 1;}
		return 0;
	}

	public int checkOzone(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getOzone() > dataAVG.get(i).getOzone()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getOzone() < dataAVG.get(i).getOzone()) cptAlert++;
			}
		}
		if (cptNoAlert == nbAlertMax) alert.setOzone(false);
		if (cptAlert == nbAlertMax) {alert.setOzone(true); return 1;}
		return 0;
	}
}
