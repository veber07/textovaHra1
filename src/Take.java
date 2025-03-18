import java.util.ArrayList;

public class Take extends Command {

    private Movement movement;
    private Inventaros inve;

    public Take(Movement movement, Inventaros inve) {
        this.movement = movement;
        this.inve = inve;
    }

    @Override
    public String execute() {
        Location currroom = movement.mapa.get(movement.getCurLoc());
        if (currroom != null) {
            ArrayList<Item> itemsinroom = currroom.getItems();




            if (itemsinroom != null && !itemsinroom.isEmpty()) {
                Item item = itemsinroom.get(0);
                inve.addItem(item);
                currroom.removeItem(item);
                return "pred" + item.getName() + " sis vzal do inventu";
            } else {
                return "neni predmet";
            }
        } else {
            return "";
        }
    }

    @Override
    public boolean exit() {

        return false;
    }
}