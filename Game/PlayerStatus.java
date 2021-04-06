public class PlayerStatus {
    private int currentHP;
    private int maxHP;
    private String currentPosition;
    private String[] playerInventory;

    private String weaponEquipped;
    private int weaponValue;
    private boolean isUnderworld;

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

    public String getWeaponEquipped() {
        return this.weaponEquipped;
    }

    public int getWeaponValue() {
        return this.weaponValue;
    }

    public boolean isUnderworld() { return this.isUnderworld; }
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

    public void setWeaponEquipped(String weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
    }

    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }

    public void setUnderworld(boolean underworld) { this.isUnderworld = underworld; }
    //endregion
}
