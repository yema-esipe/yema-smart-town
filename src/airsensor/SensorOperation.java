package airsensor;

import java.util.ArrayList;
import common.Alert;
import common.DataAir;
import common.DeviceAir;
import common.DeviceConfigAir;
import connection.PropertiesFileReader;

public class SensorOperation {
	private RequestsAQS request = new RequestsAQS();
	PropertiesFileReader file = new PropertiesFileReader();

	private DeviceAir device;	
	private Alert alert;
	private ArrayList<Integer> alertCounter = new ArrayList<Integer>();

	private int nbAlertMax;

	public SensorOperation(DeviceAir device) {
		file.initSensor();
		nbAlertMax = Integer.parseInt(file.getProperty("nbAlertMax"));
		this.device = device;

		for(int i = 0; i < 6; i++) {
			alertCounter.add(0);
		}

	}

	public void start() throws InterruptedException {
		device.getValues(device.getId());
		ArrayList<DataAir> datas = device.getDatas();
		device.setActive(true);

		while (device.isActive()) {
			for (DataAir d : datas) {
				request.sendValues(d);
				this.checkAlert();
				Thread.sleep(device.getFrequency() * Integer.parseInt(file.getProperty("unit"))); //en seconde : *1000 en minute : *60000
			}
		}
	}

	public void checkAlert() {
		if(device.isOnAlert()) 	alert = request.getAlert(device.getId()); 
		else alert = new Alert();
		
		DeviceConfigAir config = request.getConfig();
		DataAir dataAVG = request.getAVG(device);
		
		int indic;
		indic = this.checkAlertCo2(alert, config, dataAVG) + this.checkAlertCarbonMonoxide(alert, config, dataAVG) + this.checkAlertFinesParticules(alert, config, dataAVG) + this.ckeckSulfurDioxide(alert, config, dataAVG) + this.checkNitrogenDioxide(alert, config, dataAVG) + this.checkOzone(alert, config, dataAVG);
		
		if ((indic > 0) && (indic < 10)) {
			alert.setIdDeviceAir(device.getId());
			device.setOnAlert(true);
			request.sendAlert(alert, "insert");
		} else if (!(alert.isCo2()) && !(alert.isCarbonMonoxide()) && !(alert.isFinesParticules()) && !(alert.isSulfurDioxide()) && !(alert.isNitrogenDioxide()) && !(alert.isOzone())) {
			device.setOnAlert(false);
			request.sendAlert(alert, "delete");
		} else if(indic >= 10) {
			request.sendAlert(alert, "update");
		}

	}

	public int checkAlertCo2(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if(device.isOnAlert()) { 

			if (alert.isCo2()) { 
				if (dataAVG.getCo2() < config.getCo2()) {
					if(alertCounter.get(0) == nbAlertMax - 1) {	alert.setCo2(false); alertCounter.set(0, 0); result = 10;}  //1
					else alertCounter.set(0, alertCounter.get(0) + 1); //2
				} else alertCounter.set(0, 0); //3
			} else {
				if (dataAVG.getCo2() >= config.getCo2()) {
					if(alertCounter.get(0) == nbAlertMax - 1) {	alert.setCo2(true);	alertCounter.set(0, 0); result = 10;} //4
					else  alertCounter.set(0, alertCounter.get(0) + 1); //5
				} else alertCounter.set(0,0); //6
			}
		} else {
			if (dataAVG.getCo2() >= config.getCo2()) {
				if(alertCounter.get(0) == nbAlertMax - 1) {	alert.setCo2(true);	alertCounter.set(0, 0); result = 1;} //7
				else { alertCounter.set(0, alertCounter.get(0) + 1);  alert.setCo2(false); } //8
			} else { alertCounter.set(0,0); alert.setCo2(false); }//9
		}

		return result;
	}

	public int checkAlertCarbonMonoxide(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if(device.isOnAlert()) {
			
			if (alert.isCarbonMonoxide()) {
				if (dataAVG.getCarbonMonoxide() < config.getCarbonMonoxide()) {
					if(alertCounter.get(1) == nbAlertMax - 1) { alert.setCarbonMonoxide(false); alertCounter.set(1, 0);result = 10;}
					else alertCounter.set(1, alertCounter.get(1) + 1);
				} else alertCounter.set(1, 0);
			} else {
				if (dataAVG.getCarbonMonoxide() >= config.getCarbonMonoxide()) {
					if(alertCounter.get(1) == nbAlertMax - 1) { alert.setCarbonMonoxide(true); alertCounter.set(1, 0);result = 10;}
					else alertCounter.set(1, alertCounter.get(1) + 1);
				} else alertCounter.set(1, 0);
			}
		} else {
			if (dataAVG.getCarbonMonoxide() >= config.getCarbonMonoxide()) {
				if(alertCounter.get(1) == nbAlertMax - 1) { alert.setCarbonMonoxide(true); alertCounter.set(1, 0); result = 1; }
				else {alertCounter.set(1, alertCounter.get(1) + 1); alert.setCarbonMonoxide(false); }
			} else { alertCounter.set(1, 0); alert.setCarbonMonoxide(false); }
		}

		return result;
	}

	public int checkAlertFinesParticules(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if (device.isOnAlert()) {
			
			if (alert.isFinesParticules()) {
				if (dataAVG.getFinesParticules() < config.getFinesParticules()) {
					if(alertCounter.get(2) == nbAlertMax - 1) { alert.setFinesParticules(false); alertCounter.set(2, 0); result = 10;}
					else alertCounter.set(2, alertCounter.get(2) + 1);
				} else alertCounter.set(2, 0);
			} else {
				if (dataAVG.getFinesParticules() >= config.getFinesParticules()) {
					if(alertCounter.get(2) == nbAlertMax - 1) { alert.setFinesParticules(true); alertCounter.set(2, 0); result = 10;}
					else alertCounter.set(2, alertCounter.get(2) + 1);
				} else alertCounter.set(2, 0);
			}
		} else {
			if (dataAVG.getFinesParticules() >= config.getFinesParticules()) {
				if(alertCounter.get(2) == nbAlertMax - 1) { alert.setFinesParticules(true); alertCounter.set(2, 0); result = 1; }
				else { alertCounter.set(2, alertCounter.get(2) + 1); alert.setFinesParticules(false); }
			} else { alertCounter.set(2, 0); alert.setFinesParticules(false); }
		}
		
		return result;
	}
	
	public int ckeckSulfurDioxide(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if (device.isOnAlert()) {
			
			if (alert.isSulfurDioxide()) {
				if (dataAVG.getSulfurDioxide() < config.getSulfurDioxide()) {
					if(alertCounter.get(3) == nbAlertMax - 1) { alert.setSulfurDioxide(false); alertCounter.set(3, 0); result = 10;}
					else alertCounter.set(3, alertCounter.get(3) + 1);
				} else alertCounter.set(3, 0);
			} else {
				if (dataAVG.getSulfurDioxide() >= config.getSulfurDioxide()) {
					if(alertCounter.get(3) == nbAlertMax - 1) { alert.setSulfurDioxide(true); alertCounter.set(3, 0); result = 10;}
					else alertCounter.set(3, alertCounter.get(3) + 1);
				} else alertCounter.set(3, 0);
			}
		} else {
			if (dataAVG.getSulfurDioxide() >= config.getSulfurDioxide()) {
				if(alertCounter.get(3) == nbAlertMax - 1) { alert.setSulfurDioxide(true); alertCounter.set(3, 0); result = 1; }
				else { alertCounter.set(3, alertCounter.get(3) + 1); alert.setSulfurDioxide(false); }
			} else { alertCounter.set(3, 0); alert.setSulfurDioxide(false);}
		}
		
		return result;
	}
	
	public int checkNitrogenDioxide(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if (device.isOnAlert()) {
			
			if (alert.isNitrogenDioxide()) {
				if (dataAVG.getNitrogenDioxide() < config.getNitrogenDioxide()) {
					if(alertCounter.get(4) == nbAlertMax - 1) { alert.setNitrogenDioxide(false); alertCounter.set(4, 0); result = 10;}
					else alertCounter.set(4, alertCounter.get(4) + 1);
				} else alertCounter.set(4, 0);
			} else {
				if (dataAVG.getNitrogenDioxide() >= config.getNitrogenDioxide()) {
					if(alertCounter.get(4) == nbAlertMax - 1) { alert.setNitrogenDioxide(true); alertCounter.set(4, 0); result = 10;}
					else alertCounter.set(4, alertCounter.get(4) + 1);
				} else alertCounter.set(4, 0);
			}
		} else {
			if (dataAVG.getNitrogenDioxide() >= config.getNitrogenDioxide()) {
				if(alertCounter.get(4) == nbAlertMax - 1) { alert.setNitrogenDioxide(true); alertCounter.set(4, 0); result = 1; }
				else { alertCounter.set(4, alertCounter.get(4) + 1); alert.setNitrogenDioxide(false); }
			} else { alertCounter.set(4, 0); alert.setNitrogenDioxide(false); }
		}
		return result;
	}

	public int checkOzone(Alert alert, DeviceConfigAir config, DataAir dataAVG) {
		int result = 0;
		if (device.isOnAlert()) {
			
			if (alert.isOzone()) {
				if (dataAVG.getOzone() < config.getOzone()) {
					if(alertCounter.get(5) == nbAlertMax - 1) { alert.setOzone(false); alertCounter.set(5, 0); result = 10;}
					else alertCounter.set(5, alertCounter.get(5) + 1);
				} else alertCounter.set(5, 0);
			} else {
				if (dataAVG.getOzone() >= config.getOzone()) {
					if(alertCounter.get(5) == nbAlertMax - 1) { alert.setOzone(true); alertCounter.set(5, 0); result = 10;}
					else alertCounter.set(5, alertCounter.get(5) + 1);
				} else alertCounter.set(5, 0);
			}
		} else {
			if (dataAVG.getOzone() >= config.getOzone()) {
				if(alertCounter.get(5) == nbAlertMax - 1) { alert.setOzone(true); alertCounter.set(5, 0); result = 1; }
				else { alertCounter.set(5, alertCounter.get(5) + 1); alert.setOzone(false); }
			} else { alertCounter.set(5, 0); alert.setOzone(false); }
		}
		return result;
	}

}
