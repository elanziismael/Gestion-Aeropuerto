package clases;

import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>Clase AvionDAO</h1>
 * Esta clase permite el acceso a los datos relacionados con aviones,
 * incluyendo su obtención por ID, vuelos asociados a una puerta de embarque
 * y la actualización del estado de un vuelo.
 * Utiliza JDBC para la conexión con la base de datos "Aeropuerto".
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class AvionDAO {

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    /**
     * Constructor. Inicializa la conexión con la base de datos.
     */
    public AvionDAO() {
        conexion = conectar();
    }

    /**
     * Establece la conexión con la base de datos utilizando JDBC.
     *
     * @return Objeto Connection conectado a la base de datos.
     */
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

    /**
     * Obtiene un objeto Avion desde la base de datos a partir de su ID.
     *
     * @param id Identificador del avión.
     * @return Objeto Avion si se encuentra, o null en caso contrario.
     */
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

    /**
     * Obtiene una lista de vuelos que tienen asignada una puerta de embarque específica.
     *
     * @param idPuerta ID de la puerta de embarque.
     * @return Lista de objetos Vuelo asociados a la puerta.
     */
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

    /**
     * Actualiza el estado de un vuelo en la base de datos.
     *
     * @param idVuelo ID del vuelo a actualizar.
     * @param nuevoEstado Nuevo estado del vuelo (ej: Cancelado, Retrasado).
     * @return true si la actualización fue exitosa; false en caso contrario.
     */
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

