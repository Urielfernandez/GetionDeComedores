package pagosCualificaciones;

import java.util.HashMap;
import java.util.Scanner;

import codigoPrincipal.IGestionDatos;
import codigoPrincipal.IPagosCualificaciones;
import codigoPrincipal.ISensores;
import datos.Bandeja;
import datos.Plato;
import gestionDatos.GestionDatosImpl;


public class PagosCualificacionesImpl implements IPagosCualificaciones{
	private ISensores conexionSensor; //Para la generacion y lectura de codigos.
	private IGestionDatos conexionManipuladorDeDatos; //Para la lectura-escritura de los datos
	private HashMap<String,Bandeja> historialDeBandejas;
	
	public PagosCualificacionesImpl(ISensores conexionSensores) {
		this.conexionSensor = conexionSensores;
		this.historialDeBandejas = new HashMap<>();
		this.conexionManipuladorDeDatos = new GestionDatosImpl();
	}
	
	@Override
	public Bandeja pagarMenu(Bandeja bandeja) {
		/*Como en el primer incremento no tratamos el pago de menús,
		 *  lo único que realizamos es la asignacion de una bandeja a nuestro usuario
		 */
		//Generamos un vale para simular el pago
		this.conexionSensor.generarCodigoVale();
		
		//Le solicitamos al usuario que lo introduzca
		System.out.println("Por favor, introduce un vale para realizar el pago.");
		Scanner escaner = new Scanner("System.in");
		String valeAux = escaner.nextLine();
		
		if(this.conexionSensor.canjearVale(valeAux)) {
			Bandeja bandejaDelUsuario = new Bandeja(this.conexionSensor.generarCodigoBandeja(), this.conexionSensor.generarCodigoVale(), bandeja.getPrimeroSeleccionado(), bandeja.getSegundoSeleccionado(), bandeja.getPostreSeleccionado(), bandeja.getBebidaSeleccionada());
			
			//Introducimos la bandeja en el historial para poder tener cuenta de las que estamos entregando a los usuarios, y realizar las comprobaciones necesarias cuando estas son devueltas
			this.historialDeBandejas.put(bandejaDelUsuario.getId(), bandejaDelUsuario);
			
			return bandejaDelUsuario;
		}
		
		return null;
	}

	@Override
	public void guardarValoracion(Bandeja bandeja){
		//Recurrimos al sistema que se encarga de trabajar con los archivos y le pasamos la valoracion
		HashMap<Plato,Integer> valoraciones = bandeja.getEstadisticas().getValoracionesPlatos();
		Integer nota = 0;
		String entradaPorTeclado= new String();
		Scanner entradaEscaner = new Scanner("System.in");
		
		//Comprobamos que el conjunto de valoraciones ha sido creado
		if(valoraciones == null) {
			valoraciones = new HashMap<>();
		}
		
		//Solicitamos la nota para el primer plato
		System.out.println("Introduce una valoración para el primer plato (Mínimo: 0 - Máximo: 5)");
		entradaPorTeclado = entradaEscaner.nextLine();
		nota = Integer.parseInt(entradaPorTeclado) % 5;
		
		if(!valoraciones.containsKey(bandeja.getPrimeroSeleccionado())) {
			valoraciones.put(bandeja.getPrimeroSeleccionado(), nota);
		}
		
		//Solicitamos la nota para el segundo plato
		System.out.println("Introduce una valoración para el segundo plato (Mínimo: 0 - Máximo: 5)");
		entradaPorTeclado = entradaEscaner.nextLine();
		nota = Integer.parseInt(entradaPorTeclado) % 5;
		
		if(!valoraciones.containsKey(bandeja.getSegundoSeleccionado())) {
			valoraciones.put(bandeja.getSegundoSeleccionado(), nota);
		}
		
		//Solicitamos la nota para el postre plato
		System.out.println("Introduce una valoración para el postre (Mínimo: 0 - Máximo: 5)");
		entradaPorTeclado = entradaEscaner.nextLine();
		nota = Integer.parseInt(entradaPorTeclado) % 5;
		
		if(!valoraciones.containsKey(bandeja.getPostreSeleccionado())) {
			valoraciones.put(bandeja.getPostreSeleccionado(), nota);
		}
		
		//Una vez introducidas todas las valoraciones, las almacenamos en la bandeja para luego almacenarlas en los archivos
		bandeja.getEstadisticas().setValoracionesPlatos(valoraciones);
		
		//Le pasamos la bandeja a la funcion encargada de almacenar dicha bandeja
		this.conexionManipuladorDeDatos.almacenarValoracion(bandeja);
	}

	@Override
	public String devolverBandeja(Bandeja bandeja) {
		if(this.historialDeBandejas.containsKey(bandeja.getId())) {
			this.conexionManipuladorDeDatos.almacenarValoracion(bandeja);
			return this.conexionSensor.generarCodigoBandejaDevuelta(bandeja);
		}
		
		return null;
	}
}
