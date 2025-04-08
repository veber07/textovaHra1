import java.util.Map;

/**
 * Třída reprezentující  příkaz pro sebrání klíče v místnosti .

 */
public class View extends Command {
    private Movement movement;
    private Inventaros inventory;


    /**
     * Konstruktor
     *

     * @param movement  správa pohybu mezi místnostmi.
     * @param inventory  správa inventáře hráče.
     */
    public View(Movement movement, Inventaros inventory) {
            this.movement = movement;
            this.inventory = inventory;
        }
    /**
     * Provádí příkaz sebrání klíče v místnosti pokud tam je a ze souboru nacte popis mistnosti ve ktere se hrac nachazi.
     *
     .
     */
    public String execute() {
        Location currentRoom = movement.getMapa().get(movement.getCurLoc());
        if (currentRoom == null) {
            return "nejde";
        }

        StringBuilder result = new StringBuilder();
        String roomName = currentRoom.getName();




        Map<String, String> descriptions = movement.getWorldMap().getRoomDes();
        if (descriptions.containsKey(roomName)) {
            result.append("Popis mistnosti/").append(descriptions.get(roomName)).append("\n");
        } else {
            result.append("Popis nejde");
        }

        String keyName = null;

        if (roomName.equals("Kuchyn")) {
            keyName = "Klic Spizirna";
        } else if (roomName.equals("Garaz")) {
            keyName = "Klic Loznice";
        }

        if (keyName != null) {
            Item key = new Item(keyName, 0, currentRoom);
            inventory.addItem(key);
            result.append("Nasel si klic").append(keyName);
        } else {
            result.append("tady klickos neni.");
        }



        return result.toString();
    }
    /**
     * Určuje, že hra nemá být ukončena.
     *
     */
        @Override
        public boolean exit() {
            return false;
        }
}

