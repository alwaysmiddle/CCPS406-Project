public class PlayerStatus {
    private int saveFileId;
    private int currentHP;
    private int maxHP;
    private String currentPosition;
    private String[] playerInventory;
    private int currentStage;
    private String weaponEquipped;
    private int weaponValue;
    private boolean isUnderworld;
    private String[] roomsVisited;

    //region getters
    public int getCurrentHP() {
        return this.currentHP;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public String getCurrentPosition() {
        return this.currentPosition;
    }

    public String[] getPlayerInventory() {
        return this.playerInventory;
    }

    public int getCurrentStage() {return this.currentStage;}

    public String getWeaponEquipped() {
        return this.weaponEquipped;
    }

    public int getWeaponValue() {
        return this.weaponValue;
    }

    public boolean isUnderworld() { return this.isUnderworld; }

    public String[] getRoomsVisited() { return roomsVisited; }

    public int getSaveFileId() { return saveFileId; }
    //endregion

    //region setters
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setPlayerInventory(String[] playerInventory) {
        this.playerInventory = playerInventory;
    }

    public void setCurrentStage (int currentStage) {this.currentStage = currentStage;}

    public void setWeaponEquipped(String weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }

    public void setUnderworld(boolean underworld) { this.isUnderworld = underworld; }

    public void setRoomsVisited(String[] roomsVisited) {
        this.roomsVisited = roomsVisited;
    }

    public void setSaveFileId(int saveFileId) { this.saveFileId = saveFileId; }
    //endregion
}
