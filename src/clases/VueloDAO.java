package clases;

import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>Clase VueloDAO</h1>
 * Proporciona operaciones CRUD (Create, Read, Update, Delete) para la entidad Vuelo,
 * así como consultas adicionales hacia la base de datos del aeropuerto.
 * Utiliza JDBC para la conexión y ejecución de sentencias SQL.
 *
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class VueloDAO {

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto";

    /**
     * Constructor. Establece la conexión a la base de datos.
     */
    public VueloDAO() {
        conexion = conectar();
    }

    /**
     * Establece la conexión con la base de datos utilizando los parametros definidos.
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
     * Inserta un nuevo vuelo en la base de datos.
     *
     * @param vuelo Objeto Vuelo que se desea registrar.
     */
    public void create(Vuelo vuelo) {
        if (vuelo != null) {
            String sql = "INSERT INTO Vuelos(numero_vuelo, id_compania, id_avion, id_piloto, id_puerta_embarque, origen, destino, hora_salida, hora_llegada, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = conexion.prepareStatement(sql);
                stmt.setString(1, vuelo.getNumeroVuelo());
                stmt.setInt(2, vuelo.getIdCompania());
                stmt.setInt(3, vuelo.getIdAvion());
                stmt.setInt(4, vuelo.getIdPiloto());
                stmt.setInt(5, vuelo.getIdPuertaEmbarque());
                stmt.setString(6, vuelo.getOrigen());
                stmt.setString(7, vuelo.getDestino());
                stmt.setTimestamp(8, Timestamp.valueOf(vuelo.getHoraSalida()));
                stmt.setTimestamp(9, Timestamp.valueOf(vuelo.getHoraLlegada()));
                stmt.setString(10, vuelo.getEstado());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al insertar vuelo");
            }
        }
    }

    /**
     * Recupera un vuelo desde la base de datos mediante su identificador.
     *
     * @param id Identificador único del vuelo.
     * @return Objeto Vuelo correspondiente al ID o null si no existe.
     */
    public Vuelo read(int id) {
        Vuelo vuelo = null;
        String sql = "SELECT * FROM Vuelos WHERE id_vuelo=?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vuelo = new Vuelo(
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
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar vuelo");
        }
        return vuelo;
    }

    /**
     * Actualiza la informacion de un vuelo en la base de datos.
     *
     * @param vuelo Objeto Vuelo con los datos actualizados.
     */
    public void update(Vuelo vuelo) {
        if (vuelo != null) {
            String sql = "UPDATE Vuelos SET numero_vuelo=?, id_compania=?, id_avion=?, id_piloto=?, id_puerta_embarque=?, origen=?, destino=?, hora_salida=?, hora_llegada=?, estado=? WHERE id_vuelo=?";
            try {
                PreparedStatement stmt = conexion.prepareStatement(sql);
                stmt.setString(1, vuelo.getNumeroVuelo());
                stmt.setInt(2, vuelo.getIdCompania());
                stmt.setInt(3, vuelo.getIdAvion());
                stmt.setInt(4, vuelo.getIdPiloto());
                stmt.setInt(5, vuelo.getIdPuertaEmbarque());
                stmt.setString(6, vuelo.getOrigen());
                stmt.setString(7, vuelo.getDestino());
                stmt.setTimestamp(8, Timestamp.valueOf(vuelo.getHoraSalida()));
                stmt.setTimestamp(9, Timestamp.valueOf(vuelo.getHoraLlegada()));
                stmt.setString(10, vuelo.getEstado());
                stmt.setInt(11, vuelo.getIdVuelo());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar vuelo");
            }
        }
    }

    /**
     * Elimina un vuelo de la base de datos mediante su ID.
     *
     * @param id Identificador del vuelo a eliminar.
     */
    public void delete(int id) {
        String sql = "DELETE FROM Vuelos WHERE id_vuelo=?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar vuelo");
        }
    }

    /**
     * Recupera todos los vuelos almacenados en la base de datos.
     *
     * @return Lista de objetos Vuelo.
     */
    public ArrayList<Vuelo> listarVuelos() {
        ArrayList<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vuelos";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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
                lista.add(vuelo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar vuelos");
        }
        return lista;
    }

    /**
     * Recupera una lista de vuelos que tienen asignada una determinada puerta de embarque.
     *
     * @param idPuerta Identificador de la puerta de embarque.
     * @return Lista de vuelos asignados a esa puerta.
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
     * Actualiza el estado de un vuelo específico.
     *
     * @param idVuelo Identificador del vuelo a actualizar.
     * @param nuevoEstado Nuevo estado del vuelo.
     * @return true si el vuelo fue actualizado, false si no se realizó ningún cambio.
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

