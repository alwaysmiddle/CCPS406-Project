import java.util.*;

public class Verbs {
    //action verbs and the nextPositions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    public static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static final String[] words = {"to ", "at ", "up ", "into ", "using ", "the "};
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

        //take split input to compare against verbs
        if (verbs.containsKey(actionVerb)){
            act = verbs.get(actionVerb);
        }
        //will remove the preposition to sanitize input
        for (String preposition: words) {
            if (trailingAction.contains(preposition)){
                trailingAction = trailingAction.replace(preposition, "").trim();
            }
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
            case 1 -> Go.playerMove(actionVerb, trailingAction);
            //take the item and put in inventory
            //then check if the obtained item has triggered a progress in the story
            case 2 -> {
                Inventory.takeItem(trailingAction.trim());
                Progress.checkStage(actionVerb, trailingAction);
            }
            //This is displaying the inventory
            case 3 -> Inventory.displayInventory();
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
            //Will also check if using some item will progress the story
            case 5 -> {
                Inventory.useItem(trailingAction.trim());
                Progress.checkStage(actionVerb, trailingAction);
            }
            //This looks around the room inventory for items the user can take, if in inventory cannot take
            case 6 -> Inventory.roomInventoryLook();

            //tells the user where they are
            case 7 -> {
                    System.out.println("Your HP: " + player.getCurrentHP());
                    System.out.println("Your Weapon: " + player.getWeaponEquipped());
                    System.out.println("You are at the " + player.getCurrentPosition());
                    if(player.isUnderworld()){
                        System.out.println("\n[You are in the Underworld. Be careful since all NPCs are aggressive.]");
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
                if(trailingAction.equals("")){
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
                System.out.println("[Welcome back to the Capulet Manor! In your abscence father has gotten heavily injured.]");
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
            case 11 -> Progress.checkNpcs(actionVerb, trailingAction.trim());
        }
    }

}
