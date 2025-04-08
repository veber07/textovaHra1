import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
/**
 * Třída Console  zpracovává uživatelské  příkazy ve hře.
 * Inicializuje mapu světa, postavy, předměty a příkazy.
 */
public class Console {
    ArrayList<String> commands;
    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> prikazy;
    private Char characterManager;
    private Itemikz itemikz;




    /**
     * Inicializuje herní prostředí, načte mapu, postavy, dialogy a předměty a nastavý příkazy.
     *
     */
    public void inicial() {
        WorldMap wm = new WorldMap();
        wm.loadMap();
        Map<Integer, Location> mapa = wm.getMapa();
        String dialogiskyFilePath = "src/dialog";
        String itemsPath = "src/items";
        String charPath = "src/char";
        String roomsPath = "src/rooms";
        itemikz = new Itemikz( wm, itemsPath);
        characterManager = new Char(wm,charPath, dialogiskyFilePath);

        Inventaros inve = new Inventaros();
        Movement movement = new Movement(mapa, 1,inve,wm);


        prikazy = new HashMap<>();

        prikazy.put("help",new Help(this));
        prikazy.put("go", new Move(movement));
        prikazy.put("exit", new Exit());
        prikazy.put("give", new Give(characterManager,inve,movement));
        prikazy.put("take", new Take(movement,inve));
        prikazy.put("speak", new Speak(characterManager, movement));
        prikazy.put("view", new View(movement, inve));
        prikazy.put("unlock", new Unlock(movement, inve));

    }
    /**
     * Zpracuje uživatelský příkazy.
     */
    private void doIt() {
        System.out.print(">>>>");

        String prikaz = sc.next();
        if (prikazy.containsKey(prikaz)) {

             if (prikaz.equals("go")) {
                String direction = sc.next();
                Move movee = (Move) prikazy.get(prikaz);
                movee.setDirection(direction);
                System.out.println(movee.execute());
            } else {
                System.out.println(prikazy.get(prikaz).execute());
                exit = prikazy.get(prikaz).exit();
            }
        } else {
            System.out.println("neznam tento příkazík");
        }


    }


    /**
     * Metoda pro čtení a výpis textoveho souboru Zacatek.
     */
    private void printingStart() {
        String filePath = "src/Zacatek"; // Zadejte správnou cestu k souboru

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*** Spustí herní smyčku dokud ji hrář neukončí.
     * *
     * **/
    public void start(){
        printingStart();

        inicial();
        do{
            doIt();

        }while(!exit);
    }

    @Override
    public String toString() {
        return "Console{" +
                "prikazy=" + prikazy +
                '}';
    }

    public HashMap<String, Command> getPrikazy() {
        return prikazy;
    }
}
