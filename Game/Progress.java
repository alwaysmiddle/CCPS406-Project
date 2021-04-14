import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Progress {
    // TODO add a method here to update the player stage for category, item, world and npc
    // We will read the gameprogression.json
    // display worldAnnouncement upon stage change
    // check the requirementCategory to see which category it is
    // depending on category, we check either player status.currentPosition (world)
    // check item in player inventory (item)
    // check actionVerb == talk and trailing == npcname (Npc)
    // then we display world announcement upon stage change

    private static PlayerStatus player = JsonDataObjList.getInstance().getPlayerStatus();
    private static GameProgressionData current = JsonDataObjList.getInstance().getListOfProgressionData().get(player.getCurrentStage()-1);
    private static boolean stageProgressed = false;
    // update the npc status to aggressive when we enter underworld

    public static GameProgressionData getNextProgress(){
        return getProgressStage(player.getCurrentStage() + 1);
    }

    public static GameProgressionData getProgressStage(int progressToLoad){
        List<GameProgressionData> progress = JsonDataObjList.getInstance().getListOfProgressionData();
        return progress.get(progressToLoad - 1);
    }

    public static void checkStage(String actionVerb, String trailing){
        //TODO: add back enter underworld to gameprogression and handle better
        stageProgressed = false;
        switch (current.requirementCategory){
            // progress only if the requirement category is npc and talking to requirement npc
            case "npc" -> {
                if(actionVerb.equalsIgnoreCase("talk") && trailing.equalsIgnoreCase(current.stageRequirement)){stageProgressed = true;}
            }
            case "item" -> {
                if (trailing.equalsIgnoreCase(current.stageRequirement) && Arrays.stream(player.getPlayerInventory()).anyMatch(current.stageRequirement::equalsIgnoreCase)){
                    if (actionVerb.equalsIgnoreCase("take") || actionVerb.equalsIgnoreCase("use")){stageProgressed = true;}
                }
            }
        }

        if (stageProgressed){
            if (current.stageRequirement.equalsIgnoreCase("Gemstone")){
                player.setUnderworld(true);
                List<NPC> allNPC = JsonDataObjList.getInstance().getListOfNPCs();
                for (NPC eachNpc :  allNPC){
                    if(!eachNpc.getNpcName().equalsIgnoreCase("Father")){eachNpc.setAggressive(true);}
                }
            }
            current = Progress.getNextProgress();
            player.setCurrentStage(current.stageNum);
            System.out.println(current.worldAnnouncement + "\n");
            System.out.println(current.stageDialogue + "\n");
            if (current.tip != null){System.out.println("\n\n Small tip:" + current.tip);}
            }
    }

    // check npc status when entering a room
    public static void checkNpcs(String actionVerb, String trailing){
        Room currentRoom = JsonDataObjList.getInstance().getSingleRoom(player.getCurrentPosition());
        NPC nonPlayer = currentRoom.getNpcInThisRoom();
        checkStage(actionVerb, trailing);
        if (!stageProgressed){
            if(nonPlayer != null && actionVerb.trim().equalsIgnoreCase("talk".trim())){
                if (!nonPlayer.isAggressive() && trailing.equalsIgnoreCase(nonPlayer.getNpcName())) {
                    Random rand = new Random();
                    int output = rand.nextInt(3);
                    switch (output) {
                        case 0 -> System.out.println("Hello Madelyn, how're you doing? Be careful since the building is quite old. The garden is beautiful around this time of year.");
                        case 1 -> System.out.println("Oh, Madelyn it's you! I believe I spotted some fresh baked bread in the Kitchen. Try your luck now.");
                        case 2 -> System.out.println("Do be more careful when playing around, Madelyn. You're not a child.");
                    }
                } else {
                    System.out.println("You cannot talk to this NPC. They are hostile to you.");
                    player.setCurrentHP(player.getCurrentHP()-1);
                    System.out.println("You have lost 1 HP for trying to talk to a demon.");
                }
            } else if (actionVerb.equalsIgnoreCase("go") && nonPlayer != null) {
                System.out.println("\nThe " + nonPlayer.getNpcName() + " is currently in the room. ");
            } else if (actionVerb.equalsIgnoreCase("talk")) {
                System.out.println("No one is in the room. Maybe the birds outside will speak to you.");
            }
            stageProgressed = false;
        }
    }
}

