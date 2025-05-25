package clases;

/**
 * <h1>Clase Avion</h1>
 * Representa un avión dentro del sistema del aeropuerto.
 * Contiene información como el identificador, modelo, fabricante,
 * capacidad de pasajeros y la compañía a la que pertenece.
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class Avion {

    private int idAvion;
    private String modelo;
    private String fabricante;
    private int capacidad;
    private int idCompania;

    /**
     * Constructor para la clase Avion.
     * 
     * @param idAvion Identificador único del avión.
     * @param modelo Modelo del avión.
     * @param fabricante Nombre del fabricante del avión.
     * @param capacidad Capacidad de pasajeros del avión.
     * @param idCompania Identificador de la compañía a la que pertenece.
     */
    public Avion(int idAvion, String modelo, String fabricante, int capacidad, int idCompania) {
        this.idAvion = idAvion;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidad = capacidad;
        this.idCompania = idCompania;
    }

    /**
     * @return Identificador del avión.
     */
    public int getIdAvion() {
        return idAvion;
    }

    /**
     * @param idAvion Establece el identificador del avión.
     */
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    /**
     * @return Modelo del avión.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo Establece el modelo del avión.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return Fabricante del avión.
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante Establece el fabricante del avión.
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return Capacidad del avión (nmero de pasajeros).
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad Establece la capacidad de pasajeros del avión.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return Identificador de la compañia aerea propietaria del avión.
     */
    public int getIdCompania() {
        return idCompania;
    }

    /**
     * @param idCompania Establece el identificador de la compañía aerea.
     */
    public void setIdCompania(int idCompania) {
        this.idCompania = idCompania;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Avion.
     * 
     * @return String con los datos del avión.
     */
    @Override
    public String toString() {
        return "Avion [idAvion=" + idAvion +
               ", modelo=" + modelo +
               ", fabricante=" + fabricante +
               ", capacidad=" + capacidad +
               ", idCompania=" + idCompania + "]";
    }
}
