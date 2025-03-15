package org.bsusino.gestorVehiculos;

import java.io.IOException;
import java.util.Arrays;
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

	// Lista de vehículos cargada desde el fichero vehiculos.txt
	private static List<Vehiculo> listaVehiculos = GestorFichero.cargarDesdeFichero();

	public static void main(String[] args) {

		// Menú principal del gestor de vehículos
		System.out.print("Bienvenido/a al gestor de flota. ");
		while (true) {
			System.out.println(
					"Indica qué operación quieres realizar: " + "\n1. Agregar vehículo" + "\n2. Eliminar vehículo"
							+ "\n3. Buscar vehículos" + "\n4. Mostrar vehículos" + "\n0. Salir del programa");

			try {
				String seleccionarOpcion = pedirInput("");
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
				}
				}
			} catch (CancelarOperacionException e) {
				System.out.println("¡Gracias por utilizar nuestros servicios!");
				// Comando que detiene el funcionamiento del programa
				System.exit(0);
			}
		}

	}

	// Método que obtiene un input tipo String por parte del usuario; si equivale a
	// 0, obliga al método que lo llame a lanzar una excepción que cancela la
	// operación en curso y devuelve al usuario al menú principal del gestor de
	// vehículos
	private static String pedirInput(String mensaje) throws CancelarOperacionException {
		System.out.println(mensaje);
		String inputUsuario = input.nextLine();
		if (inputUsuario.equals("0")) {
			throw new CancelarOperacionException("");
		} else {
			return inputUsuario;
		}
	}

	// Método que agrega vehículos al gestor
	private static void agregarVehiculo(List<Vehiculo> listaVehiculos) {

		Nombre nombre = null;
		String matricula, marca, modelo = null;
		double velocidadMaxima = 0;
		TipoCambio tipoCambio;
		TipoCombustible tipoCombustible;
		NumeroPuertas numeroPuertas;
		Cilindrada cilindrada;

		while (true) {
			System.out.println(
					"Indica si quieres registrar un coche (1) o una moto (2): \n(Pulsa 0 en cualquier momento para cancelar la operación)");

			try {
				String seleccionarOpcion = pedirInput("");
				// Condicional que evalúa el valor de seleccionarOpcion y, en función de este (1
				// o 2), asigna uno de los dos valores disponibles (coche o moto) a la variable
				// Nombre (Enum):
				if (seleccionarOpcion.equals("1")) {
					nombre = Nombre.COCHE;
				} else if (seleccionarOpcion.equals("2")) {
					nombre = Nombre.MOTO;
				} else {
					System.out.println("Opción no válida. Inténtalo de nuevo: ");
					// Sentencia que evita que las siguientes líneas de código dentro del bucle se
					// ejecuten hasta que se introduzca un valor válido en seleccionarOpcion:
					continue;
				}

				// Asignación de valores a los atributos comunes a coche y moto
				matricula = validarMatricula(comprobarMatriculaRegistrada().toUpperCase().trim().replace(" ", ""));
				marca = pedirInput("Introduce la marca: ").toUpperCase().trim();
				modelo = pedirInput("Indica el modelo: ").toUpperCase().trim();
				velocidadMaxima = validarVelocidadMaxima(pedirInput("Introduce su velocidad máxima: "));

				// Asignación de valores a los atributos específicos de coche (empleando el
				// valor anteriormente indicado en seleccionarOpcion):
				switch (seleccionarOpcion) {
				case "1" -> {
					tipoCambio = elegirTipoCambio();
					tipoCombustible = elegirTipoCombustibleCoche();
					numeroPuertas = elegirNumeroPuertas();

					// Creación del nuevo objeto Coche, adición a listaVehiculos y actualización del
					// fichero
					Vehiculo v = new Coche(nombre, matricula, marca, modelo, velocidadMaxima, tipoCambio,
							tipoCombustible, numeroPuertas);
					listaVehiculos.add(v);
					System.out.println("Vehículo añadido con éxito. ");
					try {
						GestorFichero.guardarEnFichero(listaVehiculos);
					} catch (IOException e) {
						System.out.println("Error al guardar el fichero." + e.getMessage());
					}
					return;
				}

				// Asignación de valores a los atributos específicos de moto (empleando el valor
				// anteriormente indicado en seleccionarOpcion):
				case "2" -> {
					tipoCombustible = elegirTipoCombustibleMoto();
					cilindrada = elegirCilindrada();

					// Creación del nuevo objeto Moto, adición a listaVehiculos y actualización del
					// fichero
					Vehiculo v = new Moto(nombre, matricula, marca, modelo, velocidadMaxima, tipoCombustible,
							cilindrada);
					listaVehiculos.add(v);
					System.out.println("Vehículo añadido con éxito. ");
					try {
						GestorFichero.guardarEnFichero(listaVehiculos);
					} catch (IOException e) {
						System.out.println("Error al guardar el fichero." + e.getMessage());
					}
					return;
				}
				default -> {
					System.out.println("Opción no válida. Inténtalo de nuevo: ");
				}
				}
			} catch (CancelarOperacionException e) {
				System.out.println("Operación cancelada. ");
				return;
			}
		}

	}

	// Método en el que el usuario elige el tipo de cambio del coche
	private static TipoCambio elegirTipoCambio() throws CancelarOperacionException {
		String seleccionarOpcion = pedirInput("Selecciona si el tipo de cambio es manual (1) o automático (2): ");
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

	// Método en el que el usuario elige el tipo de combustible del coche
	private static TipoCombustible elegirTipoCombustibleCoche() throws CancelarOperacionException {
		String seleccionarOpcion = pedirInput(
				"Indica si el tipo de combustible es gasolina (1), diésel (2), híbrido (3) o eléctrico (4): ");
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

	// Método en el que el usuario elige número de puertas del coche
	private static NumeroPuertas elegirNumeroPuertas() throws CancelarOperacionException {
		String seleccionarOpcion = pedirInput("Finalmente, introduce el número de puertas (2, 3, 4 o 5): ");

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

	// Método en el que el usuario elige el tipo de combustible de la moto
	private static TipoCombustible elegirTipoCombustibleMoto() throws CancelarOperacionException {
		String seleccionarOpcion = pedirInput("Indica si el tipo de combustible es gasolina (1) o eléctrico (2): ");
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

	// Método en el que el usuario elige el tipo de cilindrada de la moto
	private static Cilindrada elegirCilindrada() throws CancelarOperacionException {
		String seleccionarOpcion = pedirInput(
				"Finalmente, selecciona si el tipo de cilindrada es baja (1), media (2), alta (3) o muy alta (4): ");
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

	// Método que permite comprobar si la matrícula indicada por el usuario ya se
	// encuentra registrada en el sistema
	private static String comprobarMatriculaRegistrada() throws CancelarOperacionException {
		final String matricula = pedirInput("Escribe la matrícula: ");
		if (listaVehiculos.stream().anyMatch(v -> v.getMatricula().equalsIgnoreCase(matricula))) {
			System.out.println(
					"La matrícula indicada ya se encuentra registrada en nuestro sistema. Inténtalo otra vez: ");
			return comprobarMatriculaRegistrada();
		} else {
			return matricula;
		}
	}

	// Método que permite comprobar la validez de una matrícula (española) a partir
	// del cumplimiento de una serie de requisitos
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

	// Método que permite encontrar un vehículo a partir de su matrícula (tras
	// emplear el validador)
	private static void buscarVehiculo(List<Vehiculo> listaVehiculos) {
		try {
			String matricula = validarMatricula((pedirInput(
					"Introduce la matrícula del vehículo que quieres buscar: \n(Pulsa 0 en cualquier momento para cancelar la operación) ")
					.toUpperCase().trim().replace(" ", "")));
			Vehiculo v = vehiculoEncontrado(matricula, listaVehiculos);
			if (v != null) {
				System.out.println("Información del vehículo: \n");
				v.mostrarInformacion();
			} else {
				System.out.println("No se ha encontrado ningún vehículo con la matrícula ingresada. ");
			}
		} catch (CancelarOperacionException e) {
			System.out.println("Operación cancelada");
			return;
		}
	}

	// Método que devuelve un vehículo de la lista a partir de su matrícula, o nulo
	// si no se encuentra en ella
	private static Vehiculo vehiculoEncontrado(String matricula, List<Vehiculo> listaVehiculos) {
		for (Vehiculo v : listaVehiculos) {
			if (v.getMatricula().equalsIgnoreCase(matricula)) {
				return v;
			}
		}
		return null;
	}

	// Método que permite comprobar si el valor introducido por el usuario para la
	// velocidad máxima es válido
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

	// Método con el cual se elimina un vehículo a partir de la matrícula indicada
	// por el usuario
	private static void eliminarVehiculo(List<Vehiculo> listaVehiculos) {
		try {
			String matriculaValidada = validarMatricula((pedirInput(
					"Introduce la matrícula del vehículo a eliminar: \n(Pulsa 0 en cualquier momento para cancelar la operación) ")
					.toUpperCase().trim().replace(" ", "")));
			Vehiculo v = vehiculoEncontrado(matriculaValidada, listaVehiculos);
			if (v == null) {
				System.out.println(
						"No se ha podido eliminar el vehículo solicitado porque no se encuentra registrado en nuestro sistema. ");
			} else {
				System.out.println("\nInformación del vehículo encontrado: \n");
				v.mostrarInformacion();

				while (true) {
					String confirmar = pedirInput("\n¿Quieres confirmar la eliminación? (s/n): ");
					if (confirmar.equalsIgnoreCase("s")) {
						listaVehiculos.remove(v);
						System.out.println("Vehículo eliminado con éxito. ");
						try {
							GestorFichero.guardarEnFichero(listaVehiculos);
						} catch (IOException e) {
							System.out.println("Error al guardar el fichero." + e.getMessage());
						}
						return;
					} else if (confirmar.equalsIgnoreCase("n")) {
						System.out.println("Operación cancelada. ");
						return;
					} else {
						System.out.println("Respuesta no válida. Inténtalo de nuevo: ");
					}
				}

			}
		} catch (CancelarOperacionException e) {
			System.out.println("Operación cancelada.");
			return;
		}
	}

	// Método que lista los vehículos registrados en el sistema y su información, ya
	// sean solo coches, solo motos o ambos
	private static void mostrarVehiculos(List<Vehiculo> listaVehiculos) {
		System.out.println(
				"Indica si quieres consultar la información de los coches (1), de las motos (2) o de todos los vehículos (3): \n(Pulsa 0 en cualquier momento para cancelar la operación)");
		while (true) {
			try {
				String inputUsuario = pedirInput("");
				int contador = 1;
				boolean existente = false;
				switch (inputUsuario) {
				case "1" -> {
					for (Vehiculo v : listaVehiculos) {
						if (v instanceof Coche) {
							System.out.println("Información del coche " + contador + ":");
							v.mostrarInformacion();
							existente = true;
							contador++;
							System.out.println("\n");
						}
					}
					if (!existente) {
						System.out.println("No se ha encontrado ningún coche registrado en nuestro sistema. ");

					}
					return;
				}
				case "2" -> {
					for (Vehiculo v : listaVehiculos) {
						if (v instanceof Moto) {
							System.out.println("Información de la moto " + contador + ": ");
							v.mostrarInformacion();
							existente = true;
							contador++;
							System.out.println("\n");
						}
					}
					if (!existente) {
						System.out.println("No se ha encontrado ninguna moto registrada en nuestro sistema. ");
					}
					return;
				}
				case "3" -> {
					for (Vehiculo v : listaVehiculos) {
						if (!listaVehiculos.isEmpty()) {
							System.out.println("Información del vehículo " + contador + ": ");
							v.mostrarInformacion();
							existente = true;
							contador++;
							System.out.println("\n");
						}
					}
					if (!existente) {
						System.out.println("No se ha encontrado ningún vehículo registrado en nuestro sistema. ");
					}
					return;
				}
				default -> {
					System.out.println("Opción no válida. Inténtalo de nuevo: ");
				}
				}
			} catch (CancelarOperacionException e) {
				System.out.println("Operación cancelada.");
				return;
			}
		}

	}

	// Método que pregunta al usuario si desea seguir realizando operaciones del
	// gestor o interrumpir su funcionamiento
	private static void continuarOperando() throws CancelarOperacionException {
		while (true) {
			String respuestaUsuario = pedirInput("¿Quieres realizar otra operación? (s/n): ");
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
