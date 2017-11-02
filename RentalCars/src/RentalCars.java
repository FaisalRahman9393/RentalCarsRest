/**
 * Rental Cars Vehicle Exercise
 * Author Faisal Rahman
 * Created: 05/08/2017
 */

import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class RentalCars {

    public ArrayList<Car> carList = new ArrayList<>(); //For storing and accessing our JSON results

    private String vehiclesString;
    private JsonObject vehicles, singleVehicle;
    private JsonArray vehiclesJSONArray;

public void init() throws Exception{


            /**
             * Downloading and digesting JSON
             */
            //Retrieved the JSON using the URL (stored as a string)
            vehiclesString = new Scanner(new URL("http://www.rentalcars.com/js/vehicles.json").openStream(), "UTF-8").useDelimiter("\\A").next();
            //Parse the received string and make it in to a JSON object using the key
            vehicles = ((new JsonParser()).parse(vehiclesString).getAsJsonObject().get("Search").getAsJsonObject());
            vehiclesJSONArray = vehicles.get("VehicleList").getAsJsonArray();

            /**
             * Scanning the JSON array, turning its elements in to objects and storing the results
             */
            for (int i = 0; i < vehiclesJSONArray.size(); i++){
                singleVehicle = vehiclesJSONArray.get(i).getAsJsonObject();
                carList.add(new Car(
                        singleVehicle.get("sipp").getAsString(),
                        singleVehicle.get("name").getAsString(),
                        singleVehicle.get("price").getAsDouble(),
                        singleVehicle.get("supplier").getAsString(),
                        singleVehicle.get("rating").getAsDouble()));
            }

    /**
     * Each car contains with it a SIPP value, we use a dictionary which simply gets the SIPP letters and
     * returns with an array of values corresponding to the 4 letters.
     */
            Map[] sippValues = SippList.makeSippList();
                for (int i = 0; i < carList.size(); i++) {
                    try {
                        //The sippResult is an array and contains: car type at index 0, doorType at index 1, transmission at index 2.
                        String[] sippResult = carList.get(i).readSipp(sippValues);

                        // Air con or fuel at index 3 are split further...
                        String[] airConOrFuel = sippResult[3].split("/", 2);

                        //For a single car, we update the SIPP info
                        carList.get(i).updateSipInfo(sippResult[0],sippResult[1],sippResult[2],airConOrFuel[0],airConOrFuel[1]);

                    } catch (Exception e){
                        //Can be expanded to recover as much information as possible from an invalid SIPP
                        // e.g. the first letter can still be used to find car type even if other three are invalid.
                        System.out.println("ERROR: Invalid SIPP");
                        carList.get(i).updateSipInfo("Invalid SIPP","Invalid SIPP",
                                "Invalid SIPP","Invalid SIPP","Invalid SIPP");
                    }
                }
    }

    /**
     * We serialise each of the responses to Strings and format them so they can easily be understood
     * @return A JSON formatted String
     */
    public String prettyPrinter(String uglyString){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = ((new JsonParser())).parse(uglyString); //The ugly string is now the new formatted element
        return gson.toJson(element);
    }

    /**
     * @return  a JSON formatted String of car name, price(Ascending)
     */
    public String carsAscending(){

        JSONObject jsonToSend = new JSONObject(); //The final object to be sent
        JSONArray arrayToSend = new JSONArray(); //The array used to make the final object to be sent

        carList.sort((p2, p1) -> Double.compare(p2.getPrice(), p1.getPrice()));  //Sorting to ascending price

        for (int i = 0; i < carList.size(); i++) {
            JSONObject singleCarJSON = new JSONObject(); //For each car, we make a new object
            singleCarJSON.put("Name", carList.get(i).getName());
            singleCarJSON.put("Price", carList.get(i).getPrice());

            arrayToSend.put(singleCarJSON);
        }
        jsonToSend.put("carsAscending", arrayToSend); //Json to be sent
        return prettyPrinter(jsonToSend.toString()); //Format before sending
    }

    /**
     * Returns a JSON formatted String of SIPP results: Name, SIPP, CarType, DoorType, Transmission, Fuel and AirCon.
     */
    public String specBySIPP(){

        JSONObject jsonToSend = new JSONObject(); //The final object to be sent
        JSONArray arrayToSend = new JSONArray(); //The array used to make the final object to be sent

        for (int i = 0; i < carList.size(); i++) {
            JSONObject singleCarJSON = new JSONObject();
            singleCarJSON.put("Name", carList.get(i).getName());
            singleCarJSON.put("Sipp", carList.get(i).getSipp());
            singleCarJSON.put("CarType", carList.get(i).getCarType());
            singleCarJSON.put("DoorType", carList.get(i).getDoorCarType());
            singleCarJSON.put("Transmission", carList.get(i).getTransmission());
            singleCarJSON.put("Fuel", carList.get(i).getFuel());
            singleCarJSON.put("AirCon", carList.get(i).getAirCon());

            arrayToSend.put(singleCarJSON);
        }

        jsonToSend.put("SIPP", arrayToSend); //The JSON key with the array containing each result

        return prettyPrinter(jsonToSend.toString()); //Format before sending

    }

    /**
     * Sorts the JSON by the rating of the supplier
     *
     * @return Name, CarType, Supplier, Rating(descending) in the from of a JSON
     */
    public String ratedSupplierDescending(){

        JSONObject jsonToSend = new JSONObject(); //The final object to be sent
        JSONArray arrayToSend = new JSONArray(); //The array used to make the final object to be sent

        //sorting by rating descending
        carList.sort((p2, p1) -> Double.compare(p1.getRating(), p2.getRating()));

        for (int i = 0; i < carList.size(); i++) {
            JSONObject singleCarJSON = new JSONObject();

            singleCarJSON.put("Name", carList.get(i).getName());
            singleCarJSON.put("CarType", carList.get(i).getCarType());
            singleCarJSON.put("Supplier", carList.get(i).getSupplier());
            singleCarJSON.put("Rating:", carList.get(i).getRating());

            arrayToSend.put(singleCarJSON);
        }

        jsonToSend.put("SupplierDescending", arrayToSend); //The JSON key with the array containing each result

        return prettyPrinter(jsonToSend.toString()); //Format before sending

    }

    /**
     * The sum is the Vehicle score added with the rating of the supplier.
     * @return Name, VehicleScore, Rating, Sum(Descending)
     */
    public String sumRatingDescending(){

        JSONObject jsonToSend = new JSONObject(); //The final object to be sent
        JSONArray arrayToSend = new JSONArray(); //The array used to make the final object to be sent

        //Giving vehicles their scores
        for (int i = 0; i < carList.size(); i++) {
            String transmissionType = carList.get(i).getTransmission();
            String airCon = carList.get(i).getAirCon();
            carList.get(i).setVehicleScore(carList.get(i).sumOfVehicleScore(transmissionType,airCon));
            carList.get(i).setSumOfAllScores(carList.get(i).
                    getVehicleScore()+carList.get(i).getRating()); //Adding up the scores.
        }

        //Sorting getSumOgAllScores, descending
        carList.sort((p2, p1) -> Double.compare(p1.getSumOfAllScores(), p2.getSumOfAllScores()));

        for (int i = 0; i < carList.size(); i++) {
            JSONObject singleCarJSON = new JSONObject();

            singleCarJSON.put("Name", carList.get(i).getName());
            singleCarJSON.put("VehicleScore", carList.get(i).getVehicleScore());
            singleCarJSON.put("Rating", carList.get(i).getRating());
            singleCarJSON.put("Sum:", carList.get(i).getSumOfAllScores());

            arrayToSend.put(singleCarJSON);
        }
        jsonToSend.put("HighestSumRating", arrayToSend); //The JSON key with the array containing each result

        return prettyPrinter(jsonToSend.toString()); //Format before sending
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public String getVehiclesString() {
        return vehiclesString;
    }

    public JsonObject getVehicles() {
        return vehicles;
    }

    public JsonObject getSingleVehicle() {
        return singleVehicle;
    }

    public JsonArray getVehiclesJSONArray() {
        return vehiclesJSONArray;
    }
}



