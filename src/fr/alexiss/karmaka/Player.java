package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
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
    private final boolean bot;


    public Player(String name, boolean bot) {
        this.name = name;
        this.hand = new Pile<>();
        this.deck = new Pile<>();
        this.futureLife = new Pile<>();
        this.deeds = new Pile<>();
        this.karmicLadder = KarmicLadder.DUNG_BEETLE;
        this.bot = bot;
    }


    public KarmicLadder getKarmicLadder() {
        return karmicLadder;
    }

    public void setKarmicLadder(KarmicLadder karmicLadder) {
        this.karmicLadder = karmicLadder;
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

    public void addToHand(Card card) {
        hand.addLast(card);
    }

    public void addToDeck(Card card) {
        deck.addFirst(card);
    }
}
