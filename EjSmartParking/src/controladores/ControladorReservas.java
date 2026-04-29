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
import modelo.gestoresplazas.huecos.Hueco;
import modelo.gestoresplazas.huecos.Plaza;


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
		return registroReservas.obtenerReserva(numReserva);
	}

	//PRE: la plaza dada está libre y la reserva está validada
	public void ocuparPlaza(int i, int j, int numPlaza, int numReserva, Vehiculo vehiculo) throws PlazaOcupada, ReservaInvalida {
	if (!esValidaReserva(i,j,numPlaza,numReserva,vehiculo.getMatricula())) { // Validar que la reserva sí corresponde con la plaza, zona y matrícula.
	throw new ReservaInvalida ("La reserva no es válida, vuelva a intentarlo de nuevo");
	}
	Reserva reserva = registroReservas.obtenerReserva(numReserva);    //Obtenemos la reserva y comprobamos que la plaza ya está ocupada
	Plaza plaza = reserva.getHueco().getPlaza();
	if (plaza.getVehiculo() != null) { // Comprobamos que en dicha plaza a reservar no hay ningún vehículo.
	throw new PlazaOcupada ("La plaza ya está ocupada");
	}
	plaza.setVehiculo(vehiculo);
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

