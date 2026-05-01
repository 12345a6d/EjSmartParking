package modelo.reservas.solicitudesreservas;

import java.time.LocalDateTime;

import modelo.vehiculos.Vehiculo;
import modelo.gestoresplazas.GestorLocalidad;
public class SolicitudReservaAnticipada extends SolicitudReserva {

private TEnumPrioridad prioridad;

	public SolicitudReservaAnticipada(int i, int j, LocalDateTime tI, LocalDateTime tF, Vehiculo vehiculo) {
		super(i, j, tI, tF, vehiculo);
		// TODO Auto-generated constructor stub
	}
	public SolicitudReservaAnticipada (int i, int j, LocalDateTime tI, LocalDateTime tf, Vehiculo vehiculo, TEnumPrioridad prioridad) {
		super(i,j,tI,tf,vehiculo);
		this.prioridad=prioridad;
	}
	public TEnumPrioridad getPrioridad() {
	return prioridad;
	}
	
	@Override
	public void gestionarSolicitudReserva(GestorLocalidad gestor) {
	super.gestionarSolicitudReserva(gestor);
		if(super.getHueco() == null) {
		SolicitudReservaAnticipada solicitud = new SolicitudReservaAnticipada (super.getIZona(),super.getJZona(), super.getTInicial(), super.getTFinal(), super.getVehiculo(), prioridad);
		gestor.getGestorZona(getIZona(), getJZona()).meterEnListaEspera(solicitud);
		gestor.getGestorZona(getIZona(), getJZona()).reservarHueco(getTInicial(), getTFinal());
		setHueco(solicitud.getHueco());
		}
			}
	
	@Override
	public boolean esValida (GestorLocalidad gestorLocalidad) {
	return super.esValida(gestorLocalidad);
	}
}
