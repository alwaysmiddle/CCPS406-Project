import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Progress {
    //TODO add a method here to update the player stage for category, item, world and npc
    //We will read the gameprogression.json
    //displayworldannoucement upon stagechange
    //check the requirementCategory to see which category it is
    //depending on category, we check either playerstatus.currentPosition (world)
    //check item in player inventory (item)
    //check actionVerb == talk and trailing == npcname (Npc)
    //then we display world annoucement upon stagechange
    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static GameProgressionData current = JsonDataObjList.getInstance().getListOfProgressionData().get(player.getCurrentStage()-1);
    private static boolean stageProgressed = false;
    //we update the npc status to aggressive when we enter underworld

    public static GameProgressionData getNextProgress(){
        return getProgressStage(player.getCurrentStage() + 1);
    }

    public static GameProgressionData getProgressStage(int progressToLoad){
        List<GameProgressionData> progress = JsonDataObjList.getInstance().getListOfProgressionData();
        return progress.get(progressToLoad - 1);
    }

    public static void checkStage(String actionVerb, String trailing){
        stageProgressed = false;
        switch (current.requirementCategory){
            //progress only if the requirement category is npc and talking to requirement npc
            case "npc" -> {
                if(actionVerb.equalsIgnoreCase("talk") && trailing.equalsIgnoreCase(current.stageRequirement)){stageProgressed = true;}
            }
            case "item" -> {
                if (trailing.equalsIgnoreCase(current.stageRequirement)){
                    if (actionVerb.equalsIgnoreCase("take") || actionVerb.equalsIgnoreCase("use")){stageProgressed = true;}
                }
            }
        }
        if (stageProgressed){
            current = Progress.getNextProgress();
            player.setCurrentStage(current.stageNum);
            System.out.println(current.worldAnnoucement + "\n");
            System.out.println(current.stageDialogue + "\n");
            if (current.tip != null){System.out.println("\n\n Small tip:" + current.tip);}
            }
    }

    //check npc status when entering a room
    public static void checkNpcs(String actionVerb, String trailing){
        Room currentRoom = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition());
        NPC nonplayer = JsonDataObjList.getInstance().getSingleNPC(currentRoom.getNpc());
        checkStage(actionVerb, trailing);
        if (!stageProgressed && nonplayer != null && actionVerb.equalsIgnoreCase("talk")){
            if (!nonplayer.isAggressive() && trailing.equalsIgnoreCase(nonplayer.getNpcName())) {
                Random rand = new Random();
                int output = rand.nextInt(4);
                switch (output) {
                    case 1 -> System.out.println("Hello Madelyn, how're you doing? Be careful since the building is quite old. The garden is beautiful around this time of year.");
                    case 2 -> System.out.println("Oh, Madelyn it's you! I believe I spotted some fresh baked bread in the Kitchen. Try your luck now.");
                    case 3 -> System.out.println("Do be more careful when prouncing around, Madelyn. You're not a child.");
                }
            }//else battle i guess
        }else if(!stageProgressed && actionVerb.equalsIgnoreCase("go") && !currentRoom.getNpc().equalsIgnoreCase("")){
            System.out.println("\n The " + currentRoom.getNpc() + " is currently in the room. ");
        }
        //check npc in the room
        //if not aggressive, then we do:
        //load npc description
        //display choices of actions: talk, attack
        //there is an aggressive npc
        //we do combat
    }
}

