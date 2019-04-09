import java.util.ArrayList;
import java.util.HashMap;


public abstract class Creature {
    protected Level.Room currentRoom;
//    HashMap<String, Level.Room> map;
//    ArrayList<Level.Room> rooms;
    protected Player player;
    protected Level level;
   // Level.Room currentPlayerRoom;
    public Creature(Level.Room currentRoom){
        this.currentRoom = currentRoom;
        player = Main.getPlayer();
         level =Main.getLevel();
        level.addCreature(this);
    }
    protected abstract void move();

    protected Level.Room getRandomAdjacentRoom(){
        HashMap<String, Level.Room> map  = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room>(map.values());
        int rand = (int)(Math.random()*rooms.size());
        if(rooms.size()>0) {
            return rooms.get(rand);
        }
        return currentRoom;
    }
    protected Level.Room getCurrentRoom(){
        return currentRoom;
    }

    protected boolean moveToRoom(Level.Room room){
        if(currentRoom.getNeighbor(room.getName())!=null){
            currentRoom.removeCreature(this);
            currentRoom = room;
            currentRoom.addCreature(this);
            return true;
        }
        return false;
    }
    protected void moveRandom(){
        Level.Room randRoom = getRandomAdjacentRoom();

        if(randRoom != null){ moveToRoom(randRoom);}
    }


}
