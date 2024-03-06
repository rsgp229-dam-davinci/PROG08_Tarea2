package main;

import java.util.Scanner;

/***
 * @author Rafael Sánchez González-Palacios
 *
 * Esta clase tiene método estáticos para ayudar a la entrada de datos por consola.
 *
 * Los métodos no salen hasta haber recibido las entradas correctas.
 */
public class InputHelper {

    /***
     * Método auxiliar para pedir enteros.
     * @param message Mensaje a mostrar para la introducción de datos
     * @param maxInclusive El número máximo, inclusive, que admite como entrada buena. El mínimo es siempre cero.
     * @return El entero aceptado entre los límites
     */
    public static int readInt(String message, int maxInclusive){
        int option = -1;
        while (option < 0 || option > maxInclusive){
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            try {
                option = scanner.nextInt();
            } catch (Exception e){

            } finally {
                if (option < 0 || option > maxInclusive)
                    System.out.println("Opción incorrecta. Introduzca una opción entre 0 y " + maxInclusive);
            }
        }
        return option;
    }

    public static double readDouble (String message){
        double value = -1;
        while (value < 0 || value > Double.MAX_VALUE){
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            try {
                value = scanner.nextDouble();
            } catch (Exception e){

            } finally {
                if (value < 0 || value > Double.MAX_VALUE)
                    System.out.println("Opción incorrecta. Introduzca una opción entre 0 y " + Double.MAX_VALUE);
            }
        }
        return value;
    }

    /***
     * Entrada simple de una cadena de texto
     * @param message Mensaje para indicar el contexto de la solicitud
     * @return La cadena introducida
     */
    public static String readString(String message){
        String output = "";
        while (output == ""){
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            try{
                output = scanner.nextLine();
            } catch (Exception e){
                System.out.println("Valor no válido.");
            }
        }
        return output;
    }

    /***
     * Este método valida la entrada contra un patrón
     * @param message Mensaje de ayuda para el contexto de la entrada esperada
     * @param pattern El patrón contra el que debe validarse la entrada
     * @return La cadena validada
     */
    public static String readPattern (String message, String pattern){
        String output = null;
        boolean notMatch = true;
        while (notMatch){
            System.out.println(message);
            try{
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNext(pattern)){
                    output = scanner.next(pattern);
                    notMatch = false;
                }
            } catch (Exception e){
                System.out.println("Introduzca el patrón correcto.");
            }
        }
        return output;
    }
}
