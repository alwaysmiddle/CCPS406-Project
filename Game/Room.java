import java.util.Arrays;

public class Room {
    //private fields
    private String roomName;
    private String shortDescription;
    private String longDescription;
    private String npc;
    private String[] roomsConnected;
    private String[] roomInventory;

    //region getters
    //indicates whether a room has been visited or NOT
    public boolean isVisited(){
        String[] visitedRooms = JsonDataObjList.getInstance().getPlayerStatus().getRoomsVisited();

        return Arrays.asList(visitedRooms).contains(this.getRoomName());
    }

    public String getRoomName(){ return this.roomName;}

    public String getNpc(){return this.npc;}

    //provides short description of rooms
    public String getShortDescription(){ return this.shortDescription;}

    //provides long description of room
    public String getLongDescription(){ return this.longDescription; }

    public String[] getRoomInventory() { return this.roomInventory; }

    public String[] getRoomsConnected() { return this.roomsConnected; }
    //endregion

    //region setters
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

    public void setRoomsConnected(String[] roomsConnected) {
        this.roomsConnected = roomsConnected;
    }

    public void setRoomInventory(String[] roomInventory) {
        this.roomInventory = roomInventory;
    }
    //endregion

    //determine which description to load
    public String loadDescription() {
        if (!this.isVisited()) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }
}
