import java.util.HashMap;
import java.util.Map;

/**
 * Created by Faze on 30/10/2017.
 */
public class SippList {

    public static Map[] makeSippList(){
        // In the future the dictionaries could be imported so they can be easily maintained and modified independently of this program
        Map<Character, String> sippFirst = new HashMap<Character, String>() {{
            put('M', "Mini");
            put('E', "Economy");
            put('C', "Compact");
            put('I', "Intermediate");
            put('S', "Standard");
            put('F', "Full size");
            put('P', "Premium");
            put('L', "Luxury");
            put('X', "Special");
        }};

        Map<Character, String> sippSecond = new HashMap<Character, String>() {{
            put('B', "2 doors");
            put('C', "4 doors");
            put('D', "5 doors");
            put('W', "Estate");
            put('T', "Convertible");
            put('F', "SUV");
            put('P', "Pick up");
            put('V', "Passenger Van");
        }};

        Map<Character, String> sippThird = new HashMap<Character, String>() {{
            put('M', "Manual");
            put('A', "Automatic");
        }};

        // Since the Value indicated two separate properties (Fuel and Air Con) we can either use a different data type then String (maybe a string array)
        // or manipulate the string later when we need to separate the two properties (for example before we print them)
        Map<Character, String> sippFourth = new HashMap<Character, String>() {{
            put('N', "Petrol/no AC");
            put('R', "Petrol/AC");
        }};

        Map[] sippValues = new Map[]{sippFirst, sippSecond, sippThird, sippFourth};

        return sippValues;
    }
}
