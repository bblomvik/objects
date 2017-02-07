/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Item itemInRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cellar;
        Item flower, sword, potion, key;

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
        
        //create items
        flower = new Item("Flower: a beautiful red flower.", 3);
        sword = new Item("Longsword: a powerful weapon", 2);
        potion = new Item("Potion, a healing drink", 1);
        key = new Item("Key: this will open a locked door somewhere. but it's heavy!", 5);
        sword.printItem();
        
        // place items in room
        outside.setItem("flower", flower);
        pub.setItem("potion", potion);
        office.setItem("key", key);
        cellar.setItem("sword", sword);
        
        
        /////////////////////////////////////////////
        
        
        currentRoom = outside;  // start game outside
        itemInRoom = flower;    // places a flower outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println();
        printLocationInfo();
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
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            System.out.println(parser.showCommands());
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("fight")) {
            fight();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
    private void back() {
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
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
