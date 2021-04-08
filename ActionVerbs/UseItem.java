import java.util.ArrayList;
import java.util.Arrays;

public class UseItem {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();

    public static void consume(Item item){
        ArrayList<String> inventory = new ArrayList<>(Arrays.asList(JsonDataObjList.getInstance().getPlayerStatus().getPlayerInventory()));
        System.out.println("[You have used the " + item.getItemName() + "Not the greatest meal, but it'll do. Your health has increased 2 points.]");
        player.setCurrentHP(player.getCurrentHP()+2);
        inventory.remove(item.getItemName());
        player.setPlayerInventory(inventory.toArray(String[]::new));
    }
}

