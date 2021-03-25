import java.io.*;
import java.util.*;

public class Room {

    //max number of rooms in house
    public static final int MAXROOMS = 8;

    //array to hold all of the rooms
    public static Room[] house = new Room[MAXROOMS+1];

    //provides long description of room
    public String longDescription(){
        String longDes = "long";

        return longDes;
    }

    //provides short description of rooms
    public String shortDescription(){
        String shortDes = "short";

        return shortDes;
    }

    //indicates whether a room has been visited or not
    public boolean visited(){

        return true;
    }

    //determine which description to load
    public static void loadDescription(Boolean visited){

    }
}
