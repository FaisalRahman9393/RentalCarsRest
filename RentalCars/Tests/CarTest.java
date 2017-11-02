import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Faze on 02/11/2017.
 */
class CarTest {


    /**
     * To see if a new instance of a car can be added successfully
     */
    @Test
    void newCar() {

        Car c = new Car("FVAR","Honda",120,"TestCorp",3.4);

        assertEquals("FVAR",c.getSipp());
        assertEquals("Honda",c.getName());
        assertEquals(120,c.getPrice());
        assertEquals("TestCorp",c.getSupplier());
        assertEquals(3.4,c.getRating());


    }

}