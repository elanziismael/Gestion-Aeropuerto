package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuertaEmbarqueDAO {
	

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    public PuertaEmbarqueDAO() {
        conexion = conectar();
    }

    private Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
        try {
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD desde PuertaEmbarqueDAO");
        }
        return con;
    }

    public PuertaEmbarque obtenerPorId(int id) {
        PuertaEmbarque puerta = null;
        String sql = "SELECT * FROM PuertasEmbarque WHERE id_puerta_embarque = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                puerta = new PuertaEmbarque(
                    rs.getInt("id_puerta_embarque"),
                    rs.getString("numero_puerta"),
                    rs.getString("terminal")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar puerta de embarque");
        }

        return puerta;
    }

    public ArrayList<Vuelo> obtenerVuelosPorPuerta(int idPuerta) {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM Vuelos WHERE id_puerta_embarque = ?";

        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idPuerta);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vuelo v = new Vuelo(
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
                vuelos.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener vuelos por puerta");
        }

        return vuelos;
    }
    
    public boolean actualizarPuertaEmbarque(PuertaEmbarque puerta) {
        String sql = "UPDATE PuertasEmbarque SET numero_puerta = ?, terminal = ? WHERE id_puerta_embarque = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, puerta.getNumero());
            stmt.setString(2, puerta.getTerminal());
            stmt.setInt(3, puerta.getId());
            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar puerta de embarque: " + e.getMessage());
            return false;
        }
    }

}
