import java.util.Arrays;

public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMove(String selectedRoomName){
        //access player current position
        String currentRoomName = JsonDataObjList.getInstance ().getPlayerStatus().getCurrentPosition();
        Room nextRoom = JsonDataObjList.getInstance().getSingleRoom(selectedRoomName);

        Console.textArea.setText("You are currently at " + currentRoomName + ".\n");
        Console.textArea.append("You have moved to the " + nextRoom.getRoomName() + ".\n\n");
        Console.textArea.append(nextRoom.loadDescription());
        player.setCurrentPosition(nextRoom.getRoomName());
        player.setCurrentHP((player.getCurrentHP()-1));
        //move to one of the locations
    }
    public static void printConnected(String roomName){
        System.out.println(Arrays.toString(JsonDataObjList.getInstance().getSingleRoom(roomName).getRoomsConnected()));
    }
}
