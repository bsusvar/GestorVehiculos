package org.bsusino.gestorVehiculos.vehiculos;

public class CancelarOperacionException extends Exception {

	// Toda clase hija de Throwable es serializable, por lo que es recomendable que posea un identificador único:
	private static final long serialVersionUID = 16645323456L;

	public CancelarOperacionException(String message) {
		super(message);
	}

}
