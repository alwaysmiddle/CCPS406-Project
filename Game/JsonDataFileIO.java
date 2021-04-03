import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//singleton object only 1 instance exists per game.
public class JsonDataFileIO {
    private static JsonDataFileIO jsonIoInstance = null;
    private JSONParser jsonParser = new JSONParser();

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

    public void WriteToJsonFile(Object objectToWrite, String filePath)
    {
        //write file to game folder here
        try (FileWriter file = new FileWriter(filePath)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(((JSONObject)objectToWrite).toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonDataFileIO getInstance()
    {
        if (jsonIoInstance == null){
            jsonIoInstance = new JsonDataFileIO();
        }
        return jsonIoInstance;
    }
}
