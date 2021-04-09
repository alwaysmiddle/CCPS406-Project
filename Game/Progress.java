import java.util.List;

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

    //we update the npc status to aggressive when we enter underworld

    public static GameProgressionData getNextProgress(){
        return getProgressStage(JsonDataObjList.getInstance().getPlayerStatus().getCurrentStage() + 1);
    }

    public static GameProgressionData getProgressStage(int progressToLoad){
        List<GameProgressionData> progress = JsonDataObjList.getInstance().getListOfProgressionData();
        return progress.get(progressToLoad - 1);
    }

    public static void checkStage(String actionVerb, String trailing){
        GameProgressionData current = JsonDataObjList.getInstance().getListOfProgressionData().get(player.getCurrentStage());
        switch (current.requirementCategory){
            //progress only if the requirement category is npc and talking to requirement npc
            case "npc" -> {
                if(actionVerb.equalsIgnoreCase("talk") && trailing.equalsIgnoreCase(current.stageRequirement)){
                    current = Progress.getNextProgress();
                    player.setCurrentStage(current.stageNum);
                    System.out.println(current.worldAnnoucement);
                    System.out.println(current.stageDialogue);
                }
            }
        }
    }


    //check npc status when entering a room
    public void checkNpcs(){
        //check npc in the room
        //if not aggressive, then we do:
        //load npc description
        //display choices of actions: talk, attack

        //there is an aggressive npc
        //we do combat
    }

}
