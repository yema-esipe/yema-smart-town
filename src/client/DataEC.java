package client;

public class DataEC {
	private String nom;
	private Double distance; // distance avg
	private Integer nombre;
	private Integer nombremax;
	private Double co2;
	private Integer passengeravg;
	private Double fe;
	private Double ec;
	  
	public DataEC(String name) {
		this.nom = name;
		this.distance = 0.0D; // distance avg
		this.nombre = 0;
		this.nombremax  = 0;
		this.co2  = 0.0D;
		this.passengeravg  = 0;
		this.fe = 0.0D;
		this.ec = 0.0D;
	}

	/* for fe calcul*/
	
	public Double calculfe() {
		if(this.passengeravg != null && this.co2 != null) {
			return (Double)(this.co2 /this.passengeravg);
		}else
		{
			return (Double)(1D / 1D);
		}
		
	}
	
	/* for ec calcul*/
	public Double calculec() {
		if(this.distance != null && this.nombre != null && this.fe != null) {
			return (Double)(this.distance * this.nombre * this.fe);
		}else
		{
			return (Double)(1D * 1D * 1D);
		}
		
	}
	
	/* getters and setters*/
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

	public Integer getNombremax() {
		return nombremax;
	}

	public void setNombremax(Integer nombremax) {
		this.nombremax = nombremax;
	}

	public Double getCo2() {
		return co2;
	}

	public void setCo2(Double co2) {
		this.co2 = co2;
	}

	public Integer getPassengeravg() {
		return passengeravg;
	}

	public void setPassengeravg(Integer passengeravg) {
		this.passengeravg = passengeravg;
	}
	
	public Double getFe() {
		return ((this.fe).doubleValue());
	}

	public void setFe() {
		this.fe = (Double)calculfe();
	}

	public Double getEc() {
		return ((this.ec).doubleValue());
	}

	public void setEc() {
		this.ec = calculfe().doubleValue();
	}

	
	public String toString() {
		setFe();
		setEc();
		return "mode : " + getNom() + " distance moyenne parcourue : " + getDistance() +" facteur d'émission: " + getFe() + " nombre: " + getNombre() + "/" + getNombremax() +" empreinte carbonne: " + getEc();
	}
	

}
