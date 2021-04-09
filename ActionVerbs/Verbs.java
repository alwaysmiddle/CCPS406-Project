import java.util.*;

public class Verbs {
    //action verbs and the nextPositions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    public static List<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));
    public static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void init(){
        verbs.put("go", 1);
        verbs.put("take", 2);
        verbs.put("inventory", 3);
        //verbs.put("attack", 4);
        verbs.put("use", 5);
        verbs.put("eat", 5);
        verbs.put("look", 6);
        verbs.put("status", 7);
        verbs.put("save", 8);
        verbs.put("load", 9);
        verbs.put("restart", 10);
        verbs.put("start", 10);
        verbs.put("talk", 11);
    }

    //compare user input with verbs and compare nextPosition for output
    public static void IdentifyInput(String actionVerb, String trailingAction){
        Integer act = 0;
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
            //TODO: CHECK PROGRESS
            case 1 -> {
                Room nextPosition = JsonDataObjList.getInstance().getSingleRoom(trailingAction);
                boolean canGoToRoom = Arrays.stream(JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomsConnected()).anyMatch(trailingAction::equalsIgnoreCase);
                if (canGoToRoom) {
                    if (player.getCurrentHP() < 3){
                        System.out.println("[Your HP is very low. Consider repleneshing health with some food.]\n");
                    }
                    if (nextPosition != null){Go.playerMove(nextPosition.getRoomName());}
                    Progress.checkNpcs(actionVerb, trailingAction);
                    break;
                }else if(nextPosition == null && !trailingAction.equalsIgnoreCase("")){
                    System.out.println("Sorry didn't quite get where \"" + trailingAction + "\" is.");
                }else if(!trailingAction.equalsIgnoreCase("")){
                    System.out.println("You can't enter this room from where you are.");
                }
                System.out.println(" You can try heading to the following rooms: ");
                Go.printConnected(player.getCurrentPosition());
                Progress.checkStage(actionVerb, trailingAction);
                JsonDataObjList.getInstance().Save();
            }
            //take the item and put in inventory
            //TODO: CHECK PROGRESS
            case 2 -> {
                if(item != null  && !inventory.contains(item.getItemName())){
                   inventory.add(Take.takeItem(item));
                }else{
                    System.out.println("[How dare you try to take this. This is not yours for the taking.]");
                }
                Progress.checkStage(actionVerb, trailingAction);
            }
            //This is displaying the inventory
            case 3 ->{
                    if(inventory.size() == 0){
                        System.out.println("[You're too poor to display anything. Maybe try grabbing some stale bread?]");
                    }else {
                        //more to inventory class later *************
                        System.out.println("Within your inventory are the following items: ");
                        for (String s : inventory) {
                            System.out.println("  - " + s + ".");
                        }
                    }
            }
            //checking if weapon equipped, if so then attack
            //TODO: needs to be improved upon to handle npc
//            case 4 ->{
//                    //if, then you tell them no
//                    //check if npc is in the room first
//                    //if npc is in the room, then do combat
//
//                    //create class if have time *****************
//                    if (player.getWeaponEquipped() != null){
//                        System.out.println("[You have used "+ player.getWeaponEquipped() + "to attack. You have done " + player.getWeaponValue() + "pts in damage.]\n");
//                    }
//            }
            //Using the item, if edible then remove from inventory
            case 5 -> {
                if(item != null) {
                    //put in useItem method *********************
                    System.out.println(item.getItemDescription() + "\n");
                    if (item.isEdible()) {
                        UseItem.consume(item);
                    }
                }
                Progress.checkStage(actionVerb, trailingAction);
            }
            //This looks around the room inventory for items the user can take, if in inventory cannot take
            case 6 -> {
                List<String> roomInventory = getRoomInventory();
                if (roomInventory.size() != 0) {
                        System.out.println("Here are the items that you can take: \n");
                        for (String r : roomInventory) {
                                System.out.println("  - " + r + ".\n");
                        }
                    }else{
                    System.out.println("Nothing to see here, carry on...");
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
            //save actionVerb
            case 8 -> {
                if(trailingAction.equalsIgnoreCase("")){
                    System.out.println("'Save' action must be followed by a number between 1 to 10!");
                    return;
                }
                //only allow second input to be 1 to 10 integer
                Integer someInt = 0;
                try{
                    someInt = Integer.parseInt(trailingAction);
                    if(someInt < 1 || someInt > 10){
                        System.out.println("You are only allowed to 'Save' to a file slot between number 1 to 10");
                        return;
                    }
                }catch(Exception exception) {
                    exception.printStackTrace();
                }

                JsonDataObjList.getInstance().Save(someInt);
                //successfully saved
                System.out.println("You have sucessfully saved!\n");
            }
            //load action verb
            case 9 -> {
                if(trailingAction == ""){
                    System.out.println("'Load' action must be followed by a number between 1 to 10!");
                    return;
                }

                //when loading the game, only allow second input to be 1 to 10 integer
                int someInt = 0;
                try{
                    someInt = Integer.parseInt(trailingAction);
                    if(someInt < 1 || someInt > 10){
                        System.out.println("You are only allowed to 'Load' to a file slot between number 1 to 10");
                        return;
                    }
                }catch(Exception exception) {
                    exception.printStackTrace();
                }

                JsonDataObjList.getInstance().Load(someInt);
                //load stage world annoucement
                System.out.println("[Welcome back to the Capulet Manor!  in your abscence father has gotten heavily injured.]");
                System.out.println("\n Here is your status!:");
                System.out.println("Your HP: " + player.getCurrentHP());
                System.out.println("Your Weapon: " + player.getWeaponEquipped());
                System.out.println("You are at the " + player.getCurrentPosition());
                //load action description
                System.out.println(("Load completed!"));
            }
            //start a new game
            case 10 -> {
                    GameProgressionData start = JsonDataObjList.getInstance().getListOfProgressionData().get(0);
                    JsonDataObjList.getInstance().resetPlayerStatusToDefault();
                    player = JsonDataObjList.getInstance().getPlayerStatus();
                    System.out.println(start.worldAnnoucement);
            }
            case 11 -> {
                    if (trailingAction.contains("to")){
                        trailingAction = trailingAction.replace("to", "").trim();
                    }
                    Progress.checkNpcs(actionVerb, trailingAction.trim());

            }
        }
    }
    //will get room inventory and compare it to player inventory
    private static List<String> getRoomInventory(){
        String[] roomInventory = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomInventory();
        List<String> newRoomInventory = new ArrayList<>();
        for (String item : roomInventory){
            if (!inventory.contains(item)){
                newRoomInventory.add(item);
            }
        }
        return newRoomInventory;
    }
}
