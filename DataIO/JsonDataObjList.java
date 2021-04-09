import com.google.gson.reflect.TypeToken;
import java.util.*;

public class JsonDataObjList {
    //private fields
    private static Map<String, Room> _mapOfRooms = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, Item> _mapOfItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, NPC> _mapOfNpcs = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private static PlayerStatus _player = new PlayerStatus();
    private List<Room> _listOfRooms;
    private List<Item> _listOfItems;
    private List<PlayerStatus> _players;
    private List<NPC> _listOfNPCs;
    private List<GameProgressionData> _listOfProgressData;

    private static JsonDataObjList singletonInstance = null;

    //constructor
    public JsonDataObjList() {
        //load the list of json objects into public fields for rest of the program to work with

        //Load Player status of the player's choice of saves
        if(GameSaveSystem.getCurrentSaveIndex() == 0) {
            this.resetPlayerStatusToDefault();
        }else{
            _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
        }


        //rooms
        _listOfRooms = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Room>>(){}.getType(), GlobalReference.ROOM_JSON_FILE_LOCATION);
        for (Room r: _listOfRooms) {
            _mapOfRooms.put(r.getRoomName().toLowerCase(), r);
        }

        //items
        _listOfItems = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Item>>(){}.getType(), GlobalReference.ITEM_JSON_FILE_LOCATION);
        for (Item t: _listOfItems) {
            _mapOfItems.put(t.getItemName().toLowerCase(), t);
        }

        //NPCs
        _listOfNPCs = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<NPC>>(){}.getType(), GlobalReference.NPC_FILE_LOCATION);

        //Progression data
        _listOfProgressData = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<GameProgressionData>>(){}.getType(), GlobalReference.PROGRESSION_FILE_LOCATION);
    }

    public void Save()
    {
        //save to the save files loation

//        //save the list of rooms
//        JsonDataFileIO.writeJsonFile(_listOfRooms, GlobalReference.ROOM_JSON_FILE_LOCATION);
//
//        //save the list of items
//        JsonDataFileIO.writeJsonFile(_listOfItems, GlobalReference.PLAYER_STATUS_FILE_LOCATION);
//
//        //save the player status
//        JsonDataFileIO.writeJsonFile(_players, GlobalReference.PLAYER_STATUS_FILE_LOCATION);
    }

    public void resetPlayerStatusToDefault(){
        _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.DEFAULT_PLAYER_STATUS_FILE_LOCATION);
    }

    public PlayerStatus getPlayerStatus()
    {
        //load save file number instead of zero
        for(PlayerStatus s : _players){
            if(s.getSaveFileId() == GameSaveSystem.getCurrentSaveIndex())
            return s;
        }

        return null;
    }

    //map format is: <room_name, room_object>, getSingle will try to match the input with key and return object
    //returns null if this method fails to find the passed field within dictionary. Case insensitive.
    public Room getSingleRoom(String roomName){
        return _mapOfRooms.get(roomName.trim().toLowerCase());
    }

    //map format is: <item_name, item_object>, getSingle will try to match the input with key and return object
    //returns null if this method fails to find the passed field within dictionary. Case insensitive.
    public Item getSingleItem(String itemName){
        return _mapOfItems.get(itemName.trim().toLowerCase());
    }


    public Map<String, Room> getRoomsHashmap() {
        //return hashmap
        return _mapOfRooms;
    }

    public Map<String, Item> getItemsHashmap()
    {
        //return hashmap
        return _mapOfItems;
    }

    public List<GameProgressionData> getListOfProgressionData(){
        return _listOfProgressData;
    }

    public static JsonDataObjList getInstance()
    {
        if (singletonInstance == null){
            singletonInstance = new JsonDataObjList();
        }
        return singletonInstance;
    }
}
