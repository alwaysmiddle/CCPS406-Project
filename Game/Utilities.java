import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilities {
    public static String[] removeElements(String[] input, String deleteMe) {
        List result = new ArrayList();

        for(String item : input)
            if(!deleteMe.equals(item)) {
                result.add(item);
            }

        return (String[])result.toArray(input);
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
