import java.util.Map;
public class Movement {
    private Map<Integer, Location> mapa;
    private int curLoc;

    public Movement(Map<Integer, Location> mapa,int startloc) {
        this.mapa = mapa;
        this.curLoc = startloc;
    }

    private void move(String direction) {
        Location curr = mapa.get(curLoc);
        int newLoc = curr.getExit(direction);
        if(newLoc != 0 && mapa.containsKey(newLoc)) {
            curLoc = newLoc;

            System.out.println("presunuli jste se do " + mapa.get(curLoc).getName());
        }else {
            System.out.println("nejde");
        }
    }
    public void curVypis(){
        System.out.println("jses v " + mapa.get(curLoc).getName());
    }

}
