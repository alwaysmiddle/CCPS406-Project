import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDataObjList {
    //private fields
    private static List<Room> _listOfRooms = new ArrayList<Room>();
    private static List<Item> _listOfItems = new ArrayList<Item>();
    private static JsonDataObjList singletonInstance = null;
    private JsonReader reader;

    //constructor
    public JsonDataObjList() {
        //load the list of json objects into public fields for rest of the program to work with

    }


    public Object ReadFile(String filePath){
        Object obj;

        try (FileReader reader = new FileReader(filePath))
        {
            //read JSON file into JSON array
            obj = jsonParser.parse(reader);
            return obj;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void SaveToJsonFile()
    {
        Gson gson = new Gson();
        //write file to game folder here

        String jsonToWrite = gson.toJson(_listOfItems);

        try (FileWriter writer = new FileWriter(GlobalReference.ITEM_JSON_FILE_LOCATION)) {
            gson.toJson()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Room getSingleRoom(String roomName){
        return null;
    }

    public List<Room> getListOfRooms(){
        return _listOfRooms;
    }

    public List<Room> getListOfItems(){
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
