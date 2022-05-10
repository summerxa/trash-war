import java.util.*;

/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 05/10/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Server extends PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;
    
    private ServerThread sThread;
    private Player self;
    private GameThread gThread; // we will only have one player for now
    private Score scores;

    /**
     * Constructs a server that begins accepting players.
     */
    public Server() {
        // whether this list contains the host or not is tbd
        players = new ArrayList<Player>();
        // TODO initialize the host
        // TODO initialize scores
        sThread = new ServerThread();
        sThread.start();
    }

    
    /**
     * Starts the game by getting all connected users (including the host)
     * and creating a GameThread for each user.
     */
    public void startGame() {
        players = sThread.stopThread(true);
        // the last condition is because we expect this to be a 2-player game
        if (players == null || players.isEmpty() || players.size() != 2) {
            System.out.println("Something went wrong while accessing the players.");
            return;
        }
        gThread = new GameThread(this, true, sThread.getSocket());
        gThread.start();
        gThread.startGame();
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        gThread.stopGame();
        // TODO debug next line, make sure it doesn't kill the thread before it stops the game
        gThread.stopThread();
    }

    /**
     * Simulates this player slapping a card.
     */
    public void slapCard() {
        slapCard(self);
    }

    /**
     * Handles a non-host player slapping a card.
     * @param player the player who slapped the card.
     */
    public void slapCard(Player player) {
        // TODO check if slap is valid
        // TODO update points locally
        // TODO gThread.changeScore()
    }

    /**
     * Sends a message to the client that a player's
     * score has changed.
     * @param player the player whose score changed.
     * @param newScore the new score of the player.
     */
    public void updatePoints(Player player, int newScore) {
        player.setPoints(newScore);
        messenger.changeScore(player, newScore);
    }
    
    /**
     * Simulates a new random card being dealt by the host.
     */
    public void dealCard() {
        Card card = new Card();
        dealCard(self, card);
    }

    /**
     * Simulates a new random card being dealt.
     * Sends a message to the client that a new card was dealt.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
     */
    public void dealCard(Player player, Card card) {
        if (card == null) {
            card = new Card();
        }
        gThread.dealCard(player, card);
    }

    /**
     * For debugging purposes only
     * @param args
     */
    public static void main(String[] args) {
        Server aTest = new Server();
        aTest.startGame();
    }
}
