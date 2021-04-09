import java.util.Arrays;
import java.util.List;

public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMove(String selectedRoomName) {
            //access player current position
            String currentRoomName = JsonDataObjList.getInstance().getPlayerStatus().getCurrentPosition();
            Room nextRoom = JsonDataObjList.getInstance().getSingleRoom(selectedRoomName);
            System.out.println("You are currently at " + currentRoomName + ".");
            System.out.println("You have moved to the " + nextRoom.getRoomName() + ".\n");
            System.out.println(nextRoom.loadDescription());
            nextRoom.setVisited(true);
            player.setCurrentPosition(nextRoom.getRoomName());
            player.setCurrentHP((player.getCurrentHP() - 1));
        //move to one of the locations
    }
    public static void printConnected(String roomName){
        String[] allNames = JsonDataObjList.getInstance().getSingleRoom(roomName).getRoomsConnected();
        List<String> inventory = Arrays.asList(player.getPlayerInventory());
        for (String nm : allNames){
            if(nm.equalsIgnoreCase("Underworld")){
                if(!inventory.contains("Gemstone") || player.isUnderworld()){
                    continue;
                }
            }else if(nm.equalsIgnoreCase("Real World")){
                if (!inventory.contains("Gemstone") || !player.isUnderworld()){
                continue;
                }
            }
            System.out.println("  - " + nm + "\n");
        }
    }
}
