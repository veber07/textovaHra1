public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap();
        if(worldMap.loadMap()){
            worldMap.movement.curVypis();
            worldMap.movement.move("west");
            worldMap.movement.curVypis();

        }

    }
}