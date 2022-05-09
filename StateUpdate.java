/**
 * Represents an update to the game state.
 * 
 * @author  Anne Xia
 * @version 05/08/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class StateUpdate {
    /**
     * Card slap update.
     */
    public static final int CARD_SLAP = 1234;
    /**
     * Score change update.
     */
    public static final int NEW_SCORE = 5678;
    /**
     * Deal card update.
     */
    public static final int DEAL_CARD = 9012;
    /**
     * Game over update.
     * TODO figure out if this is actually needed
     * TODO implement this update
     */
    public static final int STOP_GAME = 3456;

    private Player player;
    private int type;
    private int score = -1;
    private Card card = null;

    /**
     * Constructs a card slap update.
     * @param player the player who slapped a card.
     */
    public StateUpdate(Player player) {
        type = CARD_SLAP;
        this.player = player;
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
     * Constructs a deal card update.
     * @param player the player whose score changed.
     * @param newScore the player's new score.
     */
    public StateUpdate(Player player, Card card) {
        type = DEAL_CARD;
        this.player = player;
        this.card = card;
    }

    /**
     * Gets the type of update.
     * @return type the type of update.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the player for this update.
     * @return player the player for this update.
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
     * If this update is a card deal, gets the new card.
     * If not, returns null.
     * @return the new card, or null if this is
     *         not a deal card update.
     */
    public Card getCard() {
        return card;
    }

    /**
    * String representation of this update.
    * @return String representation of this update.
    */
    public String toString() {
        if (type == CARD_SLAP) {
            return type + " CARD_SLAP " + player.getName();
        } else if (type == NEW_SCORE) {
            return type + " NEW_SCORE " + player.getName() + " " + score;
        } else {
            return type + " DEAL_CARD " + player.getName() + " " + card.typeOfCard();
        }
    }
}
