package models;

/***
 * @author Rafael Sánchez González-Palacios
 *
 * Modelo que representa un propietario.
 */
public class Owner {
    private String fullName;
    private Dni dni;

    /***
     *
     * @param fullName El argumento debe recibir AL MENOS TRES PALABRAS que no contengan números.
     * @param dni El número del DNI entre uno y nueve dígitos y la letra correspondiente. Ejemplo 123R 349858922T
     * @throws IllegalArgumentException
     */
    public Owner (String fullName, String dni) throws IllegalArgumentException{
        this.dni = new Dni(dni);
        if (Owner.validateFullName(fullName))
            this.fullName = fullName.trim().toUpperCase();
        else
            throw new IllegalArgumentException("El nombre completo no sigue el formato establecido.");
    }

    /***
     * Método que implementa la lógica de la validación del nombre según
     * lo indicado en el ejercicio. Se da por bueno cualquier cadena
     * que tenga dos o más espacios (descartando los espacios múltiples), no
     * contenga números y tenga menos de 40 caracteres.
     *
     * @param input La cadena completa de texto
     * @return Indica si cumple lo indicado en el ejercicio
     */
    public static boolean validateFullName(String input){
        if (input == null || input.isEmpty()) return false;
        //La cadena se convierte a mayúsculas y se eliminan los espacios en los extremos
        String processedInput = input.trim().toUpperCase();
        if (processedInput.length() > 40) return false;
        char[] chars = processedInput.toCharArray();
        int spaces = 0;
        for (int i = 0; i < chars.length; i++) {
            //Si es un número, no es válido
            if (Character.isDigit(chars[i])) return false;
            //Se comprueba que el espacio doble no cuente para evitar falsear la entrada
            if (Character.isSpaceChar(chars[i]) && !Character.isSpaceChar(chars[i+1])) spaces++;
        }
        return spaces >= 2;
    }
}