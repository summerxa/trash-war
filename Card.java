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
  private String[] images;
  /**
   * Constructor
   */
  public Card(){

  }

  /**
   * Return the type of the card (Compost, Recycle, or Trash)
   * @return String
   */
  public String typeOfCard(){
    
    String[] type = {"Compost", "Recycle", "Trash"};
    Random rand = new Random();
    return type[rand.nextInt(3)];
  }
    
}