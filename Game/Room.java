import java.util.Arrays;
import java.util.List;

public class Room {
    //private fields
    private String roomName;
    private String shortDescription;
    private String longDescription;
    private String[] roomsConnected;
    private String[] roomInventory;

    //region simple getters

    public boolean isVisited(){
        String[] visitedRooms = JsonDataObjList.getInstance().getPlayerStatus().getRoomsVisited();

        return Arrays.asList(visitedRooms).contains(this.getRoomName());
    }

    public String getRoomName(){ return this.roomName;}

    //provides short description of rooms
    public String getShortDescription(){ return this.shortDescription;}

    //provides long description of room
    public String getLongDescription(){ return this.longDescription; }

    public String[] getRoomInventory() { return this.roomInventory; }

    public String[] getRoomsConnected() { return this.roomsConnected; }
    //endregion

    //region simple setters
    public void setRoomsConnected(String[] roomsConnected) {
        this.roomsConnected = roomsConnected;
    }

    public void setRoomInventory(String[] roomInventory) {
        this.roomInventory = roomInventory;
    }
    //endregion

    //visited status
    public void setVisited(boolean visited) {
        //set to visited
        if(visited) {
            //if this is not visited
            if(!this.isVisited()){
                String[] visitedRooms = JsonDataObjList.getInstance().getPlayerStatus().getRoomsVisited();
                String[] newVisitedRooms = Arrays.copyOf(visitedRooms, visitedRooms.length + 1);
                newVisitedRooms[newVisitedRooms.length - 1] = this.getRoomName();
                JsonDataObjList.getInstance().getPlayerStatus().setRoomsVisited(newVisitedRooms);
            }
        }else{
            if(this.isVisited()){
                String[] visitedRooms = JsonDataObjList.getInstance().getPlayerStatus().getRoomsVisited();
                visitedRooms = Utilities.removeElements(visitedRooms, this.getRoomName());
                JsonDataObjList.getInstance().getPlayerStatus().setRoomsVisited(visitedRooms);
            }
        }
    }

    //determine which description to load based on visited
    public String loadDescription() {
        if (!this.isVisited()) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }

    //=========================== npc loading ===========================

    //find first npc in the room
    public NPC getNpcInThisRoom(){
        List<NPC> listOfNpcs= JsonDataObjList.getInstance().getListOfNPCs();

        for(NPC k: listOfNpcs)
        {
            if(k.getCurrentPosition().equalsIgnoreCase(this.roomName)){
                return k;
            }
        }
        return null;
    }

    public void moveNpcToThisRoom(String npcName)
    {
        List<NPC> listOfNpcs= JsonDataObjList.getInstance().getListOfNPCs();

        for(NPC k: listOfNpcs)
        {
            if(k.getCurrentPosition().equalsIgnoreCase(npcName)){
                k.setCurrentPosition(this.roomName);
            }
        }

        JsonDataObjList.getInstance().UpdateNpcJson();
    }
}
