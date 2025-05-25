package clases;

import java.sql.Date;

/**
 * <h1>Clase Empleado</h1>
 * Representa a un empleado del sistema del aeropuerto, incluyendo información como
 * identificador, nombre, apellido, cargo, compañía a la que pertenece y fecha de contratación.
 * Esta clase se utiliza principalmente para representar pilotos y su relación con vuelos.
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class Empleado {

    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String cargo;
    private int idCompania;
    private Date fechaContratacion;

    /**
     * Constructor para la clase Empleado.
     *
     * @param idEmpleado Identificador del empleado.
     * @param nombre Nombre del empleado.
     * @param apellido Apellido del empleado.
     * @param cargo Cargo desempeñado (ej. Piloto, Mantenimiento).
     * @param idCompania ID de la compañía a la que pertenece.
     * @param fechaContratacion Fecha de contratación del empleado.
     */
    public Empleado(int idEmpleado, String nombre, String apellido, String cargo, int idCompania, Date fechaContratacion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.idCompania = idCompania;
        this.fechaContratacion = fechaContratacion;
    }

    /**
     * @return Identificador del empleado.
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado Establece el identificador del empleado.
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return Nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Establece el nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Apellido del empleado.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido Establece el apellido del empleado.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return Cargo del empleado (por ejemplo, Piloto).
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo Establece el cargo del empleado.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return Identificador de la compañía a la que pertenece el empleado.
     */
    public int getIdCompania() {
        return idCompania;
    }

    /**
     * @param idCompania Establece el identificador de la compañía.
     */
    public void setIdCompania(int idCompania) {
        this.idCompania = idCompania;
    }

    /**
     * @return Fecha en que fue contratado el empleado.
     */
    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    /**
     * @param fechaContratacion Establece la fecha de contratación.
     */
    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    /**
     * Devuelve una representación en forma de cadena del empleado.
     * 
     * @return Cadena con el ID, nombre completo y cargo del empleado.
     */
    @Override
    public String toString() {
        return idEmpleado + " - " + nombre + " " + apellido + " (" + cargo + ")";
    }
}
