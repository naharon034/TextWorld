public class TakeCommand implements Command {
    Level level;
    String itemName;
    public  TakeCommand(Level level){
        this.level = level;
    }
    public void init(String userString){
        this.itemName = getLastWordIn(userString);
    }

    @Override
    public boolean execute() {
        Player p = level.getPlayer();
    }
}
