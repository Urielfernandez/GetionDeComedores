package Sensores;

import java.util.ArrayList;

import codigoPrincipal.ISensores;

public class SensoresImpl implements ISensores{
	private ArrayList<String> valesGenerados; //Para llevar el control de los vales que han sido generados por el sistema
	private int generadorDeVales; //dígito para generar el identificador de los vales, ya que tienen la forma VXXXXXXXX
	private ArrayList<String> bandejasEntregadas;
	private int generadorDeBandejas;
	
	public SensoresImpl() {
		this.valesGenerados = new ArrayList<>();
		this.generadorDeVales = 0;
		this.bandejasEntregadas = new ArrayList<>();
		this.generadorDeBandejas = 0;
	}
	
	@Override
	public String generarCodigoVale() {
		String valeGenerado = new String();
		
		if(this.generadorDeVales >= 999999999) {
			this.generadorDeVales = 0;
		}
		
		valeGenerado = "V"+Integer.toString(this.generadorDeVales);
		
		while(this.valesGenerados.contains(valeGenerado)) {
			this.generadorDeVales++;
			valeGenerado = "V"+Integer.toString(this.generadorDeVales);
		}
		
		this.generadorDeVales++;
		this.valesGenerados.add(valeGenerado);
		
		return valeGenerado;
	}

	@Override
	public String canjearVale() {
		
		return null;
	}

	@Override
	public String generarCodigoBandeja() {
		String bandejaGenerada = new String();
		
		if(this.generadorDeBandejas >= 999999999) {
			this.generadorDeBandejas = 0;
		}
		
		bandejaGenerada = "V"+Integer.toString(this.generadorDeBandejas);
		
		while(this.bandejasEntregadas.contains(bandejaGenerada)) {
			this.generadorDeBandejas++;
			bandejaGenerada = "V"+Integer.toString(this.generadorDeBandejas);
		}
		
		this.generadorDeBandejas++;
		this.bandejasEntregadas.add(bandejaGenerada);
		
		return bandejaGenerada;
	}

	@Override
	public String generarCodigoBandejaDevuelta() {
		// TODO Auto-generated method stub
		return null;
	}

}
