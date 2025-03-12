package org.bsusino.gestorVehiculos.vehiculos;

public enum Cilindrada {
	BAJA_CILINDRADA("Baja cilindrada"), MEDIA_CILINDRADA("Media cilindrada"), ALTA_CILINDRADA("Alta cilindrada"),
	MUY_ALTA_CILINDRADA("Muy alta cilindrada");

	private String descripcion;

	Cilindrada(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
