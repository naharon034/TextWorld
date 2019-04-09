import java.util.Scanner;

public class Main {
    private static Player player;
    private static Level g;
    public static void main(String[] args) {

        g= new Level();
        g.addRoom("hall", "A dark, narrow hallway");
        g.addRoom("closet", "a dusty closet");
        g.addRoom("dungeon", "an old dungeon");

        g.addDirectedEdge("hall", "dungeon");
        g.addUndirectedEdge("hall", "closet");
        // Level.Room current = g.getRoom("hall");
        player = new Player("Noa", "Aharon");
        player.setCurrentRoom(g.getRoom("hall"));
        String response = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Type \"go <roomname> \" to go to the room");
        System.out.println("Type \"look\" to display all neighbors");
        System.out.println("Type \"add room <roomname, description> \" then a new neighbor will be added to the current room");
        System.out.println("Type \"take <item name>\" to remove an item from the room and add it to the player.");
        System.out.println("Type \"drop <item name>\" to will remove an item from the player and add it to the room.");

        System.out.println("Type \"quit\" to quit");
        Chicken chicken = new  Chicken(player.getCurrentRoom());

        do{
            String currentName = player.getCurrentRoom().getName();
            System.out.println("You are currently in the " + currentName);
            System.out.print("What do you want to do? >");
            response = s.nextLine();

            System.out.println(chicken.getCurrentRoom().getName());
            chicken.move();

            if(toMove(response)){
                String roomName = response.substring(4, response.length()-1);
                if(player.getCurrentRoom().getNeighbor(roomName) != null){
                    player.setCurrentRoom(player.getCurrentRoom().getNeighbor(roomName));
                }
            }
            if(response.equals("look")){
                System.out.print("You are in the "+ currentName + ": "+ player.getCurrentRoom().getDescription()+ ". You can go to the: ");
                g.displayNeighbors(player.getCurrentRoom());
            }

            if(toAddARoom(response)){
                String nodeName = response.substring(10, response.indexOf(","));
                String description = response.substring(response.indexOf(",")+2, response.length()-1);
                if(g.getRoom(nodeName) != null){
                    g.addUndirectedEdge(player.getCurrentRoom().getName(), nodeName);
                }else{
                    g.addRoom(nodeName, description);
                    g.addUndirectedEdge(player.getCurrentRoom().getName(), nodeName);
                }
            }
            if(response.length() >2 && response.substring(0,4).equals("take")){
                String item = response.substring(response.indexOf("<")+1, response.indexOf(">"));
                Item itemRemoved = player.getCurrentRoom().removeItem(item);
                if(itemRemoved!= null) {
                    player.addItem(itemRemoved);
                }
                System.out.println("You took the "+ itemRemoved.getName());
            }
            if(response.length()>2&& response.substring(0,4).equals("drop")){
                String item = response.substring(response.indexOf("<")+1, response.indexOf(">"));
                Item itemToDrop = player.removeItem(item);
                if(itemToDrop!= null){
                    player.getCurrentRoom().addItem(itemToDrop.getName());
                }
                System.out.println(itemToDrop.getName() + "was dropped in "+ player.getCurrentRoom());
            }

        }while (!response.equals("quit"));
    }

    private static boolean toAddARoom(String response) {
        if(response.length() <10) return false;
        if(response.substring(0, 10).equals("add room <")&&
                response.substring(response.length()-1,response.length()).equals(">")){
            return true;
        }
        return false;
    }

    private static boolean toMove(String str){
        if(str.length() < 4) return false;
        if (str.substring(0, 4).equals("go <")&&
                str.substring(str.length()-1,str.length()).equals(">")){
            return true;
        }
        return false;
    }
    public static Player getPlayer(){
        return player;
    }
    public static Level getLevel(){
        return g;
    }

}