public class Item {
    private String itemName;
    private String itemDescription;
    private String progressRequirement;
    private boolean isWeapon;
    private boolean edible;

    ///////////////// getters //////////////////////////////
    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public String getProgressRequirement(){ return this.progressRequirement;}

    public boolean isWeapon() {
        return this.isWeapon;
    }

    public boolean isEdible() {
        return this.edible;
    }

    //////////////////// setters /////////////////////////////////
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setProgressRequirement(String progressRequirement) { this.progressRequirement = progressRequirement;}

    public void setWeapon(boolean weapon) {
        isWeapon = weapon;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }
}
