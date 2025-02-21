package org.bsusino.gestorVehiculos;

import org.bsusino.gestorVehiculos.vehiculos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFichero {

    static File archivo = new File("vehiculos.txt");

    public static void guardarEnFichero(List<Vehiculo> listaVehiculos, String actualizar) {
        if (actualizar.equalsIgnoreCase("agregar")) {
            try (FileWriter fw = new FileWriter(archivo, true); BufferedWriter escritor = new BufferedWriter(fw)) {
                for (Vehiculo v : listaVehiculos) {
                    escritor.append(v.getMatricula()).append(", ").append(v.getMarca()).append(", ").append(v.getModelo()).append(", ").append(Double.toString(v.getVelocidadMaxima())).append(", ");
                    if (v instanceof Coche) {
                        escritor.append(((Coche) v).getTipoCambio().toString()).append(", ").append(((Coche) v).getTipoCombustible().toString()).append(", ").append(((Coche) v).getNumeroPuertas().toString());

                    } else if (v instanceof Moto) {
                        escritor.append(((Moto) v).getCilindrada().toString()).append(", ").append(((Moto) v).getTipoCombustible().toString());
                    }
                    escritor.append("\n");
                }
                System.out.println("Fichero actualizado.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (actualizar.equalsIgnoreCase("eliminar")) {
            try (FileWriter fw = new FileWriter(archivo, false); BufferedWriter escritor = new BufferedWriter(fw)) {
                for (Vehiculo v : listaVehiculos) {
                    escritor.append(v.getMatricula()).append(", ").append(v.getMarca()).append(", ").append(v.getModelo()).append(", ").append(Double.toString(v.getVelocidadMaxima())).append(", ");
                    if (v instanceof Coche) {
                        escritor.append(((Coche) v).getTipoCambio().toString()).append(", ").append(((Coche) v).getTipoCombustible().toString()).append(", ").append(((Coche) v).getNumeroPuertas().toString());

                    } else if (v instanceof Moto) {
                        escritor.append(((Moto) v).getCilindrada().toString()).append(", ").append(((Moto) v).getTipoCombustible().toString());
                    }
                    escritor.append("\n");
                }
                System.out.println("Fichero actualizado.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void cargarDesdeFichero() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try (FileReader fr = new FileReader(archivo); Scanner lector = new Scanner(fr)) {
            while (lector.hasNext()) {
                String lineaArchivo = lector.nextLine();
                String[] partesLinea = lineaArchivo.split(", ");
                String matricula = partesLinea[0];
                String marca = partesLinea[1];
                String modelo = partesLinea[2];
                double velocidadMaxima = Double.parseDouble(partesLinea[3]);
                if (partesLinea[4].chars().allMatch(Character::isDigit)) {
                    Cilindrada cilindrada = Cilindrada.valueOf(partesLinea[4]);
                    TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[5]);
                    Vehiculo v = new Moto(matricula, marca, modelo, velocidadMaxima, cilindrada, tipoCombustible);
                    listaVehiculos.add(v);
                } else {
                    TipoCambio tipoCambio = TipoCambio.valueOf(partesLinea[4]);
                    TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[5]);
                    NumeroPuertas numeroPuertas = NumeroPuertas.valueOf(partesLinea[6]);
                    Vehiculo v = new Coche(matricula, marca, modelo, velocidadMaxima, tipoCambio, tipoCombustible, numeroPuertas);
                    listaVehiculos.add(v);
                }
            }
            System.out.println("Lista de vehículos: ");
                for(Vehiculo v : listaVehiculos) {
                    v.mostrarInformacion();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

