import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class Take {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static ArrayList<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));
    private static Map<String, Item> itemList = JsonDataObjList.getInstance().getItemsHashmap();

    public static String takeItem(String itemName){
        System.out.println("Congratulations. You have finally obtained " + itemName + ".");
        inventory.add(itemName);
        JsonDataObjList.getInstance().getPlayerStatus().setPlayerInventory(inventory.toArray(String[]::new));
        checkStage(itemName);
        return itemName;
    }

    private static void checkStage(String itemName){
        for (Map.Entry<String, Item> entry : itemList.entrySet()){
            if (entry.getValue().getProgressRequirement().equalsIgnoreCase(itemName)){
                System.out.println("You have unlocked a piece of the puzzle. Welcome to the next stage");//TODO: once added in dialogue handling use nextStage string
                player.getCurrentStage(); //use current stage to get find in dialogue and get next stage, and print text
            }
        }

    }
}
