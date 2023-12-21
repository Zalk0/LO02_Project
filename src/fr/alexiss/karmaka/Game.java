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
    }

    public void addPlayer(String name, boolean isBot) {
        if (isBot) {
            players.add(new BotPlayer(name));
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

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.players.get(0).getKarmicLadder());
        System.out.println(game.well.size());
        System.out.println(game.players.get(0).getHand());
        System.out.println(game.players.get(0).getDeck());
    }
}
