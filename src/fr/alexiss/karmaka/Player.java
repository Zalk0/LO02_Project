package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.KarmicLadder;
import fr.alexiss.karmaka.enums.StrategyTypes;

public class Player {

    private final String name;
    private StrategyTypes strategy;

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


    public Player(String name, boolean isBot) {
        this.name = name;
        this.hand = new Pile<>();
        this.deck = new Pile<>();
        this.futureLife = new Pile<>();
        this.deeds = new Pile<>();
        this.karmicLadder = KarmicLadder.DUNG_BEETLE;
        if (isBot) {
            setStrategy();
        }
    }

    private void setStrategy() {
        System.out.println("Veuillez choisir la stratégie que le bot suivra pendant la partie :");
        for (StrategyTypes strategyType : StrategyTypes.values()) {
            System.out.println(strategyType.ordinal() + ". " + strategyType);
        }
        int maxOrdinal = StrategyTypes.values().length - 1;
        int input = Integer.parseInt(Menu.getInput("[0-" + maxOrdinal + "]", "Veuillez entre un chiffre en 0 et " + maxOrdinal));
        strategy = StrategyTypes.values()[input];
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
