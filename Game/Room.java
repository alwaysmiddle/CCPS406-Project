public class Room {
    //private fields
    private String roomName;
    private String shortDescription;
    private String longDescription;
    private boolean visited;
    private String[] roomsConnected;
    private String[] roomInventory;

    //region getters
    //indicates whether a room has been visited or NOT
    public boolean isVisited(){ return visited; }

    public String getRoomName(){ return roomName;}

    //provides short description of rooms
    public String getShortDescription(){ return shortDescription;}

    //provides long description of room
    public String getLongDescription(){ return longDescription; }

    public String[] getRoomInventory() { return roomInventory; }

    public String[] getRoomsConnected() { return roomsConnected; }
    //endregion

    //region setters
    public void setVisited(boolean visited) {
        this.visited = visited;
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
        if (!visited) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }
}
