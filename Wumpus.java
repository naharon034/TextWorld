import java.util.ArrayList;
import java.util.HashMap;

public class Wumpus extends Creature{
    public Wumpus(Level.Room currentRoom) {
        super(currentRoom);
    }
    public void move(){
        moveAwayRandom();
    }

    private void moveAway(){
        HashMap<String, Level.Room> map  = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room>(map.values());
        ArrayList availableRooms =new ArrayList();
        for (int i = 0; i < rooms.size(); i++) {
            HashMap<String, Level.Room> map2 = rooms.get(i).getNeighbors();
            ArrayList<Level.Room> neighbors = new ArrayList<Level.Room>(map2.values()); //neighbors of the neighboring room

            if(!rooms.get(i).equals(player.getCurrentRoom())) { //if player is in neighboring room

                for (int j = 0; j < neighbors.size(); j++) {
                    if (!neighbors.get(j).equals(player.getCurrentRoom())) { //if player is in neighbor's neighbor
                        availableRooms.add(rooms.get(i));
                    }
                }
            }
        }
        int rand = (int)(Math.random() *availableRooms.size());
        if(availableRooms.size() > 0){  moveToRoom(availableRooms.get(rand));}

        }

    private void moveAwayRandom() {
        ArrayList<Level.Room> adjacentRoomsWithoutPlayer = new ArrayList<>();
        HashMap<String, Level.Room> map  = currentRoom.getNeighbors();
        ArrayList<Level.Room> rooms = new ArrayList<Level.Room>(map.values());
        for (Level.Room room: rooms) {
            if (!room.equals(player.getCurrentRoom())){
                adjacentRoomsWithoutPlayer.add(room);
            }
        }
        if(adjacentRoomsWithoutPlayer.size() > 0){{
            int rand = (int)(Math.random()*adjacentRoomsWithoutPlayer.size());
            moveToRoom(adjacentRoomsWithoutPlayer.get(rand));
        }

        }
    }
}
