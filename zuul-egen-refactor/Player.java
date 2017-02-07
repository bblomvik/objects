import java.util.HashMap;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int totalWeight;
    private Room currentRoom;
    private Room previousRoom;
    private HashMap<String, Item> carriedItems;

    /**
     * Constructor for objects of class Player
     * Creates a player with generic name and total weight 2000g
     */
    public Player()
    {
        name = "playerName";
        totalWeight = 2000;
        carriedItems = new HashMap();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        cellar = new Room("below a secret hatch in the office");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("down", cellar);

        cellar.setExit("up", office);

        /////////////////////////////////////////////


        /////////////////////////////////////////////
        
        currentRoom = outside;  // start game outside
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;          
            System.out.println(currentRoom.getLongDescription());
            System.out.println();
            System.out.println();
        }
    }

    /**
     * Go to previous room
     */
    public void back() {
        if (previousRoom == null) {
            System.out.println("Sorry, you can't go back");
        }
        else {
            Room nextRoom = previousRoom;
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println();
            System.out.println();
        }
    }

    /**
     * Prints your current location and possible exits
     */
    public void printLocationInfo()
    {       
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Take a look around the room and print description and exits
     */
    public void look() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Engage battle with creature
     */
    public void fight() {
        boolean creaturePresent = false;
        if (creaturePresent = false) {
            System.out.println("You look around and see no foe");
        }
        else {
            System.out.println("You attack the monster with your bare hands!" + " \n" + "Only one of you will remain victorious"); 
        }
    }
}
