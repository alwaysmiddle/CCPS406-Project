import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//singleton object only 1 instance exists per game.
public class JsonDataFileIO {
    private static JsonDataFileIO singletonInstance = null;

    public static <T> List<T> ReadFile(TypeToken<List<T>> fileType, String filePath){
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath))
        {
            return gson.fromJson(reader, fileType.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void WriteToJsonFile(List<T> objToWrite, String filePath)
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
