import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Třída Location reprezentuje místnost ve hře.

 */
public class Location {
    private String name;
    int id;
    private Map<String, Integer> exits;
    private ArrayList<Item> items;
    private boolean locked = false;
    private String reqKez;
    /**
     * Konstruktor pro vytvoření nové místnosti.
     *
     * @param name Jméno místnosti.
     * @param id ID místnosti.
     * @param nor ID místnosti na severu.
     * @param east ID místnosti na východě.
     * @param south ID místnosti na jihu.
     * @param west ID místnosti na západě.
     */
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

    public void setExit(String direction, int roomId) {
        exits.put(direction, roomId);
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

    /**
     * Odstraní předmět z místnosti.
     *
     * @param item Předmět, který má být odstraněn.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
    /**
     * porovnává  dva  objekty, pokud maji stejné id tak jsou stejný.
     *
     * @param obj Objek se kterým porovnáváme tuto místnost.
     */
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