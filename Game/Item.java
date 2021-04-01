import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Item {

    public static void main(String[] arg)
    {
        new Item();
    }

    public Item(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("Game/item.json"))
        {
            //read JSON file into JSON array

            Object obj = jsonParser.parse(reader);

            JSONArray itemList = (JSONArray) obj;
            System.out.print(itemList);

            itemList.forEach( emp -> parseItemObject ((JSONObject) emp ));

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseItemObject(JSONObject item)
    {
        //Get employee object within list
        JSONObject itemObject = (JSONObject) item.get("item");

        //Get item name
        String name = (String) itemObject.get("itemName");
        System.out.println(name);

        //Get item description
        String itemDes = (String) itemObject.get("itemDescription");
        System.out.println(itemDes);

        //Get item location
        String itemLoc = (String) itemObject.get("itemLocation");
        System.out.println(itemLoc);
    }

    //provides item Name
    public void itemName(){
        System.out.print("itemName");
    }

    //provides item location
    public void itemLocation(){
        System.out.print("itemLocation");
    }

    //provides item description
    public void itemDescription(){
        System.out.print("itemDescription");
    }

}
