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
    private PlayerStatus _playerState;
    private List<NPC> _listOfNPCs;
    private List<GameProgressionData> _listOfProgressData;

    private static JsonDataObjList singletonInstance = null;

    //constructor
    public JsonDataObjList() {
        //load the list of json objects into public fields for rest of the program to work with

        //Load Player status of the player's choice of saves
        _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
        _playerState = _players.get(0);

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
        _playerState.setSaveFileId(0);
        _players.set(0, _playerState);
        JsonDataFileIO.writeJsonFile(_players, GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
    }

    public void Save(Integer saveSlot)
    {
        _playerState.setSaveFileId(saveSlot);
        _players.set(saveSlot, _playerState);
        JsonDataFileIO.writeJsonFile(_players, GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
    }

    public void Load(Integer saveSlot)
    {
        _playerState = _players.get(saveSlot);
        _players.set(0, _playerState);
    }

    public void resetPlayerStatusToDefault(){
        PlayerStatus defaultPlayerStatus = (PlayerStatus) JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.DEFAULT_PLAYER_STATUS_FILE_LOCATION).get(0);
        _players.set(0, defaultPlayerStatus);
    }

    public PlayerStatus getPlayerStatus()
    {
        return _playerState;
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

    public NPC getSingleNPC(String npcName) {
        return this._listOfNPCs.contains(npcName) ? (NPC)this._listOfNPCs.get(this._listOfNPCs.indexOf(npcName)) : null;
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
