
/**
 * Abstratkní třída Command dělá základ pro všechny příkazy ve hře

 */
public abstract class Command {

    /**
     * Spustí příkaz a vrátí výsledek jeho provedení jako řetězec.
     *
     */
    public abstract String execute();
    /**
     *Určí jestli příkaz ukončí hru
     */
    public abstract boolean exit();

}
