
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private Map<Integer,Location> mapa = new HashMap<>();
    private Movement movement;

public boolean loadMap(){
    try (BufferedReader br = new BufferedReader(new FileReader("src/rooms"))) {
        String line = br.readLine();
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
        movement = new Movement(mapa, 1);

        return true;
    }catch (IOException e){
        e.printStackTrace();
        return false;

    }






        }






}
