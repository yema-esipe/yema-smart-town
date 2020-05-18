package airsensor;

import java.util.ArrayList;

import common.Alert;
import common.DataAirAVG;
import common.DeviceAir;
import common.DeviceConfigAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
import connection.PropertiesFileReader;

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

	public void checkAlert(ArrayList<DataAirAVG> dataAVG) {
		if(device.isOnAlert()) alert = (Alert) request.selectOne(device.getId(), source, "alert");
		else alert = new Alert();

		if (dataAVG.size() >= nbAlertMax) {
			DeviceConfigAir config = (DeviceConfigAir) request.selectOne(device.getId(), source, "deviceconfigair");
			int indic = this.checkCo2(alert, config, dataAVG) + this.checkCarbonMonoxide(alert, config, dataAVG) + this.checkFinesParticules(alert, config, dataAVG) + this.checkSulfurDioxide(alert, config, dataAVG) + this.checkNitrogenDioxide(alert, config, dataAVG) + this.checkOzone(alert, config, dataAVG);

			if (device.isOnAlert()) {
				if (!(alert.isCo2()) && !(alert.isCarbonMonoxide()) && !(alert.isFinesParticules()) && !(alert.isSulfurDioxide()) && !(alert.isNitrogenDioxide()) && !(alert.isOzone())) {
					device.setOnAlert(false);
					request.delete(alert, source, "alert");
				} else request.update(alert, source, "alert");
			} else {
				if (indic > 0) {
					alert.setIdDeviceAir(device.getId());
					device.setOnAlert(true);
					request.insert(alert, source, "alert");
				}
			}
		}
	}

	public int checkCo2(Alert alert, DeviceConfigAir config, ArrayList<DataAirAVG> dataAVG) {
		int cptNoAlert = 0;
		int cptAlert = 0;
		for (int i = 0; i < nbAlertMax; i++) {
			if (device.isOnAlert()) {
				if (config.getCo2() > dataAVG.get(i).getCo2()) cptNoAlert++;
				else cptAlert++;
			} else {
				if (config.getCo2() < dataAVG.get(i).getCo2()) cptAlert++;
			}
		}
		if (cptNoAlert == dataAVG.size()) alert.setCo2(false);
		if (cptAlert == dataAVG.size()) {alert.setCo2(true); return 1;}
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
		if (cptNoAlert == dataAVG.size()) alert.setCarbonMonoxide(false);
		if (cptAlert == dataAVG.size()) {alert.setCarbonMonoxide(true); return 1;}
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
		if (cptNoAlert == dataAVG.size()) alert.setFinesParticules(false);
		if (cptAlert == dataAVG.size()) {alert.setFinesParticules(true); return 1;}
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
		if (cptNoAlert == dataAVG.size()) alert.setSulfurDioxide(false);
		if (cptAlert == dataAVG.size()) {alert.setSulfurDioxide(true); return 1;}
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
		if (cptNoAlert == dataAVG.size()) alert.setNitrogenDioxide(false);
		if (cptAlert == dataAVG.size()) {alert.setNitrogenDioxide(true); return 1;}
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
		if (cptNoAlert == dataAVG.size()) alert.setOzone(false);
		if (cptAlert == dataAVG.size()) {alert.setOzone(true); return 1;}
		return 0;
	}
}
