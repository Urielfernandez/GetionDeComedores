package codigoPrincipal;

import java.util.*;


import datos.Bandeja;
import datos.Bebida;
import datos.Estadisticas;
import datos.Plato;
import estadisticas.EstadisticasImpl;
import gestionDatos.GestionDatosImpl;
import gestionMenus.GestionMenusImpl;
import pagosCualificaciones.PagosCualificacionesImpl;
import sensores.SensoresImpl;

public class Main {

	  public static void main(String args[]) {
		  /////////////////////////////////////
		  HashMap<String,Bandeja> listadoDeBandejas = new HashMap<>();
		  boolean condicionDeParada = false;
		  String caracterIntroducido = new String();
		  Scanner escaner = new Scanner(System.in);
		  IGestionDatos gestionDeDatos = new GestionDatosImpl();
		  IGestionMenus gestionDeMenus = new GestionMenusImpl(gestionDeDatos);
		  ISensores gestionSensores = new SensoresImpl();
		  IPagosCualificaciones gestionDePagosCualificaciones = new PagosCualificacionesImpl(gestionSensores);
		  IEstadisticas gestionEstadisticas = new EstadisticasImpl(gestionSensores);
			System.out.println("------------ Bienvenido a KomeComSA --------------");
			
			while(condicionDeParada != true) {
				////////////////////////////////////////////////
				//Generamos las estadísticas actualizadas
				Estadisticas estadisticas = gestionEstadisticas.generarEstadisticas(gestionDeDatos.recopilarDatosEstadisticas());
				////////////////////////////////////////////////
				System.out.println("Introduzca una de las opciones: ");
				System.out.println("a) Ver Menú del día");
				System.out.println("b) Seleccionar Platos");
				System.out.println("c) Ver Platos mejor valorado");
				System.out.println("d) Ver platos peor Valorado");
				System.out.println("e) Ver platos más seleccionado");
				System.out.println("f) Ver platos menos seleccionado");
				System.out.println("g) Consultar grado de ocupacion");
				System.out.println("--------------------------------------");
				System.out.println("k) Crear Menú.");
				System.out.println("---------------------------------------");
				System.out.println("j) Devolver Bandeja");
				System.out.println("----------------------------------------");
				System.out.println("q) Salir.");
				
				
				caracterIntroducido = escaner.nextLine();
				
				switch(caracterIntroducido) {
					case "a":
						System.out.println(gestionDeMenus.mostrarMenuDelDia().toString());
						break;
					case "b":
						System.out.println(gestionDeMenus.mostrarMenuDelDia().toString());
						System.out.println("Introduce tu primer plato:");
						String primerPlato = escaner.nextLine();
						
						
						System.out.println("Introduce tu segundo plato:");
						String segundoPlato = escaner.nextLine();
						
						System.out.println("Introduce tu postre:");
						String postre = escaner.nextLine();
						
						System.out.println("Selecciona una bebida:");
						String bebida = escaner.nextLine();
						
						Bandeja bandejaNueva = new Bandeja(new Plato(primerPlato,"primero"), new Plato(segundoPlato,"segundo"), new Plato(postre, "postre"), new Bebida(bebida));
						String codigoBandeja = gestionSensores.generarCodigoBandeja();
						bandejaNueva.setId(codigoBandeja);
						listadoDeBandejas.put(codigoBandeja, bandejaNueva);
						
						//Hacemos esto porque como no se completa el pago, damos el vale para el pago para que se introducido a posteriori.
						String vale = gestionSensores.generarCodigoVale();
						System.out.println(vale);
						bandejaNueva.setIdVale(vale);
						break;
					case "c":
						System.out.println("Los platos mejor valorados son: ");
						System.out.println("\t Primero-> " + estadisticas.getPrimeroMejorValorado().getNombre());
						System.out.println("\t Segundo-> " + estadisticas.getSegundoMejorValorado().getNombre());
						System.out.println("\t Postre-> " + estadisticas.getPostreMejorValorado().getNombre());
						break;
					case "d":
						System.out.println("Los platos peor valorados son: ");
						System.out.println("\t Primero-> " + estadisticas.getPrimeroPeorValorado().getNombre());
						System.out.println("\t Segundo-> " + estadisticas.getSegundoPeorValorado().getNombre());
						System.out.println("\t Postre-> " + estadisticas.getPostrePeorValorado().getNombre());
						break;
					case "e":
						System.out.println("Los platos más solicitados son: ");
						System.out.println("\t Primero-> " + estadisticas.getPrimeroMasSolicitado().getNombre());
						System.out.println("\t Segundo-> " + estadisticas.getSegundoMasSolicitado().getNombre());
						System.out.println("\t Postre-> " + estadisticas.getPostreMasSolicitado().getNombre());
						break;
					case "f":
						System.out.println("Los platos menos solicitados son: ");
						System.out.println("\t Primero-> " + estadisticas.getPrimeroMenosSolicitado().getNombre());
						System.out.println("\t Segundo-> " + estadisticas.getSegundoMenosSolicitado().getNombre());
						System.out.println("\t Postre-> " + estadisticas.getPostreMenosSolicitado().getNombre());
						break;
					case "g":
						System.out.println("El grado de ocupacion es de: " + estadisticas.getGradoOcupacion() + "%");
						System.out.println("Porque hay " + gestionSensores.numeroDeBandejasNoDevueltas() + " bandejas ocupadas.");
						break;
					case "k":
						ArrayList<Plato> primeros= new ArrayList<>();
						ArrayList<Plato> segundos= new ArrayList<>();
						ArrayList<Plato> postres= new ArrayList<>();
						ArrayList<Bebida> bebidas= new ArrayList<>();
						ArrayList<String> diasSemana=new ArrayList<>();
						diasSemana.add("Lunes");
						diasSemana.add("Martes");
						diasSemana.add("Miercoles");
						diasSemana.add("Jueves");
						diasSemana.add("Viernes");
						System.out.println("Introduce el dia de la semana para el menu (de Lunes a Viernes)");
						String dia= escaner.nextLine();
						dia = dia.substring(0,1).toUpperCase() + dia.substring(1).toLowerCase();
						if(!diasSemana.contains(dia)) {
							System.out.println("El dia de la semana introducido no es correcto");
							return;
						}
						System.out.println("Introduce los primeros platos");
						System.out.println("Plato 1:");
						Plato platoP1=new Plato("primero", escaner.nextLine());
						platoP1.setNotaMedia((float)2.5);
						primeros.add(platoP1);
						System.out.println("Plato 2:");
						Plato platoP2=new Plato("primero", escaner.nextLine());
						platoP2.setNotaMedia((float)2.5);
						primeros.add(platoP2);
						System.out.println("Plato 3:");
						Plato platoP3=new Plato("primero", escaner.nextLine());
						platoP3.setNotaMedia((float)2.5);
						primeros.add(platoP3);
						System.out.println("Introduce los segundos platos");
						System.out.println("Plato 1:");
						Plato platoS1=new Plato("segundo", escaner.nextLine());
						platoS1.setNotaMedia((float)2.5);
						segundos.add(platoS1);
						System.out.println("Plato 2:");
						Plato platoS2=new Plato("segundo", escaner.nextLine());
						platoS2.setNotaMedia((float)2.5);
						segundos.add(platoS2);
						System.out.println("Plato 3:");
						Plato platoS3=new Plato("segundo", escaner.nextLine());
						platoS3.setNotaMedia((float)2.5);
						segundos.add(platoS3);
						System.out.println("Introduce los postres");
						System.out.println("Postre 1:");
						Plato postre1=new Plato("postre", escaner.nextLine());
						postre1.setNotaMedia((float)2.5);
						postres.add(postre1);
						System.out.println("Postre 2:");
						Plato postre2=new Plato("postre", escaner.nextLine());
						postre2.setNotaMedia((float)2.5);
						postres.add(postre2);
						System.out.println("Postre 3:");
						Plato postre3=new Plato("postre", escaner.nextLine());
						postre3.setNotaMedia((float)2.5);
						postres.add(postre3);
						System.out.println("Introduce el numero de bebidas");
						int bebidasN = escaner.nextInt();
						escaner.nextLine();
						for(int i=0;i<bebidasN;i++) {
							System.out.println("Bebida "+(i+1)+": ");
							Bebida b=new Bebida(escaner.nextLine());
							bebidas.add(b);
						}
						gestionDeMenus.construirMenu(dia, primeros, segundos, postres, bebidas);
						
						break;
					case "j":
						System.out.println("Introduce el código de tu bandeja.");
						String codigo = escaner.nextLine();
						
						//simularia el pasar la bandeja por el lector
						if(listadoDeBandejas.containsKey(codigo)) {
							Bandeja bandejaDevuelta = listadoDeBandejas.get(codigo);
							//Solicito las valoraciones
							System.out.println("Introduce una nota para el primer plato.");
							Integer notaPrimero = escaner.nextInt();
							
							System.out.println("Introduce una nota para el segundo plato.");
							Integer notaSegundo = escaner.nextInt();
							
							System.out.println("Introduce una nota para el postre.");
							Integer notaPostre = escaner.nextInt();
							
							HashMap<Plato, Integer> notas = new HashMap<>();
							notas.put(bandejaDevuelta.getPrimeroSeleccionado(), notaPrimero);
							notas.put(bandejaDevuelta.getSegundoSeleccionado(), notaSegundo);
							notas.put(bandejaDevuelta.getPostreSeleccionado(), notaPostre);
							bandejaDevuelta.getEstadisticas().setValoracionesPlatos(notas);
							
							/////// Terminamos por devolver la bandeja
							gestionDePagosCualificaciones.devolverBandeja(bandejaDevuelta);
						}else {
							System.out.println("Dicha bandeja nop existe en el sistema.");
						}
						escaner.nextLine();
						break;
					case "q":
						condicionDeParada = true;
						break;
				}
			}
	  }
	 
}
 	