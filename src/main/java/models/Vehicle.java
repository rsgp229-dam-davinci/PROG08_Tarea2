package models;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/***
 * @author Rafael Sánchez
 *
 * Clase modelo que representa un vehículo
 *
 * El código es simple y suficientemente descriptivo. Los comentarios se
 * añaden donde parecía necesario.
 */
public class Vehicle implements Comparable<Vehicle>{
    private String brand;
    private String plate;
    private double price;
    private int kilometers;
    private String description;
    private Owner owner;

    public static final String PLATE_REGEX_PATTERN = "^([0-9]{4})([b-zB-Z&&[^aeiouAEIOU]]{3}$)";

    public Vehicle(String brand, String plate) throws IllegalArgumentException{
        setBrand(brand);
        setPlate(plate);
        setDescription("");
        setOwner(null);
    }

    /***
     * <p><strong>Método testeado con JUnit en el paquete de pruebas.</strong></p>
     *
     * <p>Este método valida las matrícula de un vehículo con placas españolas.</p>
     * La expresión regular es la siguiente:<br>
     * ^([0-9]{4}) Este grupo corresponde a los cuatro dígitos iniciales<br>
     * ([B-Z&&[^AEIOU]]{3}$) Este grupo corresponde con las letras finales;
     * se excluyen las vocales utilizando la expresión lógica "Todas las
     * consonantes Y NO las vocales", ya que las placas españolas de este
     * formato no contienen vocales.
     * @param plate Cadena de texto con la matrícula a validar
     * @return true si la matrícula es válida, false en caso contrario
     */
    public static boolean validatePlate(String plate) {
        if (plate == null || plate.isEmpty()) return false;
        Pattern p = Pattern.compile(PLATE_REGEX_PATTERN);
        Matcher m = p.matcher(plate.toUpperCase());
        return m.matches();
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) throws IllegalArgumentException{
        if (brand == null || brand.isEmpty())
            throw new IllegalArgumentException("La marca del vehículo no puede estar vacía");
        this.brand = brand;
    }
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) throws IllegalArgumentException{
        if (validatePlate(plate))
            this.plate = plate.trim().toUpperCase();
        else
            throw new IllegalArgumentException("Placa no válida. Utilice el formato NNNNCCC," +
                    " donde N son números y C son consonantes");
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) throws IllegalArgumentException {
        if (price < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        this.price = price;
    }
    public int getKilometers() {
        return kilometers;
    }
    public void setKilometers(int kilometers) throws IllegalArgumentException {
        if (kilometers < 0)
            throw new IllegalArgumentException("Los kilómetros no pueden ser negativos");
        this.kilometers = kilometers;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if (description == null)
            this.description = "";
        else
            this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        return String.format("Marca: %s \nMatrícula: %s \nPrecio: %.2f€ \nKilómetros: %d \nDescripción: %s", brand, plate, price, kilometers, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }


    @Override
    public int compareTo(Vehicle o) {
        return plate.compareTo(o.plate);
    }
}