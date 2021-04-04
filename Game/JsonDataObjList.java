import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDataObjList {
    //private fields
    private static Map<String, Room> _mapOfRooms = new HashMap<String, Room>;
    private static Map<String, Item> _mapOfItems = new HashMap<String, Item>;
    private static JsonDataObjList singletonInstance = null;

    //constructor
    public JsonDataObjList() {
        //load the list of json objects into public fields for rest of the program to work with
        List<Room> _listOfRooms = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Room>>(){}, GlobalReference.ROOM_JSON_FILE_LOCATION);
        _mapOfRooms = _listOfRooms.stream().collect(Room.)

        List<Item> _listOfItems = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Item>>(){}, GlobalReference.ITEM_JSON_FILE_LOCATION);
        _mapOfItems

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
