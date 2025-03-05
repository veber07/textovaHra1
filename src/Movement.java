import java.util.Map;
public class Movement extends Command {
    private Map<Integer, Location> mapa;
    private int curLoc;

    public Movement(Map<Integer, Location> mapa,int startloc) {
        this.mapa = mapa;
        this.curLoc = startloc;
    }

    public void move(String direction) {
        Location curr = mapa.get(curLoc);

        if(curr == null) {
            System.out.println("chyba");
            return;
        }
        int newLoc = curr.getExit(direction);
        if(newLoc != 0 && mapa.containsKey(newLoc)) {
            curLoc = newLoc;

            System.out.println("presunuli jste se do " + mapa.get(curLoc).getName());
        }else {
            System.out.println("nejde");
        }
    }

    public void curVypis(){
        Location currentLocation = mapa.get(curLoc);
        if (currentLocation != null) {
            System.out.println("Jses v " + currentLocation.getName());
        } else {
            System.out.println("Nejde toto  " + curLoc);
        }
    }

    @Override
    public String execute() {
        return "jdes ;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
