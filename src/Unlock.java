

/**
 * Třída reprezentující příkaz pro odemknutí zamčených místností.
 *
 */
public class Unlock extends Command {
    private Movement movement;
    private Inventaros inventory;


    /**
     * Konstruktor

     * @param movement  správa pohybu mezi místnostmi.
     * @param inventory správa inventáře hráče.
     */
    public Unlock(Movement movement, Inventaros inventory) {
        this.movement = movement;
        this.inventory = inventory;
    }
    /**
     * Provádí příkaz odemknutí zamčené místnosti.
     *
     */
    @Override
    public String execute() {
        Location currentRoom = movement.getMapa().get(movement.getCurLoc());


        for (String direction : new String[]{"north", "east", "south", "west"}) {
            int ID = currentRoom.getExit(direction);
            Location modifRomm  = movement.getMapa().get(ID);


            if (modifRomm  != null && modifRomm .isLocked()) {
                String requiredKez = modifRomm .getReqKez();


                for (Item item : inventory.getItems()) {
                    if (item.getName().equals(requiredKez)) {
                        modifRomm .unlock();
                        inventory.removeItem(requiredKez);
                        movement.unlockRoom();
                        return "odemkl jsi  " + modifRomm .getName() ;
                    }
                }
                return "potrebujeds " + requiredKez;
            }
        }

        return " nemas okolo sebe zamcene dvere";
    }
    /**
     * Určuje, že hra nemá být ukončena.

     */
    @Override
    public boolean exit() {
        return false;
    }
}
