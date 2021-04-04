import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//singleton object only 1 instance exists per game.
public class JsonDataFileIO {
    private static JsonDataFileIO singletonInstance = null;
    private JsonReader reader;

    public <T> List<T> ReadFile(String filePath){
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath))
        {
            Type typePassed = new TypeToken<List<T>>(){}.getType();

            //read JSON file into a list
            List<T> readList = gson.fromJson(reader, typePassed);
            return readList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> void WriteToJsonFile(List<T> objToWrite, String filePath)
    {
        Gson gson = new Gson();
        //write file to game folder here
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(objToWrite, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonDataFileIO getInstance()
    {
        if (singletonInstance == null){
            singletonInstance = new JsonDataFileIO();
        }
        return singletonInstance;
    }
}
