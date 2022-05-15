
/**
 * Center Deck pile which collected cards from players and checks if a player identified one of the three patterns
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */
import java.util.*;

public class CenterDeck
{
   

    /**
     * Collection of all cards in the center deck TBD: What collection we are
     * going to use; temporarily ArrayList
     */
    private LinkedList<String> centerDeckCards;

    /**
     * Constructor
     */
    public CenterDeck()
    {
        
        centerDeckCards = new LinkedList<String>();
    }


    /**
     * Adds a new card to the center deck
     * 
     * @param card
     */
    public void addCard(String card)
    {
        centerDeckCards.add(card);
    }

    /**
     * Adds a new card to the bottom of centerDeck
     * 
     * @param card
     */
    public void addBottom(String card)
    {
        centerDeckCards.add(0, card);
    }

    /**
     * Emptyies out all the cards in the center deck
     */
    public void emptyDeck()
    {
        centerDeckCards.clear();
    }


    /**
     * Returns the total number of cards in the center deck
     * 
     * @return int
     */
    public int totalCards()
    {
        return centerDeckCards.size();
    }


    /**
     * Checks if the top card and card directly beneath it are of the same
     * type(Recycle, Compost, or Trash)
     * 
     * @return
     */
    public boolean sandwich()
    {
        if (centerDeckCards.size() >= 2) {
            return centerDeckCards.get(centerDeckCards.size() - 1)
            .equals(centerDeckCards.get(centerDeckCards.size() - 2));
            
        }
        else
            return false;
    }

    /**
     * Checks if three cards in a row are of types Trash, Recycle, and Compost in no particular order
     * @return boolean
     */
    public boolean threeInARow()
    {
        if (centerDeckCards.size() >= 3)
        {
            int trashCount = 0;
            int compostCount = 0;
            int recycleCount = 0;
            String one = centerDeckCards.get(centerDeckCards.size() - 1);
            if (one.equals("Trash"))
                trashCount++;
            else if (one.equals("Compost"))
                compostCount++;
            else
                recycleCount++;
            String two = centerDeckCards.get(centerDeckCards.size() - 2);
            if (two.equals("Trash"))
                trashCount++;
            else if (two.equals("Compost"))
                compostCount++;
            else
                recycleCount++;
            String three = centerDeckCards.get(centerDeckCards.size() - 3);
            if (three.equals("Trash"))
                trashCount++;
            else if (three.equals("Compost"))
                compostCount++;
            else
                recycleCount++;
            return (trashCount == 1) && (compostCount == 1) && (recycleCount == 1);
        }
        else
            return false;

    }

    /**
     * Check if the top card and bottom card in the center deck are of the same type
     * @return boolean
     */
    public boolean topBottom()
    {
        if (centerDeckCards.size() >= 2)
            return centerDeckCards.get(0)
                .equals(centerDeckCards.get(centerDeckCards.size() - 1));
        else
            return false;
    }


    /**
     * Checks if the center deck is empty
     * 
     * @return boolean
     */
    public boolean isEmpty()
    {
        return centerDeckCards.isEmpty();
    }

}
