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
    
    private Player currentPlayer;

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
        this.currentPlayer = players.get(0);
        String winner = null;
        while (!isOver()) {
            for (Player player : players) {
                if (player.isWinner()) {
                    winner = player.getName();
                    break;
                }
                player.playTurn();
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("\n" + winner + " a gagné !");
        System.out.println("Appuyez sur \"entrée\" pour retourner au menu principal");
        //Menu.getInstance().getScanner().next();
    }

    public Player getCurrentPlayer() {
		return currentPlayer;
	}
    
    public Player getOppositePlayer() {
		return null;
    	//TODO 
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
            players.add(new BotPlayer(name, this));
            return;
        }
        players.add(new Player(name, this));
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
            well.add(new AnotherDay(this));
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

    //C'est pas très beau mais j'ai pas d'autre idée
	public void nextPlayer() {
		if(getCurrentPlayer().getName().equals(players.get(0).getName())) {
			this.currentPlayer = players.get(1);
		} else {
			this.currentPlayer = players.get(0);
		}
	}
}
