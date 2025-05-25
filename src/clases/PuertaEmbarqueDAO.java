package clases;

import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>Clase PuertaEmbarqueDAO</h1>
 * Gestiona el acceso a datos relacionados con puertas de embarque,
 * incluyendo operaciones como obtención por ID, actualización
 * y obtención de vuelos asociados a una puerta específica.
 * Utiliza JDBC para conexión con la base de datos "Aeropuerto".
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class PuertaEmbarqueDAO {

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    /**
     * Constructor. Establece la conexión con la base de datos.
     */
    public PuertaEmbarqueDAO() {
        conexion = conectar();
    }

    /**
     * Establece la conexión con la base de datos utilizando JDBC.
     * 
     * @return Objeto Connection si la conexión fue exitosa; null si hubo error.
     */
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

    /**
     * Recupera una puerta de embarque desde la base de datos a partir de su ID.
     * 
     * @param id Identificador de la puerta de embarque.
     * @return Objeto PuertaEmbarque si se encuentra; null en caso contrario.
     */
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

    /**
     * Obtiene todos los vuelos que salen desde una puerta de embarque específica.
     * 
     * @param idPuerta Identificador de la puerta de embarque.
     * @return Lista de objetos Vuelo asociados a esa puerta.
     */
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

    /**
     * Actualiza los datos (número y terminal) de una puerta de embarque.
     * 
     * @param puerta Objeto PuertaEmbarque con los datos actualizados.
     * @return true si la actualización fue exitosa; false en caso contrario.
     */
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
