import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Itemikz {
    private Map<Integer, Location> rooms;
    private ArrayList<Item> items;
   private Location location;

    public Itemikz(WorldMap worldMap, String itemsFile) {


        System.out.println("predmety jdou na sva mista ");

        this.rooms = worldMap.getMapa();
        this.items = new ArrayList<>();

        if (this.rooms == null) {
            System.out.println("nenacetli se misntnosti ");
        } else if (this.rooms.isEmpty()) {
            System.out.println("jsou prazdny");
        } else {
            System.out.println("mistnosti/ " + this.rooms.keySet());
        }


        loadItems(itemsFile);

    }




        private void loadItems (String filePath){
            try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {

                String line;
                while ((line = bf.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        System.out.println("funguje");
                        String itemName = parts[0].trim();
                        int roomId = Integer.parseInt(parts[1].trim());
                        System.out.println(roomId + "uuuu" + itemName);
                        int importanteId = Integer.parseInt(parts[2].trim());


                        Location location = rooms.get(roomId);
                        System.out.println("pridavam" + itemName + "do" + roomId);

//lohv
                        if (location != null) {
                            Item item = new Item(itemName, importanteId, location);
                            location.addItem(item);
                            System.out.println("Predmet" + itemName + " pridan do mistnosti " + roomId);
                            items.add(item);
                        } else {
                            System.out.println("Místnost s id" + roomId + "neexistuje");
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public ArrayList<Item> getItems () {
            return items;
        }


        public Map<Integer, Location> getRooms () {
            return rooms;
        }






}