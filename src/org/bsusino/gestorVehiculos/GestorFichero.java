package org.bsusino.gestorVehiculos;

import org.bsusino.gestorVehiculos.vehiculos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFichero {

	public static File archivo = new File("vehiculos.txt");
	
    public static void guardarEnFichero(List<Vehiculo> listaVehiculos) {
            try (FileWriter fw = new FileWriter(archivo, false); BufferedWriter escritor = new BufferedWriter(fw)) {
                for (Vehiculo v : listaVehiculos) {
                    escritor.append(v.getNombre().toString()).append(", ").append(v.getMatricula()).append(", ").append(v.getMarca()).append(", ").append(v.getModelo()).append(", ").append(Double.toString(v.getVelocidadMaxima())).append(", ");
                    if (v instanceof Coche) {
                        escritor.append(((Coche) v).getTipoCambio().name()).append(", ").append(((Coche) v).getTipoCombustible().name()).append(", ").append(((Coche) v).getNumeroPuertas().name());

                    } else if (v instanceof Moto) {
                        escritor.append(((Moto) v).getCilindrada().name()).append(", ").append(((Moto) v).getTipoCombustible().name());
                    }
                    escritor.append("\n");
                }
                System.out.println("Fichero actualizado.");
                escritor.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    public static List<Vehiculo> cargarDesdeFichero() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try (FileReader fr = new FileReader(archivo); Scanner lector = new Scanner(fr)) {
            while (lector.hasNext()) {
                String lineaArchivo = lector.nextLine();
                String[] partesLinea = lineaArchivo.split(", ");
                Nombre nombre = Nombre.valueOf(partesLinea[0]);
                String matricula = partesLinea[1];
                String marca = partesLinea[2];
                String modelo = partesLinea[3];
                double velocidadMaxima = Double.parseDouble(partesLinea[4]);
                if (partesLinea[0].equalsIgnoreCase("MOTO")) {
                    Cilindrada cilindrada = Cilindrada.valueOf(partesLinea[5]);
                    TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[6]);
                    Vehiculo v = new Moto(nombre, matricula, marca, modelo, velocidadMaxima, cilindrada, tipoCombustible);
                    listaVehiculos.add(v);
                } else {
                    TipoCambio tipoCambio = TipoCambio.valueOf(partesLinea[5]);
                    TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[6]);
                    NumeroPuertas numeroPuertas = NumeroPuertas.valueOf(partesLinea[7]);
                    Vehiculo v = new Coche(nombre, matricula, marca, modelo, velocidadMaxima, tipoCambio, tipoCombustible, numeroPuertas);
                    listaVehiculos.add(v);
                }
            }
              return listaVehiculos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

