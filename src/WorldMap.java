
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Třída pro správu místností .
 *

 */
public class WorldMap {
    private Map<Integer,Location> mapa = new HashMap<>();
    public Movement movement;
    private Map<String, String> roomDes = new HashMap<>();

    public Map<String, String> getRoomDes() {
        return roomDes;
    }

    public Map<Integer, Location> getMapa() {
        return mapa;
    }
    public void setMapa(Map<Integer, Location> mapa) {
        this.mapa = mapa;
    }
    String line;
    /**
     * Načítá místnosti ze souboru, každí´á místnost má id a vchody a určuje se jestli je místnost zamčená
     *
     */
    public boolean loadMap() {
        try {
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

                    Location location = new Location(name, id, nor, east, south, west);

                    if (name.equals("Spizirna")) {
                        location.lock("Klic Spizirna");
                    } else if (name.equals("Loznice")) {
                        location.lock("Klic Loznice");
                    }

                    mapa.put(id, location);
                }
            }


            try (BufferedReader brDes = new BufferedReader(new FileReader("src/popis"))) {
                String line;
                while ((line = brDes.readLine()) != null) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        roomDes.put(parts[0].trim(), parts[1].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("poposi se nenacetli");
                e.printStackTrace();
            }


            if (!mapa.containsKey(1)) {
                System.out.println("nenalezena mistnost 1");
            }

            Inventaros inve = new Inventaros();
            movement = new Movement(mapa, 1, inve,this);
            movement.setWorldMap(this);
            movement.curVypis();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }









        }








