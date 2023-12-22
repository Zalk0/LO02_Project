package fr.alexiss.karmaka;

import java.util.Iterator;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.*;

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
        
        //TODO Afficher la main
        System.out.println("Carte(s) présente(s) dans la main:\n");
        for (int i = 0; i < hand.size(); i++ ) {
        	System.out.println((i+1) + " - " + hand.get(i));
        }
        
        System.out.println("\nChoisir une action:");
        System.out.println("Sélectionner une carte par son numéro");
        System.out.println("Passer (P)");
        System.out.println("Aide WIP (aide)");
    	//TODO Print du jeu de carte
        
        String action = Menu.getInstance().getInput("[1-"+(hand.size()+1)+"]|P", "Commande inconnue!");
        
        if (action == "[[1-"+(hand.size()+1)+"]") {
            System.out.println("PËNIS");
        }
        
        System.out.println("\n---------- Fin du Tour du joueur: " + this.name + " ----------\n");
    }
    
    protected void beginTurn() {
    	System.out.println("\n---------- Début du Tour du joueur: " + this.name + " ----------\n");
        if (hand.isEmpty() && deck.isEmpty()) {
            reincarnate();
            return;
        }
        
        //Pioche d'une carte si la pioche n'est pas vide.
        if (!deck.isEmpty()) {
        	System.out.println("Pioche d'une carte, il reste " + deck.size() + " cartes dans la pile.");
        	addToHand(this.deck.removeFirst());;
        }
    }
    
    protected void takeCard(Card card) {
    	
    }
    
    public void addToFutureLife(Card card) {
    	futureLife.addFirst(card);
    }

    public void addToDeeds(Card card) {
    	deeds.addFirst(card);
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
