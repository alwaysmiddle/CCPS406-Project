import java.io.*;
import java.util.*;

public class Room {

    //max number of rooms in house
    public static final int ROOMS = 6;
    public static final int FEATURES = 4;

    //array to hold all of the rooms
    public static Room[][] house = new Room[ROOMS][FEATURES];

    private Room(){
        JSONParser jsonParser = new JSONParser();
    }

    //provides long description of room
    private String longDescription(){
        String longDes = "long";

        return longDes;
    }

    //provides short description of rooms
    private String shortDescription(){
        String shortDes = "short";

        return shortDes;
    }

    //indicates whether a room has been visited or not
    public boolean visited(){

        return true;
    }

    //determine which description to load
    public String loadDescription(Boolean visited){
        if (visited = false){
            return longDescription();
        }
        else {
            return shortDescription();
        }
    }
}
