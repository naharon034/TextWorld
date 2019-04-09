
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Level {
    private HashMap<String, Room> rooms;
    ArrayList<Creature> creatures;
    

    public Level() {
        rooms = new HashMap<String, Room>();
        creatures = new ArrayList<>();
    }

    public void tickAllCreatures(){
        for(Creature creature: creatures){
            creature.move();
        }
    }
    public void addCreature(Creature creature){
        if(!creature.equals(null)){ creatures.add(creature);}
    }
    public boolean removeCreature(Creature creature){
        return creatures.remove(creature);
    }

    public void addRoom(String name, String description) {
        Room room = new Room(name, description);
        rooms.put(name, room);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        if(n1!= null && n2!= null) {
            n1.addNeighbor(n2);
        }
    }

    public void addUndirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);
        if(n1!= null && n2!= null) {

            n1.addNeighbor(n2);
            n2.addNeighbor(n1);
        }
    }

    public Room getRoom(String name) {
        for(String key: rooms.keySet()){
            if(key.equals(name)){
                return rooms.get(key);
            }
        }
        return null;
    }
    public void displayNeighbors(Room n){
        System.out.println(n.getNeighborNames());
    }

//_______________________________________________________________________________________________________
    //ROOM:

    public class Room {
        private String name;
        //private ArrayList<Room> neighbors;
        private HashMap <String, Room> neighbors;
        private String description;
        private ArrayList<Creature> creatures;

        private Room(String name, String description) {
            this.description = description;
            this.name = name;
            neighbors = new HashMap<String, Room>();
            creatures = new ArrayList<>();
        }
        public int getNumNeighbors(){
            ArrayList<Room> rooms= new ArrayList<Room>(neighbors.values());
            return rooms.size();
        }

        public boolean removeCreature(Creature creature){
                return creatures.remove(creature);
        }
        public boolean addCreature(Creature creature){
            if(creature != null) {
                creatures.add(creature);
                return true;
            }
            return false;
        }
        private int getIndexOfCreature(Creature c){
            for(int i = 0; i < creatures.size(); i++){
                if(creatures.get(i) .equals(c)){
                    return i;
                }
            }
            return  -1;
        }
        public ArrayList<Creature> getCreatures() {
            return creatures;
        }

        public void setCreatures(ArrayList<Creature> creatures) {
            this.creatures = creatures;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HashMap<String, Room> getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(HashMap<String, Room> neighbors) {
            this.neighbors = neighbors;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }

        private ArrayList <Item> items;


        public ArrayList<Item> getItems(){
            return items;
        }
        public String displayItems(){
            String output = "";
            for(Item item: items){
                output+=item.getName() + ", ";
            }
            return output;
        }
        public void  addItem(String str){
            addItem(str, "no description");
        }
        public void addItem(String name, String description){
            items.add(new Item(name, description));
        }
        public Item removeItem(String name){
            Item item = getItemByName(name);
            if(item!= null) {
                items.remove(item);
                return item;
            }
            return null;
        }
        public boolean  destroyItem(){
            Item item = getItemByName(name);
            if(item!= null) {
                items.remove(item);
                return true;
            }
            return false;
        }
        private Item getItemByName(String name){
            for(Item item:items){
                if(item.getName().equals(name)){
                    return item;
                }
            }
            return null;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return description;
        }
        private void addNeighbor(Room toAdd) {
            neighbors.put(toAdd.getName(),toAdd);
        }

        public String getNeighborNames() {
            String output = "";
            for (String key : neighbors.keySet()) {
                output += key + " \t";
            }
            return output;
        }

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public String getName() {
            return name;
        }
    }

}