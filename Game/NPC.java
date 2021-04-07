public class NPC {
    //private fields
    private String npcName;
    private String shortDescription;
    private String longDescription;
    private boolean underworld;


    //region getters
    //indicates whether a room has been visited or NOT
    public boolean isVisited(){ return visited; }

    public String getNpcName(){ return npcName;}

    //provides short description of NPC
    public String getShortDescription(){ return shortDescription;}

    //provides long description of NPC
    public String getLongDescription(){ return longDescription; }


    //aggression setter
    public void setAggression(boolean underworld) {
        this.underworld = underworld;
    }

    //determine which description to load
    public String loadDescription() {
        if (!visited) {
            return longDescription;
        } else {
            return shortDescription;
        }
    }

}
