package codigoPrincipal;

import sensores.SensoresImpl;

public class Main {

	  public static void main(String args[]) {
			System.out.println("------------ Bienvenido a KomeComSA --------------");
			
			//Prueba c�digo de PagosCualificaciones
			
			
			//Prueba c�digo de Sensores
			ISensores sensor = new SensoresImpl();
			System.out.println(sensor.generarCodigoVale());
			String aux = sensor.generarCodigoVale();
			System.out.println(aux);
			
			System.out.println(sensor.canjearVale(aux));
	  }
}
 	