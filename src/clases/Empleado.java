package clases;

import java.sql.Date;

public class Empleado {
	
	private int idEmpleado;
    private String nombre;
    private String apellido;
    private String cargo;
    private int idCompania;
    private Date fechaContratacion;

    public Empleado(int idEmpleado, String nombre, String apellido, String cargo, int idCompania, java.sql.Date fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.idCompania = idCompania;
        this.fechaContratacion = fechaContratacion;
    }

    

    public int getIdEmpleado() {
		return idEmpleado;
	}



	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getCargo() {
		return cargo;
	}



	public void setCargo(String cargo) {
		this.cargo = cargo;
	}



	public int getIdCompania() {
		return idCompania;
	}



	public void setIdCompania(int idCompania) {
		this.idCompania = idCompania;
	}



	public java.sql.Date getFechaContratacion() {
		return fechaContratacion;
	}



	public void setFechaContratacion(java.sql.Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}



    @Override
    public String toString() {
        return idEmpleado + " - " + nombre + " " + apellido + " (" + cargo + ")";
    }

}
