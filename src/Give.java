public class Give extends Command {

    private Char characterManager;

    public Give(Char charManag) {
        this.characterManager = charManag;
    }


    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}





