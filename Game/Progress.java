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

    //we update the npc status to aggressive when we enter underworld

    public GameProgressionData getNextProgress(){
        return getProgressStage(JsonDataObjList.getInstance().getPlayerStatus().getCurrentStage() + 1);
    }

    public GameProgressionData getProgressStage(int progressToLoad){
        List<GameProgressionData> progress = JsonDataObjList.getInstance().getListOfProgressionData();
        return progress.get(progressToLoad - 1);
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
