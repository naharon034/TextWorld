import java.util.ArrayList;
import java.util.HashMap;

public class PopStar extends Creature{
    public PopStar(Level.Room currentRoom) {
        super(currentRoom);
    }
    public void move(){
        moveTowards();
    }
    protected void moveTowards(){
        HashMap<String, Level.Room> map  = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room>(map.values());
        for (int i = 0; i < rooms.size(); i++) {
            HashMap<String, Level.Room> map2 = rooms.get(i).getNeighbors();
            ArrayList<Level.Room> neighbors = new ArrayList<Level.Room>(map2.values()); //neighbors of the neighboring room

            if(rooms.get(i).equals(player.getCurrentRoom())){ //if player is in neighboring room
                moveToRoom(player.getCurrentRoom());
            }

            for (int j = 0; j < neighbors.size(); j++) {
                if(neighbors.get(j).equals(player.getCurrentRoom())){ //if player is in neighbor's neighbor
                    moveToRoom(rooms.get(i));
                }
            }
        }
    }
}
