import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Room {

    public static void main(String[] arg)
    {
        new Room();
    }

    //max number of rooms in house
    public static final int RoomNumber = 6;
    public static final int INFO = 6;

    // index of room information
    public static final int  NAME = 0;
    public static final int  VISITED = 1;
    public static final int  SHORT = 2;
    public static final int  LONG = 3;
    public static final int  FEATURES = 4;

    int room = 0;

    //array to hold all of the rooms and room details
    public static String[][] house = new String[ROOMS][INFO];

    //loads starting state room info into array
    public Room(){

    }


    //indicates whether a room has been visited or NOT
    public boolean visited(){
        if (house[room][VISITED].equals("no")) {
            house[room][VISITED] = "yes";
            return false;
        }
        else {
            return true;
        }
    }

    //provides short description of rooms
    public void shortDescription(){
        System.out.print(house[room][SHORT]);
    }

    //provides long description of room
    public void longDescription(){
        System.out.print(house[room][LONG]);
    }

    //determine which description to load
    public void loadDescription(Boolean visited){
        if (!visited()){
            longDescription();
        }
        else {
            shortDescription();
        }
    }

    //loop through current room features
    public void listOfRoomFeatures (Integer room){
        for (var i=0; i<3; i++){
            System.out.print(house[room][FEATURES+i]);
        }
    }
}
