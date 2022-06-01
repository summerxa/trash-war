import java.util.List;

import javax.swing.SwingUtilities;

/**
 * Abstract parent class for the Server and Client. Provides
 * methods to perform actions and update the game state.
 * Has methods to modify the GUI game window.
 * 
 * @author  Anne Xia
 * @version 05/22/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
*/
public abstract class PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;
    /**
     * Number of points you need to win the game.
     */
    public static final int WIN_POINTS = 10 ;

    /**
     * The GUI game window.
     */
    protected Game gameWindow;
    /**
     * Name of this player.
     */
    protected String name;
    /**
     * List of all players.
     */
    protected List<Player> players;
    /**
     * Boolean value determining whether the game is currently running.
     */
    protected boolean isPlaying = false;

    private Score scores;

    /**
     * Starts the game.
     */
    public void startGame() {
        isPlaying = true;
        launchGame();
    }

    /**
     * Launches the game window by initializing a new Game object.
     */
    private void launchGame() {
        if (!SwingUtilities.isEventDispatchThread()) {
            // move this method call to main thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    launchGame();
                }
            });
            return;
        }
        try {
            gameWindow = new Game(this);
        } catch (Exception e) {
            System.out.println("Error while launching game window:");
            e.printStackTrace();
        }
    }
    
    /**
     * Stops the game with synchronization.
     */
    public synchronized void stopGame() {
        isPlaying = false;
        showWinLose();
        initScores();
    }

    /**
     * Initializes the scoreboard. The scoreboard will later construct
     * a GUI window, which is why this method call must be done on
     * the main thread.
     */
    private void initScores() {
        if (!SwingUtilities.isEventDispatchThread()) {
            // move this method call to main thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    initScores();
                }
            });
            return;
        }
        try {
            scores = new Score(getSelf().getPoints());
        } catch (Exception e) {
            System.out.println("Error initializing leaderboard:");
            e.printStackTrace();
        }
    }
    
    /**
     * Simulates a player slapping a card.
     */
    public abstract void slapCard();
    
    /**
     * Overloaded version of slapCard for Server class.
     * @param player the player who slapped the card.
     */
    public void slapCard(String player) {
        // do nothing
    }
    
    /**
     * Updates the score of a given player with synchronization.
     * @param player the player whose score to update.
     * @param diff how much the score changed by,
     *             positive for add points and negative for subtract.
     */
    public synchronized void updatePoints(String player, int diff) {
        if (isPlaying) {
            Player cur = getMatch(player);
            cur.addPoints(diff);
            if (diff > 0) {
                clearDeck();
                if (cur.getPoints() < WIN_POINTS) {
                    showPopup(true);
                    // If the game stops, we will show a win/lose popup instead of congrats
                }
            } else {
                showPopup(false);
            }
        }
    }

    /**
     * Empties out the center deck.
     */
    private void clearDeck() {
        if (isPlaying) {
            gameWindow.getCenterDeck().emptyDeck();
            gameWindow.changeVisibilityOfCenterDeck(false);
            // Make centerdeck invisible (empty)
        }
    }

    /**
     * Notifies game window to display a popup once the player
     * slaps the center deck.
     * @param congratulate True if this is a congrats popup, false if burn.
     */
    private void showPopup(boolean congratulate) {
        if (!SwingUtilities.isEventDispatchThread()) {
            // move this method call to main thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    showPopup(congratulate);
                }
            });
            return;
        }
        try {
            gameWindow.showCongratsWithPause(congratulate);
        } catch (Exception e) {
            System.out.println("Error while displaying congratulations window:");
            e.printStackTrace();
        }
    }

    /**
     * Displays either a win/lose popup depending on the player's score.
     */
    private void showWinLose() {
        if (!SwingUtilities.isEventDispatchThread()) {
            // move this method call to main thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    showWinLose();
                }
            });
            return;
        }
        try {
            Player self = getSelf();
            int score = self.getPoints();
            if (score >= WIN_POINTS) {
                gameWindow.newYouWon(score);
            } else {
                gameWindow.newYouLost(score);
            }
        } catch (Exception e) {
            System.out.println("Error displaying win/lose screen:");
            e.printStackTrace();
        }
    }
    
    /**
     * Simulates a new card being dealt.
     * Randomly generates a new card from the current player's deck.
     */
    public abstract void dealCard();
    
    /**
     * Displays a card on the GUI game window.
     * @param card the card to draw.
     */
    public void drawCard(Card card) {
        if (!SwingUtilities.isEventDispatchThread()) {
            // if this method was called by a non-main thread, move it to main
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    drawCard(card);
                }
            });
            return;
        }
        if (isPlaying) {
            try {
                gameWindow.draw(card);
            } catch (Exception e) {
                System.out.println("Error drawing card:");
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the player with a given name from the current
     * object's list of players, or null if player not found.
     * @param name player name to search for, or null if not found.
     */
    public Player getMatch(String name) {
        if (players == null) {
            return null;
        }
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns this person's player object.
     * @return this person's player object.
     */
    public Player getSelf() {
        return getMatch(name);
    }
    
    /**
     * Returns a list of all players.
     * @return a list of all players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * For client class only, sets the local players list
     * to the given list.
     * @param players a list of players.
     */
    public void setPlayers(List<Player> players) {
        // do nothing
    }
}
