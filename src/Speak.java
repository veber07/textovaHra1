import java.util.Map;

public class Speak extends Command {

    private Char charmanag;
    private Movement movement;

    public Speak(Char charmanag, Movement movement) {
        this.charmanag = charmanag;
        this.movement = movement;
    }

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

    @Override
    public boolean exit() {
        return false;
    }

    private Location getroombyid(int id) {
        Location room = movement.getMapa().get(id);
        if (room == null) {
            System.out.println("nenasel jsem to");
        } else {
            System.out.println("jdeto ");
        }
        return room;
    }


    private String charbyroom(Location room) {
        for (Map.Entry<String, Location> entry : charmanag.getCharacterRoomMap().entrySet()) {
            System.out.println("lol" + entry.getValue().getName() + " (idicko" + entry.getValue().getId() + " misnoti" + room.getName() + " idicko " + room.getId() + ")");


            if (entry.getValue().getId() == room.getId()) {
                System.out.println("Postava" + entry.getKey() + "je v mistnusce " + room.getName());
                return entry.getKey();
            }
        }
        return null;
    }
    }
