import java.util.ArrayList;

public class Player {
    private String name, description; // fields
    private ArrayList<Item> items;
    private  Level.Room currentRoom;

    Player(String name, String description){
        this.name = name;
        this.description = description;
    }
    public void addItem( Item item ){
        items.add(item);
    }
    public Item removeItem( String name ){
        Item item = getItem(name);
        if(item!= null) {
            items.remove(item);
            return item;
        }
        return null;
    }
    public boolean destroyItem( String name ){
        if(removeItem(name)== null){
            return false;
        }
        return true;
    }
    public ArrayList getItems(){
        return items;
    }
    public void displayInventory(){
        for(Item item:items){
            System.out.print(item.getName() + "\t");
        }
        System.out.println();
    }
    public Level.Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Level.Room newroom){
        currentRoom = newroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean moveToRoom(String name ){
        if(currentRoom.getNeighbor(name) != null) {
            currentRoom = currentRoom.getNeighbor(name);
            return true;
        }
        return false;
    }
    private Item getItem(String name){
        for(Item item: items){
            if (item.getName().equals(name)){
                return  item;
            }
        }
        return null;
    }

// return true if successful, false otherwise
}