package controladores;

import controladores.excepciones.PlazaOcupada;
import controladores.excepciones.ReservaInvalida;
import controladores.excepciones.SolicitudReservaInvalida;
import list.IList;
import modelo.gestoresplazas.GestorLocalidad;
import modelo.reservas.EstadoValidez;
import modelo.reservas.Reserva;
import modelo.reservas.Reservas;
import modelo.reservas.solicitudesreservas.SolicitudReserva;
import modelo.reservas.solicitudesreservas.SolicitudReservaInmediata;
import modelo.vehiculos.Vehiculo;
import modelo.gestoresplazas.huecos.Hueco;
import modelo.gestoresplazas.huecos.Plaza;
import controladores.excepciones.SolicitudReservaInvalida;

public class ControladorReservas {
	private Reservas registroReservas;
	private GestorLocalidad gestorLocalidad;

	public GestorLocalidad getGestorLocalidad() {
		return gestorLocalidad;
	}

	public Reservas getRegistroReservas() {
		return registroReservas;
	}

	public boolean esValidaReserva(int i, int j, int numPlaza, int numReserva, String noMatricula) {
		Reserva reserva = this.registroReservas.obtenerReserva(numReserva);
		if (reserva == null)
			return false;
		reserva.validar(i, j, numPlaza, noMatricula, gestorLocalidad);
		return reserva.getEstadoValidez() == EstadoValidez.OK;
	}

	//TO-DO alumno obligatorio

	public ControladorReservas(int[][] plazas, double[][] precios) {
	registroReservas = new Reservas ();
	gestorLocalidad = new GestorLocalidad(plazas,precios);
	}


	//PRE: la solicitud es válida
	public int hacerReserva(SolicitudReserva solicitud) throws SolicitudReservaInvalida {
		int resultado = -1;
	    if (gestorLocalidad.existeHuecoReservado(solicitud.getHueco(), solicitud.getIZona(), solicitud.getJZona()) == false) {
	        throw new SolicitudReservaInvalida("No se ha podido realizar correctamente la reserva.");
	    }
	    else {
	    if (gestorLocalidad.getGestorZona(solicitud.getIZona(), solicitud.getJZona()).existeHueco(solicitud.getTInicial(), solicitud.getTFinal()) == false) {
	    resultado = registroReservas.registrarReserva(solicitud);
	    }
	    }
	    	
	    return resultado;
	}

	public Reserva getReserva(int numReserva) {
		return registroReservas.obtenerReserva(numReserva);
	}

	//PRE: la plaza dada está libre y la reserva está validada
	public void ocuparPlaza(int i, int j, int numPlaza, int numReserva, Vehiculo vehiculo) throws PlazaOcupada, ReservaInvalida {
	if (!esValidaReserva(i, j, numPlaza, numReserva, vehiculo.getMatricula()) == false) {
	throw new ReservaInvalida("La reserva no se ha realizado correctamente. Pruebe a realizarla más tarde.");}
	else if () {
		
	}
	
	}
	


	//TO-DO alumno opcional

	public void desocuparPlaza(int numReserva) {
	Vehiculo vehiculo = registroReservas.obtenerReserva(numReserva).getHueco().getPlaza().getVehiculo();
	vehiculo = null;
	// liberar hueco.
	
	}

	public void anularReserva(int numReserva) {
		//TO-DO
	}

		
	// PRE (no es necesario comprobar): todas las solicitudes atendidas son válidas.
	public IList<Integer> getReservasRegistradasDesdeListaEspera(int i, int j){
		//TO-DO
		return null;
	}
}
