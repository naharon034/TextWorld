public class Chicken extends  Creature{

    public Chicken(Level.Room currentRoom) {
        super(currentRoom);
    }
    public void move(){
        moveRandom();
    }
}
