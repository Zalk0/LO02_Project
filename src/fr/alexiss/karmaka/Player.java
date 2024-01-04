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
        // Actions done at the beginning of every turn
        // Return true when rebirth is happening, so the player doesn't play afterward
        if (beginTurn()) {
            return;
        }

        // Play a card
        play();

        System.out.println("\n---------- Fin du Tour du joueur : " + this.name + " ----------");
    }

    public void play() {
        // Méthode play nécessaire pour l'utiliser dans des cartes
        // TODO divide the code in methods ?
        // TODO Revoir tout les print

        System.out.println("Carte(s) présente(s) dans la main:\n");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }

        System.out.println("\nChoisir une action:");
        System.out.println("Sélectionner une carte par son numéro");
        System.out.println("Passer (0)");
        System.out.println("Aide WIP (aide)");

        int action = getChoice(1, hand.size() + 1);

        if (action == 0) {
            if (deck.isEmpty()) {
                System.out.println("Vous ne pouvez pas passer votre tour");
                play();
            }
            return;
        }

        Card cardSelected = hand.remove(action + 1);
        System.out.println("\n" + cardSelected.getDetails());

        System.out.println("\nChoisir une action:");
        System.out.println("0. Retour");
        System.out.println("1. Jouer pour des points");
        System.out.println("2. Placer dans la Vie Future");
        System.out.println("3. Jouer la capacité");
        System.out.println("Aide WIP (aide)");

        action = getChoice(1, 3);

        switch (action) { // TODO Faire toutes les actions réelles
            case 0 -> {
                play();
            }
            case 1 -> {
                System.out.println("Je mets " + cardSelected + " dans mes Oeuvres");
                addToDeeds(cardSelected);
            }
            case 2 -> {
                System.out.println("Je mets " + cardSelected + " dans ma Vie Future");
                addToFutureLife(cardSelected);
            }
            case 3 -> {
                System.out.println("Je joue la compétence de " + cardSelected);
                Menu.getInstance().getGame().getOppositePlayer().takeCard(cardSelected);
                cardSelected.ability();
            }
        }
    }

    protected boolean beginTurn() {
        System.out.println("\n---------- Début du Tour du joueur : " + this.name + " ----------");
        if (reincarnate()) {
            rebirth();
            return true;
        }

        // Draw a card from the deck if it's not empty
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
        return false;
    }

    private boolean reincarnate() {
        // Don't reincarnate if the hand and the deck are not both empty
        if (!(getHand().isEmpty() && getDeck().isEmpty())) {
            return false;
        }
        // Get the numbers of points in his Deeds
        int points = getPoints();
        System.out.println("Réincarnation du joueur avec " + points + " points");
        switch (karmicRing) {
            case 0 -> System.out.println("Vous n'avez pas d'Anneau Karmique");
            case 1 -> System.out.println("Vous avez 1 Anneau Karmique");
            default -> System.out.println("Vous avez " + karmicRing + " Anneaux Karmiques");
        }
        // If enough points to advance the Karmic Ladder, go up
        if (points >= karmicLadder.getValue()) {
            karmicLadder = KarmicLadder.values()[karmicLadder.ordinal() + 1];
            System.out.println("Montée d'un cran sur l'Échelle Karmique");
            System.out.println("Le joueur est maintenant au cran " + karmicLadder);
            return true;
        }
        // If player has at least 1 Karmic Ring
        if (karmicRing > 0 && useKarmicRing(points)) {
            karmicRing -= karmicLadder.getValue() - points;
            karmicLadder = KarmicLadder.values()[karmicLadder.ordinal() + 1];
            System.out.println("Montée d'un cran sur l'Échelle Karmique");
            System.out.println("Le joueur est maintenant au cran " + karmicLadder);
            switch (karmicRing) {
                case 0 -> System.out.println("Il ne vous reste plus d'Anneau Karmique");
                case 1 -> System.out.println("Il vous reste 1 Anneau Karmique");
                default -> System.out.println("Il vous reste " + karmicRing + " Anneaux Karmiques");
            }
            return true;
        }
        // If player can't go up the Karmic Ladder, give him a Karmic Ring
        System.out.println("Vous n'avez pas assez de points pour progresser sur l'Échelle Karmique");
        System.out.println("Vous gagnez un Anneau Karmique");
        karmicRing++;
        return true;
    }

    /**
     * Do the rebirth of the player if he reincarnates
     */
    private void rebirth() {
        // Deeds to Ruins
        while (!deeds.isEmpty()) {
            Menu.getInstance().getGame().addToRuins(deeds.removeFirst());
        }
        // Future Life to Hand
        while (!futureLife.isEmpty()) {
            addToHand(futureLife.removeFirst());
        }
        // If cards in Hand < 6, add cards to Deck to reach 6 cards total in Deck + Hand
        while ((hand.size() + deck.size()) < 6) {
            addToDeck(Menu.getInstance().getGame().getWell().removeFirst());
        }
    }

    /**
     * Calculate the number of points per color in the Deeds and return the max.
     *
     * @return Number of points
     */
    private int getPoints() {
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
        return Math.max(Math.max(blue, green), red);
    }


    public void takeCard(Card card) {
        System.out.println(this + " voulez vous prendre la carte ?");
        if (getChoice()) {
            addToFutureLife(card);
            return;
        }
        Menu.getInstance().getGame().addToRuins(card);
    }

    public int getChoice(int min, int max) {
        String regex = "[" + min + "-" + max + "]|(?i)menu|aide";
        String msg = "Veuillez entrer un nombre entre " + min + " et " + max;
        String choice = Menu.getInstance().getInput(regex, msg);
        if (choice.equalsIgnoreCase("aide")) {
            //TODO implémenter l'aide
        }
        return Integer.parseInt(choice);
    }

    public boolean getChoice() {
        String regex = "(?i)oui|non|menu|aide";
        String msg = "Veuillez entrer \"oui\" ou \"non\"";
        String choice = Menu.getInstance().getInput(regex, msg);
        if (choice.equalsIgnoreCase("aide")) {
            //TODO implémenter l'aide
        }
        return choice.equalsIgnoreCase("oui");
    }

    protected boolean useKarmicRing(int max) {
        return false;
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

    public int getKarmicRing() {
        return karmicRing;
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

    public void addToFutureLife(Card card) {
        futureLife.addFirst(card);
    }

    public void addToDeeds(Card card) {
        deeds.addFirst(card);
    }
}
