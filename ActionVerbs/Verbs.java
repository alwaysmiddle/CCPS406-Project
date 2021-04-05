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
        PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
        Room direction = null;
        Item item = null;
        //Peter's notes here
        //String[] ListofConnectedRoomsForHallway1 = JsonDataObjList.getInstance().getSingleRoom("hallway 1").getRoomsConnected();
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
                    if (player.getCurrentHP() < 3){
                        Console.textArea.setText("Your HP is very low. Consider repleneshing health with some food.");
                    }
                    Go.playerMove(direction.getRoomName());
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
                    Console.textArea.setText("Within your inventory are the following items: \n");
                    for (String s : inventory) {
                        Console.textArea.append("  - " + s + ".\n");
                    }
            }
            case 4 ->{
                    if (player.getWeaponEquipped() != null){
                        Console.textArea.setText("Congratulations! You have used "+ player.getWeaponEquipped() + "to attack. You have done " + player.getWeaponValue() + "pts in damage.\n");
                        Console.textArea.append("Your item has been used. Look for a new one or be cautious. Your fate is in your hands.");
                        player.setWeaponEquipped(null);
                        player.setWeaponValue(0);
                    }
            }
            case 5 -> {
                Console.textArea.setText("You have used the item" + item.getItemName() +".");
                if (item.isEdible()) {
                    Console.textArea.append(" Not the greatest meal, but it'll do. Your health has increased 2 points.");
                    player.setCurrentHP(player.getCurrentHP()+2);
                }
                inventory.remove(item.getItemName());
                player.setPlayerInventory(inventory.toArray(String[]::new));
            }
        }
    }
}
