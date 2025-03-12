package org.bsusino.gestorVehiculos.vehiculos;

public enum NumeroPuertas {
	DOS_PUERTAS("2 puertas"), TRES_PUERTAS("3 puertas"), CUATRO_PUERTAS("4 puertas"), CINCO_PUERTAS("5 puertas");

	private String descripcion;

	NumeroPuertas(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
