import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Char {
    private boolean alive;
    private int id;

        private Map<String, Location> charMAp = new HashMap<>();
        private Map<String, String> dialog = new HashMap<>();
    private Map<Integer, Item> charItems = new HashMap<>();

        public Char(WorldMap worldMap, String dialogFilePath) {
            this.alive = true;

            ArrayList<String> characters = new ArrayList<>(Arrays.asList("Bazilisek", "VitekKvitek", "Sciuris", "Ninja", "Bruce"));
            Map<Integer, Location> rooms = worldMap.getMapa();
            List<Location> avaibleR = new ArrayList<>(rooms.values());
            avaibleR.removeIf(room -> room.getName().equals("CHodba"));


            Random rd = new Random();
            for (String character : characters) {

                int rommid = rd.nextInt(avaibleR.size());
                Location noromm = avaibleR.remove(rommid);
                charMAp.put(character, noromm);
            }


            loaddialog(dialogFilePath);
        }


        private void loaddialog(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String character = parts[0].trim();
                        String dialogue = parts[1].trim();
                        dialog.put(character, dialogue);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public String getCharacterDialogue(String character) {
            return dialog.getOrDefault(character, "nic");
        }


        public Map<String, Location> getCharacterRoomMap() {
            System.out.println("Postavy v mistnostech");
            for (Map.Entry<String, Location> entry : charMAp.entrySet()) {
                System.out.println(entry.getKey() + "je v mistnosti" + entry.getValue().getName() + " (ID " + entry.getValue().getId() + ")");
            }
            return charMAp;
        }
    public void useItem(Item item) {
        if (!alive) {
            System.out.println("Postave je mrtva ");
            return;
        }


        if (item.importanteId() == this.id) {
            System.out.println("good");
        } else {
            this.alive = false;
            System.out.println("bad");
        }
    }


    public void toChar(Item item) {
        charItems.put(item.importanteId(), item);
    }
    public String getName() {
        return "posatva " + this.id;
    }


    public boolean isAlive() {
        return alive;
    }



    public String death() {
        return "";

    }

    public String heal() {
        return "";

    }

    public String speak() {
        return "";
    }
}





