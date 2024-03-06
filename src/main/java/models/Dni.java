package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Esta clase representa un DNI
 */
public class Dni {
    private String dni;
    private int dniNumber;
    private char dniLetter;

    public Dni (String dniInput) throws IllegalArgumentException{
        if (checkDni(dniInput)){
            encodeDni(dniInput);
        } else
            throw new IllegalArgumentException("El DNI introducido no tiene el formato correcto");
    }

    // Este método codifica la cadena de texto recibida en el constructor a sus correspondientes componentes
    // por separado
    private void encodeDni(String dniInput){
        dni = dniInput;
        Pattern p = Pattern.compile(Dni.dniRegex);
        Matcher m = p.matcher(dniInput);
        m.matches();
        dniNumber = Integer.parseInt(m.group(1));
        dniLetter = m.group(2).toUpperCase().charAt(0);
    }

    /**
     * Devuelve la cadena de texto que representa un DNI completo.
     *
     * @return DNI completo, incluyendo la letra
     */
    public String getDni() {
        return dni;
    }

    public int getDniNumber() {
        return dniNumber;
    }

    public char getDniLetter() {
        return dniLetter;
    }

    /*  Expresión regular para validar el DNI
     *  La expresión regular está compuesta por dos grupos:
     *  ^([0-9]{1,8}) Primer grupo de 8 dígitos correspondiente con la numeración del DNI. Al menos se espera la
     *  introducción de un dígito.
     *  ([A-Z])$ Segundo grupo formado por una letra mayúscula que corresponde con el dígito de control del DNI
     *  Los grupos se utilizarán para separar los números del caracter y utilizar el validador (importado del ejercicio
     *  de la UD5)
     * */
    private static final String dniRegex = "^([0-9]{1,9})([A-Za-z])$";

    /***
     * Este método comprueba el DNI recibido en una cadena de texto.<br>
     * Es una sobrecarga del utilizado en el ejercicio anterior <br>
     * El método se hace estático para poder utilizarse en cualquier parte del código que sea necesaria.
     * @param dni La cadena de texto que representa un DNI <strong>incluyendo la letra.</strong>
     * @return
     */
    public static boolean checkDni(String dni){
        if (dni == null || dni.isEmpty()) return false;
        Pattern p = Pattern.compile(dniRegex);
        Matcher m = p.matcher(dni);
        //Si la expresión regular es buena, se aprovecha el comprobador del ejercicio de la
        // UD5 para validarlo completamente
        if (m.matches()){
            //Se aprovechan los grupos de la expresión regular
            //Se extrae el grupo 1 que representa el número del DNI
            int dniNumber = Integer.parseInt(m.group(1));
            //Se extrae el grupo 2 que representa la letra del DNI
            char dniLetter = m.group(2).toUpperCase().charAt(0);
            //Se valida completamente el DNI con el método sobrecargado del ejercicio UD5
            return checkDni(dniNumber,dniLetter);
        }
        return false;

    }

    private static final char[] dniLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D','X',
            'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /** Comprueba si un DNI es válido según la normativa vigente.
     * @param dniNumber El número del DNI. Valores válidos entre 0 y 99999999
     * @param dniLetter Letra que corresponda con el DNI
     * @return
     */
    public static boolean checkDni(int dniNumber, char dniLetter) {
        if (dniNumber < 0 || dniNumber > 99999999) {
            return false;
        }
        return dniLetters[dniNumber % 23] == Character.toUpperCase(dniLetter);
    }
}
