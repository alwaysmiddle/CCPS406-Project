public class Item {
    private String itemName;
    private String itemDescription;
    private boolean isWeapon;
    private boolean edible;

    //region getters
    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public boolean isWeapon() {
        return isWeapon;
    }

    public boolean isEdible() {
        return edible;
    }

    //endregion

    //region setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setWeapon(boolean weapon) {
        isWeapon = weapon;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    //endregion
}
