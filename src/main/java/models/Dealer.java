package models;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/***
 * Clase que representa el concesionario.
 *
 * Desde aquí se añaden y buscan los vehículos.
 * @autor: Rafael Sánchez González-Palacios
 *
 */
public class Dealer {
    private SortedMap<String, Vehicle> vehicles;
    private int pointer;

    public Dealer() {
        vehicles = new TreeMap<>();
    }

    /***
     * Este devuelve el vehículo cuya matrícula se pasa como parámetro.
     *
     * @param plate Una cadena de texto formatada como una matrícula española
     * @return Una cadena de texto con la información del vehículo, o null si no existe
     */
    public Vehicle getVehicle(String plate){
        if (dealerIsEmpty()) return null;
        if (plate == null) return null;
        if (Vehicle.validatePlate(plate)){
            return vehicles.get(plate);
        }
       return null;
    }

    public int getIndex() {return pointer;}

    /***
     * Este método indica si el vehículo está en el concesionario.
     *
     * @param plate Una cadena de texto formatada como una matrícula española
     * @return true si el vehículo está en el concesionario, false si no
     */
    public boolean vehicleExists(String plate){
        if (Vehicle.validatePlate(plate)){
            return vehicles.containsKey(plate);
        }
        return false;
    }

    /***
     * Este método devuelve un listado de todos los vehículos del concesionario.
     *
     * @return Un array de objetos Vehicle
     */
    public Vehicle[] getVehicles(){
        return (Vehicle[]) vehicles.values().toArray();
    }

    /*  No me encuentro cómodo en absoluto con éste método.
        No tiene sentido que devuelva -1 si el listado está vacío...
        La finalidad del método es claramente introducir un vehículo y el resultado es confuso, ya que el valor devuelto
        no siempre indica el resultado. Si el concesionario no tiene vehículo debería comprobarse en método aparte
    */
    public int insertVehicle(Vehicle input) throws NullPointerException{
        if (input == null) throw new NullPointerException("El objeto aportado es null");
        else if (vehicleExists(input.getPlate()))
            return -2;
        else {
            boolean wasEmpty = vehicles.isEmpty();
            vehicles.put(input.getPlate(), input);
            if (wasEmpty)
                return -1;
            else
                return -2;
        }
    }

    /*  Se realiza este método alternativo como yo lo haría
    */
    public boolean insertNewVehicle(Vehicle input){
        if (input == null) return false;
        Vehicle result = vehicles.put(input.getPlate(), input);
        return result == null;
    }

    /***
     * Este método indica si el concesionario está vacío.
     *
     * @return true si el concesionario está vacío, false si no
     */
    public boolean dealerIsEmpty(){
        return  vehicles.isEmpty();
    }

}
