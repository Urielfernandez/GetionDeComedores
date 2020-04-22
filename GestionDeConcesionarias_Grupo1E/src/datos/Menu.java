package datos;
import java.util.*;
//clase que define el menu 
public class Menu {
private ArrayList <Plato> primerosDisponibles;
private ArrayList <Plato> segundossDisponibles;
private ArrayList <Plato> postresDisponibles;
private ArrayList <Bebida> bebidas;
private String diaSemana;
public Menu() {
	super();
	
}
public Menu(ArrayList<Plato> primerosDisponibles, ArrayList<Plato> segundossDisponibles,
		ArrayList<Plato> postresDisponibles, ArrayList<Bebida> bebidas, String diaSemana) {
	super();
	this.primerosDisponibles = primerosDisponibles;
	this.segundossDisponibles = segundossDisponibles;
	this.postresDisponibles = postresDisponibles;
	this.bebidas = bebidas;
	this.diaSemana = diaSemana;
}
public ArrayList<Plato> getPrimerosDisponibles() {
	return primerosDisponibles;
}
public void setPrimerosDisponibles(ArrayList<Plato> primerosDisponibles) {
	this.primerosDisponibles = primerosDisponibles;
}
public ArrayList<Plato> getSegundossDisponibles() {
	return segundossDisponibles;
}
public void setSegundossDisponibles(ArrayList<Plato> segundossDisponibles) {
	this.segundossDisponibles = segundossDisponibles;
}
public ArrayList<Plato> getPostresDisponibles() {
	return postresDisponibles;
}
public void setPostresDisponibles(ArrayList<Plato> postresDisponibles) {
	this.postresDisponibles = postresDisponibles;
}
public ArrayList<Bebida> getBebidas() {
	return bebidas;
}
public void setBebidas(ArrayList<Bebida> bebidas) {
	this.bebidas = bebidas;
}
public String getDiaSemana() {
	return diaSemana;
}
public void setDiaSemana(String diaSemana) {
	this.diaSemana = diaSemana;
}



}
