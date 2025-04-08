import java.util.Map;
/**
 * Třída reprezentující  příkaz pro mluvení s postavou.

 */
public class Speak extends Command {

    private Char charmanag;
    private Movement movement;
    /**
     * Konstruktor
     *

     *
     * @param charmanag správa postav a jejich dialogů.
     * @param movement  správa pohybu mezi místnostmi.
     */
    public Speak(Char charmanag, Movement movement) {
        this.charmanag = charmanag;
        this.movement = movement;
    }
    /**
     * Provádí příkaz mluvení s postavou v aktuální místnosti,pokud se zde nenachazí vrátí mu zprávu o tom.
     *
     */
    @Override
    public String execute() {

        Location currentRoom = getroombyid(movement.getCurLoc());
        String charc = charbyroom(currentRoom);

        if (charc != null) {
            return charmanag.getCharacterDialog(charc);
        } else {
            return "neni tady postavicka";
        }
    }
    /**
     * Určuje, že hra nemá být ukončena.
     *
     */
    @Override
    public boolean exit() {
        return false;
    }
    /**
     * Získá místnost podle jejího ID.
     *
     *
     *
     * @param id ID místnosti, kterou chceme získat.
     */
    private Location getroombyid(int id) {
        Location room = movement.getMapa().get(id);
        if (room == null) {
            System.out.println("nenasel jsem to");
        } else {
          //  System.out.println("jdeto ");
        }
        return room;
    }

    /**
     * Získá postavu, která se nachází v dané místnosti(prochází seznam postav a hleda ID).
     *

     *
     * @param room Místnost, ve které hledáme postavu.
     */
    private String charbyroom(Location room) {
        for (Map.Entry<String, Location> entry : charmanag.getCharacterRoomMap().entrySet()) {
           // System.out.println("lol" + entry.getValue().getName() + " (idicko" + entry.getValue().getId() + " misnoti" + room.getName() + " idicko " + room.getId() + ")");


            if (entry.getValue().getId() == room.getId()) {
               // System.out.println("Postava" + entry.getKey() + "je v mistnusce " + room.getName());
                return entry.getKey();
            }
        }
        return null;
    }
    }
