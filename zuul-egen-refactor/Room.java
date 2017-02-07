import java.util.Set;
import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> items;
    private Creature creature;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
        creature = null;
    }

    /**
     * Define an exit from this room
     * 
     * @param direction The direction of the exit
     * @param neighbor The room in the given direcrion
     */
    public void setExit(String direction, Room neighbor){
        exits.put(direction, neighbor);
    }
    
    /**
     * Returns the selected exit
     * 
     * @param String the direction the player wishes to move
     * @return The Room in the specified direction
     */
    public Room getExit(String direction){
        return exits.get(direction);
    }

    /**
     * Defines items in the room
     * 
     * @param name The name of the item
     * @param item The item in question
     */
    public void setItem(String name, Item item) {
        items.put(name, item);
    }
    
    /**
     * Returns the item
     * 
     * @param String Name of the item to be returned
     * @return item The Item object
     */
    public Item getItem(String item) {
        return items.get(item);
    }

    
    /**
     * Return a description of the room's exits,
     * for example "Exits: north west".
     * 
     * @return A list of the available exits.
     */
    public String getExitString() {
        String returnString="Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
        
    /**
     * Returns a list of the items in the room
     * 
     * @return items A list of items
     */
    public String getItemsString() {
        String returnString="Items:";
        Set<String> keys = items.keySet();
        for (String item: keys) {
            returnString += "" + item;
        }
        return returnString;
    }
    
    /**
     * @return The description of the room.
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * @return The description of the room with available exits.
     */
    public String getLongDescription()
    {
        return "You are: " + description + "\n" + getExitString();
    }
    
  

}
