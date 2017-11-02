import org.json.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// The Java function will be hosted at the URI path "/rest{function url}"
@Path("/rest")
public class RestService extends RentalCars {

    /**
     * @return a JSON response to the call http://localhost:8080/RentalCars/rest/ascendingbyprice
     *
     * JSON is the name with the prices(ascending) of cars
     */
    @GET
    @Path("/ascendingbyprice")
    @Produces(MediaType.APPLICATION_JSON)
    public String ascendingByPriceJSON() {
        try {init();}catch (Exception e) { e.printStackTrace();}
        return carsAscending();
    }

    /**
     * @return a JSON response to the call http://localhost:8080/RentalCars/rest/sipp
     *
     * JSON is the Name, SIPP, CarType, DoorType, Transmission, Fuel and AirCon of a car.
     */
    @GET
    @Path("/sipp")
    @Produces(MediaType.APPLICATION_JSON)
    public String sippJSON() {
        try {init();} catch (Exception e) {e.printStackTrace();}
        return specBySIPP();
    }

    /**
     * @return a JSON response to the call http://localhost:8080/RentalCars/rest/supplierrating
     *
     * JSON is the Name, CarType, Supplier, Rating(descending) of a car
     */
    @GET
    @Path("/supplierrating")
    @Produces(MediaType.APPLICATION_JSON)
    public String supplierRatingJSON() {
        try {init();} catch (Exception e) {e.printStackTrace();}
        return ratedSupplierDescending();
    }

    /**
     * @return a JSON response to the call http://localhost:8080/RentalCars/rest/highsumratingdescending
     *
     * JSON is the Name, VehicleScore, Rating, Sum(Descending) of a car
     */
    @GET
    @Path("/highsumratingdescending")
    @Produces(MediaType.APPLICATION_JSON)
    public String highSumJSON() {
        try {init();} catch (Exception e) {e.printStackTrace();}
        return sumRatingDescending();
    }
}