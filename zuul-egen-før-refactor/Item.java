
/**
 * This class represents items in the game.
 * The item has description and weight.
 * 
 * @author Brynjar 
 * @version 1.0.0
 */
public class Item
{
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
    }

    /**
     * Define an item
     */
    public void setItem(String description, int weight) {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Print info about item
     */
    public void printItem() {
        System.out.println("" + description + " weight: "+ weight + "kg");
    }
    
    /**
     * Gathers and returns info about an item
     * 
     * @return String description of item
     */
    public String getItemDescription() {
        return "In this room you found" + description + " weight: " + weight + " kg";
    }
}
