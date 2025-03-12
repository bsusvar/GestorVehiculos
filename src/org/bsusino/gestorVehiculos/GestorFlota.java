package org.bsusino.gestorVehiculos;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.bsusino.gestorVehiculos.vehiculos.Cilindrada;
import org.bsusino.gestorVehiculos.vehiculos.Coche;
import org.bsusino.gestorVehiculos.vehiculos.Moto;
import org.bsusino.gestorVehiculos.vehiculos.Nombre;
import org.bsusino.gestorVehiculos.vehiculos.NumeroPuertas;
import org.bsusino.gestorVehiculos.vehiculos.TipoCambio;
import org.bsusino.gestorVehiculos.vehiculos.TipoCombustible;
import org.bsusino.gestorVehiculos.vehiculos.Vehiculo;

public class GestorFlota {

	private static Scanner input = new Scanner(System.in);

	// Expresión regular compuesta de un primer carácter de tipo letra, cuatro
	// numéricos y otro final alfabético:
	private static String regexSeis = "[A-Z]\\d{4}[A-Z]$";

	// Expresión regular compuesta por cuatro caracteres numéricos y tres
	// alfabéticos:
	private static String regexSieteNuevo = "\\d{4}[A-Z]{3}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y otro final alfabético:
	private static String regexSieteAntiguoUno = "[A-Z]{2}\\d{4}[A-Z]$";

	// Expresión regular compuesta por un carácter alfabético, cuatro
	// numéricos y dos finales alfabéticos:
	private static String regexSieteAntiguoDos = "[A-Z]\\d{4}[A-Z]{2}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y dos finales alfabéticos:
	private static String regexOchoAntiguoUno = "[A-Z]{2}\\d{4}[A-Z]{2}$";

	// Expresión regular compuesta por un carácter alfabético, cuatro
	// numéricos y tres finales alfabéticos:
	private static String regexOchoAntiguoDos = "[A-Z]\\d{4}[A-Z]{3}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y tres finales alfabéticos:
	private static String regexNueveAntiguo = "[A-Z]{2}\\d{4}[A-Z]{3}$";

	// Array de provincias que emplean una sola letra para identificarse en
	// matrículas antiguas:
	private static String[] provinciasUnaLetra = new String[] { "A", "B", "C", "H", "J", "L", "M", "O", "P", "S", "T",
			"U", "Z" };

	// Array de provincias que emplean dos letras para identificarse en matrículas
	// antiguas:
	private static String[] provinciasDobleLetra = new String[] { "VI", "AB", "AL", "AV", "BA", "PM", "BU", "CC", "CA",
			"CS", "CR", "CO", "CU", "GE", "GR", "GU", "SS", "HU", "LE", "LU", "MA", "MU", "NA", "OR", "GC", "PO", "SA",
			"TF", "SG", "SE", "SO", "TE", "TO", "VA", "BI", "ZA", "CE", "ML" };

	private static List<Vehiculo> listaVehiculos = GestorFichero.cargarDesdeFichero();
	private static int seleccionarOpcion;

	public static void main(String[] args) {

		boolean continuarOperacion = true;
		System.out.print("Bienvenido/a al gestor de flota. ");
		while (continuarOperacion) {
			System.out.println(
					"Indica qué operación quieres realizar: " + "\n1. Agregar vehículo" + "\n2. Eliminar vehículo"
							+ "\n3. Buscar vehículos" + "\n4. Mostrar vehículos" + "\n5. Salir del programa");
			seleccionarOpcion = input.nextInt();
			switch (seleccionarOpcion) {
			case 1 -> {
				agregarVehiculo(listaVehiculos);
				continuarOperando();
			}
			case 2 -> {
				eliminarVehiculo(listaVehiculos);
				continuarOperando();
			}
			case 3 -> {
				buscarVehiculo(listaVehiculos);
				continuarOperando();
			}
			case 4 -> {
				mostrarVehiculos(listaVehiculos);
				continuarOperando();
			}
			case 5 -> {
				System.out.println("¡Gracias por utilizar nuestros servicios!");
				System.exit(0);
			}
			}
		}

	}

	public static void agregarVehiculo(List<Vehiculo> listaVehiculos) {
		System.out.println("Indica si quieres registrar un coche (1) o una moto (2): ");
		int opcionVehiculo = input.nextInt();
		Nombre nombre = null;
		while (true) {
			if (opcionVehiculo == 1) {
				nombre = Nombre.COCHE;
				break;
			} else if (opcionVehiculo == 2) {
				nombre = Nombre.MOTO;
				break;
			} else {
				System.out.println("Opción no válida. Inténtalo de nuevo: ");
				opcionVehiculo = input.nextInt();
			}
		}

		input.nextLine();
		System.out.println("Escribe la matrícula: ");
		String matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
		String matricula = validarMatricula(matriculaUsuario);
		System.out.println("Introduce la marca: ");
		String marca = input.nextLine().toUpperCase().trim();
		System.out.println("Indica el modelo: ");
		String modelo = input.nextLine().toUpperCase().trim();
		System.out.println("Introduce su velocidad máxima: ");
		String velocidadMaximaUsuario = input.nextLine();
		double velocidadMaxima = validarVelocidadMaxima(velocidadMaximaUsuario);

		switch (opcionVehiculo) {
		case 1 -> {
			System.out.println("Selecciona si el tipo de cambio es manual (1) o automático (2): ");
			TipoCambio tipoCambio = elegirTipoCambio();
			System.out.println(
					"Indica si el tipo de combustible es gasolina (1), diésel (2), híbrido (3) o eléctrico (4): ");
			TipoCombustible tipoCombustible = elegirTipoCombustibleCoche();
			System.out.println("Finalmente, introduce el número de puertas (2, 3, 4 o 5): ");
			NumeroPuertas numeroPuertas = elegirNumeroPuertas();
			input.nextLine();
			Vehiculo v = new Coche(nombre, matricula, marca, modelo, velocidadMaxima, tipoCambio, tipoCombustible,
					numeroPuertas);
			listaVehiculos.add(v);
		}

		case 2 -> {
			System.out.println("Indica si el tipo de combustible es gasolina (1) o eléctrico (2): ");
			TipoCombustible tipoCombustible = elegirTipoCombustibleMoto();
			System.out.println(
					"Finalmente, selecciona si el tipo de cilindrada es baja (1), media (2), alta (3) o muy alta (4): ");
			Cilindrada cilindrada = elegirCilindrada();
			input.nextLine();
			Vehiculo v = new Moto(nombre, matricula, marca, modelo, velocidadMaxima, tipoCombustible, cilindrada);
			listaVehiculos.add(v);
		}
		}
		GestorFichero.guardarEnFichero(listaVehiculos);
		System.out.println("Vehículo añadido con éxito. ");
	}

	private static TipoCambio elegirTipoCambio() {
		switch (seleccionarOpcion) {
		case 1 -> {
			return TipoCambio.MANUAL;
		}
		case 2 -> {
			return TipoCambio.AUTOMATICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCambio();
		}
		}
	}

	private static TipoCombustible elegirTipoCombustibleCoche() {
		switch (seleccionarOpcion) {
		case 1 -> {
			return TipoCombustible.GASOLINA;
		}
		case 2 -> {
			return TipoCombustible.DIESEL;
		}
		case 3 -> {
			return TipoCombustible.HIBRIDO;
		}
		case 4 -> {
			return TipoCombustible.ELECTRICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCombustibleCoche();
		}
		}
	}

	private static NumeroPuertas elegirNumeroPuertas() {
		switch (seleccionarOpcion) {
		case 2 -> {
			return NumeroPuertas.DOS_PUERTAS;
		}
		case 3 -> {
			return NumeroPuertas.TRES_PUERTAS;
		}
		case 4 -> {
			return NumeroPuertas.CUATRO_PUERTAS;
		}
		case 5 -> {
			return NumeroPuertas.CINCO_PUERTAS;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirNumeroPuertas();
		}
		}

	}

	private static TipoCombustible elegirTipoCombustibleMoto() {
		switch (seleccionarOpcion) {
		case 1 -> {
			return TipoCombustible.GASOLINA;
		}
		case 2 -> {
			return TipoCombustible.ELECTRICO;
		}
		default -> {
			System.out.println("Valor no válido. Inténtalo otra vez: ");
			return elegirTipoCombustibleMoto();
		}
		}
	}

	private static Cilindrada elegirCilindrada() {
		switch (seleccionarOpcion) {
		case 1 -> {
			return Cilindrada.BAJA_CILINDRADA;
		}
		case 2 -> {
			return Cilindrada.MEDIA_CILINDRADA;
		}
		case 3 -> {
			return Cilindrada.ALTA_CILINDRADA;
		}
		case 4 -> {
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
			System.out.println("Información del vehículo: ");
			vehiculoEncontrado.mostrarInformacion();
		} else {
			System.out.println("No se encuentra ningún vehículo con la matrícula ingresada. ");
		}
	}

	private static String validarMatricula(String matriculaUsuario) {

		if (matriculaUsuario.length() == 6) {
			// Matrículas antiguas de 6 caracteres con provincias de una letra
			if (Pattern.matches(regexSeis, matriculaUsuario)
					&& Arrays.asList(provinciasUnaLetra).contains(matriculaUsuario.substring(0, 1))) {
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
			if (Pattern.matches(regexSieteAntiguoUno, matriculaUsuario)
					&& Arrays.asList(provinciasDobleLetra).contains(matriculaUsuario.substring(0, 2))) {
				return matriculaUsuario;

				// Matrículas antiguas de 7 caracteres con provincias de una letra y dos letras
				// al final
			} else if (Pattern.matches(regexSieteAntiguoDos, matriculaUsuario)
					&& Arrays.asList(provinciasUnaLetra).contains(matriculaUsuario.substring(0, 1))) {
				return matriculaUsuario;
			} else {
				System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
				matriculaUsuario = input.nextLine().toUpperCase().trim().replace(" ", "");
				return validarMatricula(matriculaUsuario);
			}
			// Matrículas nuevas de 7 caracteres (cuatro números y tres letras)
		} else if (matriculaUsuario.length() == 7
				&& matriculaUsuario.substring(0, 1).chars().allMatch(Character::isDigit)) {
			if (Pattern.matches(regexSieteNuevo, matriculaUsuario)) {
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
			if (Pattern.matches(regexOchoAntiguoUno, matriculaUsuario)
					&& Arrays.asList(provinciasDobleLetra).contains(matriculaUsuario.substring(0, 2))) {
				return matriculaUsuario;
			} // Matrículas antiguas de 8 caracteres con provincias de una letra y otras tres
				// letras al final

			else if (Pattern.matches(regexOchoAntiguoDos, matriculaUsuario)
					&& Arrays.asList(provinciasUnaLetra).contains(matriculaUsuario.substring(0, 1))) {
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
			if (Pattern.matches(regexNueveAntiguo, matriculaUsuario)
					&& Arrays.asList(provinciasDobleLetra).contains(matriculaUsuario.substring(0, 2))) {
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
			listaVehiculos.remove(vehiculoEncontrado);
			GestorFichero.guardarEnFichero(listaVehiculos);
			System.out.println("Vehículo eliminado con éxito. ");
		}
	}

	private static void mostrarVehiculos(List<Vehiculo> listaVehiculos) {
		System.out.println(
				"Indica si quieres consultar la información de los coches (1), de las motos (2) o de todos los vehículos (3): ");
		seleccionarOpcion = input.nextInt();
		int contador = 1;
		switch (seleccionarOpcion) {
		case 1 -> {
			for (Vehiculo v : listaVehiculos) {
				if (v instanceof Coche) {
					System.out.println("Información del coche " + contador + ":");
					v.mostrarInformacion();
					contador++;
					System.out.println("\n");
				}
			}
		}
		case 2 -> {
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
		case 3 -> {
			for (Vehiculo v : listaVehiculos) {
				System.out.println("Información del vehículo " + contador + ": ");
				v.mostrarInformacion();
				contador++;
				System.out.println("\n");
			}
		}
		default -> {
			System.out.println("Opción no válida. Inténtalo de nuevo: ");
			input.nextInt();
		}
		}
		input.nextLine();
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
