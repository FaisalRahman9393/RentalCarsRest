import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Faze on 02/11/2017.
 */
class RentalCarsTest extends RentalCars{
    String downloadedJSON;
    RentalCars r = new RentalCars();

    /**
     * Testing if the JSON is being downloaded from the server
     */
    @Test
    void initTest() {
        try {
             downloadedJSON = new Scanner(
                    new URL("http://www.rentalcars.com/js/vehicles.json")
                            .openStream(), "UTF-8").useDelimiter("\\A").next();

        } catch (IOException e) {
            System.out.println("ERROR: There was a problem trying to download JSON for testing: ");
            e.printStackTrace();
        }
        try {
            r.init();
        } catch (Exception e) {
            System.out.println("ERROR: There wasa problem initializing RentalCars Class: ");
            e.printStackTrace();
        }
        assertEquals(downloadedJSON,r.getVehiclesString());
    }

    /**
     * To check if the prices are sorted in ascending order.
     */
    @Test
    void carsAscendingTest() {
        try {
            r.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        r.carsAscending();
        double previous = 0;
        boolean fail = true;

        for (int i = 0; i < r.carList.size(); i++) {
            if (r.carList.get(i).getPrice() > previous){
                previous = r.carList.get(i).getPrice();
                fail = false;
            }
        }
        assertEquals(false,fail);
    }

    /**
     * We test here to see if the supplier ratings are descending in order
     */
    @Test
    void ratedSupplierDescendingTest() {
        try {
            r.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        r.ratedSupplierDescending();
        double previous = 1000;
        boolean fail = true;

        for (int i = 0; i < r.carList.size(); i++) {
            if (r.carList.get(i).getRating() < previous){
                previous = r.carList.get(i).getRating();
                fail = false;
            }
        }
        assertEquals(false,fail);
    }

    /**
     * This is a test to see if overall sum is sorted in descending order
     */
    @Test
    void sumRatingDescendingTest() {

        try {
            r.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        r.sumRatingDescending();
        double previous = 1000;
        boolean fail = true;

        for (int i = 0; i < r.carList.size(); i++) {
            if (r.carList.get(i).getRating() < previous){
                previous = r.carList.get(i).getRating();
                fail = false;
            }
        }
        assertEquals(false,fail);
    }


}