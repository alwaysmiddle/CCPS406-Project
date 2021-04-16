import com.google.gson.reflect.TypeToken;
import java.util.*;

public class JsonDataObjList {
    private static Map<String, Room> _mapOfRooms = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, Item> _mapOfItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static Map<String, NPC> _mapOfNpcs = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static PlayerStatus _player = new PlayerStatus();
    private static JsonDataObjList singletonInstance = null;
    private List<Room> _listOfRooms;
    private List<Item> _listOfItems;
    private List<PlayerStatus> _players;
    private PlayerStatus _playerState;
    private List<NPC> _listOfNPCs;
    private List<GameProgressionData> _listOfProgressData;

    public JsonDataObjList() { // load json obj[] into public fields

        _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
        _playerState = _players.get(0);

        _listOfRooms = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Room>>(){}.getType(), GlobalReference.ROOM_JSON_FILE_LOCATION);
        for (Room r: _listOfRooms) {
            _mapOfRooms.put(r.getRoomName().toLowerCase(), r);
        }

        _listOfItems = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<Item>>(){}.getType(), GlobalReference.ITEM_JSON_FILE_LOCATION);
        for (Item t: _listOfItems) {
            _mapOfItems.put(t.getItemName().toLowerCase(), t);
        }

        _listOfNPCs = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<NPC>>(){}.getType(), GlobalReference.NPC_FILE_LOCATION);

        _listOfProgressData = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<GameProgressionData>>(){}.getType(), GlobalReference.PROGRESSION_FILE_LOCATION);
    }

    public List<GameProgressionData> getListOfProgressionData() {
        return _listOfProgressData;
    }

    public void Save() {
        _playerState.setSaveFileId(0);
        _players.set(0, _playerState);
        JsonDataFileIO.writeJsonFile(_players, GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
    }

    public void Save(Integer saveSlot) {
        _playerState.setSaveFileId(saveSlot);
        refreshPlayersObject();
        _players.set(saveSlot, _playerState);
        JsonDataFileIO.writeJsonFile(_players, GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
    }

    public void Load(Integer saveSlot) {
        _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
        _playerState = _players.get(saveSlot);
        _players.set(0, _playerState);
    }

    public void resetPlayerStatusToDefault() {
        PlayerStatus defaultPlayerStatus = (PlayerStatus) JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.DEFAULT_PLAYER_STATUS_FILE_LOCATION).get(0);
        _players.set(0, defaultPlayerStatus);
        _playerState = _players.get(0);
    }

    public PlayerStatus getPlayerStatus() {
        return _playerState;
    }

    public Room getSingleRoom(String roomName) {
        // return null if it fails to find
        return _mapOfRooms.get(roomName.trim().toLowerCase());
    }

    public Item getSingleItem(String itemName) {
        return _mapOfItems.get(itemName.trim().toLowerCase());
    }

    public NPC getSingleNPC(String npcName) {
        for (NPC npc: _listOfNPCs){
            if (npc.getNpcName().equalsIgnoreCase(npcName)) {
                return npc;
            }
        }
        return null;
    }

    public List<NPC> getListOfNPCs() {
        return _listOfNPCs;
    }

    public void UpdateNpcJson() {
        JsonDataFileIO.getInstance().writeJsonFile(_listOfNPCs, GlobalReference.NPC_FILE_LOCATION);
    }

    private void refreshPlayersObject(){
        _players = JsonDataFileIO.getInstance().readJsonFile(new TypeToken<List<PlayerStatus>>(){}.getType(), GlobalReference.PLAYER_STATUS_SAVEFILE_LOCATION);
    }

    public static JsonDataObjList getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new JsonDataObjList();
        }
        return singletonInstance;
    }
}
