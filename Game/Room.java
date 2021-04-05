public class Room {
    //private fields
    private String roomName;
    private String shortDescription;
    private String longDescription;
    private boolean visited;
    private String[] roomsConnected;
    private String[] roomInventory;

    //public constructor, do not recommend using
    public Room(String roomName, String shortDescription, String longDescription, boolean visited, String[] roomsConnected, String[] roomInventory) {
        this.roomName = roomName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.visited = visited;
        this.roomsConnected = roomsConnected;
        this.roomInventory = roomInventory;
    }

    //indicates whether a room has been visited or NOT
    public boolean isVisited(){ return visited; }

    public String getRoomName(){ return roomName;}

    //provides short description of rooms
    public String getShortDescription(){ return shortDescription;}

    //provides long description of room
    public String getLongDescription(){ return longDescription; }

    //determine which description to load
    public String loadDescription() {
        if (!visited) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }

    public String[] getConnectedRooms(){
        return roomsConnected;
    }
}
