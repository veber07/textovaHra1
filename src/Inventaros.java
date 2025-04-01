import java.util.ArrayList;
/**
 * Třída Inventaros představuje inventář hráče.
 */
public class Inventaros {

    private ArrayList<Item> items;
    /**
     * Konstruktor třídy Inventaros.

     */
    public Inventaros() {
        this.items = new ArrayList<>();
    }
    /**
     * Přidává předmět do inventáře.
     *
     * @param item Předmět, který má být přidán.
     */
    public boolean addItem(Item item) {
        return items.add(item);
    }


    /**
     * Odebere předmět z inventáře podle jeho názvu.
     *
     * @param itemName Jméno předmětu, který má být odebrán.
     */
    public void removeItem(String itemName) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public ArrayList<Item> getItems() {
        return items;
    }

}
