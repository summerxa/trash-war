/**
   The Player class that represents a player in the game. Stores information
   about the player. 

   @author  Meenakshi Mukkamala
   @version 04/27/2022

   @author  Sources - Anne, Vaishnavi
 */
public class Player
{ 
    /**
     * a integer variable that stores the number of points player has
     */
    private int points; 

    /**
     * a name for the player
     */
    private String name;


    /**
     * 
     * @param name the name of the player
     * sets the name of the player and the points to 0
     */
    public Player(String name){
        this.name = name;
        points = 0;
    }

    /**
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @return points
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * 
     * @param numPoints the number of points to add
     * Adds numPoints to the current number of points
     */
    public void addPoints(int numPoints)
    {
        points += numPoints; 
    }


    /**
     * 
     * @param numPoints the number of points to subtract
     * Subtracts numPoints from the current number of points
     */
    public void subtractPoints(int numPoints)
    {
        points -= numPoints;
    }
   
   /**
     * Sets the number of points to numPoints
     * @param numPoints
     */
    public void setPoints(int numPoints)
    {
        points = numPoints;
    }

}
