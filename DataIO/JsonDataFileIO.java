import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Map;

// singleton object
public class JsonDataFileIO {
    private static JsonDataFileIO singletonInstance = null;

    public static <T> List<T> readJsonFile(Type fileType, String filePath){
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(filePath))
        {
            return gson.fromJson(reader, fileType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> readJsonFileAsMap(String filePath){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingStrategy(new MyFieldNamingStrategy());
        Gson gson = gsonBuilder.create();
        Type fileType = new TypeToken<Map<String, Object>>(){}.getType();

        try (FileReader reader = new FileReader(filePath))
        {
            return gson.fromJson(reader, fileType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void writeJsonFile(List<T> objToWrite, String filePath)
    {
        Gson gson = new GsonBuilder().serializeNulls().create();

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

class MyFieldNamingStrategy implements FieldNamingStrategy
{
    //Translates the Java field name into its JSON element name representation.
    @Override
    public String translateName(Field field)
    {
        String name = field.getName();
        char newFirstChar = Character.toLowerCase(name.charAt(1));
        return newFirstChar + name.substring(2);
    }
}