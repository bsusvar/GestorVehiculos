package org.bsusino.gestorVehiculos.vehiculos;

public enum Cilindrada {
	
	BAJA_CILINDRADA("Baja"), MEDIA_CILINDRADA("Media"), ALTA_CILINDRADA("Alta"),
	MUY_ALTA_CILINDRADA("Muy alta");

	private String descripcion;

	Cilindrada(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
