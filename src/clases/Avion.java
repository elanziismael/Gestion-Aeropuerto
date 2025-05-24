package clases;

public class Avion {
	
	private int idAvion;
    private String modelo;
    private String fabricante;
    private int capacidad;
    private int idCompania;

    public Avion(int idAvion, String modelo, String fabricante, int capacidad, int idCompania) {
        this.idAvion = idAvion;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidad = capacidad;
        this.idCompania = idCompania;
    }

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
	}

	@Override
	public String toString() {
		return "Avion [idAvion=" + idAvion + ", modelo=" + modelo + ", fabricante=" + fabricante + ", capacidad="
				+ capacidad + ", idCompania=" + idCompania + "]";
	}
    

}
