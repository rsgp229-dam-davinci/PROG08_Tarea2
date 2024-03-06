import models.Owner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.Theory;

import static org.junit.Assert.*;

public class OwnerTest {
    @Test
    public void testValidFullNames(){
        String[] validFullNames = {"Rafael Sánchez González-Palacios",
                "Alberto García Rumiez", "Juan Pérez López García De los Santos"};
        for (String fullName : validFullNames){
            assertTrue("Error en la cadena " + fullName,
                    Owner.validateFullName(fullName));
        }
    }

    @Test
    public void testInvalidFullNames(){
        String[] invalidFullNames = {null, "", "Juan", "Juan Pérez",
                "Juan Pérez López García Rumiez De La Fuente Neira De todos los Santos",
                "Juan Perez 2o de DAM"};
        for (String fullName : invalidFullNames){
            assertFalse("Error en la cadena " + fullName,
                    Owner.validateFullName(fullName));
        }
    }

    @Test
    public void testConstructorValidData(){
        Owner owner = new Owner("Rafael Sánchez González-Palacios", "54074290W");
    }

    @Test
    public void testConstructorInvalidFullName(){
        assertThrows(IllegalArgumentException.class, () -> {
            Owner owner = new Owner("Rafael", "54074290W");
        });
    }

    @Test
    public void testConstructorInvalidDni(){
        assertThrows(IllegalArgumentException.class, () -> {
            Owner owner = new Owner("Rafael Sánchez González-Palacios", "54074290");
        });

    }

}
