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





    public void inicial() {
        WorldMap wm = new WorldMap();
        wm.loadMap();
        Map<Integer, Location> mapa = wm.getMapa();
        String dialogueFilePath = "src/dialog";
        characterManager = new Char(wm, dialogueFilePath);
        Movement movement = new Movement(mapa, 1);
        Inventaros inve = new Inventaros();

        prikazy = new HashMap<>();


        prikazy.put("go", new Move(movement));
        prikazy.put("exit", new Exit());
        prikazy.put("give", new Give(characterManager));
        prikazy.put("take", new Take(movement,inve));
        prikazy.put("speak", new Speak(characterManager, movement));

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
