import java.util.*;

/**
 * Represents an update to the game state.
 * 
 * @author  Anne Xia
 * @version 05/03/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class StateUpdate {
  /**
  * A StateUpdate with this type is a card slap update.
  */
  public static final int CARD_SLAP = 1357;
  /**
  * A StateUpdate with this type is a score change update.
  */
  public static final int NEW_SCORE = 2468;
  
  private Player player;
  private int type;
  private int score;
  
  /**
  * Constructs a card slap update.
  * @param player the player who slapped a card.
  */
  public StateUpdate(Player player) {
    type = CARD_SLAP;
    this.player = player;
    score = -1;
  }
  
  /**
  * Constructs a score change update.
  * @param player the player whose score changed.
  * @param newScore the player's new score.
  */
  public StateUpdate(Player player, int newScore) {
    type = NEW_SCORE;
    this.player = player;
    score = newScore;
  }
  
  /**
  * Gets the type of update.
  * @return type the type of update.
  */
  public int getType() {
    return type;
  }
  
  /**
  * Gets the player affected by this update.
  * @return player the player affected by this update.
  */
  public Player getPlayer() {
    return player;
  }
  
  /**
  * If this update is a score change, gets the new score
  * of the player. If not, returns -1.
  * @return the new score of the player, or -1 if this is
  *         not a score change update.
  */
  public int getScore() {
    return score;
  }
  
  /**
  * String representation of this update.
  * @return String representation of this update.
  */
  public String toString() {
    if (type == CARD_SLAP) {
      return type + " " + player.getName();
    } else {
      return type + " " + player.getName() + " " + score;
    }
  }
}
