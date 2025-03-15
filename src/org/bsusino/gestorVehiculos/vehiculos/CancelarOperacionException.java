package org.bsusino.gestorVehiculos.vehiculos;

public class CancelarOperacionException extends Exception {

	// Clase con excepción personalizada e identificador único (toda clase hija de
	// Throwable es serializable)
	private static final long serialVersionUID = 16645323456L;

	public CancelarOperacionException(String message) {
		super(message);
	}

}
