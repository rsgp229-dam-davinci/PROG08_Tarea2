import org.junit.Test;
import static  org.junit.Assert.*;
import models.Vehicle;

public class VehicleTest {

    @Test
    public void checkValidPlates(){
        String[] valids = {"0000BBB", "9999ZZZ"};
        for (String t : valids){
            assertTrue("Error en la matrícula: " + t, Vehicle.validatePlate(t));
        }
    }

    @Test
    public void checkInvalidPlates(){
        String[] invalids = {null, "", "0", "0000", "B","BBB","0000BBA", "0000BBB0", "9999BDT2222RSP"};
        for (String t : invalids){
            assertFalse("Error en la matrícula: " + t, Vehicle.validatePlate(t));
        }
    }

    @Test
    public void checkNullBrand(){
        Vehicle v = new Vehicle("Audi", "0000BBB");
        assertThrows(IllegalArgumentException.class, () -> v.setBrand(null));
    }

    @Test
    public void checkEmptyBrand(){
        Vehicle v = new Vehicle("Audi", "0000BBB");
        assertThrows(IllegalArgumentException.class, () -> v.setBrand(""));
    }

    @Test
    public void checkNegativePrice(){
        Vehicle v = new Vehicle("Audi", "0000BBB");
        assertThrows(IllegalArgumentException.class, () -> v.setPrice(-1));
    }

    @Test
    public void checkNegativeKilometers(){
        Vehicle v = new Vehicle("Audi", "0000BBB");
        assertThrows(IllegalArgumentException.class, () -> v.setKilometers(-1));
    }

    @Test
    public void testToString(){
        Vehicle v = new Vehicle("Audi", "0000BBB");
        v.setPrice(114550.45);
        v.setKilometers(100000);
        v.setDescription("Vehículo en buen estado");
        assertEquals("Marca: Audi \nMatrícula: 0000BBB \nPrecio: 114550,45€ \nKilómetros: 100000 \nDescripción: Vehículo en buen estado", v.toString());
    }

    @Test
    public void testComparableUsingPlates(){
        Vehicle v1 = new Vehicle("Audi", "0000BBB");
        Vehicle v2 = new Vehicle("BMW", "5555LLL");
        Vehicle v3 = new Vehicle("Mercedes", "9999ZZZ");
        assertTrue(v1.compareTo(v2) < 0);
        assertTrue(v2.compareTo(v1) > 0);
        assertTrue(v1.compareTo(v3) < 0);
        assertTrue(v3.compareTo(v1) > 0);
        assertTrue(v2.compareTo(v3) < 0);
        assertTrue(v3.compareTo(v2) > 0);
    }
}
