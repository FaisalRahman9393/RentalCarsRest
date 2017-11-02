/**
 * A simple runner program used for debugging classes without having to start the server
 */

public class consoleRunner {

    public static void main(String[] args) {
        RentalCars v = new RentalCars();

        try {
            v.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n---------\nCars, with their prices(ascending)\n-----------");
        System.out.println(v.carsAscending());

        System.out.println("\n------\nCar spec by their SIPP\n-----------");
        System.out.println(v.specBySIPP());

        System.out.println("\n------\nCars with the highest rated supplier\n-----------");
        System.out.println(v.ratedSupplierDescending());

        System.out.println("\n------\nCars with the highest sum of the scores in descending order\n-----------");
        System.out.println(v.sumRatingDescending());
    }

}