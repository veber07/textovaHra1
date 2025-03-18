import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Itemikz {
    private Map<Integer, Location> rooms;
    private List<Item> items;

    public Itemikz(String locationsFile, String itemsFile) {
        this.rooms = new HashMap<>();
        this.items = new ArrayList<>();


        loadRooms(locationsFile);
        loadItems(itemsFile);
    }


    private void loadRooms(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    int roomId = Integer.parseInt(parts[1].trim());

                    Location location = rooms.get(roomId);

                    if (location != null) {
                        Item item = new Item(itemName, location);
                        location.addItem(item);

                    } else {
                        System.out.println("neni tu predmet");
                    }

                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }


        private void loadItems (String filePath){
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String itemName = parts[0].trim();
                        int roomId = Integer.parseInt(parts[1].trim());

                        Location location = rooms.get(roomId);

                        if (location != null) {
                            Item item = new Item(itemName, location);
                            location.addItem(item);

                        } else {
                            System.out.println("neexistuje");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public List<Item> getItems () {
            return items;
        }


        public Map<Integer, Location> getRooms () {
            return rooms;
        }


        public List<Item> getItemsInRoom (Location room){
            List<Item> itemsinroom = new ArrayList<>();
            for (Item item : items) {
                if (item.getLocation().equals(room)) {
                    itemsinroom.add(item);
                }
            }
            return itemsinroom;
        }



}