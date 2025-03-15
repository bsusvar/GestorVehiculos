package org.bsusino.gestorVehiculos.vehiculos;

public abstract class Vehiculo {

	private Nombre nombre;
    private String matricula;
    private String marca;
    private String modelo;
    private double velocidadMaxima;

    public Vehiculo(Nombre nombre, String matricula, String marca, String modelo, double velocidadMaxima) {
    	this.nombre = nombre;
    	this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
    }
    
    public Nombre getNombre() {
    return nombre;
    }
    
    public void setNombre(Nombre nombre) {
    	this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public abstract void mostrarInformacion();
}
