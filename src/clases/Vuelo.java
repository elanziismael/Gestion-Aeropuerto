package clases;

import java.time.LocalDateTime;

/**
 * <h1>Clase Vuelo</h1>
 * Representa un vuelo dentro del sistema de gestión de vuelos.
 * Contiene información como número de vuelo, compañía, avión, piloto,
 * puerta de embarque, origen, destino, hora de salida y llegada, y estado del vuelo.
 *
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class Vuelo {

    private int idVuelo;
    private String numeroVuelo;
    private int idCompania;
    private int idAvion;
    private int idPiloto;
    private int idPuertaEmbarque;
    private String origen;
    private String destino;
    private LocalDateTime horaSalida;
    private LocalDateTime horaLlegada;
    private String estado;

    /**
     * Constructor para la clase Vuelo.
     *
     * @param idVuelo Identificador único del vuelo.
     * @param numeroVuelo Número del vuelo.
     * @param idCompania Identificador de la compañía aérea.
     * @param idAvion Identificador del avión asignado.
     * @param idPiloto Identificador del piloto asignado.
     * @param idPuertaEmbarque Identificador de la puerta de embarque.
     * @param origen Ciudad o aeropuerto de origen.
     * @param destino Ciudad o aeropuerto de destino.
     * @param horaSalida Fecha y hora de salida.
     * @param horaLlegada Fecha y hora estimada de llegada.
     * @param estado Estado actual del vuelo (ej. programado, en vuelo, cancelado).
     */
    public Vuelo(int idVuelo, String numeroVuelo, int idCompania, int idAvion, int idPiloto, int idPuertaEmbarque,
                 String origen, String destino, LocalDateTime horaSalida, LocalDateTime horaLlegada, String estado) {
        this.idVuelo = idVuelo;
        this.numeroVuelo = numeroVuelo;
        this.idCompania = idCompania;
        this.idAvion = idAvion;
        this.idPiloto = idPiloto;
        this.idPuertaEmbarque = idPuertaEmbarque;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.estado = estado;
    }

    /**
     * @return Identificador del vuelo.
     */
    public int getIdVuelo() {
        return idVuelo;
    }

    /**
     * @param idVuelo Identificador del vuelo.
     */
    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    /**
     * @return Número del vuelo.
     */
    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    /**
     * @param numeroVuelo Número del vuelo.
     */
    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    /**
     * @return Identificador de la compañía.
     */
    public int getIdCompania() {
        return idCompania;
    }

    /**
     * @param idCompania Identificador de la compañía.
     */
    public void setIdCompania(int idCompania) {
        this.idCompania = idCompania;
    }

    /**
     * @return Identificador del avión.
     */
    public int getIdAvion() {
        return idAvion;
    }

    /**
     * @param idAvion Identificador del avión.
     */
    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    /**
     * @return Identificador del piloto.
     */
    public int getIdPiloto() {
        return idPiloto;
    }

    /**
     * @param idPiloto Identificador del piloto.
     */
    public void setIdPiloto(int idPiloto) {
        this.idPiloto = idPiloto;
    }

    /**
     * @return Identificador de la puerta de embarque.
     */
    public int getIdPuertaEmbarque() {
        return idPuertaEmbarque;
    }

    /**
     * @param idPuertaEmbarque Identificador de la puerta de embarque.
     */
    public void setIdPuertaEmbarque(int idPuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
    }

    /**
     * @return Ciudad o aeropuerto de origen.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen Ciudad o aeropuerto de origen.
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return Ciudad o aeropuerto de destino.
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino Ciudad o aeropuerto de destino.
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return Fecha y hora de salida.
     */
    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida Fecha y hora de salida.
     */
    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return Fecha y hora de llegada.
     */
    public LocalDateTime getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * @param horaLlegada Fecha y hora estimada de llegada.
     */
    public void setHoraLlegada(LocalDateTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    /**
     * @return Estado actual del vuelo.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado Estado actual del vuelo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representacion en forma de cadena de texto del objeto Vuelo.
     *
     * @return String con la información detallada del vuelo.
     */
    @Override
    public String toString() {
        return "Vuelo{" +
                "idVuelo=" + idVuelo +
                ", numeroVuelo='" + numeroVuelo + '\'' +
                ", idCompania=" + idCompania +
                ", idAvion=" + idAvion +
                ", idPiloto=" + idPiloto +
                ", idPuertaEmbarque=" + idPuertaEmbarque +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", horaSalida=" + horaSalida +
                ", horaLlegada=" + horaLlegada +
                ", estado='" + estado + '\'' +
                '}';
    }
}
