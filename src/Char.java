import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Char {
    private Map<String, Integer> charIdsss = new HashMap<>();
    private Map<String, Location> charMap = new HashMap<>();
    private Map<String, String> dialog = new HashMap<>();
    private Map<String, Boolean> charHealth = new HashMap<>();
    private Map<Integer, Item> charItems = new HashMap<>();

    public Char(WorldMap worldMap, String characterFilePath, String dialogFilePath) {
        loadchar(characterFilePath);
        charToRooms(worldMap);
        loadDialog(dialogFilePath);


        for (String character : charIdsss.keySet()) {
            charHealth.put(character, false);
        }

        charlocation();
    }

    private void loadchar(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String character = parts[1].trim();
                    charIdsss.put(character, id);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void charToRooms(WorldMap worldMap) {
        ArrayList<Location> availableRooms = new ArrayList<>(worldMap.getMapa().values());
        availableRooms.removeIf(room -> room.getName().equals("chodba"));
        Random rd = new Random();
        for (String character : charIdsss.keySet()) {
            if (!availableRooms.isEmpty()) {
                Location room = availableRooms.remove(rd.nextInt(availableRooms.size()));
                charMap.put(character, room);
            } else {
                System.out.println("malo mistnosti");
            }
        }
    }

    public void charlocation() {
        System.out.println("Postavy bzly umisteni do mistnosti ");
        for (Map.Entry<String, Location> entry : charMap.entrySet()) {
            System.out.println( entry.getKey() + "je v mistnosti" + entry.getValue().getName());
        }
    }

    private void loadDialog(String filePath) {
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



    public Map<String, Location> getCharacterRoomMap() {
        return charMap;
    }

    public void useItem(String characterName, Item item) {
        if (!charHealth.containsKey(characterName)) {
            System.out.println("neni postavickos.");
            return;
        }

        if (charHealth.get(characterName)) {
            System.out.println("postava" + characterName + "je vylecena ");
            return;
        }

        int correctItemId = charIdsss.get(characterName);
        int usedItemikId = item.importanteId();


        System.out.println("dobry predmet/" + characterName + "ID" + correctItemId);
        System.out.println("pouzil/ " + usedItemikId);

        if (usedItemikId == correctItemId) {

            charHealth.put(characterName, true);


            if (allChaHealed()) {
                System.out.println("uzdravil si vsechny simulanty");
                System.exit(0);
            }
        } else {
            System.out.println("postava " + characterName + " zemrela takze si prohral");
            System.exit(0);
        }

    }

    public void toChar(Item item) {
        charItems.put(item.importanteId(), item);
    }

    public String getCharacterDialog(String character) {
        return dialog.getOrDefault(character, "ma zasitou pusu");
    }
    public boolean allChaHealed() {
        for (boolean healed : charHealth.values()) {
            if (!healed) {
                return false;
            }
        }
        return true;
    }
}






