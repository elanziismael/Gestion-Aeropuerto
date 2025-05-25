package clases;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Clase Main</h1>
 * Clase principal del sistema de gestión de vuelos.
 * Presenta un menú interactivo en consola para realizar diversas operaciones como listar, agregar,
 * filtrar, actualizar y eliminar vuelos.
 * Utiliza DAO para acceso a datos de vuelos, empleados, aviones y puertas de embarque.
 * 
 * @author elanz
 * @version 1.0
 * @since 2025-05-24
 */
public class Main {
	

    private static final Scanner scanner = new Scanner(System.in);
    private static final VueloDAO VueloDao = new VueloDAO();
    private static final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private static final PuertaEmbarqueDAO puertaDAO = new PuertaEmbarqueDAO();

    /**
     * Método principal que lanza la aplicación del menú de gestión de vuelos.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: mostrarVuelos(); break;
                case 2: agregarVuelo(); break;
                case 3: ObtenerVueloPuertEmbarque(); break;
                case 4: filtrarVuelosPorDestino(); break;
                case 5: contarVuelosPorDestino(); break;
                case 6: mostrarPilotosYAviones(); break;
                case 7: actualizarPuertaYCancelarVuelosSevilla(); break;
                case 8: eliminarVuelo(); break;
                case 9: System.out.println("saliendo..."); break;
                default:
                    System.out.println("Opción incorrecta. Intente nuevamente.");
            }
        } while (opcion != 9);

        scanner.close();
    }

    /**
     * Muestra el menú de opciones disponibles en consola.
     */
    private static void mostrarMenu() {
        System.out.println("--- Menú Vuelos ---");
        System.out.println("1. Ver datos de vuelos");
        System.out.println("2. Agregar un vuelo");
        System.out.println("3. Vuelos según puerta de embarque");
        System.out.println("4. Filtrar vuelos por ciudad destino");
        System.out.println("5. Contar cantidad de vuelos por destino");
        System.out.println("6. Mostrar aviones que ha pilotado cada piloto");
        System.out.println("7. Actualizar la Puerta Y Cancelar Vuelos Sevilla");
        System.out.println("8. Eliminar un vuelo");
        System.out.println("9. Salir");
        System.out.print("Ingrese opción: ");
    }

    /**
     * Muestra todos los vuelos almacenados en la base de datos.
     */
    private static void mostrarVuelos() {
        ArrayList<Vuelo> vuelos = VueloDao.listarVuelos();
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados en la base de datos.");
        } else {
            System.out.println("Lista de vuelos:");
            for (Vuelo v : vuelos) {
                System.out.println(v);
            }
        }
    }

    /**
     * Agrega un nuevo vuelo a la base de datos mediante datos introducidos por el usuario.
     */
    private static void agregarVuelo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            System.out.println("Ingrese número de vuelo:");
            String numeroVuelo = scanner.nextLine();

            System.out.println("Ingrese id compañía (entero):");
            int idCompania = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese id avión (entero):");
            int idAvion = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese id piloto (entero):");
            int idPiloto = Integer.parseInt(scanner.nextLine());

            if (!empleadoDAO.esPiloto(idPiloto)) {
                System.out.println("Error: el ID del empleado no corresponde a un piloto.");
                return;
            }

            System.out.println("Ingrese id puerta embarque (entero):");
            int idPuerta = Integer.parseInt(scanner.nextLine());

            System.out.println("Ingrese origen:");
            String origen = scanner.nextLine();

            System.out.println("Ingrese destino:");
            String destino = scanner.nextLine();

            System.out.println("Ingrese hora de salida (formato yyyy-MM-dd HH:mm):");
            LocalDateTime horaSalida = LocalDateTime.parse(scanner.nextLine(), formatter);

            System.out.println("Ingrese hora de llegada (formato yyyy-MM-dd HH:mm):");
            LocalDateTime horaLlegada = LocalDateTime.parse(scanner.nextLine(), formatter);

            String estado = "Correcto";

            Vuelo nuevoVuelo = new Vuelo(0, numeroVuelo, idCompania, idAvion, idPiloto, idPuerta, origen, destino, horaSalida, horaLlegada, estado);
            VueloDao.create(nuevoVuelo);
            System.out.println("Vuelo agregado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al agregar vuelo. Asegúrese de ingresar datos válidos.");
        }
    }

    /**
     * Muestra los vuelos asignados a una puerta de embarque específica.
     */
    private static void ObtenerVueloPuertEmbarque() {
        System.out.println("Ingrese ID de la puerta de embarque:");
        int idPuerta = Integer.parseInt(scanner.nextLine());

        PuertaEmbarque puerta = puertaDAO.obtenerPorId(idPuerta);
        if (puerta == null) {
            System.out.println("No se encontró la puerta de embarque con ese ID.");
            return;
        }

        System.out.println("Datos de la puerta:");
        System.out.println(puerta);

        ArrayList<Vuelo> vuelos = puertaDAO.obtenerVuelosPorPuerta(idPuerta);
        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados desde esta puerta.");
        } else {
            System.out.println("Vuelos que salen desde esta puerta:");
            for (Vuelo v : vuelos) {
                System.out.println(v);
            }
        }
    }

    /**
     * Filtra y muestra vuelos que tienen como destino una ciudad ingresada por el usuario.
     */
    private static void filtrarVuelosPorDestino() {
        ArrayList<Vuelo> vuelos = VueloDao.listarVuelos();
        Set<String> destinosUnicos = vuelos.stream()
            .map(Vuelo::getDestino)
            .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Ciudades disponibles como destino:");
        destinosUnicos.forEach(System.out::println);

        System.out.print("Escriba el nombre de la ciudad destino a filtrar: ");
        String ciudad = scanner.nextLine().trim();

        List<Vuelo> vuelosFiltrados = vuelos.stream()
            .filter(v -> v.getDestino().equalsIgnoreCase(ciudad))
            .sorted(Comparator.comparing(Vuelo::getHoraSalida))
            .collect(Collectors.toList());

        if (vuelosFiltrados.isEmpty()) {
            System.out.println("No se encontraron vuelos con destino a " + ciudad);
        } else {
            System.out.println("Vuelos con destino a " + ciudad + ":");
            vuelosFiltrados.forEach(System.out::println);
        }
    }

    /**
     * Muestra la cantidad de vuelos agrupados por ciudad de destino.
     */
    private static void contarVuelosPorDestino() {
        ArrayList<Vuelo> vuelos = VueloDao.listarVuelos();
        Map<String, Long> conteoPorDestino = vuelos.stream()
            .collect(Collectors.groupingBy(
                Vuelo::getDestino,
                TreeMap::new,
                Collectors.counting()
            ));

        System.out.println("Cantidad de vuelos por ciudad de destino:");
        conteoPorDestino.forEach((ciudad, cantidad) ->
            System.out.println(ciudad + ": " + cantidad + " vuelo(s)")
        );
    }

    /**
     * Muestra los pilotos y los aviones que han pilotado, sin repetir aviones.
     */
    private static void mostrarPilotosYAviones() {
        AvionDAO avionDAO = new AvionDAO();
        ArrayList<Vuelo> vuelos = VueloDao.listarVuelos();
        Map<Integer, List<Integer>> pilotoAvionesMap = new HashMap<>();

        for (Vuelo v : vuelos) {
            int idPiloto = v.getIdPiloto();
            int idAvion = v.getIdAvion();

            pilotoAvionesMap
                .computeIfAbsent(idPiloto, k -> new ArrayList<>())
                .add(idAvion);
        }

        for (Map.Entry<Integer, List<Integer>> entry : pilotoAvionesMap.entrySet()) {
            Empleado piloto = empleadoDAO.obtenerPilotoPorId(entry.getKey());

            if (piloto != null) {
                System.out.println("Piloto: " + piloto.getNombre() + " " + piloto.getApellido());
                System.out.println("Aviones que ha pilotado:");
                entry.getValue().stream().distinct().forEach(idAvion -> {
                    Avion avion = avionDAO.obtenerAvionPorId(idAvion);
                    if (avion != null) {
                        System.out.println(" - " + avion);
                    }
                });
                System.out.println();
            }
        }
    }

    /**
     * Actualiza datos de una puerta de embarque y cancela vuelos con destino Sevilla asignados a ella.
     */
    private static void actualizarPuertaYCancelarVuelosSevilla() {
        System.out.print("Ingrese ID de la puerta de embarque a actualizar: ");
        int idPuerta = Integer.parseInt(scanner.nextLine());

        PuertaEmbarque puerta = puertaDAO.obtenerPorId(idPuerta);
        if (puerta == null) {
            System.out.println("No existe una puerta con ese ID.");
            return;
        }

        System.out.println("Puerta actual: " + puerta);
        System.out.print("Ingrese nuevo número de puerta (deje vacío para no cambiar): ");
        String nuevoNumero = scanner.nextLine().trim();
        if (!nuevoNumero.isEmpty()) puerta.setNumero(nuevoNumero);

        System.out.print("Ingrese nuevo terminal (deje vacío para no cambiar): ");
        String nuevoTerminal = scanner.nextLine().trim();
        if (!nuevoTerminal.isEmpty()) puerta.setTerminal(nuevoTerminal);

        if (!puertaDAO.actualizarPuertaEmbarque(puerta)) {
            System.out.println("Error al actualizar la puerta de embarque.");
            return;
        }
        System.out.println("Puerta actualizada correctamente.");

        ArrayList<Vuelo> vuelos = VueloDao.obtenerVuelosPorPuerta(idPuerta);
        List<Vuelo> vuelosSevilla = vuelos.stream()
            .filter(v -> v.getDestino().equalsIgnoreCase("Sevilla"))
            .collect(Collectors.toList());

        for (Vuelo v : vuelosSevilla) {
            boolean exito = VueloDao.actualizarEstadoVuelo(v.getIdVuelo(), "Cancelado");
            System.out.println(exito ?
                "Vuelo " + v.getNumeroVuelo() + " cancelado." :
                "Error al cancelar vuelo " + v.getNumeroVuelo());
        }
    }

    /**
     * Elimina un vuelo de la base de datos a partir de su ID ingresado por el usuario.
     */
    private static void eliminarVuelo() {
        try {
            System.out.print("Ingrese el ID del vuelo a eliminar: ");
            int idVuelo = Integer.parseInt(scanner.nextLine());

            Vuelo vuelo = VueloDao.read(idVuelo);
            if (vuelo == null) {
                System.out.println("No existe un vuelo con ese ID.");
                return;
            }

            VueloDao.delete(idVuelo);
            System.out.println("Vuelo eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor ingrese un número entero.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar eliminar el vuelo.");
        }
    }
}

