package org.bsusino.gestorVehiculos;

import org.bsusino.gestorVehiculos.vehiculos.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.bsusino.gestorVehiculos.GestorFichero.guardarEnFichero;

public class GestorFlota {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        int seleccionarOpcion;
        boolean continuarOperacion = true;
        while (continuarOperacion) {
            System.out.println("Bienvenido/a al gestor de flota. Indica a continuación qué operación quieres realizar: " + "\n1. Agregar vehículo" + "\n2. Eliminar vehículo" + "\n3. Buscar vehículos" + "\n4. Mostrar vehículos" + "\n5. Salir del programa");
            seleccionarOpcion = input.nextInt();
            switch (seleccionarOpcion) {
                case 1 -> {
                    agregarVehiculo(listaVehiculos);
                    continuarOperacion = seleccionarOtraOperacion();
                }
                case 2 -> {
                    eliminarVehiculo(listaVehiculos);
                    continuarOperacion = seleccionarOtraOperacion();
                }
                case 3 -> {
                    buscarVehiculo(listaVehiculos);
                    continuarOperacion = seleccionarOtraOperacion();
                }
                case 4 -> {
                    GestorFichero.cargarDesdeFichero();
                    continuarOperacion = seleccionarOtraOperacion();
                }
                case 5 -> {
                    System.out.println("¡Gracias por utilizar nuestros servicios!");
                    continuarOperacion = false;
                }
            }
        }


    }

    public static void agregarVehiculo(List<Vehiculo> listaVehiculos) {
        System.out.println("Indica si quieres registrar un coche (1) o una moto (2): ");
        int seleccionarOpcion = input.nextInt();
        System.out.println("Escribe la matrícula: ");
        String matricula = validarMatricula();
        System.out.println("Introduce la marca: ");
        String marca = input.nextLine().toUpperCase().trim();
        System.out.println("Indica el modelo: ");
        String modelo = input.nextLine().toUpperCase().trim();
        System.out.println("Introduce su velocidad máxima: ");
        double velocidadMaxima = validarVelocidadMaxima();

        switch (seleccionarOpcion) {
            case 1 -> {
                TipoCambio tipoCambio = null;
                TipoCombustible tipoCombustible = null;
                NumeroPuertas numeroPuertas = null;
                System.out.println("Selecciona si el tipo de cambio es manual (1) o automático (2): ");
                seleccionarOpcion = input.nextInt();
                switch (seleccionarOpcion) {
                    case 1 -> {
                        tipoCambio = TipoCambio.MANUAL;
                    }
                    case 2 -> {
                        tipoCambio = TipoCambio.AUTOMATICO;
                    }
                    default -> {
                        System.out.println("Valor no válido. Inténtalo otra vez: ");
                        input.nextInt();
                    }
                }
                System.out.println("Indica si el tipo de combustible es gasolina (1), diésel (2), híbrido (3) o eléctrico (4): ");
                seleccionarOpcion = input.nextInt();
                switch (seleccionarOpcion) {
                    case 1 -> {
                        tipoCombustible = TipoCombustible.GASOLINA;
                    }
                    case 2 -> {
                        tipoCombustible = TipoCombustible.DIESEL;
                    }
                    case 3 -> {
                        tipoCombustible = TipoCombustible.HIBRIDO;
                    }
                    case 4 -> {
                        tipoCombustible = TipoCombustible.ELECTRICO;
                    }
                    default -> {
                        System.out.println("Valor no válido. Inténtalo otra vez: ");
                        input.nextInt();
                    }
                }
                System.out.println("Finalmente, introduce el número de puertas (2, 3, 4 o 5): ");
                seleccionarOpcion = input.nextInt();
                switch (seleccionarOpcion) {
                    case 2 -> {
                        numeroPuertas = NumeroPuertas.DOS_PUERTAS;
                    }
                    case 3 -> {
                        numeroPuertas = NumeroPuertas.TRES_PUERTAS;
                    }
                    case 4 -> {
                        numeroPuertas = NumeroPuertas.CUATRO_PUERTAS;
                    }
                    case 5 -> {
                        numeroPuertas = NumeroPuertas.CINCO_PUERTAS;
                    }
                    default -> {
                        System.out.println("Número no válido. Inténtalo otra vez: ");
                        input.nextInt();
                    }
                }

                Vehiculo v = new Coche(matricula, marca, modelo, velocidadMaxima, tipoCambio, tipoCombustible, numeroPuertas);
            }
            case 2 -> {
                TipoCombustible tipoCombustible = null;
                Cilindrada cilindrada = null;
                System.out.println("Indica si el tipo de combustible es gasolina (1) o eléctrico (2): ");
                seleccionarOpcion = input.nextInt();
                switch (seleccionarOpcion) {
                    case 1 -> {
                        tipoCombustible = TipoCombustible.GASOLINA;
                    }
                    case 2 -> {
                        tipoCombustible = TipoCombustible.ELECTRICO;
                    }
                }
                System.out.println("Finalmente, selecciona el tipo de cilindrada: ");
                {
                    seleccionarOpcion = input.nextInt();
                    switch (seleccionarOpcion) {
                        case 1 -> {
                            cilindrada = Cilindrada.PEQUEÑA;
                        }
                        case 2 -> {
                            cilindrada = Cilindrada.MEDIANA;
                        }
                        case 3 -> {
                            cilindrada = Cilindrada.GRANDE;
                        }
                        default -> {
                            System.out.println("Entrada inválida. Inténtalo otra vez: ");
                            input.nextInt();
                        }
                    }
                }
                Vehiculo v = new Moto(matricula, marca, modelo, velocidadMaxima, cilindrada, tipoCombustible);
                listaVehiculos.add(v);
            }
        }
        String actualizar = "agregar";
        guardarEnFichero(listaVehiculos, actualizar);
        System.out.println("Vehículo añadido con éxito. ");

    }

    public static void buscarVehiculo(List<Vehiculo> listaVehiculos) {

        System.out.println("Introduce la matrícula del vehículo que quieres buscar: ");
        String matricula = validarMatricula();

        for (Vehiculo v : listaVehiculos) {
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println(v);
            } else {
                System.out.println("No se encuentra ningún vehículo con la matrícula ingresada. ");
            }
        }


    }

    public static String validarMatricula() {

        String matricula = input.nextLine().toUpperCase().trim().replace(" ", "");
        String primeraLetra; // Variable que sirve para clasificar el tipo de matrícula dependiendo de si es un carácter numérico o de tipo letra
        String regexSeis = "[A-Z]\\d{4}\\[A-Z]$"; // Expresión regular compuesta de un primer carácter de tipo letra, cuatro numéricos y otro final de tipo letra
        String regexSieteNuevo = "d{4}\\[A-Z]{3}$"; // Expresión regular compuesta por cuatro caracteres numéricos y tres de tipo letra
        String regexSieteAntiguo = "[A-Z]{2}\\d{4}\\[A-Z]$"; // Expresión regular compuesta por dos caracteres de tipo letra, cuatro numéricos y otro final de tipo letra
        String[] provinciasUnaLetra = new String[]{"A", "B", "C", "H", "J", "L", "M", "O", "P", "S", "T", "U", "Z"};
        String[] provinciasDobleLetra = new String[]{"VI", "AB", "AL", "AV", "BA", "PM", "BU", "CC", "CA", "CS", "CR", "CO", "CU", "GE", "GR", "GU", "SS", "HU", "LE", "LU", "MA", "MU", "NA", "OR", "GC", "PO", "SA", "TF", "SG", "SE", "SO", "TE", "TO", "VA", "BI", "ZA", "CE", "ML"};

        if (matricula.length() == 6) // Algunas matrículas antiguas de 6 caracteres alfanuméricos (provincias con una inicial de una sola letra)
        {
            primeraLetra = matricula.substring(0, 1);
            if (Pattern.matches(regexSeis, matricula) && Arrays.asList(provinciasUnaLetra).contains(primeraLetra)) {
                return matricula;
            }
        } else if (matricula.length() == 7 && matricula.substring(0, 1).chars().allMatch(Character::isLetter)) // Matrículas nuevas de 7 caracteres alfanuméricos y algunas matrículas antiguas (provincias con doble inicial)
        {
            primeraLetra = matricula.substring(0, 2);
            if (Pattern.matches(regexSieteAntiguo, matricula) && Arrays.asList(provinciasDobleLetra).contains(primeraLetra)) {
                return matricula;
            }
        } else if (matricula.length() == 7 && matricula.substring(0, 1).chars().allMatch(Character::isDigit)) {
            if (Pattern.matches(regexSieteNuevo, matricula)) {
                return matricula;
            }
        } else {
            System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
            validarMatricula();
            return null;
        }
        return null;
    }


    public static double validarVelocidadMaxima() {

        while (true) {
            String velocidadMaximaString = input.nextLine();
            try {
                double velocidadMaxima = Double.parseDouble(velocidadMaximaString);
                if (velocidadMaxima >= 60 && velocidadMaxima <= 350) {
                    return velocidadMaxima;
                } else {
                    System.out.println("El valor debe estar comprendido entre 60 y 350. Inténtalo de nuevo: ");
                    validarVelocidadMaxima();
                }
            } catch (NumberFormatException e) {
                System.out.println("El valor proporcionado no es válido. Inténtalo de nuevo: ");
            }
        }
    }


    public static void eliminarVehiculo(List<Vehiculo> listaVehiculos) {

        String matriculaValidada = validarMatricula();

        for (Vehiculo v : listaVehiculos) {
            if (v.getMatricula().equalsIgnoreCase(matriculaValidada)) {
                listaVehiculos.remove(v);
                String actualizar = "eliminar";
                guardarEnFichero(listaVehiculos, actualizar);
                System.out.println("Vehículo eliminado con éxito. ");
            } else {
                System.out.println("No se ha podido eliminar el vehículo solicitado porque no se encuentra registrado en nuestro sistema");
            }
        }
    }

    public static boolean seleccionarOtraOperacion() {
        String seleccionarOpcion;
        boolean continuarOperando = true;
        System.out.println("¿Quieres realizar otra operación? (s/n)");
        seleccionarOpcion = input.nextLine();
        if (seleccionarOpcion.equalsIgnoreCase("s")) {
            continuarOperando = true;
        } else if (seleccionarOpcion.equalsIgnoreCase("n")) {
            continuarOperando = false;
        } else {
            System.out.println("Respuesta no válida. Inténtalo de nuevo. ");
            {
                seleccionarOtraOperacion();
            }
        }
        return continuarOperando;
    }
}
