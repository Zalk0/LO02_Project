package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the game class which contains the game logic with the initialization and the game turns.
 */
public class Game {

    private final int HAND = 4;
    private final int DECK = 2;
    private final List<Player> players;

    // Source
    private final Pile<Card> well;
    // Fosse
    private final Pile<Card> ruins;


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
        while (!isOver()) {
            for (Player player : players) {
                if (player.isWinner()) {
                    break;
                }
                player.playTurn();
            }
        }
        //TODO what happens when win
    }

    private boolean isOver() {
        for (Player player : players) {
            if (player.isWinner()) {
                return true;
            }
        }
        return false;
    }

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

    public List<Player> getPlayers() {
        return players;
    }

    public Pile<Card> getRuins() {
        return ruins;
    }

    public Pile<Card> getWell() {
        return well;
    }

    public void addToRuins(Card card) {
        ruins.addFirst(card);
    }

    public void addToWell(Card card) {
        well.addFirst(card);
    }
}
