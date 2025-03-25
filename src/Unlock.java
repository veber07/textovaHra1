public class Unlock extends Command {
    private Movement movement;
    private Inventaros inventory;



    public Unlock(Movement movement, Inventaros inventory) {
        this.movement = movement;
        this.inventory = inventory;
    }

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
                        return "odemkl jis  " + modifRomm .getName() ;
                    }
                }
                return "potrebujeds " + requiredKez;
            }
        }

        return " nemas okolo sebe zamcene dvere";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
