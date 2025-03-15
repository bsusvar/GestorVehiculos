package org.bsusino.gestorVehiculos.vehiculos;

public enum Nombre {
	COCHE("Coche"), MOTO("Moto");

	private String descripcion;

	Nombre(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
