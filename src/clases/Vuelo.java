package clases;

import java.time.LocalDateTime;

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


	public int getIdVuelo() {
		return idVuelo;
	}


	public void setIdVuelo(int idVuelo) {
		this.idVuelo = idVuelo;
	}


	public String getNumeroVuelo() {
		return numeroVuelo;
	}


	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}


	public int getIdCompania() {
		return idCompania;
	}


	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
	}


	public int getIdAvion() {
		return idAvion;
	}


	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}


	public int getIdPiloto() {
		return idPiloto;
	}


	public void setIdPiloto(int idPiloto) {
		this.idPiloto = idPiloto;
	}


	public int getIdPuertaEmbarque() {
		return idPuertaEmbarque;
	}


	public void setIdPuertaEmbarque(int idPuertaEmbarque) {
		this.idPuertaEmbarque = idPuertaEmbarque;
	}


	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}


	public LocalDateTime getHoraLlegada() {
		return horaLlegada;
	}


	public void setHoraLlegada(LocalDateTime horaLlegada) {
		this.horaLlegada = horaLlegada;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
    
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
