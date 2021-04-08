import java.util.Arrays;

public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMove(String selectedRoomName){
        //access player current position
        String currentRoomName = JsonDataObjList.getInstance ().getPlayerStatus().getCurrentPosition();
        Room nextRoom = JsonDataObjList.getInstance().getSingleRoom(selectedRoomName);

        System.out.println("You are currently at " + currentRoomName + ".\n");
        System.out.println("You have moved to the " + nextRoom.getRoomName() + ".\n\n");
        System.out.println(nextRoom.loadDescription());
        nextRoom.setVisited(true);
        player.setCurrentPosition(nextRoom.getRoomName());
        player.setCurrentHP((player.getCurrentHP()-1));
        //move to one of the locations
    }
    public static void printConnected(String roomName){
        String[] allNames = JsonDataObjList.getInstance().getSingleRoom(roomName).getRoomsConnected();
        for (String nm : allNames){
            System.out.println("  - " + nm + "\n");
        }
    }
}
