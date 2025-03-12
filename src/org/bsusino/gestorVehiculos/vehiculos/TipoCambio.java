package org.bsusino.gestorVehiculos.vehiculos;

public enum TipoCambio {
	MANUAL("Manual"), AUTOMATICO("Automático");

	private String descripcion;

	TipoCambio(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
