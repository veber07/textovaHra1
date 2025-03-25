import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Location {
    private String name;
    int id;
   private Map<String, Integer> exits;
    private ArrayList<Item> items;
    private boolean locked = false;
    private String reqKez;

    public Location(String name, int id, int nor, int east, int south, int west) {
        this.name = name;
        this.id = id;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.locked = false;

        exits.put("north", nor);
        exits.put("east", east);
        exits.put("south", south);
        exits.put("west", west);
    }

    public int getExit(String direction) {
        return exits.getOrDefault(direction, 0);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addItem(Item item) {
        items.add(item);
    }


    public ArrayList<Item> getItems() {
        return items;
    }
    public boolean isLocked() {
        return locked;
    }

    public void lock(String key) {
        this.locked = true;
        this.reqKez = key;
    }


    public void unlock() {
        this.locked = false;
        this.reqKez = null;
    }

    public String getReqKez() {
        return reqKez;
    }



    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return id == location.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}