import java.util.*;

public class Verbs {
    //action verbs and the nextPositions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();
    public static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static final String[] words = {"at ", "up ", "into ", "using ", "the ", "to "};
    private static final String[] verbLang = {"go", "take", "inventory", "use", "eat", "look", "status", "save", "load", "restart", "talk", "start", "attack"};

    //compare user input with verbs and compare nextPosition for output
    public static void IdentifyInput(String actionVerb, String trailingAction){
        String act = "";

        //take split input to compare against verbs
        for (String verb : verbLang){
            if (verb.contains(actionVerb)){act = verb;}
        }
        //will remove the preposition to sanitize input
        for (String preposition: words) {
            if (trailingAction.contains(preposition)){
                trailingAction = trailingAction.replace(preposition, "").trim();
            }
        }

        //will execute different actions depending on the verb
            switch (act) {
                case "" -> {
                    System.out.println("Cannot resolve \"" + actionVerb + "\". Try one of the following: ");
                    for (Map.Entry<String, Integer> entry : verbs.entrySet()) {
                        System.out.println("  - " + entry.getKey());
                    }
                }
            //going somewhere
            case "go" -> Go.playerMove(actionVerb, trailingAction);
            //take the item and put in inventory
            //then check if the obtained item has triggered a progress in the story
            case "take" -> {
                Inventory.takeItem(trailingAction.trim());
                Progress.checkStage(actionVerb, trailingAction);
            }
            //This is displaying the inventory
            case "inventory" -> Inventory.displayInventory();
            //checking if weapon equipped, if so then attack
            case "attack" ->{
                // public static NPC npc = JsonDataObjList.getSingleRoom("Room_name").getNpcInThisRooom();
                NPC npc = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getNpcInThisRoom();
                if (player.getWeaponValue() > 0 && npc != null){
                    Combat.doCombatWithNpc(player, npc.getNpcName());
                }
                else {
                    System.out.println("You do not have a weapon.");
                    Combat.doHiding(player);
                }
            }
            //Using the item, if edible then remove from inventory
            //Will also check if using some item will progress the story
            case "eat", "use" -> {
                Inventory.useItem(trailingAction.trim());
                Progress.checkStage(actionVerb, trailingAction);
            }

            //This looks around the room inventory for items the user can take, if in inventory cannot take
            case "look" -> Inventory.roomInventoryLook();

            //tells the user their health, weapond equipped, and where they are
            case "status" -> {
                    System.out.println("Your HP: " + player.getCurrentHP());
                    System.out.println("Your Weapon: " + player.getWeaponEquipped());
                    System.out.println("You are at the " + player.getCurrentPosition());
                    if(player.isUnderworld()){
                        System.out.println("\n[You are in the Underworld. Be careful since all NPCs are aggressive.]");
                    }
            }
            //save actionVerb
            case "save" -> {
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
            case "load" -> {
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
            case "start"-> {
                    GameProgressionData start = JsonDataObjList.getInstance().getListOfProgressionData().get(0);
                    JsonDataObjList.getInstance().resetPlayerStatusToDefault();
                    player = JsonDataObjList.getInstance().getPlayerStatus();
                    System.out.println(start.worldAnnouncement);
            }
            case "talk" -> Progress.checkNpcs(actionVerb, trailingAction.trim());
        }
    }

}
