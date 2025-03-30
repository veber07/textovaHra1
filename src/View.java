public class View extends Command {
    private Movement movement;
    private Inventaros inventory;



    public View(Movement movement, Inventaros inventory) {
            this.movement = movement;
            this.inventory = inventory;
        }

        @Override
        public String execute() {
            Location currentRoom = movement.getMapa().get(movement.getCurLoc());
            if (currentRoom == null) {
                return "nejde";
            }

            String roomName = currentRoom.getName();
            String keyName = null;

            if (roomName.equals("Kuchyn")) {
                keyName = "Klic Spizirna";
            } else if (roomName.equals("Garaz")) {
                keyName = "Klic Loznice";
            }


            if (keyName != null) {
                Item key = new Item(keyName, 0, currentRoom);
                inventory.addItem(key);
                return "nasel si " + keyName;
            }

            return "Tady klickos neni";
        }

        @Override
        public boolean exit() {
            return false;
        }
}

