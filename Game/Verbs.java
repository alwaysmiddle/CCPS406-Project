import java.util.*;

public class Verbs {
    //action verbs and the directions the player can move, item names "borrowed"
    public static final HashMap<String, Integer> verbs = new HashMap<>();

    public static void main(String... args){
        verbs.put("go", 1);
        verbs.put("take", 2);
        verbs.put("inventory", 3);
        verbs.put("attack", 4);
        verbs.put("use", 5);
        verbs.put("eat", 5);
        IdentifyInput();
    }

    //compare user input with verbs and compare direction for output
    public static void IdentifyInput(){
        String[] split = Console.input.trim().split(" ");
        Integer act = 0;
        String direction = null;
        String item = null;
        //String[] ListofConnectedRooms = JsonDataFileIO.getSingleRoom(PlayerStatus.position).getConnectedRooms();

        //after splitting the input compare the input with the verbs and get direction
        //need to add items to this one that has been completed
        for (String s : split) {

            if (verbs.containsKey(s)) {
                act = verbs.get(s);
            }
            //for(int i = 0; i < listofConnectedRooms.length, i++){
            //    if(s.equalsIgnoreCase(listofConnectedRooms[i])){
            //        PlayerStatus.position = listofConnectedRooms[i];
            //        direction = listofConnectedRooms[i];
            //    }
            //}
            //item = JsonDataFileIO.getSingleItem()
        }
        //will execute different actions depending on the verb
            switch (act) {
                case 0 -> {
                    Console.textArea.setText("Cannot resolve \"" + split[0] + "\". Try one of the following: \n");
                    for (Map.Entry<String, Integer> entry : verbs.entrySet()) {
                        Console.textArea.append("  - " + entry.getKey());
                    }
                }
            case 1 -> {
                boolean check = false;
                    if(direction != null){
                        Console.textArea.setText("You are headed to the " + direction + "\n");
                        //Console.textArea.append(JsonDataFileIO.getRoomDescription);
                    System.out.println("You can head to the following rooms: \n");
                    }else {
                        Console.textArea.setText("You are headed " + direction + ".");
                        //for(int j = 0; j < listofConnectedRooms.length; j++){
                        //    System.out.println("  - " + listofConnectedRooms[i] + "\n");
                    }
            }
                case 2 -> {
                if(item != null){
                   Console.textArea.setText("Congratulations. You have finally obtained " + item + ".");
                   //PlayerStatus.accumulate("inventory", );
                //}else{
                    //Console.textArea.setText("How dare you try to take this item. This is not yours for the taking.");
                }
            }
            case 3 -> Console.textArea.setText("Within your inventory are the following items: \n" + item);
            case 4 -> Console.textArea.setText("You have used " + item + "  to attack.");
            case 5 -> {
                Console.textArea.setText("You have used the item" + item +".");
                if (item.equals("bread")) {
                    Console.textArea.append(" Not the greatest meal, but it'll do. Your health has increased 2 points.");
                }
            }
            case 6 -> {
                Console.textArea.setText("hi");
            }
        }
    }
}
