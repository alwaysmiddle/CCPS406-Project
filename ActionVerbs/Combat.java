public class Combat {
    public static void doCombatWithNpc(PlayerStatus player, String npcName){
        NPC npc = JsonDataObjList.getInstance().getSingleNPC(npcName);
        int npcHP = npc.getCurrentHP();
        int npcWeaponVal = npc.getWeaponValue();
        int playerHP = player.getCurrentHP();
        int playerWeaponVal = player.getWeaponValue();

        if (playerWeaponVal > npcHP) {
            // player attacks first
            npc.setCurrentHP(0);
            System.out.println("[You have used "+ player.getWeaponEquipped() + "to attack. You have done "
                + playerWeaponVal + "pts in damage and defeated" + npcName + ". You won!]\n");
        }
        if (npcHP > playerWeaponVal) {
            // player attacks first
            npc.setCurrentHP(npcHP - playerWeaponVal);
            System.out.println("[You have used "+ player.getWeaponEquipped() + "to attack. You have done "
                + playerWeaponVal + "pts in damage.]\n");

            if (playerHP > npcWeaponVal) {
                // npc attacks back
                player.setCurrentHP(playerHP - npcWeaponVal);
                System.out.println("[" + npcName + " has used "+ npc.getWeaponEquipped() + "to attack. " + npcName + " has done "
                    + npcWeaponVal + "pts in damage. You won!]\n");
            }

            if (playerHP < npcWeaponVal) {
                doHiding(player);
                System.out.println("[You have decided to hide from the stronger opponent. You have now moved to "
                    + player.getCurrentPosition() + ".]\n");
            }
        }
    }

    public static void doHiding(PlayerStatus player) {
        // go to a random connected room
        player.setcurrentRoom(jsonObjList.getInstance().getSingleRoom(player.getCurrentPosition()).getRoomsConnected()[0]);
    }
}
