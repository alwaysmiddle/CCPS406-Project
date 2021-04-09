import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class Take {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static ArrayList<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));
    private static Map<String, Item> itemList = JsonDataObjList.getInstance().getItemsHashmap();

    public static String takeItem(Item item){
        System.out.println("Congratulations. You have finally obtained " + item.getItemName() + ".");
        inventory.add(item.getItemName());
        if(item.isWeapon()){
            player.setWeaponEquipped(item.getItemName());
        }
        JsonDataObjList.getInstance().getPlayerStatus().setPlayerInventory(inventory.toArray(String[]::new));
        return item.getItemName();
    }
}

