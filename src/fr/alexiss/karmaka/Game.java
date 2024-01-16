package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the game class which contains the game logic with the initialization and the game turns.
 */
public class Game implements Serializable {

    // Constants
    public static final int HAND = 4;
    public static final int DECK = 2;

    // List of players
    private final List<Player> players;

    // Game piles
    // Source
    private final Pile<Card> well;
    // Fosse
    private final Pile<Card> ruins;

    // Current player
    private Player currentPlayer;


    /**
     * Constructor
     */
    public Game() {
        this.players = new ArrayList<>();
        this.well = new Pile<>();
        this.ruins = new Pile<>();
        this.initWell();
    }

    /**
     * Distribute cards from the well to the players.
     */
    private void initPlayerCards() {
        for (Player player : players) {
            for (int i = 0; i < HAND; i++) {
                player.addToHand(well.removeFirst());
            }
            for (int i = 0; i < DECK; i++) {
                player.addToDeck(well.removeFirst());
            }
        }
    }

    /**
     * Begin the game
     */
    public void begin() {
        initPlayerCards();
        gameLoop();
    }

    /**
     * Resume game from a saved game
     */
    public void resume() {
        currentPlayer.play();
        if (currentPlayer == players.get(0)) {
            currentPlayer = players.get(1);
            currentPlayer.playTurn();
        }
        gameLoop();
    }

    /**
     * Game loop
     */
    private void gameLoop() {
        do {
            for (Player player : players) {
                this.currentPlayer = player;
                player.playTurn();
                if (player.isWinner()) {
                    break;
                }
            }
        } while (!currentPlayer.isWinner());
        System.out.println("----------------------------------------");
        System.out.println("\n" + currentPlayer + " a gagné !");
    }

    /**
     * Get the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the opposite player.
     *
     * @return the opposite player
     */
    public Player getOppositePlayer() {
        return players.get((players.indexOf(currentPlayer) + 1) % 2);
    }

    /**
     * Add a player to the game.
     *
     * @param name  the name of the player
     * @param isBot true if the player is a bot
     */
    public void addPlayer(String name, boolean isBot) {
        if (isBot) {
            players.add(new BotPlayer(name));
            return;
        }
        players.add(new Player(name));
    }

    /**
     * Add the cards to the well and shuffle them.
     */
    private void initWell() {
        for (int i = 0; i < 2; i++) {
            well.add(new Jubilee());
            well.add(new Mimic());
            well.add(new Spite());
            well.add(new Swindle());
            well.add(new Thievery());
            well.add(new Vengeance());
            well.add(new Voyage());
        }
        for (int i = 0; i < 3; i++) {
            well.add(new AnotherDay());
            well.add(new Crisis());
            well.add(new Denial());
            well.add(new Destiny());
            well.add(new Dwindle());
            well.add(new HellsHeart());
            well.add(new Longevity());
            well.add(new Panic());
            well.add(new Peek());
            well.add(new Recycle());
            well.add(new Roulette());
            well.add(new Salvage());
            well.add(new Sowing());
            well.add(new StolenDreams());
            well.add(new Transmigrate());
        }
        for (int i = 0; i < 5; i++) {
            well.add(new Embody());
        }
        Collections.shuffle(well);
    }

    /**
     * Get the list of players.
     *
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the ruins.
     *
     * @return the ruins
     */
    public Pile<Card> getRuins() {
        return ruins;
    }

    /**
     * Get the well.
     * If the well is empty, the ruins are shuffled and put in the well except 3 cards.
     *
     * @return the well
     */
    public Pile<Card> getWell() {
        if (well.isEmpty()) {
            Collections.shuffle(ruins);
            for (int i = 0; i < ruins.size() - 3; i++) {
                well.addLast(ruins.removeFirst());
            }
        }
        return well;
    }

    /**
     * Add a card to the ruins.
     *
     * @param card the card to add
     */
    public void addToRuins(Card card) {
        ruins.addLast(card);
    }

    /**
     * Add a card to the well.
     *
     * @param card the card to add
     */
    public void addToWell(Card card) {
        well.addLast(card);
    }
}
