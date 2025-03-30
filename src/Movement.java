import java.util.HashSet;
import java.util.Map;
public class Movement {
    public Map<Integer, Location> mapa;
    private int curLoc;
    private HashSet<Integer> lockedRooms = new HashSet<>();
    private Inventaros inventaros;
    public Map<Integer, Location> getMapa() {
        return mapa;
    }
    private int lastloc = 1;

    public int getCurLoc() {
        return curLoc;
    }

    public void setCurLoc(int curLoc) {
        this.curLoc = curLoc;
    }
    private boolean roomUnlock = false;

    public Movement(Map<Integer, Location> mapa, int startloc,Inventaros inventaros) {
        this.mapa = mapa;
        this.curLoc = startloc;
        this.inventaros = inventaros;
        lockedRooms.add(3);
        lockedRooms.add(6);




    }

    public void move(String direction) {
        Location currentRoom = mapa.get(curLoc);

        if (currentRoom == null) {
            currentRoom = mapa.get(lastloc);
        }


        int nextLocId = currentRoom.getExit(direction);


        if (nextLocId == -1) {
            System.out.println("neplatny smer ");
            return;
        }

        Location nextRoom = mapa.get(nextLocId);

        if (nextRoom != null && nextRoom.isLocked()) {
            if (!roomUnlock) {
                System.out.println("mistnost je zamcena musis ji odemknout prikazem unlock pokud mas klic");
                return;
            }
        }


        if (nextRoom != null) {
            lastloc = curLoc;
            curLoc = nextLocId;
            System.out.println("jsi v mistnosti: " + nextRoom.getName());
        } else {
            System.out.println("spatny prikaz");
        }

    }

    public String curVypis() {
        Location currentRoom = mapa.get(curLoc);
        if (currentRoom != null) {
            return "jsi v místnosti" + currentRoom.getName();
        }
        return "neexistuje";
    }


    public void unlockRoom() {
        roomUnlock = true;
    }
    }






