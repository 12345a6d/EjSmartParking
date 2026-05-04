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
	private ArrayList<SolicitudReservaAnticipada> listaEspera;
	private GestorHuecos gestorHuecos;
	private ArrayList <Hueco> huecosReservados;
	
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
	    this.plazas = new Plaza[noPlazas];
	    for(int n = 0; n< noPlazas; n++) {
	    this.plazas[n] = new Plaza (n);
	    }
	    this.precio = precio;
	    listaEspera = new ArrayList<>();
	    gestorHuecos = new GestorHuecos(plazas);
	    huecosReservados = new ArrayList<>();
	}
	
	public Hueco reservarHueco(LocalDateTime tI, LocalDateTime tF) {
	    if (!gestorHuecos.existeHueco(tI, tF)) {
	        return null;
	    }
	    Hueco hueco = gestorHuecos.reservarHueco(tI, tF);
	    huecosReservados.add(huecosReservados.size(), hueco);
	    return hueco;
	}
	public boolean existeHueco(LocalDateTime tI, LocalDateTime tF) { 
	return gestorHuecos.existeHueco(tI, tF);
	}
	
	
	public void meterEnListaEspera(SolicitudReservaAnticipada solicitud) {
	    int posicion = listaEspera.size(); // Por defecto al final
	    boolean encontrada = false;
	    
	    for (int i = 0; i < listaEspera.size() && !encontrada; i++) {
	        if (solicitud.getPrioridad().compareTo(listaEspera.get(i).getPrioridad()) > 0) {
	            posicion = i;
	            encontrada = true;
	        } else if (solicitud.getPrioridad() == listaEspera.get(i).getPrioridad() &&
	                   solicitud.getTInicial().compareTo(listaEspera.get(i).getTInicial()) < 0) {
	            posicion = i;
	            encontrada = true;
	        }
	    }
	    listaEspera.add(posicion, solicitud);
	}
	
	
	
	public boolean existeHuecoReservado(Hueco hueco) {
		boolean resultado = false;
	    // Recorremos la lista de huecos reservados
	    for (int i = 0; i < huecosReservados.size(); i++) {
	        if (huecosReservados.get(i).gettI().equals(hueco.gettI()) &&
	            huecosReservados.get(i).gettF().equals(hueco.gettF()) &&
	            huecosReservados.get(i).getPlaza().getNumero() == hueco.getPlaza().getNumero()) {
	            resultado = true;
	        }
	    }
	    return resultado;
	}
	
	//TO-DO alumno opcionales
	
	public void liberarHueco(Hueco hueco) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado) {

			if (huecosReservados.get(i).gettI().isEqual(hueco.gettI())
					&& huecosReservados.get(i).gettF().isEqual(hueco.gettF()) && 
					huecosReservados.get(i).getPlaza().getNumero() == hueco.getPlaza().getNumero() ) {
			encontrado = true;
			huecosReservados.removeElementAt(i);
			gestorHuecos.liberarHueco(hueco);
			}
			else {
			i++;	
			}
			
		}
		
	}

	//PRE (no es necesario comprobar): las solicitudes de la lista de espera son válidas
	public ArrayList<SolicitudReservaAnticipada> getSolicitudesAtendidasListaEspera() {
	    ArrayList<SolicitudReservaAnticipada> atendidas = new ArrayList<>();
	    // Recorremos desde el principio hasta el final
	    int i = 0;
	    while (i < listaEspera.size()) {
	        SolicitudReservaAnticipada solicitud = listaEspera.get(i);
	        // Intentamos reservar el hueco
	        Hueco hueco = reservarHueco(solicitud.getTInicial(), solicitud.getTFinal());
	        if (hueco != null) {
	            solicitud.setHueco(hueco);
	            atendidas.add(0,solicitud);   // se añade en orden de aparición
	            listaEspera.removeElementAt(i); // eliminamos esta solicitud
	            // No incrementamos i porque el siguiente elemento ahora está en i
	        } else {
	            i++; // solo avanzamos si no se pudo atender
	        }
	    }
	    return atendidas;
	}

	


}
