package org.bsusino.gestorVehiculos.vehiculos;

public abstract class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private double velocidadMaxima;

    public Vehiculo(String matricula, String marca, String modelo, double velocidadMaxima) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
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
