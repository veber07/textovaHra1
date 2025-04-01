

/**
 * Třída Exit představuje příkaz pro ukončení hry.
 */
public class Exit extends Command {
    /**
     * Spustí příkaz pro ukončení hry.
     *
     */
    @Override
    public String execute() {
        return "ending....endi....";
    }

    /**
     * Určuje, že hra má být ukončena.
     *

     */
    @Override
    public boolean exit() {
        return true;
    }
}
