package estadisticas;

import java.util.ArrayList;
import java.util.HashMap;

import codigoPrincipal.IEstadisticas;
import codigoPrincipal.ISensores;
import datos.Bandeja;
import datos.Estadisticas;
import datos.Plato;



public class EstadisticasImpl implements IEstadisticas{
	public static final int plazasDisponibles=200;
	ISensores sensores;
	
	@Override
	public Estadisticas generarEstadisticas(ArrayList<Bandeja> valoraciones) {
		
		Estadisticas e= new Estadisticas();
		
		HashMap<Plato,Integer> primerosSolicitados=new HashMap<>();// key= plato value= num veces consumido
		HashMap<Plato,Integer> segundosSolicitados=new HashMap<>();//HashMaps para conocer el plato mas solicitado
		HashMap<Plato,Integer> postresSolicitados=new HashMap<>();
		Plato primero, segundo, postre;//para iterar
		
		Plato primeroMejorValorado = new Plato("",""), segundoMejorValorado= new Plato("",""), postreMejorValorado= new Plato("","");//variables para comparar
		primeroMejorValorado.setNotaMedia(0);//inicializacion a valor minimo para comparar maximos
		segundoMejorValorado.setNotaMedia(0);
		postreMejorValorado.setNotaMedia(0);
		Plato primeroPeorValorado = new Plato("",""), segundoPeorValorado= new Plato("",""), postrePeorValorado= new Plato("","");
		primeroPeorValorado.setNotaMedia(5);//inicializacion a valor maximo para comparar minimos
		segundoPeorValorado.setNotaMedia(5);
		postrePeorValorado.setNotaMedia(5);
		int valor=0;
		int nBandejasNoDevueltas=0; 
		
		//A�ADIR METODO A ISensores
		nBandejasNoDevueltas=sensores.numeroDeBandejasNoDevueltas();
		
		for(int i=0;i<valoraciones.size();i++) {//valoraciones
			primero=valoraciones.get(i).getPrimeroSeleccionado();//primero, segundo y postre para iterar
			segundo=valoraciones.get(i).getPrimeroSeleccionado();
			postre=valoraciones.get(i).getPrimeroSeleccionado();
			//primeros
				//valoraciones -> usando las funciones de comparar valoraciones
				primeroMejorValorado=compararPlatoMejorValorado(primero,primeroMejorValorado);
				primeroPeorValorado=compararPlatoPeorValorado(primero,primeroPeorValorado);
				//veces consumido
				if(primerosSolicitados.containsKey(primero)) {
					valor = primerosSolicitados.get(primero) + 1;//sumamos 1 a las veces que aparece dicho plato
					primerosSolicitados.put(primero,valor);
				}else primerosSolicitados.put(primero,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			
			
			//segundos
				segundoMejorValorado=compararPlatoMejorValorado(segundo,segundoMejorValorado);
				segundoPeorValorado=compararPlatoPeorValorado(segundo,segundoPeorValorado);
				//veces consumido
				if(segundosSolicitados.containsKey(segundo)) {
					valor = segundosSolicitados.get(segundo) + 1;//sumamos 1 a las veces que aparece dicho plato
					segundosSolicitados.put(segundo,valor);
				}else segundosSolicitados.put(segundo,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			
			//postres
				postreMejorValorado=compararPlatoMejorValorado(postre,postreMejorValorado);
				postrePeorValorado=compararPlatoPeorValorado(postre,postrePeorValorado);
				//veces consumido
				if(postresSolicitados.containsKey(postre)) {
					valor = postresSolicitados.get(postre) + 1;//sumamos 1 a las veces que aparece dicho plato
					postresSolicitados.put(postre,valor);
				}
				else
					postresSolicitados.put(postre,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			
		}//fin for
		
		//establecer los platos con los mejores y peores valores calculados
		e.setPrimeroMejorValorado(primeroMejorValorado);//mejores valorados
		e.setSegundoMejorValorado(segundoMejorValorado);
		e.setPostreMejorValorado(postreMejorValorado);
		e.setPrimeroPeorValorado(primeroPeorValorado);//peores valorados
		e.setSegundoPeorValorado(segundoPeorValorado);
		e.setPostrePeorValorado(postrePeorValorado);
		
		//establecer platos con maximas y minimas consumiciones
		e.setPrimeroMasSolicitado(platoMasSolicitado(primerosSolicitados)); //mas solicitado
		e.setSegundoMasSolicitado(platoMasSolicitado(segundosSolicitados));
		e.setPostreMasSolicitado(platoMasSolicitado(postresSolicitados));
		e.setPrimeroMenosSolicitado(platoMenosSolicitado(primerosSolicitados));//menos solicitado
		e.setSegundoMenosSolicitado(platoMenosSolicitado(segundosSolicitados));
		e.setPostreMenosSolicitado(platoMenosSolicitado(postresSolicitados));
		
		//grado ocupacion
		e.setGradoOcupacion(obtenerOcupacion(plazasDisponibles,nBandejasNoDevueltas));
		
		return e;
	}

	
	//metodos privados
	private Plato compararPlatoMejorValorado(Plato aComparar, Plato maxActual) {
		if((aComparar!=null)&&(maxActual!=null))//por si alguno de los valores es nulo
			if(aComparar.getNotaMedia() > maxActual.getNotaMedia())
				return aComparar;
			else return maxActual;
		else return null;
	}
	
	private Plato compararPlatoPeorValorado(Plato aComparar, Plato minActual) {
		if((aComparar!=null)&&(minActual!=null))//por si alguno de los valores es nulo
			if(aComparar.getNotaMedia() < minActual.getNotaMedia())
				return aComparar;
			else return minActual;
		else return null;
	}
	
	private Plato platoMasSolicitado(HashMap<Plato,Integer> mapa) {
		int maximo=0;//numero de solicitudes maximo
		Plato masSolicitado=null;
		
		if(mapa!=null)
			for(Plato p:mapa.keySet()) {
				if(mapa.get(p) > maximo) {
					maximo=mapa.get(p);//establecer numero maximo de veces que se repite el plato
					masSolicitado=p;   //establecer el plato mas solicitado asociado a la variable anterior
				}
					
			}
		return masSolicitado;
	}
	
	private Plato platoMenosSolicitado(HashMap<Plato,Integer> mapa) {
		int minimo=0;//numero de solicitudes minimo
		Plato menosSolicitado=null;
		
		if(mapa!=null)
			for(Plato p:mapa.keySet()) {
				if(mapa.get(p) < minimo) {
					minimo=mapa.get(p);//establecer numero minimo de veces que se repite el plato
					menosSolicitado=p;   //establecer el plato menos solicitado asociado a la variable anterior
				}
					
			}
		return menosSolicitado;
	}

	
	private float obtenerOcupacion(int nBandejas, int nBandejasNoDevueltas) {
		float n=0;
		n=nBandejasNoDevueltas/nBandejas;//en tanto por uno
		//n=n*100;//en tanto por ciento
		return n;
	}
}
