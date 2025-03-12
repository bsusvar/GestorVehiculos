package org.bsusino.gestorVehiculos.vehiculos;

public enum TipoCombustible {
	GASOLINA("Gasolina"), DIESEL("Diésel"), HIBRIDO("Híbrido"), ELECTRICO("Eléctrico");

	private String descripcion;

	TipoCombustible(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
