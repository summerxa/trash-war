import java.io.IOException;
import java.util.Scanner;

/**
 * The server of the game. Its main purpose is to process incoming updates
 * and send updates on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 05/22/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Server extends PlayerComputer {
    private ServerThread sThread;
    private GameThread gThread;
    
    private Player self;
    private Score scores;

    /**
     * Constructs a server that begins accepting players.
     * Also creates the host player object.
     * @param playerName the name of this player.
     */
    public Server(String playerName) {
        self = new Player(playerName);
        sThread = new ServerThread(self);
        sThread.start();
    }

    /**
     * No-args constructor for server. Sets the player name
     * to a default name.
     */
    public Server() {
        this("Player 2");
    }

    /**
     * Starts the game by getting all connected users (including the host)
     * and creating a GameThread for each user.
     */
    public void startGame() {
        players = sThread.getPlayerList();
        // the last condition is because we expect this to be a 2-player game
        if (players == null || players.isEmpty() || players.size() > 2) {
            System.out.print("Something went wrong while accessing the players. ");
            if (players == null) {
                System.out.println("Player list is null.");
            } else {
                System.out.println("Player list has size " + players.size());
            }
        }
        // TODO initialize scores
        gThread = new GameThread(this, sThread.getSocket());
        gThread.start();
        gThread.startGame();
        super.startGame();
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        super.stopGame();
        gThread.stopGame();
        try {
            gThread.join(); // wait for game thread to notify client
        } catch (Exception e) {
            System.out.println("Error in Server:");
            e.printStackTrace();
        }
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
     * @param diff the change in score of the player.
     */
    public void updatePoints(Player player, int diff) {
        if (isPlaying) {
            super.updatePoints(player, diff);
            gThread.updatePoints(player, diff);
            // TODO refresh scoreboard
            // TODO stop game internally if point threshold met
        }
    }
    
    /**
     * Simulates a new random card being dealt by the host.
     * Randomly generates a new card.
     */
    public void dealCard() {
        if (isPlaying) {
            Card card = new Card();
            // TODO player now has one less card (if using a finite # of cards vs infinite)
            drawCard(card);
        }
    }

    /**
     * Simulates a new random card being dealt.
     * Sends a message to the client that there is a new card.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
     */
    // TODO (dont remove this) make GUI stuff threadsafe
    public void drawCard(Card card) {
        if (isPlaying) {
            if (card == null) {
                card = new Card();
            }
            gThread.dealCard(card);
            //called game classes drawcard method(super.drawcard)
            try {
                super.drawCard(card);
            } catch (IOException e) {
                System.out.println("Exception in Server:");
                e.printStackTrace();
            }
        }
    }

    /**
     * For debugging purposes only. Simulates a short sequence of actions.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("#### Created server, waiting for client to join...");
        Server aTest = new Server("sErVeR");

        System.out.println("#### Enter to start game");
        scan.nextLine();
        System.out.println("#### Starting...");
        aTest.startGame();

        System.out.println("#### Players: ");
        for (Player p : aTest.getPlayers()) {
            System.out.println(p.getName() + " - " + p.getPoints());
        }

        System.out.println("#### Make CoolClient have 5 points, enter to continue");
        scan.nextLine();
        aTest.updatePoints(aTest.getMatch("CoolClient"), 5);

        System.out.println("#### Make sErVeR have 123 points, enter to continue");
        scan.nextLine();
        aTest.updatePoints(aTest.getMatch("sErVeR"), 123);

        System.out.println("#### Enter to stop game");
        scan.nextLine();
        aTest.stopGame();

        scan.close();
    }
}
