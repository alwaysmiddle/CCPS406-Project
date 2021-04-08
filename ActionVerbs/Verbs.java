import java.util.*;

public class Verbs {
    //action verbs and the nextPositions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    public static List<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));

    public static void init(String... args){
        verbs.put("go", 1);
        verbs.put("take", 2);
        verbs.put("inventory", 3);
        verbs.put("attack", 4);
        verbs.put("use", 5);
        verbs.put("eat", 5);
        verbs.put("look", 6);
        verbs.put("status", 7);
        verbs.put("start", 8);
    }

    //compare user input with verbs and compare nextPosition for output
    public static void IdentifyInput(String actionVerb, String trailingAction){
        Integer act = 0;
        PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
        Item item = JsonDataObjList.getInstance().getSingleItem(trailingAction);
        //Peter's notes here
        //String[] ListofConnectedRoomsForHallway1 = JsonDataObjList.getInstance().getSingleRoom("hallway 1").getRoomsConnected();

        //after splitting the input compare the input with the verbs and get nextPosition
        //need to add items to this one that has been completed
        if (verbs.containsKey(actionVerb)){
            act = verbs.get(actionVerb);
        }

        //will execute different actions depending on the verb
            switch (act) {
                case 0 -> {
                    System.out.println("Cannot resolve \"" + actionVerb + "\". Try one of the following: ");
                    for (Map.Entry<String, Integer> entry : verbs.entrySet()) {
                        System.out.println("  - " + entry.getKey());
                    }
                }
            //going somewhere
            case 1 -> {
                Room nextPosition = JsonDataObjList.getInstance().getSingleRoom(trailingAction);
                if (nextPosition != null) {
                    if (player.getCurrentHP() < 3){
                        System.out.println("[Your HP is very low. Consider repleneshing health with some food.]\n");
                    }
                    Go.playerMove(nextPosition.getRoomName());
                    break;
                } else if(!trailingAction.equals("")){
                    System.out.println("Sorry didn't quite get where \"" + trailingAction + "\" is.");
                }
                    System.out.println(" You can try heading to the following rooms: ");
                    Go.printConnected(player.getCurrentPosition());
            }
            //take the item and put in inventory
            case 2 -> {
                if(item != null  && !inventory.contains(item.getItemName())){
                   inventory.add(Take.takeItem(item.getItemName()));
                }else{
                    System.out.println("[How dare you try to take this. This is not yours for the taking.]");
                }
            }
            //This is displaying the inventory
            case 3 ->{
                    if(inventory.size() < 1){
                        System.out.println("[You're too poor to display anything. Maybe try grabbing some stale bread?]");
                    }else {
                        //more to inventory class later *************
                        System.out.println("Within your inventory are the following items: ");
                        for (String s : inventory) {
                            Console.textArea.append("  - " + s + ".");
                        }
                    }
            }
            //checking if weapon equipped, if so then attack
            //TODO: needs to be improved upon to handle npc
            case 4 ->{
                    //create class if have time *****************
                    if (player.getWeaponEquipped() != null){
                        System.out.println("[Congratulations! You have used "+ player.getWeaponEquipped() + "to attack. You have done " + player.getWeaponValue() + "pts in damage.]\n");
                    }
            }
            //Using the item, if edible then remove from inventory
            case 5 -> {
                    //put in useItem method *********************
                System.out.println(item.getItemDescription() + "\n");
                if (item.isEdible()) {
                    UseItem.consume(item);
//                    System.out.println("You have used the " + item.getItemName() + "Not the greatest meal, but it'll do. Your health has increased 2 points.");
//                    player.setCurrentHP(player.getCurrentHP()+2);
//                    inventory.remove(item.getItemName());
//                    player.setPlayerInventory(inventory.toArray(String[]::new));
                }
            }
            //This looks around the room inventory for items the user can take, if in inventory cannot take
            case 6 -> {
                    System.out.println("Here are the items that you can take: \n");
                    String[] roomInventory = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomInventory();
                    for (String r : roomInventory){
                        if(!inventory.contains(r)){
                            System.out.println("  - " +r + ".\n");
                        }
                    }
            }
            //tells the user where they are
            case 7 -> {
                    System.out.println("Your HP: " + player.getCurrentHP());
                    System.out.println("Your Weapon: " + player.getWeaponEquipped());
                    System.out.println("You are at the " + player.getCurrentPosition());
                    if(player.isUnderworld()){
                        System.out.println("[You are in the Underworld. Be careful since all NPCs are aggressive.]");
                    }
            }
            //start actionVerb
            case 8 -> {
                    //TODO: call first stage world annoucement
                    //System.out.println(); add in the JsonDataObjList.getInstance().getStage("Stage 1");
            }
        }
    }
}
