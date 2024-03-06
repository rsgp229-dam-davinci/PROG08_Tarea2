package main;

import models.*;
import views.MenuHelper;


/***
 * @author Rafael Sánchez González-Palacios
 *
 * Clase que inicia el programa y hace las veces de presentación y controlador del programa
 *
 * Aunque los modelos devuelven excepciones en algunos casos, se intenta que la función de
 * controlador de la lógica evite el lanzamiento de excepciones.
 *
 */
public class Main {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        mainMenu(dealer);
    }

    public static void mainMenu(Dealer dealer){
        String[] options = {"Nuevo vehículo","Listar vehículos", "Buscar vehículo", "Modificar kms vehículo"};
        boolean go = true;
        while (go){
            MenuHelper.printMenu(options);
            int option = InputHelper.readInt("Menú principal. Introduzca una opción",4);
            switch (option){
                case 0:
                    go = false;
                    break;
                case 1:
                    newVehicle(dealer);
                    break;
                case 2:
                    listVehicles(dealer);
                    break;
                case 3:
                    searchVehicle(dealer);
                    break;
                case 4:
                    updateKilometers(dealer);
                    break;
            }
        }
    }




    public static void newVehicle(Dealer dealer){

        System.out.println("NUEVO VEHÍCULO");
        System.out.println("Datos del propietario");
        String fullName = "";
        String dniString ="";
        do{
            System.out.println("Introduzca el nombre completo del propietario.\n" +
                    "Debe introducir nombre y dos apellidos con un máximo de 40 caracteres");
            fullName = InputHelper.readString("Nombre completo del propietario:");
        } while (!Owner.validateFullName(fullName));
        while (!Dni.checkDni(dniString)){
            dniString = InputHelper.readString("Introduzca el DNI del propietario.");
        }
        Owner newCarOwner = new Owner(fullName, dniString);

        System.out.println("Datos del vehículo");
        String plate = "";
        boolean vehicleExists = true;
        do{
            plate = InputHelper.readPattern("Introduzca la matrícula del nuevo vehículo", Vehicle.PLATE_REGEX_PATTERN);
            if (dealer.vehicleExists(plate))
                System.out.println("El vehículo ya existe en el concesionario");
            else
                vehicleExists = false;
        } while (vehicleExists);
        String brand = InputHelper.readString("Introduzca la marca del nuevo vehículo");
        double price = InputHelper.readDouble("Introduzca el precio del nuevo vehículo");
        int kilometers = InputHelper.readInt("Introduzca el kilometraje del nuevo vehículo", Integer.MAX_VALUE);
        String description = InputHelper.readString("Introduzca una descripción para el vehículo");

        try {
            Vehicle newVehicle = new Vehicle(brand, plate);
            newVehicle.setOwner(newCarOwner);
            newVehicle.setPrice(price);
            newVehicle.setKilometers(kilometers);
            newVehicle.setDescription(description);
            dealer.insertVehicle(newVehicle);
            System.out.println("Vehículo introducido correctamente con los siguientes datos:");
            System.out.println(newVehicle.toString());
        } catch (Exception e){
            System.out.println("Ha ocurrido algún en la inserción del vehículo.");
            System.out.println(e.getMessage());
        }

    }

    public static void listVehicles(Dealer dealer){
        if (!dealer.dealerIsEmpty()){
            for (int i = 0; i < dealer.getIndex(); i++) {
                System.out.println(dealer.getVehicles()[i].toString());
                System.out.println("----------");
            }
        } else
            System.out.println("Concesionario vacío");
    }

    public static void searchVehicle(Dealer dealer){
        System.out.println("BUSCAR VEHÍCULO");
        String plateToSearch = "";
        while(!Vehicle.validatePlate(plateToSearch)){
            plateToSearch = InputHelper.readString("Introduzca la matrícula a buscar");
        }
        Vehicle search = dealer.getVehicle(plateToSearch);
        if (search == null)
            System.out.println("Vehículo no encontrado");
        else {
            System.out.println("Se ha encontrado el vehículo con los siguientes datos:");
            System.out.println(search.toString());
        }
    }

    private static boolean updateKilometers(Dealer dealer) {
        System.out.println("MODIFICAR KILOMETRAJE");
        String plateToSearch = "";
        int newKilometers = -1;
        while(!Vehicle.validatePlate(plateToSearch)){
            plateToSearch = InputHelper.readString("Introduzca la matrícula a buscar");
        }
        Vehicle search = dealer.getVehicle(plateToSearch);
        if (search == null) {
            System.out.println("Vehículo no encontrado");
            return false;
        }
        else {
            while (newKilometers < 0){
                newKilometers = InputHelper.readInt("Introduzca los nuevos kilómetros", Integer.MAX_VALUE);
            }
            search.setKilometers(newKilometers);
            System.out.println("El vehículo ha sido modificado:");
            System.out.println(search.toString());
            return true;
        }
    }

}
