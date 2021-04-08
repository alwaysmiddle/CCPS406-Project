import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static String[] removeElements(String[] input, String deleteMe) {
        List result = new ArrayList();

        for(String item : input)
            if(!deleteMe.equals(item)) {
                result.add(item);
            }

        return (String[])result.toArray(input);
    }
}
