public class NPC {
    //private fields
    private String npcName;
    private int currentHP;
    private int maxHP;
    private String currentPosition;
    private String[] npcInventory;
    private int weaponValue;
    private boolean isAggressive;

    //region getters
    public String getNpcName() {
        return npcName;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String[] getNpcInventory() {
        return npcInventory;
    }

    public int getWeaponValue() {
        return weaponValue;
    }

    public boolean isAggressive() {
        return isAggressive;
    }
    //endregion

    //region setters

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setNpcInventory(String[] npcInventory) {
        this.npcInventory = npcInventory;
    }

    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }

    public void setAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

    //endregion
}
