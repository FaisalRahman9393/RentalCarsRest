import java.util.Map;

/**
 * Rental Cars Vehicle Exercise
 * Author Faisal Rahman
 * Created: 05/08/2017
 */
public class Car {
    private String sipp, name, supplier, carType, doorCarType, transmission, fuel, airCon;
    private double price, rating, vehicleScore, sumOfAllScores;

    public double getSumOfAllScores() {
        return sumOfAllScores;
    }

    public void setSumOfAllScores(double sumOfAllScores) {
        this.sumOfAllScores = sumOfAllScores;
    }

    public Car(String sipp, String name, double price, String supplier, double rating) {
        this.sipp = sipp;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
        this.rating = rating;
    }

    public void updateSipInfo (String carType, String doorCarType, String transmission, String fuel, String airCon){
        this.carType = carType;
        this.doorCarType = doorCarType;
        this.transmission = transmission;
        this.fuel = fuel;
        this.airCon = airCon;
    }

    public  void setCarType(String carType) {
        this.carType = carType;
    }

    public  void setVehicleScore(double vehicleScore) {
        this.vehicleScore = vehicleScore;
    }

    public  void setDoorCarType(String doorCarType) {
        this.doorCarType = doorCarType;
    }

    public  void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public  void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public  void setAirCon(String airCon) {
        this.airCon = airCon;
    }

    public String getCarType() {
        return carType;
    }

    public double getVehicleScore() {
        return vehicleScore;
    }

    public String getDoorCarType() {
        return doorCarType;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public String getAirCon() {
        return airCon;
    }

    public String getSipp() {
        return sipp;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getRating() {
        return rating;
    }

    public  void setSipp(String sipp) {
        this.sipp = sipp;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  void setPrice(float price) {
        this.price = price;
    }

    public  void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public  void setRating(float rating) {
        this.rating = rating;
    }

    String[] readSipp(Map[] sippValues) throws Exception{

        // TODO Add exception
        if(sipp.length() != 4){
            throw new Exception();
        }
        String[] sippData = new String[4];

        sipp = sipp.toUpperCase();
        char[] sippLetters = sipp.toCharArray();

        for(int i = 0; i < sipp.length(); i++){
            String value = (String) sippValues[i].get(sippLetters[i]);
            if(value == null)
                throw new Exception();
            sippData[i] = value;
        }

        return sippData;
    }

    public double sumOfVehicleScore(String transmissionType, String airCon){
        double transmissionPoints;
        double airConPoints;
        switch(transmissionType) {
            case "Manual" : transmissionPoints=1; break;
            case "Automatic" : transmissionPoints=5; break;
            default : transmissionPoints=0;
        }
        switch(airCon) {
            case "AC" : airConPoints=2; break;
            default : airConPoints=0;
        }
        return transmissionPoints + airConPoints;
    }
}