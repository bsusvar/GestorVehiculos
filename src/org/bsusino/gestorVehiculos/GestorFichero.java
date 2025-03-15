package org.bsusino.gestorVehiculos;

import org.bsusino.gestorVehiculos.vehiculos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFichero {

	protected static File archivo = new File("vehiculos.txt");

	// Método que escribe en el fichero vehiculos.txt los datos contenidos en
	// listaVehiculos
	protected static void guardarEnFichero(List<Vehiculo> listaVehiculos) throws IOException {

		try (FileWriter fw = new FileWriter(archivo, false); BufferedWriter escritor = new BufferedWriter(fw)) {
			for (Vehiculo v : listaVehiculos) {
				escritor.append(v.getNombre().toString()).append(", ").append(v.getMatricula()).append(", ")
						.append(v.getMarca()).append(", ").append(v.getModelo()).append(", ")
						.append(Double.toString(v.getVelocidadMaxima())).append(", ");
				if (v instanceof Coche) {
					escritor.append(((Coche) v).getTipoCambio().name()).append(", ")
							.append(((Coche) v).getTipoCombustible().name()).append(", ")
							.append(((Coche) v).getNumeroPuertas().name());

				} else if (v instanceof Moto) {
					escritor.append(((Moto) v).getTipoCombustible().name()).append(", ")
							.append(((Moto) v).getCilindrada().name());
				}
				escritor.append("\n");
			}
			System.out.println("Fichero actualizado.");
		} catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero." + e.getMessage());
		}
	}

	// Método que recupera los datos existentes en el fichero vehiculos.txt y los
	// añade a la lista
	protected static List<Vehiculo> cargarDesdeFichero() {
		List<Vehiculo> listaVehiculos = new ArrayList<>();
		try (FileReader fr = new FileReader(archivo); Scanner lector = new Scanner(fr)) {
			while (lector.hasNext()) {
				String lineaArchivo = lector.nextLine();
				String[] partesLinea = lineaArchivo.split(", ");
				Nombre nombre = Nombre.valueOf(partesLinea[0].trim());
				String matricula = partesLinea[1].trim();
				String marca = partesLinea[2].trim();
				String modelo = partesLinea[3].trim();
				double velocidadMaxima = Double.parseDouble(partesLinea[4].trim());
				if (partesLinea[0].equalsIgnoreCase("MOTO")) {
					TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[5].trim());
					Cilindrada cilindrada = Cilindrada.valueOf(partesLinea[6].trim());
					Vehiculo v = new Moto(nombre, matricula, marca, modelo, velocidadMaxima, tipoCombustible,
							cilindrada);
					listaVehiculos.add(v);
				} else if (partesLinea[0].equalsIgnoreCase("COCHE")) {
					TipoCambio tipoCambio = TipoCambio.valueOf(partesLinea[5].trim());
					TipoCombustible tipoCombustible = TipoCombustible.valueOf(partesLinea[6].trim());
					NumeroPuertas numeroPuertas = NumeroPuertas.valueOf(partesLinea[7].trim());
					Vehiculo v = new Coche(nombre, matricula, marca, modelo, velocidadMaxima, tipoCambio,
							tipoCombustible, numeroPuertas);
					listaVehiculos.add(v);
				}
			}
			return listaVehiculos;
		} catch (IOException e) {
			// Retorno de una lista vacía para evitar NullPointerException
			return new ArrayList<>();
		}

	}

}
