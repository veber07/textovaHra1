public class Move extends Command {
    private Movement movement;
    private String direction;


    public Move(Movement movement) {
        this.movement = movement;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute() {
        if (direction == null || direction.isEmpty()) {
            return "zadat smer";
        }

        movement.move(direction);
        return movement.curVypis();
    }

    @Override
    public boolean exit() {
        return false;
    }
}