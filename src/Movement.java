import java.util.Map;
public class Movement {
    public Map<Integer, Location> mapa;
    private int curLoc;
    public Map<Integer, Location> getMapa() {
        return mapa;
    }

    public int getCurLoc() {
        return curLoc;
    }

    public void setCurLoc(int curLoc) {
        this.curLoc = curLoc;
    }

    public Movement(Map<Integer, Location> mapa, int startloc) {
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
            System.out.println("jsi v  " + mapa.get(curLoc).getName());
        }else {
            System.out.println("nejde");
        }
    }

    public String curVypis() {
        System.out.println("1");
        System.out.println( mapa.get(curLoc));
        Location currentLocation = mapa.get(curLoc);
        if (currentLocation != null) {
            return ("jses v" + currentLocation.getName());
        } else {
           return ("nejde to" + curLoc);
        }

    }




}
