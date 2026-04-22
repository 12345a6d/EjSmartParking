package modelo.gestoresplazas;

import java.time.LocalDateTime;
import java.util.Arrays;

import list.IList;
import list.ArrayList;
import modelo.gestoresplazas.huecos.GestorHuecos;
import modelo.gestoresplazas.huecos.Hueco;
import modelo.gestoresplazas.huecos.Plaza;
import modelo.reservas.solicitudesreservas.SolicitudReservaAnticipada;

public class GestorZona {
	private int iZona;
	private int jZona;
	private Plaza[] plazas;
	private double precio;
	private IList<SolicitudReservaAnticipada> listaEspera;
	private GestorHuecos gestorHuecos;
	private IList<Hueco> huecosReservados;
	
	public int getI() {
		return iZona;
	}
	
	public int getJ() {
		return jZona;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public String getId() {
		return "z" + iZona + ":" + jZona;
	}
	
	public String getEstadoHuecosLibres() {
		return this.gestorHuecos.toString();
	}
	
	public String getEstadoHuecosReservados() {
		return this.huecosReservados.toString();
	}
	
	public String getListaEspera() {
		return this.listaEspera.toString();
	}
	
	public String getPlazas() {
		return Arrays.toString(this.plazas);
	}
	
	public String toString() {
		return getId() + ": " + getEstadoHuecosReservados();
	}
	
	//TO-DO alumno obligatorios
	
	public GestorZona(int i, int j, int noPlazas, double precio) {
	this.iZona = i;
	this.jZona = j;
	this.plazas = new Plaza [noPlazas];
	this.precio = precio;
	listaEspera = new ArrayList <>();
	gestorHuecos = new GestorHuecos (plazas);
	huecosReservados = new ArrayList <>();
	}
	
	public Hueco reservarHueco(LocalDateTime tI, LocalDateTime tF) {
		if(gestorHuecos.existeHueco(tI,tF) == true) {//Comprobamos que existe el hueco asociado a la solicitud existe
	
			gestorHuecos.reservarHueco(tI, tF);		 // Reservamos el hueco.
			
			huecosReservados.add(huecosReservados.size(), gestorHuecos.reservarHueco(tI, tF)); //Añadimos a la lista de huecos reservados el hueco reservado.
		}
	return 	gestorHuecos.reservarHueco(tI, tF); //Devolvemos el hueco que ha sido reservado.
	}
	public boolean existeHueco(LocalDateTime tI, LocalDateTime tF) {
	return gestorHuecos.existeHueco(tI, tF);
	}
	
	public boolean existeHueco(LocalDateTime tI, LocalDateTime tF) {
	return gestorHuecos.existeHueco(tI, tF);
	}
	
	
		public void meterEnListaEspera(SolicitudReservaAnticipada solicitud) {
	for (int i = 0; i<listaEspera.size(); i++) { 			// Recorremos el arrayList listaEspera para ver dónde se introduce la solicitud.
		if (listaEspera.get(i).getPrioridad().compareTo(solicitud.getPrioridad())<0){		//compareTo>0 implica que va después en prioridad.
			listaEspera.add(i, solicitud);													// Se añade en la posición 4 que sería debajo del elemento listaEspera.get(4) cuando i es 3
		}
		else if (listaEspera.get(i).getPrioridad().compareTo(solicitud.getPrioridad())==0) { // Si son iguales, entonces vemos las fechas finales
			if (listaEspera.get(i).getTFinal().compareTo(solicitud.getTInicial())>0) {   // compareTo>0 implica que va después en tiempo (se puede usar .isAfter o es .isBefore de la clase local.java.time)
			listaEspera.add(i, solicitud);}  											// Añadimos la solicitud en la posición en la que la solicitud sea de una fecha anterior.
		}
	}
	}
	
	public boolean existeHuecoReservado(Hueco hueco) {
	boolean reservado = false;
	for(int i = 0; i<huecosReservados.size() && !reservado; i++) {
	if(huecosReservados.get(i).equals(hueco)) {
	}
	reservado = true;
	}
		return reservado;
	}
	
	//TO-DO alumno opcionales
	
	public void liberarHueco(Hueco hueco) {
		//TO-DO
	}

	//PRE (no es necesario comprobar): las solicitudes de la lista de espera son válidas
	public IList<SolicitudReservaAnticipada> getSolicitudesAtendidasListaEspera() {
		//TO-DO
		return null;
	}

	


}
