package clases;

import java.sql.*;

/**
 * <h1>Clase EmpleadoDAO</h1>
 * Esta clase se encarga de acceder a los datos relacionados con los empleados,
 * específicamente para verificar si un empleado es piloto y para obtener información
 * de pilotos a través de su ID.
 * Utiliza JDBC para conectar con la base de datos "Aeropuerto".
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class EmpleadoDAO {

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    /**
     * Constructor. Establece la conexión con la base de datos.
     */
    public EmpleadoDAO() {
        conexion = conectar();
    }

    /**
     * Conecta con la base de datos utilizando los parámetros definidos.
     * 
     * @return Objeto Connection conectado a la base de datos.
     */
    private Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
        try {
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD (EmpleadoDAO)");
        }
        return con;
    }

    /**
     * Verifica si un empleado con el ID dado es piloto.
     * 
     * @param idEmpleado Identificador del empleado.
     * @return true si el empleado es piloto, false en caso contrario.
     */
    public boolean esPiloto(int idEmpleado) {
        String sql = "SELECT cargo FROM Empleados WHERE id_empleado = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Piloto".equalsIgnoreCase(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el empleado es piloto.");
        }
        return false;
    }

    /**
     * Obtiene un objeto Empleado que representa a un piloto específico según su ID.
     * 
     * @param id Identificador del piloto.
     * @return Objeto Empleado si se encuentra y es piloto, null en caso contrario.
     */
    public Empleado obtenerPilotoPorId(int id) {
        Empleado piloto = null;
        String sql = "SELECT * FROM Empleados WHERE id_empleado = ? AND cargo = 'Piloto'";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                piloto = new Empleado(
                    rs.getInt("id_empleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("cargo"),
                    rs.getInt("id_compania"),
                    rs.getDate("fecha_contratacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piloto;
    }
}
