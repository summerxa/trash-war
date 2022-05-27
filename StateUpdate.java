import java.util.Base64;

/**
 * Represents an update to the game state.
 * 
 * @author  Anne Xia
 * @version 05/12/2022
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
     * Game start update.
     */
    public static final int BGIN_GAME = 3456;
    /**
     * Game over update.
     */
    public static final int STOP_GAME = 7890;

    /**
     * Delimiter for updates.
     */
    public static final String U_DELIM = "~";
    /**
     * Delimiter for fields within updates.
     */
    public static final String F_DELIM = ":";
    /**
     * A null card's string representation.
     */
    public static final String NULL_CARD = "null";

    private String player = null;
    private int type;
    private int diff = -1;
    private Card card = null;

    /**
     * Constructs a card slap update.
     * @param player the player who slapped a card.
     */
    public StateUpdate(String player) {
        type = CARD_SLAP;
        this.player = encode64(player);
    }

    /**
     * Constructs a score change update.
     * @param player the player whose score changed.
     * @param diff the player's change in score.
     */
    public StateUpdate(String player, int diff) {
        type = NEW_SCORE;
        this.player = encode64(player);
        this.diff = diff;
    }

    /**
     * Constructs a deal card update.
     * @param player the player who dealt the card.
     * @param card the card.
     */
    public StateUpdate(Card card) {
        type = DEAL_CARD;
        this.card = card;
    }
    
    /**
     * Constructs a game start / end update.
     * @param type either StateUpdate.BGIN_GAME or StateUpdate.STOP_GAME.
     */
    public StateUpdate(int type) {
        this.type = type;
    }

    /**
     * Encodes a string to base 64. This is used to encode the player
     * name so that it does not contain any delimiter characters or whitespace.
     * @param s a string.
     * @return encoded string.
     */
    public static String encode64(String s) {
        try {
            return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
        } catch (Exception e) {
            System.out.println("Error in StateUpdate:");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decodes a string from base 64.
     * @param s the encoded string.
     * @return the decoded string.
     */
    public static String decode64(String s) {
        if (s == null) {
            return s;
        }
        byte[] bytes = Base64.getDecoder().decode(s);
        return new String(bytes);
    }

    /**
     * Gets the type of update.
     * @return type the type of update.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the name of the player, or null if this update
     * does not involve a player.
     * @return the name of the player or null.
     */
    public String getPlayer() {
        return decode64(player);
    }

    /**
     * If this update is a score change, gets the change in score
     * of the player. If not, returns -1.
     * @return the change in score of the player, or -1 if this is
     *         not a score change update.
     */
    public int getScoreDiff() {
        return diff;
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
     * Returns a string representation of this update. Player name
     * will be encoded.
     * @return a string representation of this update.
     */
    public String toString() {
        switch (type) {
            case CARD_SLAP:
                return type + F_DELIM + player;
            case NEW_SCORE:
                return type + F_DELIM + player + F_DELIM + diff;
            case DEAL_CARD:
                // if card is null, calling getType() will cause NullPointerException,
                // so just use a filler string
                String cardString = NULL_CARD;
                if (card != null) {
                    cardString = card.getType();
                }
                String ret = type + F_DELIM + cardString;
                if (card != null) {
                    ret += F_DELIM + card.getFilePath();
                }
                return ret;
            default:
                return type + "";
        }
    }
}
