package org.bsusino.gestorVehiculos.vehiculos;

public class Moto extends Vehiculo {

    private TipoCombustible tipoCombustible;
    private Cilindrada cilindrada;

    public Moto(String matricula, String marca, String modelo, double velocidadMaxima, Cilindrada cilindrada, TipoCombustible tipoCombustible) {
        super(matricula, marca, modelo, velocidadMaxima);
        this.cilindrada = cilindrada;
        this.tipoCombustible = tipoCombustible;

    }

    @Override
    public String getMatricula() {
        return super.getMatricula();
    }

    @Override
    public void setMatricula(String matricula) {
        super.setMatricula(matricula);
    }

    @Override
    public String getMarca() {
        return super.getMarca();
    }

    @Override
    public void setMarca(String marca) {
        super.setMarca(marca);
    }

    @Override
    public String getModelo() {
        return super.getModelo();
    }

    @Override
    public void setModelo(String modelo) {
        super.setModelo(modelo);
    }

    @Override
    public double getVelocidadMaxima() {
        return super.getVelocidadMaxima();
    }

    @Override
    public void setVelocidadMaxima(double velocidadMaxima) {
        super.setVelocidadMaxima(velocidadMaxima);
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
        System.out.println("Información de la moto:" +
                "\n•Matrícula: " + getMatricula()
                + "\n•Marca: " + getMarca()
                + "\n•Modelo: " + getModelo()
                + "\n•Velocidad máxima: " + getVelocidadMaxima()
                + "\n•Cilindrada: " + getCilindrada()
                + "\n•Tipo de combustible: " + tipoCombustible);
    }

}
