import java.util.*;

/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 05/08/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Server extends PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;
    
    private ServerThread sThread;
    private ArrayList<Player> players;
    private Player host;
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
        // TODO notify the client
        // for (Player p : players) {
        //     if (p == host) {
        //         continue;
        //     }
        //     // start game for player p
        // }
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        gThread.stopThread();
        // for (Player p : players) {
        //     if (p == host) {
        //         continue;
        //     }
        //     // stop game for player p
        // }
    }

    /**
     * Simulates this player slapping a card.
     */
    public void slapCard() {
        // TODO
    }

    /**
     * Handles a non-host player slapping a card.
     * @param player the player who slapped the card.
     */
    public void slapCard(Player player) {
        // TODO
    }

    /**
     * Sends a message to the client that a player's
     * score has changed.
     * @param player the player whose score changed.
     * @param newScore the new score of the player.
     */
    public void updatePoints(Player player, int newScore) {
        // TODO
    }

    /**
     * Simulates a new card being dealt by this player.
     * Randomly generates a new card from the deck.
     */
    public void dealCard() {
        // TODO
    }

    /**
     * Simulates a new card being dealt by a non-host player.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
     */
    public void dealCard(Player player, Card card) {
        // TODO
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
