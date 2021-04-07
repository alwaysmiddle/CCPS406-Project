public class NPC {
    //private fields
    private String npcName;
    private String shortDescription;
    private String longDescription;
    private boolean underworld;
    private boolean previouslyMet;
    private String[] npcStatus;


    //NPC getters
    //indicates whether a room has been visited or NOT
    public boolean npcMeeting(){ return previouslyMet; }

    public String getNpcName(){ return npcName;}

    //provides short description of NPC
    public String getShortDescription(){ return shortDescription;}

    //provides long description of NPC
    public String getLongDescription(){ return longDescription; }

    public String[] getNpcStatus() { return npcStatus; }

    //NPC setters

    //set NPC status
    public void setNpcStatus(String[] npcStatus) {
        this.npcStatus = npcStatus;
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
