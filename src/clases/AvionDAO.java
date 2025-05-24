package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvionDAO {
	
	private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto"; 

    public AvionDAO() {
        conexion = conectar();
    }

    private Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
        try {
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD");
        }
        return con;
    }
    
    public Avion obtenerAvionPorId(int id) {
        Avion avion = null;
        String sql = "SELECT * FROM Aviones WHERE id_avion = ?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                avion = new Avion(
                    rs.getInt("id_avion"),
                    rs.getString("modelo"),
                    rs.getString("fabricante"),
                    rs.getInt("capacidad"),
                    rs.getInt("id_compania")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el avión: " + e.getMessage());
        }
        return avion;
    }
    
    public ArrayList<Vuelo> obtenerVuelosPorPuerta(int idPuerta) {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM Vuelos WHERE id_puerta_embarque = ?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idPuerta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vuelo vuelo = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("numero_vuelo"),
                    rs.getInt("id_compania"),
                    rs.getInt("id_avion"),
                    rs.getInt("id_piloto"),
                    rs.getInt("id_puerta_embarque"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getTimestamp("hora_salida").toLocalDateTime(),
                    rs.getTimestamp("hora_llegada").toLocalDateTime(),
                    rs.getString("estado")
                );
                vuelos.add(vuelo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener vuelos por puerta: " + ex.getMessage());
        }
        return vuelos;
    }
    
    public boolean actualizarEstadoVuelo(int idVuelo, String nuevoEstado) {
        String sql = "UPDATE Vuelos SET estado = ? WHERE id_vuelo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idVuelo);
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar estado del vuelo: " + ex.getMessage());
            return false;
        }
    }



}
