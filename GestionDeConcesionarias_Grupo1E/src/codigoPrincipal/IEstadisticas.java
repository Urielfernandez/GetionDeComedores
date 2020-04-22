package codigoPrincipal;
import datos.*;
import java.util.*;
public interface IEstadisticas {
	
 public Estadisticas generarEstadisticas(ArrayList<Integer> valoraciones);
 public Plato seleccionarPlatoMasSolicitado(String tipo);
 public Plato seleccionarPlatoMenosSolicitado(String tipo);
 public Plato seleccionarPlatoMejorValorado(String tipo);
 public Plato seleccionarPlatoPeorValorado(String tipo);
 public float obtenerOcupacion(int nBandejas, int nBandejasDevueltas);


}
