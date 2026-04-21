package modelo.reservas.solicitudesreservas;

import java.time.LocalDateTime;

import modelo.vehiculos.Vehiculo;

public class SolicitudReservaInmediata extends SolicitudReserva {  // Hija de SolicitudResersva, solo he realizado los constructures (he empleado sobrecarga porque en test hay dos constructores distintos).
private TEnumPrioridad priority;

	public SolicitudReservaInmediata(int i, int j, LocalDateTime tI, LocalDateTime tF, Vehiculo vehiculo) {
		super(i, j, tI, tF, vehiculo);
		// TODO Auto-generated constructor stub
	}
	public SolicitudReservaInmediata(int i, int j, LocalDateTime tI, LocalDateTime tF, Vehiculo vehiculo, TEnumPrioridad priority) {
		super(i, j, tI, tF, vehiculo);
		this.priority=priority;
}
	
}
