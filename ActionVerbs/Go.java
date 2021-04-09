import java.util.Arrays;

public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMove(String selectedRoomName){
        if (player.getCurrentPosition().equalsIgnoreCase(selectedRoomName)){
            System.out.println("You are currently in" + player.getCurrentPosition());
        }else {
            //access player current position
            String currentRoomName = JsonDataObjList.getInstance().getPlayerStatus().getCurrentPosition();
            Room nextRoom = JsonDataObjList.getInstance().getSingleRoom(selectedRoomName);
            System.out.println("You are currently at " + currentRoomName + ".");
            System.out.println("You have moved to the " + nextRoom.getRoomName() + ".\n");
            System.out.println(nextRoom.loadDescription());
            nextRoom.setVisited(true);
            player.setCurrentPosition(nextRoom.getRoomName());
            player.setCurrentHP((player.getCurrentHP() - 1));
        }
        //move to one of the locations
    }
    public static void printConnected(String roomName){
        String[] allNames = JsonDataObjList.getInstance().getSingleRoom(roomName).getRoomsConnected();
        for (String nm : allNames){
            if(player.getCurrentStage() < 5 && nm.equalsIgnoreCase("Underworld")){
                continue;
            }
            System.out.println("  - " + nm + "\n");
        }
    }
}
