import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Třída Char spravuje postavy ve hře,umístěnía jejich  zdraví a dialogy, načítá postavy ze souboru a dává je do místností.
 */
public class Char {
    private Map<String, Integer> charIdsss = new HashMap<>();
    private Map<String, Location> charMap = new HashMap<>();
    private Map<String, String> dialog = new HashMap<>();
    private Map<String, Boolean> charHealth = new HashMap<>();
    private Map<Integer, Item> charItems = new HashMap<>();
    /**
     * Konstruktor
     * @param worldMap          Mapa místností.
     * @param characterFilePath Cesta k souboru s postavami.
     * @param dialogFilePath    Cesta k souboru s dialogy.
     */

    public Char(WorldMap worldMap, String characterFilePath, String dialogFilePath) {
        loadchar(characterFilePath);
        charToRooms(worldMap);
        loadDialog(dialogFilePath);


        for (String character : charIdsss.keySet()) {
            charHealth.put(character, false);
        }

        charlocation();
    }
    /**
     * Načte seznam postav ze souboru a uloží je do mapy.
     *
     * @param filePath Cesta k souboru s postavami.
     */
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

    /**
     * Přiřadí k postavě její místnost.
     *
     * @param worldMap Mapa místností..
     */

    private void charToRooms(WorldMap worldMap) {
        ArrayList<Location> availableRooms = new ArrayList<>(worldMap.getMapa().values());
        availableRooms.removeIf(room -> room.getName().equals("Chodba"));
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

    /**
     * Vrací true, pokud je postava vyléčená  jinak false.
     *
     * @param characterName Jméno postavy.
     */
    public boolean isCharacterHealed(String characterName) {
        return charHealth.getOrDefault(characterName, false);
    }


    /**
     * Vypíše do konzole umístění všech postav.
     */
    public void charlocation() {
     //   System.out.println("Postavy bzly umisteni do mistnosti ");
        for (Map.Entry<String, Location> entry : charMap.entrySet()) {
          //  System.out.println(entry.getKey() + "je v mistnosti" + entry.getValue().getName());
        }
    }
    /**
     * Načte dialogy postav ze souboru a uloží je do mapy.
     *
     * @param filePath Cesta k souboru s dialogy.
     */
    private void loadDialog(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
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
    /**
     * Vrací mapu postav a jejich umístění.
     *
     */
    public Map<String, Location> getCharacterRoomMap() {
        return charMap;
    }
    /**
     *Použije předmět na danou posatvu a podle id pozná jestli ji vyléčí.
     *
     * @param characterName Jméno postavy.
     * @param item Předmět k použití.
     */
    public void useItem(String characterName, Item item) {
        if (!charHealth.containsKey(characterName)) {
           // System.out.println("neni postavickos.");
            return;
        }




        int correctItemId = charIdsss.get(characterName);
        int usedItemikId = item.importanteId();


      //  System.out.println("dobry predmet/" + characterName + "ID" + correctItemId);
     //   System.out.println("pouzil/ " + usedItemikId);
        if (usedItemikId == correctItemId) {
            charHealth.put(characterName, true);
         //   System.out.println("Hodnota charHealth po vyleceni: " + charHealth.get(characterName));
            if (allChaHealed()) {
               System.out.println("uzdravil si vsechny simulanty");
                System.exit(0);
            }
        } else {
            System.out.println("postava " + characterName + " zemrela takze si prohral");
            System.exit(0);
        }

    }



    /**
     * Přidá předmět do mapy.
     *
     * @param item Předmět k přidání.
     */
    public void toChar(Item item) {
        charItems.put(item.importanteId(), item);
    }
    /**
     * Vrací dialog postav pokud ho má pokud ne vráti default zprávu.
     *
     * @param character Jméno postavy.
     *
     */
    public String getCharacterDialog(String character) {
        return dialog.getOrDefault(character, "ma zasitou pusu");
    }

    /**
     * Kontroluje,jestli jsou všechny postavy vyléčeny.
     *
     *
     */

    public boolean allChaHealed() {
        for (boolean healed : charHealth.values()) {
            if (!healed) {

                return false;
            }
        }
        System.out.print("Výborně výlečil jsi všechny lidi, můžeš v klidu odject domů");
        return true;
    }

    @Override
    public String toString() {
        return "Char{" +
                "charMap=" + charMap +
                '}';
    }
}







