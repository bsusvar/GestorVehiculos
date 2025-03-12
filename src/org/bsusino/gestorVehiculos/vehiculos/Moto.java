package org.bsusino.gestorVehiculos.vehiculos;

public class Moto extends Vehiculo {

	private TipoCombustible tipoCombustible;
	private Cilindrada cilindrada;

	public Moto(Nombre nombre, String matricula, String marca, String modelo, double velocidadMaxima,
			TipoCombustible tipoCombustible, Cilindrada cilindrada) {
		super(nombre, matricula, marca, modelo, velocidadMaxima);
		this.tipoCombustible = tipoCombustible;
		this.cilindrada = cilindrada;

	}

	public TipoCombustible getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(TipoCombustible tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Cilindrada getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Cilindrada cilindrada) {
		this.cilindrada = cilindrada;
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("\n• Matrícula: " + getMatricula() + "\n• Marca: " + getMarca() + "\n• Modelo: "
				+ getModelo() + "\n• Velocidad máxima: " + getVelocidadMaxima() + " km/h" + "\n• Tipo de combustible: "
				+ tipoCombustible.getDescripcion() + "\n• Cilindrada: " + cilindrada.getDescripcion());
	}

}
