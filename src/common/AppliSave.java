package common;
/**
 * AppliSave is present in the database, this class allows to record all the sensor position in the map
 * @author elisa
 *
 */
public class AppliSave {
	private int id;
	private int idDeviceAir;
	private int coordX;
	private int coordY;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdDeviceAir() {
		return idDeviceAir;
	}
	public void setIdDeviceAir(int idDeviceAir) {
		this.idDeviceAir = idDeviceAir;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
