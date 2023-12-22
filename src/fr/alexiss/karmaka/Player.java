package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.CardColor;
import fr.alexiss.karmaka.enums.KarmicLadder;

public class Player {

    private final String name;

    // Personal areas of the player
    // Main
    private final Pile<Card> hand;
    // Pile
    private final Pile<Card> deck;
    // Vie Future
    private final Pile<Card> futureLife;
    // Oeuvres
    private final Pile<Card> deeds;

    private KarmicLadder karmicLadder;
    private int karmicRing;


    public Player(String name) {
        this.name = name;
        this.hand = new Pile<>();
        this.deck = new Pile<>();
        this.futureLife = new Pile<>();
        this.deeds = new Pile<>();
        this.karmicLadder = KarmicLadder.DUNG_BEETLE;
        this.karmicRing = 0;
    }

    public void playTurn() {
    	//Appel de la fonction de début de tour
        beginTurn();
        
        System.out.println("Jouer une carte TODO");
        System.out.println("1. Nouvelle partie");
        System.out.println("2. Charger une partie");
        System.out.println("3. Quitter le jeu");
        System.out.println("4. Passer");
        System.out.println("5. Aide");
    	//TODO Print du jeu de carte
        
        /*switch (Menu.getInstance().getInput("[1-5]", "Nombre ")) {
        case "1"->
        case "2"->
        case "3"->
        case "4"->
        case "5"->
        }*/
    }
    
    protected void beginTurn() {
    	System.out.println("\n---------- Tour du joueur 1: " + this.name + " ----------");
        if (hand.isEmpty() && deck.isEmpty()) {
            reincarnate();
            return;
        }
        
        if (!deck.isEmpty()) {
        	
        	System.out.println("Pioche d'une carte, il reste " + deck.size() + " cartes.");
        	
        	//TODO Piocher une carte
        }
    }

    private void reincarnate() {
        int blue = 0;
        int green = 0;
        int red = 0;
        for (Card card : deeds) {
            switch (card.getColor()) {
                case BLUE -> blue += card.getPoints();
                case GREEN -> green += card.getPoints();
                case RED -> red += card.getPoints();
                case MOSAIC -> {
                    blue += card.getPoints();
                    green += card.getPoints();
                    red += card.getPoints();
                }
            }
        }
        if (Math.max(Math.max(blue, green), red) > karmicLadder.getValue()) {
            karmicLadder = KarmicLadder.values()[karmicLadder.ordinal() + 1];
            return;
        }
        karmicRing++;
    }

    public KarmicLadder getKarmicLadder() {
        return karmicLadder;
    }

    public boolean isWinner() {
        return karmicLadder == KarmicLadder.TRANSCENDENCE;
    }

    public Pile<Card> getHand() {
        return hand;
    }

    public Pile<Card> getDeck() {
        return deck;
    }

    public Pile<Card> getFutureLife() {
        return futureLife;
    }

    public Pile<Card> getDeeds() {
        return deeds;
    }

    public void addToHand(Card card) {
        hand.addLast(card);
    }

    public void addToDeck(Card card) {
        deck.addFirst(card);
    }
}
