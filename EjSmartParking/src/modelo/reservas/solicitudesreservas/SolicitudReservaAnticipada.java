package modelo.reservas.solicitudesreservas;

import java.time.LocalDateTime;

import modelo.vehiculos.Vehiculo;

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
}
