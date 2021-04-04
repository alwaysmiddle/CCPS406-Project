import java.util.ArrayList;
import java.util.List;

public class JsonDataObjList {
    //private fields
    private static List<Room> _listOfRooms = new ArrayList<Room>();
    private static List<Item> _listOfItems = new ArrayList<Item>();
    private static JsonDataObjList singletonInstance = null;

    //constructor
    public JsonDataObjList() {
        //load the list of json objects into public fields for rest of the program to work with
        _listOfRooms = JsonDataFileIO.getInstance().ReadFile(GlobalReference.ROOM_JSON_FILE_LOCATION);

    }

    public void Save()
    {
        //save the list of rooms
        //save the list of items
        //save the player status

    }

    public Room getSingleRoom(String roomName){

        return null;
    }

    public Room getSingleItem(String roomName){

        return null;
    }


    public List<Room> getRoomsHashmap() {
        //return hashmap
        return _listOfRooms;

    }

    public List<Room> getItemsHashmap()
    {
        //return hashmap
        return _listOfItems;

    }


    public static JsonDataObjList getInstance()
    {
        if (singletonInstance == null){
            singletonInstance = new JsonDataObjList();
        }
        return singletonInstance;
    }
}
