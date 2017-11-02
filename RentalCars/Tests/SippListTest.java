import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Faze on 02/11/2017.
 */
class SippListTest {


    @Test
    void newSIPPTest(){
        Map[] sippValues = SippList.makeSippList();
        Car c = new Car("FVAR","Honda",120,"TestCorp",3.4);

            try {
                String[] sippResult = c.readSipp(sippValues);

                String[] airConOrFuel = sippResult[3].split("/", 2);

                c.updateSipInfo(sippResult[0],sippResult[1],sippResult[2],airConOrFuel[0],airConOrFuel[1]);

            } catch (Exception e){
                System.out.println("ERROR: Invalid SIPP");
                c.updateSipInfo("Invalid SIPP","Invalid SIPP",
                        "Invalid SIPP","Invalid SIPP","Invalid SIPP");
            }

        assertEquals("Full size", c.getCarType());
        assertEquals("Passenger Van", c.getDoorCarType());
        assertEquals("Petrol", c.getFuel());
        assertEquals("AC", c.getAirCon());
        assertEquals("Automatic", c.getTransmission());

    }

    @Test
    void wrongSIPPTest(){
        Map[] sippValues = SippList.makeSippList();
        Car c = new Car("JFHG","FORD",80,"TestCorp",7);

        try {
            String[] sippResult = c.readSipp(sippValues);

            String[] airConOrFuel = sippResult[3].split("/", 2);

            c.updateSipInfo(sippResult[0],sippResult[1],sippResult[2],airConOrFuel[0],airConOrFuel[1]);

        } catch (Exception e){
            System.out.println("ERROR: Invalid SIPP");
            c.updateSipInfo("Invalid SIPP","Invalid SIPP",
                    "Invalid SIPP","Invalid SIPP","Invalid SIPP");
        }

        assertEquals("Invalid SIPP", c.getCarType());
        assertEquals("Invalid SIPP", c.getDoorCarType());
        assertEquals("Invalid SIPP", c.getFuel());
        assertEquals("Invalid SIPP", c.getAirCon());
        assertEquals("Invalid SIPP", c.getTransmission());

    }

}