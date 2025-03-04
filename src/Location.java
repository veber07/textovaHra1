import java.util.HashMap;
import java.util.Map;

public class Location {


    private String name;
    int id;
    Map<String, Integer> exits;


    public Location(String name, int id, int nor,int east,int south,int west ) {
        this.name = name;
        this.id = id;
        this.exits = new HashMap<>();
        exits.put("north", nor);
        exits.put("east", east);
        exits.put("south", south);
        exits.put("west", west);


    }
    public int getExit(String direction){
        return exits.getOrDefault(direction, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
