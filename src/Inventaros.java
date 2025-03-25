import java.util.ArrayList;

public class Inventaros {

    private ArrayList<Item> items;

    public Inventaros() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }



    public void removeItem(String itemName) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
