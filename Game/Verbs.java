import java.util.*;

public class Verbs {
    //action verbs and the directions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    private static final String[] LIST = {"north", "south", "east", "west"};
    public static final List<String> compass = Arrays.asList(LIST);

    public static void main(String... args){
        verbs.put("go", 1);
        verbs.put("take", 2);
        verbs.put("inventory", 3);
        verbs.put("attack", 4);
        verbs.put("use", 5);
        verbs.put("eat", 5);
        IdentifyInput();
    }

    //compare user input with verbs and compare direction for output
    public static void IdentifyInput(){
        String[] split = Console.input.trim().split(" ");
        Integer act = 0;
        String direction = "some direction.";
        String someItem = "bread";
        int count = 0;
        //after splitting the input compare the input with the verbs and get direction
        //need to add items to this one that has been completed
        for (String s : split) {

            if (verbs.containsKey(s)) {
                act = verbs.get(s);
            }
            if (compass.contains(s)) {
                direction = s;
            }
        }
        //will execute different actions depending on the verb
        switch (act) {
            case 0 -> {
                Console.textArea.setText("Cannot resolve \"" + split[0] + "\". Try one of the following: \n");
                for (Map.Entry<String, Integer> entry : verbs.entrySet()) {
                    count++;
                    System.out.println(count + ". " + entry.getKey());
                }
            }
            case 1 -> {
                if (direction.equals("some direction.")) {
                    Console.textArea.setText("Where are you headed? Please specify. You can go: \n");
                    for (String s : compass) {
                        count++;
                        System.out.println(count + ". " + s);
                    }
                    break;
                }
                Console.textArea.setText("You are headed " + direction + ".");
            }
            case 2 -> Console.textArea.setText("Congratulations. You have finally obtained " + someItem + ".");
            case 3 -> Console.textArea.setText("Within your inventory are the following items: \n" + someItem);
            case 4 -> Console.textArea.setText("You have used " + someItem + "  to attack.");
            case 5 -> {
                Console.textArea.setText("You have used the item" + someItem);
                if (someItem.equals("bread")) {
                    Console.textArea.append("\n Not the greatest meal, but it'll do. Your health has increased 2 points.");
                }
            }
        }
    }


}
