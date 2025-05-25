package clases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testAltaVuelos() {
		List<Vuelo> vuelos =  new ArrayList<>();
		Vuelo vuelo = new Vuelo(1, "IB123", 101, 201, 301, 401,
                "Madrid", "Barcelona",
                LocalDateTime.of(2025, 6, 1, 8, 30),
                LocalDateTime.of(2025, 6, 1, 9, 45),
                "Programado");
		
		assertFalse(vuelos.contains(vuelo)); 
		vuelos.add(vuelo); 
	    assertTrue(vuelos.contains(vuelo)); 
	}
	
	@Test
	void testBajaVuelo() {
	    List<Vuelo> vuelos = new ArrayList<>();
	    Vuelo vuelo = new Vuelo(1, "IB123", 101, 201, 301, 401,
	            "Madrid", "Barcelona",
	            LocalDateTime.of(2025, 6, 1, 8, 30),
	            LocalDateTime.of(2025, 6, 1, 9, 45),
	            "Programado");

	    vuelos.add(vuelo);
	    assertTrue(vuelos.contains(vuelo)); 
	    vuelos.remove(vuelo);               
	    assertFalse(vuelos.contains(vuelo)); 
	}
	
	@Test
	void testModificarVuelo() {
	    String numeroOriginal = "IB123";
	    Vuelo vuelo = new Vuelo(1, numeroOriginal, 101, 201, 301, 401,
	            "Madrid", "Barcelona",
	            LocalDateTime.of(2025, 6, 1, 8, 30),
	            LocalDateTime.of(2025, 6, 1, 9, 45),
	            "Programado");

	    assertEquals(vuelo.getNumeroVuelo(), numeroOriginal);

	    vuelo.setNumeroVuelo("IB999");
	    assertNotEquals(vuelo.getNumeroVuelo(), numeroOriginal);
	}
	
	
	@Test
	void testObtenerVueloPuertaEmbarque() {
	    PuertaEmbarque puerta = new PuertaEmbarque(401, "A5", "Terminal 1");

	    List<Vuelo> vuelos = new ArrayList<>();
	    Vuelo vuelo = new Vuelo(1, "IB123", 101, 201, 301, puerta.getId(),
	            "Madrid", "Barcelona",
	            LocalDateTime.of(2025, 6, 1, 8, 30),
	            LocalDateTime.of(2025, 6, 1, 9, 45),
	            "Programado");
	    vuelos.add(vuelo);
	   
	    assertNotNull(puerta);  
	    assertEquals(401, puerta.getId()); 
	    
	    assertFalse(vuelos.isEmpty()); 
	    for (Vuelo v : vuelos) {
	        assertEquals(puerta.getId(), v.getIdPuertaEmbarque());
	    }
	}

	@Test
	void testContarVuelosPorDestino() {

	    List<Vuelo> vuelos = new ArrayList<>();
	    vuelos.add(new Vuelo(1, "IB123", 101, 201, 301, 401,
	            "Madrid", "Barcelona",
	            LocalDateTime.of(2025, 6, 1, 8, 30),
	            LocalDateTime.of(2025, 6, 1, 9, 45),
	            "Programado"));

	    vuelos.add(new Vuelo(2, "IB124", 102, 202, 302, 402,
	            "Madrid", "Sevilla",
	            LocalDateTime.of(2025, 6, 2, 10, 0),
	            LocalDateTime.of(2025, 6, 2, 11, 15),
	            "Programado"));

	    vuelos.add(new Vuelo(3, "IB125", 103, 203, 303, 403,
	            "Madrid", "Barcelona",
	            LocalDateTime.of(2025, 6, 3, 12, 0),
	            LocalDateTime.of(2025, 6, 3, 13, 15),
	            "Programado"));

	    Map<String, Long> conteoPorDestino = vuelos.stream()
	            .collect(Collectors.groupingBy(
	                    Vuelo::getDestino,
	                    TreeMap::new,
	                    Collectors.counting()
	            ));

	    assertEquals(2, conteoPorDestino.get("Barcelona"));
	    assertEquals(1, conteoPorDestino.get("Sevilla"));
	    assertFalse(conteoPorDestino.containsKey("Valencia"));
	    assertEquals(2, conteoPorDestino.size());
	}


}

	