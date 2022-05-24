import java.util.Random;

/**
   The Card class represents a card in the game. It either has an image that
   represents a trash, recyclable, or compostable item, or has the word
   "Trash", "Recycle", or "Compost" written on it. The Card class will 
   contain the mechanism to deal the cards randomly into the center  

   @author  Meenakshi Mukkamala
   @author  Vaishnavi Kunapuli
   @version 04/27/2022

   @author  Sources - Anne
 */


public class Card
{ 
  /**
   * a string array to store compost pictures
   */
   private String[] compost = {"TrashWarImagesAndSounds/bananapeel.jpg", "TrashWarImagesAndSounds/branches.jpg", 
 "TrashWarImagesAndSounds/orangepeel.jpg", "TrashWarImagesAndSounds/compost.jpg"};

 /**
  *  a string array to store trash pictures
  */
  private String[] trash = {"TrashWarImagesAndSounds/trash.jpg", "TrashWarImagesAndSounds/cardboard.jpg", 
  "TrashWarImagesAndSounds/cermicpot.jpg",  "TrashWarImagesAndSounds/diaper.jpg"};

  /**
   *  a string array to store recycle pictures
   */
  private String[] recycle = {  "TrashWarImagesAndSounds/recycle.jpg", "TrashWarImagesAndSounds/aluminum.jpg", 
  "TrashWarImagesAndSounds/bottle.jpg", "TrashWarImagesAndSounds/glass.jpg"}; 

  /**
   * a String variable to store the type of card
   */
  
  private String type;

  /**
   * a String variable to store the filepath of the image on card
   */
  private String filePath; 

  /**
   * Constructor
   */
  public Card(){
    String[] typ = {"Compost", "Recycle", "Trash"};
    Random rand = new Random();
    type = typ[rand.nextInt(3)];
    if (type.equals("Compost"))
    {
      filePath = compost[rand.nextInt(4)];
    }
    else if (type.equals("Recycle"))
    {
      filePath = recycle[rand.nextInt(4)];
    }
    else if (type.equals("Trash"))
    {
      filePath = trash[rand.nextInt(4)];
    }
  }


  /**
   * Initializes filePath
   * @param type the type of Card
   */

  public Card(String type)
  {
    Random rand = new Random();
    
    if (type.equals("Compost"))
    {
      filePath = compost[rand.nextInt(4)];
    }
    else if (type.equals("Recycle"))
    {
      filePath = recycle[rand.nextInt(4)];
    }
    else if (type.equals("Trash"))
    {
      filePath = trash[rand.nextInt(4)];
    }
  }

  /**
   * 
   * @param type the type of Card
   * @param fileP the filePath name
   */
  public Card(String type, String fileP)
  {
    this.type = type; 
    filePath = fileP;
  }

  /**
   * Return the type of the card (Compost, Recycle, or Trash)
   * @return String
   */
  public String getType(){
    return type; 
   


  }

  /**
   * Returns the filePath
   * @return the filepaths
   */
  public String getFilePath(){
    return filePath;

  }
}