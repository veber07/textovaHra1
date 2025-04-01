import java.util.ArrayList;
/**
 * Třída reprezentujíci příkaz pro  zvednutí předmětu a daní do  inventáře.
 *
 n
 */
public class Take extends Command {

    private Movement movement;
    private Inventaros inve;
    /**
     * Konstruktor
     * @param movement  správa pohybu mezi místnostmi.
     * @param inve správa inventáře hráče.
     */
    public Take(Movement movement, Inventaros inve) {
        this.movement = movement;
        this.inve = inve;
    }
    /**
     * Provádí příkaz zvednutí předmětu do inventáře.
     *

     */
    @Override
    public String execute() {
        Location currroom = movement.mapa.get(movement.getCurLoc());
        if (currroom != null) {
            ArrayList<Item> itemsinroom = currroom.getItems();




            if (itemsinroom != null && !itemsinroom.isEmpty()) {
                Item item = itemsinroom.get(0);
                inve.addItem(item);
                currroom.removeItem(item);
                return "predmet" + item.getName() + "sis vzal do inventu";
            } else {
                return "neni predmet";
            }
        } else {
            return "";
        }
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