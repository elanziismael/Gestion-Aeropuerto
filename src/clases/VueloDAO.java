package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class VueloDAO {
	
	private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "Aeropuerto"; 

    public VueloDAO() {
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
                stmt.setInt(11, vuelo.getIdAvion());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar vuelo");
            }
        }
    }

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
