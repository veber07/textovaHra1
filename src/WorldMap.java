
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Integer,Location> mapa = new HashMap<>();
    public Movement movement;

    public Map<Integer, Location> getMapa() {
        return mapa;
    }

    public boolean loadMap(){
    try (BufferedReader br = new BufferedReader(new FileReader("src/rooms"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int nor = Integer.valueOf(parts[2]);
            int east = Integer.valueOf(parts[3]);
            int south = Integer.valueOf(parts[4]);
            int west = Integer.valueOf(parts[5]);
            mapa.put(id, new Location(name, id, nor, east, south, west));
        }

        System.out.println("Nactene " + mapa.keySet());
        if (!mapa.containsKey(1)) {
            System.out.println("nenalezerna mistnost 1  ");
        }

        movement = new Movement(mapa, 1);
        movement.curVypis();

        return true;
    }catch (IOException e){
        e.printStackTrace();
        return false;

    }






        }






}
