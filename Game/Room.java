import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Room {
    //max number of rooms in house
    public static final int ROOMS = 6;
    public static final int FEATURES = 6;
    int room = 0;

    //array to hold all of the rooms and room details
    public static String[][] house = new String[ROOMS][FEATURES];

    //loads starting state room info into array
    private Room(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("room.json"))
        {
            //read JSON file into JSON array
            Object obj = jsonParser.parse(reader);
            JSONArray roomList = (JSONArray) obj;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    //indicates whether a room has been visited or not
    public boolean visited(){
        if (house[room][1] == "no") {
            house[room][1] = "yes";
            return false;
        }
        else {
            return true;
        }
    }

    //provides short description of rooms
    private String shortDescription(){
        return house[room][2];
    }

    //provides long description of room
    private String longDescription(){
        return house[room][3];
    }

    //determine which description to load
    public String loadDescription(Boolean visited){
        if (!visited()){
            return longDescription();
        }
        else {
            return shortDescription();
        }
    }
}
