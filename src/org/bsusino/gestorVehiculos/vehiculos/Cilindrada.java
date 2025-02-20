package org.bsusino.gestorVehiculos.vehiculos;

public enum Cilindrada {
    PEQUEÑA("> ", 250, " CC"),
    MEDIANA(250, " - ", 500, " CC"),
    GRANDE("< ", 250, " CC");

    Cilindrada(int i, String s, int j, String t) {
    }

    Cilindrada(String s, int i, String t) {
    }

}
