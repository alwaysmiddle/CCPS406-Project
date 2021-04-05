import java.util.*;

public class Verbs {
    //action verbs and the directions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    public static List<String> inventory = Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory());

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
        PlayerStatus player = null;
        Room direction = null;
        Item item = null;
        List<String> ListofConnectedRooms = Arrays.asList(JsonDataObjList.getInstance().getSingleRoom(JsonDataObjList.getInstance().getPlayerStatus().getCurrentPosition()).getRoomsConnected());

        //after splitting the input compare the input with the verbs and get direction
        //need to add items to this one that has been completed
        for (String s : split) {

            if (verbs.containsKey(s)) {
                act = verbs.get(s);
            }
            direction = JsonDataObjList.getInstance().getSingleRoom(s);
            item = JsonDataObjList.getInstance().getSingleItem(s);
        }
        //will execute different actions depending on the verb
        switch (act) {
            case 0 -> {
                Console.textArea.setText("Cannot resolve \"" + split[0] + "\". Try one of the following: \n");
                for (Map.Entry<String, Integer> entry : verbs.entrySet()) {
                    Console.textArea.append("  - " + entry.getKey());
                }
            }
            case 1 -> {
                if (direction != null) {
                    Console.textArea.setText("You are headed to the " + direction.getRoomName() + ".\n");
                    Console.textArea.append(direction.loadDescription());
                    player.setCurrentPosition(direction.getRoomName());
                    System.out.println();
                } else {
                    Console.textArea.setText("You can head to the following rooms: \n");
                    for (String listofConnectedRoom : ListofConnectedRooms) {
                        Console.textArea.append("  - " + listofConnectedRoom + "\n");
                    }
                }
            }
            case 2 -> {
                if(item != null){
                    Console.textArea.setText("Congratulations. You have finally obtained " + item.getItemName() + ".");
                    inventory.add(item.getItemName());
                    JsonDataObjList.getInstance().getPlayerStatus().setPlayerInventory(inventory.toArray(String[]::new));
                }else{
                    Console.textArea.setText("How dare you try to take this. This is not yours for the taking.");
                }
            }
            case 3 ->{
                Console.textArea.setText("Within your inventory are the following items: \n" + item);
                for (String s : inventory) {
                    Console.textArea.append("  - " + s + ".\n");
                }
            }
            case 4 -> Console.textArea.setText("You have used " + item + "  to attack.");
            case 5 -> {
                Console.textArea.setText("You have used the item" + item +".");
                if (item.isEdible()) {
                    Console.textArea.append(" Not the greatest meal, but it'll do. Your health has increased 2 points.");
                }
            }
        }
    }
}
