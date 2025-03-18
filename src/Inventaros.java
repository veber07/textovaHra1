import java.util.ArrayList;

public class Inventaros {

    private ArrayList<Item> items;

    public Inventaros() {
        this.items = new ArrayList<>();
    }


    public boolean addItem(Item item) {
        return items.add(item);
    }
}
