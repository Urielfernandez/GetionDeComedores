package estadisticas;

import java.util.ArrayList;
import java.util.HashMap;

import codigoPrincipal.IEstadisticas;
import datos.Estadisticas;
import datos.Plato;

public class estadisticasImpl implements IEstadisticas{

	@Override
	public Estadisticas generarEstadisticas(ArrayList<Plato> valoraciones) {
		//ocupacion no se puede calcular en esta funcion
		
		Estadisticas e= new Estadisticas();//necesitaría un constructor de estadisticas para inicializar los valores con los que
										   //voy a hacer comparaciones
		HashMap<Plato,Integer> primerosSolicitados=new HashMap<>();// key= plato value= num veces consumido
		HashMap<Plato,Integer> segundosSolicitados=new HashMap<>();
		HashMap<Plato,Integer> postresSolicitados=new HashMap<>();
		Plato aux;
		int valor=0;
		

		for(int i=0;i<valoraciones.size();i++) {//valoraciones
			aux=valoraciones.get(i);
			//primeros
			if(aux.getTipo().compareTo("primero")==0) {
				//valoraciones
				if(aux.getNotaMedia() > e.getPrimeroMejorValorado().getNotaMedia())
					e.setPrimeroMejorValorado(aux);
				else if(aux.getNotaMedia() < e.getPrimeroPeorValorado().getNotaMedia())//else-> suponiendo que el mejora valorado != peor valorado
					e.setPrimeroPeorValorado(aux);									   //si contemplamos esto como posible -> quitar el else
				//veces consumido
				if(primerosSolicitados.containsKey(aux)) {
					valor = primerosSolicitados.get(aux) + 1;//sumamos 1 a las veces que aparece dicho plato
					primerosSolicitados.put(aux,valor);
				}
				else
					primerosSolicitados.put(aux,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			}
			
			//segundos
			if(aux.getTipo().compareTo("segundo")==0) {
				//valoraciones
				if(aux.getNotaMedia() > e.getSegundoMejorValorado().getNotaMedia())
					e.setSegundoMejorValorado(aux);
				else if(aux.getNotaMedia() < e.getSegundoPeorValorado().getNotaMedia())
					e.setSegundoPeorValorado(aux);
				//veces consumido
				if(segundosSolicitados.containsKey(aux)) {
					valor = segundosSolicitados.get(aux) + 1;//sumamos 1 a las veces que aparece dicho plato
					segundosSolicitados.put(aux,valor);
				}
				else
					segundosSolicitados.put(aux,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			}
			//segundos
			if(aux.getTipo().compareTo("postre")==0) {
				//valoraciones
				if(aux.getNotaMedia() > e.getPostreMejorValorado().getNotaMedia())
					e.setPostreMejorValorado(aux);
				else if(aux.getNotaMedia() < e.getPostrePeorValorado().getNotaMedia())
					e.setPostrePeorValorado(aux);
				//veces consumido
				if(postresSolicitados.containsKey(aux)) {
					valor = postresSolicitados.get(aux) + 1;//sumamos 1 a las veces que aparece dicho plato
					postresSolicitados.put(aux,valor);
				}
				else
					postresSolicitados.put(aux,1);//sino lo metemos con 1 como su valor de veces que se ha consumido
			}
			
		}//fin for
		
		//determinar maximas y minimas consumiciones
		int maxPrimeros=0, minPrimeros=100000;
		int maxSegundos=0, minSegundos=100000;
		int maxPostres=0, minPostres=100000;
		
		for(Plato p:primerosSolicitados.keySet()) {//PRIMEROS
			if(primerosSolicitados.get(p) > maxPrimeros)
				e.setPrimeroMasSolicitado(p);
			else if(primerosSolicitados.get(p) < minPrimeros)//suponiendo que el mas y menos solicitado no son el mismo
				e.setPrimeroMenosSolicitado(p);
		}
		for(Plato p:segundosSolicitados.keySet()) {//SEGUNDOS
			if(segundosSolicitados.get(p) > maxPrimeros)
				e.setSegundoMasSolicitado(p);
			else if(segundosSolicitados.get(p) < minPrimeros)//suponiendo que el mas y menos solicitado no son el mismo
				e.setSegundoMenosSolicitado(p);
		}
		for(Plato p:postresSolicitados.keySet()) {//POSTRES
			if(postresSolicitados.get(p) > maxPrimeros)
				e.setPostreMasSolicitado(p);
			else if(postresSolicitados.get(p) < minPrimeros)//suponiendo que el mas y menos solicitado no son el mismo
				e.setPostreMenosSolicitado(p);
		}
		
		return e;
	}

	@Override
	public Plato seleccionarPlatoMasSolicitado(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plato seleccionarPlatoMenosSolicitado(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plato seleccionarPlatoMejorValorado(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plato seleccionarPlatoPeorValorado(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float obtenerOcupacion(int nBandejas, int nBandejasDevueltas) {
		float n=0;
		n=(nBandejas-nBandejasDevueltas)/nBandejas;//en tanto por uno
		//n=n*100;//en tanto por ciento
		return n;
	}

}
