package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {
	
	private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    public EmpleadoDAO() {
        conexion = conectar();
    }

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
