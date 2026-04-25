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
import modelo.vehiculos.Vehiculo;


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
		//TO-DO
	}


	//PRE: la solicitud es válida
	public int hacerReserva(SolicitudReserva solicitud) throws SolicitudReservaInvalida{
		if(gestorLocalidad.existeHuecoReservado(solicitud.getHueco(), solicitud.getIZona(), solicitud.getJZona()) == false) { // Comprobamos que no existe hueco reservado asociado a la solicitud
		
		try {
		registroReservas.registrarReserva(solicitud);  // Intentamos realizar la reserva
		} catch (SolicitudReservaInvalida e) {
		throw new SolicitudReservaInvalida ("No se ha podido realizar la reserva, compruebe que los datos de la solicitud sean correctos."); // Lanzamos excepción, comentando que no se ha realizado la reserva correctamente
		}
		}
		return registroReservas.registrarReserva(solicitud);
	}

	public Reserva getReserva(int numReserva) {
		//TO-DO
		return null;
	}

	//PRE: la plaza dada está libre y la reserva está validada
	public void ocuparPlaza(int i, int j, int numPlaza, int numReserva, Vehiculo vehiculo) throws PlazaOcupada, ReservaInvalida {
		//TO-DO
	}


	//TO-DO alumno opcional

	public void desocuparPlaza(int numReserva) {
		//TO-DO
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
