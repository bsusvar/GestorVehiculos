package org.bsusino.gestorVehiculos.vehiculos;

public class Constantes {

	// Expresión regular compuesta de un primer carácter de tipo letra, cuatro
	// numéricos y otro final alfabético:
	public static final String REGEX_SEIS = "[A-Z]\\d{4}[A-Z]$";

	// Expresión regular compuesta por cuatro caracteres numéricos y tres
	// alfabéticos:
	public static final String REGEX_SIETE_NUEVO = "\\d{4}[A-Z]{3}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y otro final alfabético:
	public static final String REGEX_SIETE_ANTIGUO_UNO = "[A-Z]{2}\\d{4}[A-Z]$";

	// Expresión regular compuesta por un carácter alfabético, cuatro
	// numéricos y dos finales alfabéticos:
	public static final String REGEX_SIETE_ANTIGUO_DOS = "[A-Z]\\d{4}[A-Z]{2}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y dos finales alfabéticos:
	public static final String REGEX_OCHO_ANTIGUO_UNO = "[A-Z]{2}\\d{4}[A-Z]{2}$";

	// Expresión regular compuesta por un carácter alfabético, cuatro
	// numéricos y tres finales alfabéticos:
	public static final String REGEX_OCHO_ANTIGUO_DOS = "[A-Z]\\d{4}[A-Z]{3}$";

	// Expresión regular compuesta por dos caracteres alfabéticos, cuatro
	// numéricos y tres finales alfabéticos:
	public static final String REGEX_NUEVE_ANTIGUO = "[A-Z]{2}\\d{4}[A-Z]{3}$";

	// Array de provincias que emplean una sola letra para identificarse en
	// matrículas antiguas:
	public static final String[] PROVINCIAS_UNA_LETRA = new String[] { "A", "B", "C", "H", "J", "L", "M", "O", "P", "S",
			"T", "U", "Z" };

	// Array de provincias que emplean dos letras para identificarse en matrículas
	// antiguas:
	public static final String[] PROVINCIAS_DOS_LETRAS = new String[] { "VI", "AB", "AL", "AV", "BA", "PM", "BU", "CC",
			"CA", "CS", "CR", "CO", "CU", "GE", "GR", "GU", "SS", "HU", "LE", "LU", "MA", "MU", "NA", "OR", "GC", "PO",
			"SA", "TF", "SG", "SE", "SO", "TE", "TO", "VA", "BI", "ZA", "CE", "ML" };

}
