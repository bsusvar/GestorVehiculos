package org.bsusino.gestorVehiculos.vehiculos;

public class Coche extends Vehiculo {

    private TipoCambio tipoCambio;
    private TipoCombustible tipoCombustible;
    private int numeroPuertas;

    public Coche(String matricula, String marca, String modelo, double velocidadMaxima, TipoCambio tipoCambio, TipoCombustible tipoCombustible, int numeroPuertas) {
        super(matricula, marca, modelo, velocidadMaxima);
        this.tipoCambio = tipoCambio;
        this.tipoCombustible = tipoCombustible;
        this.numeroPuertas = numeroPuertas;
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

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
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
        System.out.println("Información del coche:" +
                "\n•Matrícula: " + getMatricula()
                + "\n•Marca: " + getMarca()
                + "\n•Modelo: " + getModelo()
                + "\n•Velocidad máxima: " + getVelocidadMaxima()
                + "\n•Tipo de cambio: " + getTipoCambio()
                + "\n•Tipo de combustible: " + tipoCombustible
                + "\n•Número de puertas: " + getNumeroPuertas());
    }
}
