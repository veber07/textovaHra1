
/**
 * Třída reprezentující příkaz pro pohyb ve hře .
 *
 * @see Command
 * @see Movement
 */
public class Move extends Command {
    private Movement movement;
    private String direction;


    public Move(Movement movement) {
        this.movement = movement;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    /**
     * Vykoná příkaz pohybu, pokud směr není zadaný tak  metoda vrátí zprávu "zadat smer".
     */
    @Override
    public String execute() {
        if (direction == null || direction.isEmpty()) {
            return "zadat smer";
        }

        movement.move(direction);
        return movement.curVypis();
    }
    /**
     * Určuje, že hra nemá být ukončena.

     */
    @Override
    public boolean exit() {
        return false;
    }
}