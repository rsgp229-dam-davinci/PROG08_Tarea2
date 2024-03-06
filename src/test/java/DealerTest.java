import models.Dealer;
import models.Vehicle;
import org.junit.Test;
import static org.junit.Assert.*;
public class DealerTest {
    @Test
    public void testGetVehicleBadPlate(){
        Dealer dealer = new Dealer();
        assertNull(dealer.getVehicle("34dr348r"));
    }

    @Test
    public void testGetVehicleGoodPlateAndDealerEmpty(){
        Dealer dealer = new Dealer();
        assertNull(dealer.getVehicle("1234bdf"));
    }

    @Test
    public void testDealerIsEmptyEmpty(){
        Dealer dealer = new Dealer();
        assertTrue(dealer.dealerIsEmpty());
    }

    @Test
    public void testDealerIsEmptyNotEmpty(){
        Dealer dealer = new Dealer();
        dealer.insertVehicle(new Vehicle("Audi", "1234BCD"));
        assertFalse(dealer.dealerIsEmpty());
    }

    @Test
    public void testInsertVehicleNullInputReturnNPException(){
        Dealer dealer = new Dealer();
        assertThrows(NullPointerException.class, () -> dealer.insertVehicle(null));
    }

    @Test
    public void testInsertVehicleReturnMinus1AfterFirstVehicleInsertion(){
        Dealer dealer = new Dealer();
        assertEquals(-1,
                dealer.insertVehicle(new Vehicle("Audi", "1234BCD")));
    }

    @Test
    public void testInsertVehicleReturn0AfterSecondVehicleInsertion(){
        Dealer dealer = new Dealer();
        dealer.insertVehicle(new Vehicle("BMW", "1234BCD"));
        assertEquals(0,
                dealer.insertVehicle(new Vehicle("Audi", "2345BCD")));
    }

    @Test
    public void testInsertVehicleReturnMinus2OnPlateAlreadyExist(){
        Dealer dealer = new Dealer();
        dealer.insertVehicle(new Vehicle("BMW", "1234BCD"));
        assertEquals(-2,
                dealer.insertVehicle(new Vehicle("BMW", "1234BCD")));
    }

}
