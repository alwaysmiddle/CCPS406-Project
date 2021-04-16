import java.util.Arrays;
import java.util.List;

public class Go {
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    public static void playerMove(String actionVerb, String trailingAction) {
            //access player current position
            boolean canGoToRoom = Arrays.stream(JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomsConnected()).anyMatch(trailingAction::equalsIgnoreCase);
            Room nextPosition = JsonDataObjList.getInstance().getSingleRoom(trailingAction);
            if (canGoToRoom) {
                if (player.getCurrentHP() < 3) {
                    System.out.println("[Your HP is very low. Consider repleneshing health with some food.]\n");
                }
                if (nextPosition != null){
                    String currentRoomName = JsonDataObjList.getInstance().getPlayerStatus().getCurrentPosition();
                    System.out.println("You are currently at " + currentRoomName + ".");
                    System.out.println("You have moved to the " + nextPosition.getRoomName() + ".\n");
                    System.out.println(nextPosition.loadDescription());
                    nextPosition.setVisited(true);
                    player.setCurrentPosition(nextPosition.getRoomName());
                    player.setCurrentHP((player.getCurrentHP() - 1));
                }
                Progress.checkNpcs(actionVerb, trailingAction);
                return;
            }else if(nextPosition == null && !trailingAction.equalsIgnoreCase("")) {
                System.out.println("Sorry didn't quite get where \"" + trailingAction + "\" is.");
            }else if(!trailingAction.equalsIgnoreCase("")){
                System.out.println("You can't enter this room from where you are.");
            }
            System.out.println(" You can try heading to the following rooms: ");
            printConnected();
            Progress.checkStage(actionVerb, trailingAction);
            JsonDataObjList.getInstance().Save();
        //move to one of the locations
    }

    public static void printConnected(){
        String[] allNames = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomsConnected();
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
