import java.util.Map;
import java.util.Scanner;

public class Give extends Command {


    private Char charManager;
    private Inventaros inventory;
    private Movement movement;
    private boolean dead = false;

    public Give(Char charManag, Inventaros inve, Movement movement) {
        this.charManager = charManag;
        this.inventory = inve;
        this.movement = movement;
    }

    @Override
    public String execute() {
        Location currentRoom = movement.mapa.get(movement.getCurLoc());

        if (currentRoom == null) {
            return "nejsi v dobre mistnosti";
        }

        Map<String, Location> characters = charManager.getCharacterRoomMap();
        String charere = null;
        for (Map.Entry<String, Location> entry : characters.entrySet()) {
            if (entry.getValue().equals(currentRoom)) {
                charere = entry.getKey();
                break;
            }
        }

        if (charere == null) {
            return "neni zde postava";
        }


        if (inventory.getItems().isEmpty()) {
            return "nemas itemicek";
        }

        System.out.println("Vyber predmet" + charere + ":");
        for (int i = 0; i < inventory.getItems().size(); i++) {
            System.out.println((i + 1)  + inventory.getItems().get(i).getName());
        }

        Scanner sc = new Scanner(System.in);
        int vyber;
        try {
            vyber = sc.nextInt();
        } catch (Exception e) {
            return "F";
        }

        if (vyber < 1 || vyber > inventory.getItems().size()) {
            return "spatny vyber";
        }


        Item selectItem = inventory.getItems().get(vyber - 1);
        inventory.getItems().remove(selectItem);


        charManager.toChar(selectItem);
        charManager.useItem(charere, selectItem);

        return "DAL SI " + selectItem.getName() + "postave" + charere ;

    }













    @Override
    public boolean exit() {
        if(dead){
          return true;
      }else
        return false;
    }
}





