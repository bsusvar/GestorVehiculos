package org.bsusino.gestorVehiculos;

import org.bsusino.gestorVehiculos.vehiculos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFlota {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        int seleccionarOpcion;
        while (true) {
            System.out.println("Bienvenido/a al gestor de flota. Indica a continuación qué operación quieres realizar: " + "\n1. Agregar vehículo" + "\n2. Eliminar vehículo" + "\n3. Buscar vehículos" + "\n4. Mostrar vehículos" + "\n5. Salir del programa");
            seleccionarOpcion = input.nextInt();
            switch (seleccionarOpcion) {
                case 1 -> {
                    agregarVehiculo(listaVehiculos);
                }
                case 2 -> {
                    eliminarVehiculo(listaVehiculos);
                }
                case 3 -> {
                    buscarVehiculo(listaVehiculos);
                }
                case 4 -> {
                    GestorFichero.cargarDesdeFichero();
                }
            }
        }


    }

    public static void agregarVehiculo(List<Vehiculo> listaVehiculos) {
        System.out.println("Indica si quieres registrar un coche (1) o una moto (2): ");
        int seleccionarOpcion = input.nextInt();
        System.out.println("Escribe la matrícula: ");
        String matricula = input.nextLine().toUpperCase().trim();
        System.out.println("Introduce la marca: ");
        String marca = input.nextLine().toUpperCase().trim();
        System.out.println("Indica el modelo: ");
        String modelo = input.nextLine().toUpperCase().trim();
        System.out.println("Introduce su velocidad máxima: ");
        double velocidadMaxima = input.nextDouble();

        switch (seleccionarOpcion) {
            case 1 -> {
                TipoCambio tipoCambio = null;
                TipoCombustible tipoCombustible = null;
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
                System.out.println("Finalmente, introduce el número de puertas: ");
                int numeroPuertas = input.nextInt();

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
        GestorFichero.guardarEnFichero(listaVehiculos);
        System.out.println("Vehículo añadido con éxito. ");

    }

    public static void buscarVehiculo(List<Vehiculo> listaVehiculos) {
        System.out.println("Introduce la matrícula del vehículo que quieres buscar: ");
        String matricula = validarMatricula(input.nextLine());
        for (Vehiculo v : listaVehiculos) {
            if (v.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println(v);
            } else {
                System.out.println("No se encuentra ningún vehículo con la matrícula ingresada. ");
            }
        }


    }

    public static String validarMatricula(String matricula) {
        String primeraLetra;
        String numeros;
        String segundaLetra;
        String terceraLetra;
        if (matricula.length() == 6) // Matrículas antiguas de 6 caracteres alfanuméricos
            {
            primeraLetra = matricula.substring(0, 1);
            numeros = matricula.substring(1, 5);
            segundaLetra = matricula.substring(5, 6);
            if (primeraLetra.chars().allMatch(Character::isLetter) && segundaLetra.chars().allMatch(Character::isLetter) && numeros.chars().allMatch(Character::isDigit)) {
                return matricula;
            }
        } else if (matricula.length() == 7) // Matrículas nuevas de 7 caracteres alfanuméricos
        {
            numeros = matricula.substring(0, 4);
            primeraLetra = matricula.substring(4, 5);
            segundaLetra = matricula.substring(5, 6);
            terceraLetra = matricula.substring(6, 7);
            if (primeraLetra.chars().allMatch(Character::isLetter)
                    && segundaLetra.chars().allMatch(Character::isLetter)
                    && terceraLetra.chars().allMatch(Character::isLetter)
                    && numeros.chars().allMatch(Character::isDigit)) {
                return matricula;
            }
        } else {
            System.out.println("Formato de matrícula no admitido. Inténtalo de nuevo: ");
            validarMatricula(input.nextLine());

        }
        return matricula;
    }

    public static void eliminarVehiculo(List<Vehiculo> listaVehiculos) {
       String matriculaValidada = validarMatricula(input.nextLine());
        for (Vehiculo v : listaVehiculos) {
            if (v.getMatricula().equalsIgnoreCase(matriculaValidada)) {
                listaVehiculos.remove(v);
            }
            else {
                System.out.println("No se ha podido eliminar el vehículo solicitado porque no se encuentra registrado en nuestro sistema");
            }
        }
    }
}
