package clases;

/**
 * <h1>Clase PuertaEmbarque</h1>
 * Representa una puerta de embarque en el sistema del aeropuerto.
 * Contiene información como el identificador, número de puerta y terminal a la que pertenece.
 * 
 * Esta clase se utiliza para asociar vuelos a puertas específicas.
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class PuertaEmbarque {

    private int id;
    private String numero;
    private String terminal;

    /**
     * Constructor de la clase PuertaEmbarque.
     * 
     * @param id Identificador único de la puerta de embarque.
     * @param numero Número de la puerta.
     * @param terminal Terminal a la que pertenece la puerta.
     */
    public PuertaEmbarque(int id, String numero, String terminal) {
        this.id = id;
        this.numero = numero;
        this.terminal = terminal;
    }

    /**
     * @return ID de la puerta de embarque.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Establece el ID de la puerta de embarque.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Número de la puerta de embarque.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero Establece el número de la puerta de embarque.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return Terminal a la que pertenece la puerta.
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * @param terminal Establece la terminal a la que pertenece la puerta.
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    /**
     * Devuelve una representación en cadena del objeto PuertaEmbarque.
     * 
     * @return String con el ID, número y terminal de la puerta.
     */
    @Override
    public String toString() {
        return "PuertaEmbarque{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
