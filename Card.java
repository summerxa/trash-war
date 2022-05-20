import java.util.Random;

/**
   The Card class represents a card in the game. It either has an image that
   represents a trash, recyclable, or compostable item, or has the word
   "Trash", "Recycle", or "Compost" written on it. The Card class will 
   contain the mechanism to deal the cards randomly into the center  

   @author  Meenakshi Mukkamala
   @author  Vaishnavi Kunapuli
   @version 04/27/2022

   @author  Sources -
 */


public class Card
{ 
 /*  private String[] compost = {"TrashWarImagesAndSounds\\bananapeel.jpg", "TrashWarImagesAndSounds\\branches.jpg", 
 "TrashWarImagesAndSounds\\orangepeel.jpg", "TrashWarImagesAndSounds\\compost.jpg"};

  private String[] trash = {"TrashWarImagesAndSounds\\trash.jpg", "TrashWarImagesAndSounds\\chips.jpg", 
  "TrashWarImagesAndSounds\\cermicpot.jpg",  "TrashWarImagesAndSounds\\diaper.jpg"};

  private String[] recycle = {  "TrashWarImagesAndSounds\\recycle.jpg", "TrashWarImagesAndSounds\\aluminum.jpg", 
  "TrashWarImagesAndSounds\\bottle.jpg", "TrashWarImagesAndSounds\\cardboard.jpg", "TrashWarImagesAndSounds\\glass.jpg"}; */
  /**
   * a String variable to store the type of card
   */
  
  private String type;
  private String filePath;


  /**
   * Constructor
   */
  public Card(){
    String[] typ = {"Compost", "Recycle", "Trash"};
    Random rand = new Random();
    type = typ[rand.nextInt(3)];
  }

  public Card(String type, String filePath)
  {
    this.type = type; 
    this.filePath = filePath;
  }

  /**
   * Return the type of the card (Compost, Recycle, or Trash)
   * @return String
   */
  public String getType(){
    return type; 
   


  }

  public String getFilePath(){
    return filePath;

  }

}