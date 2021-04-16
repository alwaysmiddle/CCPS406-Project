import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Inventory {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static ArrayList<String> inventory = new ArrayList<>(Arrays.asList(player.getPlayerInventory()));
    private static List<NPC> allNPC = JsonDataObjList.getInstance().getListOfNPCs();

    //wil display all items that are in the inventory, if there are none then default message
    public static void displayInventory(){
        if(inventory.size() == 0){
            System.out.println("\n[There is nothing to eat or use in your inventory...]");
        }else {
            //more to inventory class later *************
            System.out.println("Within your inventory are the following items: ");
            for (String s : inventory) {
                System.out.println("  - " + s + ".");
            }
        }
    }
    //will take the item and add it to inventory
    //if item in the inventory then won't add
    public static void takeItem(String itemInput){
        Item item = JsonDataObjList.getInstance().getSingleItem(itemInput);
        if (item != null && !inventory.contains(item.getItemName())) {
            System.out.println("Congratulations. You have finally obtained " + item.getItemName() + ".");
            inventory.add(item.getItemName());
            if (item.isWeapon()){
                player.setWeaponEquipped(item.getItemName());
                player.setWeaponValue(item.getIntValue());
            }
            JsonDataObjList.getInstance().getPlayerStatus().setPlayerInventory(inventory.toArray(String[]::new));
            JsonDataObjList.getInstance().Save();
        }else{
            System.out.println("You cannot take "+ itemInput);
        }
    }

    //will use the item and if the item is edible then show a special message
    //if the item is the gemstone then display the default switch message
    public static void useItem(String itemInput){
        Item item = JsonDataObjList.getInstance().getSingleItem(itemInput);
        if(item != null && inventory.contains(item.getItemName())) {
            System.out.println(item.getItemDescription() + "\n");
            //eat the food, update health and inventory
            if (item.isEdible()){
                System.out.println("[You have used the " + item.getItemName() + ". Not the greatest meal, but it'll do. " +
                        "Your health has increased " + item.getIntValue() + " points.]\n");
                player.setCurrentHP(player.getCurrentHP()+ item.getIntValue());
                inventory.remove(item.getItemName());
                player.setPlayerInventory(inventory.toArray(new String[0]));
                displayInventory();
                //transport player to the other world
            }else if(item.getItemName().equalsIgnoreCase("Gemstone")){
                String transport;
                if (player.isUnderworld()){
                    player.setUnderworld(false);
                    transport = "read world.";
                }else{
                    player.setUnderworld(true);
                    transport = "Underworld";
                }
                for (NPC npc: allNPC) {npc.setAggressive(player.isUnderworld());}
                System.out.println("You have been transported to the " + transport);
            }
            inventory = new ArrayList<>(Arrays.asList(player.getPlayerInventory()));
            JsonDataObjList.getInstance().Save();
        }else{
            System.out.println("You can't use " + itemInput);
        }
    }
    //will look in the room and display the item if it isn't already in the room
    public static void roomInventoryLook(){
        List<String> roomInventory = Inventory.getRoomInventory();
        if (roomInventory.size() != 0) {
            System.out.println("Here are the items that you can take: \n");
            for (String r : roomInventory){
                if (!inventory.contains(r)){System.out.println("  - " + r + ".\n");}
            }
        }else{
            System.out.println("Nothing to see here, carry on...");
        }
    }


    //will get room inventory and compare it to player inventory
    //only displays items that aren't in the inventory
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
