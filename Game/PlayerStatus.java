public class PlayerStatus {
    private int currentHP;
    private int maxHP;
    private String currentPosition;
    private String[] playerInventory;

    private String weaponEquipped;
    private int weaponValue;

    public PlayerStatus(int currentHP, int maxHP, String currentPosition, String[] playerInventory, String weaponEquipped, int weaponValue) {
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        this.currentPosition = currentPosition;
        this.playerInventory = playerInventory;
        this.weaponEquipped = weaponEquipped;
        this.weaponValue = weaponValue;
    }

    //region getters
    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String[] getPlayerInventory() {
        return playerInventory;
    }

    public String getWeaponEquipped() {
        return weaponEquipped;
    }

    public int getWeaponValue() {
        return weaponValue;
    }
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
    //endregion
}
