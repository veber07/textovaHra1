import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Console {
    ArrayList<String> commands;
    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> prikazy;
    private Char characterManager;
    private Itemikz itemikz;





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
        Movement movement = new Movement(mapa, 1,inve);


        prikazy = new HashMap<>();


        prikazy.put("go", new Move(movement));
        prikazy.put("exit", new Exit());
        prikazy.put("give", new Give(characterManager,inve,movement));
        prikazy.put("take", new Take(movement,inve));
        prikazy.put("speak", new Speak(characterManager, movement));
        prikazy.put("view", new View(movement, inve));
        prikazy.put("unlock", new Unlock(movement, inve));

    }
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
            System.out.println("neznam tento prikaz vocasi");
        }


    }
    public void start(){
        inicial();
        do{
            doIt();

        }while(!exit);
    }
}
