package org.bsusino.gestorVehiculos;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.bsusino.gestorVehiculos.vehiculos.CancelarOperacionException;
import org.bsusino.gestorVehiculos.vehiculos.Cilindrada;
import org.bsusino.gestorVehiculos.vehiculos.Coche;
import org.bsusino.gestorVehiculos.vehiculos.Constantes;
import org.bsusino.gestorVehiculos.vehiculos.Moto;
import org.bsusino.gestorVehiculos.vehiculos.Nombre;
import org.bsusino.gestorVehiculos.vehiculos.NumeroPuertas;
import org.bsusino.gestorVehiculos.vehiculos.TipoCambio;
import org.bsusino.gestorVehiculos.vehiculos.TipoCombustible;
import org.bsusino.gestorVehiculos.vehiculos.Vehiculo;

public class GestorFlota {

	private static Scanner input = new Scanner(System.in);

	private static List<Vehiculo> listaVehiculos = GestorFichero.cargarDesdeFichero();
	private static String seleccionarOpcion;

	public static void main(String[] args) {

		boolean continuarOperacion = true;
		System.out.print("Bienvenido/a al gestor de flota. ");
		while (continuarOperacion) {
			System.out.println(
					"Indica qué operación quieres realizar: " + "\n1. Agregar vehículo" + "\n2. Eliminar vehículo"
							+ "\n3. Buscar vehículos" + "\n4. Mostrar vehículos" + "\n0. Salir del programa");

			try {
				seleccionarOpcion = input.nextLine();
				pedirInput(seleccionarOpcion);
				switch (seleccionarOpcion) {
				case "1" -> {
					agregarVehiculo(listaVehiculos);
					continuarOperando();
				}
				case "2" -> {
					eliminarVehiculo(listaVehiculos);
					continuarOperando();
				}
				case "3" -> {
					buscarVehiculo(listaVehiculos);
					continuarOperando();
				}
				case "4" -> {
					mostrarVehiculos(listaVehiculos);
					continuarOperando();
				}
				default -> {
					System.out.println("El valor introducido no es válido. Inténtalo de nuevo.");
					continuarOperacion = true;
				}
				}
			} catch (CancelarOperacionException e) {
				System.out.println("¡Gracias por utilizar nuestros servicios!");
				System.exit(0);
			}
		}

	}

	private static void pedirInput(String input) throws CancelarOperacionException {
		if (input.equals("0")) {
			throw new CancelarOperacionException("");
		}
	}

	private static void agregarVehiculo(List<Vehiculo> listaVehiculos) {
		System.out.println(
				"Indica si quieres registrar un coche (1) o una moto (2): \n(Presiona 0 en cualquier momento para cancelar la operación)");
		Nombre nombre = null;
		String matricula = null;
		String marca = null;
		String modelo = null;
		double velocidadMaxima = 0;
		TipoCambio tipoCambio;
		TipoCombustible tipoCombustible;
		NumeroPuertas numeroPuertas;
		Cilindrada cilindrada;

		while (true) {
			try {
				pedirInput(seleccionarOpcion);
				if (seleccionarOpcion.equals("1")) {
					nombre = Nombre.COCHE;
					break;
				} else if (seleccionarOpcion.equals("2")) {
					nombre = Nombre.MOTO;
					break;
				} else {
					System.out.println("Opción no válida. Inténtalo de nuevo: ");
				}
				System.out.println("Escribe la matrícula: ");
				String matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				pedirInput(matriculaUsuario);
				matricula = validarMatricula(matriculaUsuario);
				System.out.println("Introduce la marca: ");
				marca = input.nextLine().toUpperCase().trim();
				pedirInput(marca);
				System.out.println("Indica el modelo: ");
				modelo = input.nextLine().toUpperCase().trim();
				pedirInput(modelo);
				System.out.println("Introduce su velocidad máxima: ");
				String velocidadMaximaUsuario = input.nextLine();
				velocidadMaxima = validarVelocidadMaxima(velocidadMaximaUsuario);
			} catch (CancelarOperacionException e) {
				System.out.println("Operación cancelada. ");
				break;
			}
			while (true) {
				try {
					pedirInput(seleccionarOpcion);
					switch (seleccionarOpcion) {
					case "1" -> {
						System.out.println("Selecciona si el tipo de cambio es manual (1) o automático (2): ");
						tipoCambio = elegirTipoCambio();
						pedirInput(String.valueOf(tipoCambio));
						System.out.println(
								"Indica si el tipo de combustible es gasolina (1), diésel (2), híbrido (3) o eléctrico (4): ");
						tipoCombustible = elegirTipoCombustibleCoche();
						pedirInput(String.valueOf(tipoCombustible));
						System.out.println("Finalmente, introduce el número de puertas (2, 3, 4 o 5): ");
						numeroPuertas = elegirNumeroPuertas();
						pedirInput(String.valueOf(numeroPuertas));
						Vehiculo v = new Coche(nombre, matricula, marca, modelo, velocidadMaxima, tipoCambio,
								tipoCombustible, numeroPuertas);
						listaVehiculos.add(v);
						System.out.println("Vehículo añadido con éxito. ");
						GestorFichero.guardarEnFichero(listaVehiculos);
					}

					case "2" -> {
						System.out.println("Indica si el tipo de combustible es gasolina (1) o eléctrico (2): ");
						tipoCombustible = elegirTipoCombustibleMoto();
						pedirInput(String.valueOf(tipoCombustible));
						System.out.println(
								"Finalmente, selecciona si el tipo de cilindrada es baja (1), media (2), alta (3) o muy alta (4): ");
						cilindrada = elegirCilindrada();
						pedirInput(String.valueOf(cilindrada));
						input.nextLine();
						Vehiculo v = new Moto(nombre, matricula, marca, modelo, velocidadMaxima, tipoCombustible,
								cilindrada);
						listaVehiculos.add(v);
						System.out.println("Vehículo añadido con éxito. ");
						GestorFichero.guardarEnFichero(listaVehiculos);
					}
					default -> {
						System.out.println("Opción no válida. Inténtalo de nuevo: ");
					}
					}
				} catch (CancelarOperacionException e) {
					System.out.println("Operación cancelada. ");
					break;
				}
			}

		}
	}

	private static TipoCambio elegirTipoCambio() {
		switch (seleccionarOpcion) {
		case "1" -> {
			return TipoCambio.MANUAL;
		}
		case "2" -> {
			return TipoCambio.AUTOMATICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCambio();
		}
		}

	}

	private static TipoCombustible elegirTipoCombustibleCoche() {
		seleccionarOpcion = input.nextLine();
		switch (seleccionarOpcion) {
		case "1" -> {
			return TipoCombustible.GASOLINA;
		}
		case "2" -> {
			return TipoCombustible.DIESEL;
		}
		case "3" -> {
			return TipoCombustible.HIBRIDO;
		}
		case "4" -> {
			return TipoCombustible.ELECTRICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCombustibleCoche();
		}
		}
	}

	private static NumeroPuertas elegirNumeroPuertas() {
		seleccionarOpcion = input.nextLine();

		switch (seleccionarOpcion) {
		case "2" -> {
			return NumeroPuertas.DOS_PUERTAS;
		}
		case "3" -> {
			return NumeroPuertas.TRES_PUERTAS;
		}
		case "4" -> {
			return NumeroPuertas.CUATRO_PUERTAS;
		}
		case "5" -> {
			return NumeroPuertas.CINCO_PUERTAS;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirNumeroPuertas();
		}
		}
	}

	private static TipoCombustible elegirTipoCombustibleMoto() {

		seleccionarOpcion = input.nextLine();
		switch (seleccionarOpcion) {
		case "1" -> {
			return TipoCombustible.GASOLINA;
		}
		case "2" -> {
			return TipoCombustible.ELECTRICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCombustibleMoto();
		}
		}

	}

	private static Cilindrada elegirCilindrada() {
		seleccionarOpcion = input.nextLine();
		switch (seleccionarOpcion) {
		case "1" -> {
			return Cilindrada.BAJA_CILINDRADA;
		}
		case "2" -> {
			return Cilindrada.MEDIA_CILINDRADA;
		}
		case "3" -> {
			return Cilindrada.ALTA_CILINDRADA;
		}
		case "4" -> {
			return Cilindrada.MUY_ALTA_CILINDRADA;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirCilindrada();
		}
		}

	}

	private static void buscarVehiculo(List<Vehiculo> listaVehiculos) {
		input.nextLine();
		System.out.println("Introduce la matrícula del vehículo que quieres buscar: ");
		String matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
		String matricula = validarMatricula(matriculaUsuario);
		Vehiculo vehiculoEncontrado = null;
		for (Vehiculo v : listaVehiculos) {
			if (v.getMatricula().equalsIgnoreCase(matricula)) {
				vehiculoEncontrado = v;
				break;
			}
		}
		if (vehiculoEncontrado != null) {
			System.out.println("Información del vehículo: \n");
			vehiculoEncontrado.mostrarInformacion();
		} else {
			System.out.println("No se encuentra ningún vehículo con la matrícula ingresada. ");
		}
	}

	private static String validarMatricula(String matriculaUsuario) {

		if (matriculaUsuario.length() == 6) {
			// Matrículas antiguas de 6 caracteres con provincias de una letra
			if (Pattern.matches(Constantes.REGEX_SEIS, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_UNA_LETRA).contains(matriculaUsuario.substring(0, 1))) {
				return matriculaUsuario;
			} else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);
			}
		} else if (matriculaUsuario.length() == 7
				&& matriculaUsuario.substring(0, 1).chars().anyMatch(Character::isLetter)) {
			// Matrículas antiguas de 7 caracteres con provincias de dos letras y otra letra
			// al final
			if (Pattern.matches(Constantes.REGEX_SIETE_ANTIGUO_UNO, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_DOS_LETRAS).contains(matriculaUsuario.substring(0, 2))) {
				return matriculaUsuario;

				// Matrículas antiguas de 7 caracteres con provincias de una letra y dos letras
				// al final
			} else if (Pattern.matches(Constantes.REGEX_SIETE_ANTIGUO_DOS, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_UNA_LETRA).contains(matriculaUsuario.substring(0, 1))) {
				return matriculaUsuario;
			} else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);
			}
			// Matrículas nuevas de 7 caracteres (cuatro números y tres letras)
		} else if (matriculaUsuario.length() == 7
				&& matriculaUsuario.substring(0, 1).chars().allMatch(Character::isDigit)) {
			if (Pattern.matches(Constantes.REGEX_SIETE_NUEVO, matriculaUsuario)) {
				return matriculaUsuario;
			} else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);
			}
		} else if (matriculaUsuario.length() == 8
				&& matriculaUsuario.substring(0, 2).chars().anyMatch(Character::isLetter)) {
			// Matrículas antiguas de 8 caracteres con provincias de dos letras y otras dos
			// al final
			if (Pattern.matches(Constantes.REGEX_OCHO_ANTIGUO_UNO, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_DOS_LETRAS).contains(matriculaUsuario.substring(0, 2))) {
				return matriculaUsuario;
			} // Matrículas antiguas de 8 caracteres con provincias de una letra y otras tres
				// letras al final

			else if (Pattern.matches(Constantes.REGEX_OCHO_ANTIGUO_DOS, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_UNA_LETRA).contains(matriculaUsuario.substring(0, 1))) {
				return matriculaUsuario;
			} else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);

			}

		} else if (matriculaUsuario.length() == 9
				&& matriculaUsuario.substring(0, 2).chars().allMatch(Character::isLetter)) {
			// Matrículas antiguas de 9 caracteres con provincias de dos letras y otras tres
			// al final
			if (Pattern.matches(Constantes.REGEX_NUEVE_ANTIGUO, matriculaUsuario)
					&& Arrays.asList(Constantes.PROVINCIAS_DOS_LETRAS).contains(matriculaUsuario.substring(0, 2))) {
				return matriculaUsuario;
			}

			else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);

			}
		}

		else {
			System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
			matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
			return validarMatricula(matriculaUsuario);
		}
	}

	private static double validarVelocidadMaxima(String velocidadMaximaUsuario) {

		while (true) {
			try {
				double velocidadMaxima = Double.parseDouble(velocidadMaximaUsuario);
				if (velocidadMaxima >= 60 && velocidadMaxima <= 350) {
					return velocidadMaxima;
				} else {
					System.out.println("El valor debe estar comprendido entre 60 y 350. Inténtalo de nuevo: ");
				}
			} catch (NumberFormatException e) {
				System.out.println("El valor proporcionado no es válido. Inténtalo de nuevo: ");
			}
			velocidadMaximaUsuario = input.nextLine();
		}
	}

	private static void eliminarVehiculo(List<Vehiculo> listaVehiculos) {
		input.nextLine();
		System.out.println("Introduce la matrícula del vehículo a eliminar: ");
		String matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
		String matriculaValidada = validarMatricula(matriculaUsuario);
		Vehiculo vehiculoEncontrado = null;
		for (Vehiculo v : listaVehiculos) {
			if (v.getMatricula().equalsIgnoreCase(matriculaValidada)) {
				vehiculoEncontrado = v;
				break;
			}
		}

		if (vehiculoEncontrado == null) {
			System.out.println(
					"No se ha podido eliminar el vehículo solicitado porque no se encuentra registrado en nuestro sistema. ");
		} else {
			System.out.println("\n Información del vehículo encontrado: \n");
			vehiculoEncontrado.mostrarInformacion();
			System.out.println("\n ¿Quieres confirmar la eliminación? (s/n): ");
			String confirmar = input.nextLine();
			while (true) {
				if (confirmar.equalsIgnoreCase("s")) {
					listaVehiculos.remove(vehiculoEncontrado);
					System.out.println("Vehículo eliminado con éxito. ");
					GestorFichero.guardarEnFichero(listaVehiculos);
					break;
				} else if (confirmar.equalsIgnoreCase("n")) {
					System.out.println("Operación cancelada. ");
					return;
				} else {
					System.out.println("Respuesta no válida. Inténtalo de nuevo: ");
					confirmar = input.nextLine();
				}
			}

		}
	}

	private static void mostrarVehiculos(List<Vehiculo> listaVehiculos) {
		System.out.println(
				"Indica si quieres consultar la información de los coches (1), de las motos (2) o de todos los vehículos (3): ");
		while (true) {
			seleccionarOpcion = input.nextLine();
			int contador = 1;
			switch (seleccionarOpcion) {
			case "1" -> {
				for (Vehiculo v : listaVehiculos) {
					if (v instanceof Coche) {
						System.out.println("Información del coche " + contador + ":");
						v.mostrarInformacion();
						contador++;
						System.out.println("\n");
					}
				}
			}
			case "2" -> {
				System.out.println("Motos registradas: ");
				for (Vehiculo v : listaVehiculos) {
					if (v instanceof Moto) {
						System.out.println("Información de la moto " + contador + ": ");
						v.mostrarInformacion();
						contador++;
						System.out.println("\n");
					}
				}
			}
			case "3" -> {
				for (Vehiculo v : listaVehiculos) {
					System.out.println("Información del vehículo " + contador + ": ");
					v.mostrarInformacion();
					contador++;
					System.out.println("\n");
				}
			}
			default -> {
				System.out.println("Opción no válida. Inténtalo de nuevo: ");
				seleccionarOpcion = input.nextLine();
			}
			}
		}

	}

	private static void continuarOperando() {
		String respuestaUsuario;
		System.out.println("¿Quieres realizar otra operación? (s/n)");
		while (true) {
			respuestaUsuario = input.nextLine();
			if (respuestaUsuario.equalsIgnoreCase("s"))
				return;
			else if (respuestaUsuario.equalsIgnoreCase("n")) {
				System.out.println("Gracias por utilizar nuestros servicios. ");
				System.exit(0);
			} else {
				System.out.println("Respuesta no válida. Inténtalo de nuevo. ");
			}
		}
	}
}
