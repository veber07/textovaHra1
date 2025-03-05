import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Console {
    ArrayList<String> commands;
    private Scanner sc = new Scanner(System.in);
    private boolean exit = false;
    private HashMap<String, Command> prikazy;

    public void inicial() {
        WorldMap wm = new WorldMap();
        wm.loadMap();
        prikazy = new HashMap<>();
        prikazy.put("jdi", new Movement(wm, ));

    }
    private void doIt(){
        System.out.print(">");
        String prikaz = sc.next();
        if(prikazy.containsKey(prikaz)){
            System.out.println(prikazy.get(prikaz).execute());
            exit = prikazy.get(prikaz).exit();

        }else {
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
