package org.bsusino.gestorVehiculos.vehiculos;

public class Coche extends Vehiculo {

	private TipoCambio tipoCambio;
	private TipoCombustible tipoCombustible;
	private NumeroPuertas numeroPuertas;

	public Coche(Nombre nombre, String matricula, String marca, String modelo, double velocidadMaxima,
			TipoCambio tipoCambio, TipoCombustible tipoCombustible, NumeroPuertas numeroPuertas) {
		super(nombre, matricula, marca, modelo, velocidadMaxima);
		this.tipoCambio = tipoCambio;
		this.tipoCombustible = tipoCombustible;
		this.numeroPuertas = numeroPuertas;
	}

	public NumeroPuertas getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(NumeroPuertas numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}

	public TipoCambio getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(TipoCambio tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public TipoCombustible getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("• Tipo de vehículo: " + getNombre().getDescripcion() + "\n• Matrícula: " + getMatricula()
				+ "\n• Marca: " + getMarca() + "\n• Modelo: " + getModelo() + "\n• Velocidad máxima: "
				+ getVelocidadMaxima() + " km/h" + "\n• Tipo de cambio: " + tipoCambio.getDescripcion()
				+ "\n• Tipo de combustible: " + tipoCombustible.getDescripcion() + "\n• Número de puertas: "
				+ numeroPuertas.getDescripcion());
	}
}
