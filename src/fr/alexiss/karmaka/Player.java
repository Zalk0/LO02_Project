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
        // TODO Revoir tout les print

        //Actions done at the beginning of every turn
        //Return if false because the turn doesn't start
        if (!beginTurn()) {
            return;
        }

        //TODO divide the code in methods ?
        //At least a play method ?

        boolean end = false;

        while (!end) {

            System.out.println("Carte(s) présente(s) dans la main:\n");
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + " - " + hand.get(i));
            }

            System.out.println("\nChoisir une action:");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Passer (P)");
            System.out.println("Aide WIP (aide)");

            String action = Menu.getInstance().getInput("[1-" + (hand.size() + 1) + "]|(?i)P", "Commande inconnue!");
            try {
                int index = Integer.parseInt(action);
                Card cardSelected = hand.remove(index + 1);
                System.out.println("\n" + cardSelected.getDetails());

                System.out.println("\nChoisir une action:");
                System.out.println("1. Jouer pour des points");
                System.out.println("2. Placer dans la Vie Future");
                System.out.println("3. Jouer la capacité");
                System.out.println("R. Retour");
                System.out.println("Aide WIP (aide)");

                action = Menu.getInstance().getInput("[1-3]|(?i)R", "Commande inconnue!");

                switch (action) { //TODO Faire toutes les actions réelles
                    case "1" -> {
                        System.out.println("La carte à été ajoutée à la pile des Oeuvres.");
                        deeds.addFirst(cardSelected);
                    }
                    case "2" -> {
                        System.out.println("La carte à été ajoutée à la Vie Future.");
                        futureLife.addFirst(cardSelected);
                    }
                    case "3" -> {
                        System.out.println("Activation d'une carte");
                        System.out.println("Ca fait rien pour le moment :)))))))))))))))))))))");
                        cardSelected.ability();
                        //TODO TROUVER UN MOYEN DE DONNER LA CARTE

                    }
                }

                if (!(action.equals("R") || action.equals("P"))) end = true;

            } catch (NumberFormatException e) {
                System.out.println("\nTour passé!");
            }

        }

        System.out.println("\n---------- Fin du Tour du joueur : " + this.name + " ----------");
    }

    protected boolean beginTurn() {
        System.out.println("\n---------- Début du Tour du joueur : " + this.name + " ----------");
        if (reincarnate()) {
            return false;
        }

        //Draw a card from the deck if it's not empty
        if (!deck.isEmpty()) {
            addToHand(deck.removeFirst());
            switch (deck.size()) {
                case 0 ->
                        System.out.println("Pioche d'une carte " + hand.getLast() + ", c'était la dernière carte de la pile.");
                case 1 ->
                        System.out.println("Pioche d'une carte " + hand.getLast() + ", il reste 1 carte dans la pile.");
                default ->
                        System.out.println("Pioche d'une carte " + hand.getLast() + ", il reste " + deck.size() + " cartes dans la pile.");
            }
        }
        return true;
    }

    public void takeCard(Card card) {
        // TODO FAire une interface user pour savoir si le joueur prend la carte.
    }

    public void addToFutureLife(Card card) {
        futureLife.addFirst(card);
    }

    public void addToDeeds(Card card) {
        deeds.addFirst(card);
    }

    private boolean reincarnate() {
        //Don't reincarnate if the hand and the deck are not both empty
        if (!(getHand().isEmpty() && getDeck().isEmpty())) {
            return false;
        }
        System.out.println("Réincarnation du joueur : " + name);
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
        } else {
            karmicRing++;
        }
        deeds.forEach(Menu.getInstance().getGame().getRuins()::addFirst);
        futureLife.forEach(hand::addFirst);
        while ((hand.size() + deck.size()) < 6) {
            deck.addFirst(Menu.getInstance().getGame().getWell().removeFirst());
        }
        return true;
    }

    public KarmicLadder getKarmicLadder() {
        return karmicLadder;
    }

    public boolean isWinner() {
        return karmicLadder == KarmicLadder.TRANSCENDENCE;
    }

    public String getName() {
        return name;
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
