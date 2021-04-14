import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class Take {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static ArrayList<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));
    private static Map<String, Item> itemList = JsonDataObjList.getInstance().getItemsHashmap();

    //move to Inventory class?
    //idk ANYMORE
    //TODO: for Ash
    public static void takeItem(String itemInput){
        Item item = JsonDataObjList.getInstance().getSingleItem(itemInput);
        if (item != null && !inventory.contains(item.getItemName())) {
            System.out.println("Congratulations. You have finally obtained " + item.getItemName() + ".");
            inventory.add(item.getItemName());
            if (item.isWeapon()) {
                player.setWeaponEquipped(item.getItemName());
            }
            JsonDataObjList.getInstance().getPlayerStatus().setPlayerInventory(inventory.toArray(String[]::new));
        }else{
            System.out.println("You cannot take "+ itemInput);
        }
    }
}
