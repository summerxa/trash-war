/**
 * The server of the game. Its main purpose is to process incoming updates
 * and send updates on the game state to the client player.
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
    private GameThread gThread;
    
    private boolean isPlaying;
    private Player self;
    private Score scores;

    /**
     * Constructs a server that begins accepting players.
     * Also creates the host player object.
     * @param playerName the name of this player.
     */
    public Server(String playerName) {
        isPlaying = false;
        self = new Player(playerName);
        sThread = new ServerThread(self);
        sThread.start();
    }

    /**
     * Starts the game by getting all connected users (including the host)
     * and creating a GameThread for each user.
     */
    public void startGame() {
        sThread.stopThread();
        players = sThread.getPlayerList();
        // the last condition is because we expect this to be a 2-player game
        if (players == null || players.isEmpty() || players.size() > 2) {
            System.out.println("Something went wrong while accessing the players.");
            return;
        }
        isPlaying = true;
        // TODO initialize scores
        gThread = new GameThread(this, true, sThread.getSocket());
        gThread.start();
        gThread.startGame();
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        isPlaying = false;
        gThread.stopGame();
        gThread.stopThread();
        // TODO NOTE: if things go wrong, this line might have...
        // ...killed the thread before it stopped the game
    }

    /**
     * Simulates the current player slapping a card.
     */
    public void slapCard() {
        if (isPlaying) {
            slapCard(self);
        }
    }

    /**
     * Handles any player slapping a card.
     * @param player the player who slapped the card.
     */
    public void slapCard(Player player) {
        if (isPlaying) {
            // TODO check if slap is valid
            // TODO update points locally
            // TODO updatePoints()
        }
    }

    /**
     * Sends a message to the client that a player's
     * score has changed.
     * @param player the player whose score changed.
     * @param newScore the new score of the player.
     */
    public void updatePoints(Player player, int newScore) {
        if (isPlaying) {
            gThread.changeScore(player, newScore);
        }
    }
    
    /**
     * Simulates a new random card being dealt by the host.
     */
    public void dealCard() {
        if (isPlaying) {
            Card card = new Card();
            dealCard(self, card);
        }
    }

    /**
     * Simulates a new random card being dealt.
     * Sends a message to the client that there is a new card.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
     */
    public void dealCard(Player player, Card card) {
        if (isPlaying) {
            if (card == null) {
                card = new Card();
            }
            gThread.dealCard(player, card);
        }
    }

    /**
     * For debugging purposes only
     * @param args
     */
    public static void main(String[] args) {
        Server aTest = new Server("Joe");
        aTest.startGame();
    }
}
