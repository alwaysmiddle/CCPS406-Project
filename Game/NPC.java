public class NPC {
    //private fields
    private String npcName;
    private String shortDescription;
    private String longDescription;
    private boolean underworld;
    private boolean previouslyMet;
    private String[] npcInventory;


    //NPC getters
    //indicates whether a room has been visited or NOT
    public boolean npcMeeting(){ return previouslyMet; }

    public String getNpcName(){ return npcName;}

    //provides short description of NPC
    public String getShortDescription(){ return shortDescription;}

    //provides long description of NPC
    public String getLongDescription(){ return longDescription; }

    //NPC setters

    //set NPC status
    public void setRoomsConnected(String[] npcInventory) {
        this.npcInventory = npcInventory;
    }

    //set NPC aggression
    public void setAggression(boolean underworld) {
        this.underworld = underworld;
    }

    //determine which NPC description to load
    public String loadDescription() {
        if (!previouslyMet) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }

}
