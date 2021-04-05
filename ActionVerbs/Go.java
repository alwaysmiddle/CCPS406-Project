public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMoveCommand(String selectedRoomName){
        //access player current position
        String currentRoomName = JsonDataObjList.getInstance ().getPlayerStatus().getCurrentPosition();
        Room nextRoom = JsonDataObjList.getInstance().getSingleRoom(selectedRoomName);

        Console.textArea.setText("You are currently at " + nextRoom.getRoomName() + ".\n");
        Console.textArea.setText("You are headed to the " + nextRoom.getRoomName() + ".\n");
        Console.textArea.append(nextRoom.loadDescription());
        player.setCurrentPosition(nextRoom.getRoomName());
        System.out.println();
        //move to one of the locations
    }
}
