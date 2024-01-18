package fr.alexiss.karmaka;

import fr.alexiss.karmaka.cards.Card;
import fr.alexiss.karmaka.enums.KarmicLadder;

import java.io.Serializable;

/**
 * Player class
 */
public class Player implements Serializable {

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


    /**
     * Constructor of the Player class
     *
     * @param name Name of the player
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Pile<>();
        this.deck = new Pile<>();
        this.futureLife = new Pile<>();
        this.deeds = new Pile<>();
        this.karmicLadder = KarmicLadder.DUNG_BEETLE;
        this.karmicRing = 0;
    }

    /**
     * Play a turn
     */
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

    /**
     * Play a card
     * Method necessary because it can be used in cards
     */
    public void play() {
        System.out.println("Carte(s) présente(s) dans la main:\n");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }

        System.out.println("\nChoisir une action:");
        System.out.println("Sélectionner une carte par son numéro");
        System.out.println("Passer (0)");
        System.out.println("Aide WIP (aide)");

        int action = Integer.parseInt(Menu.getInstance().getInput("[0-" + hand.size() + "]|(?i)menu", "Veuillez entrer un nombre entre 0 et " + hand.size()));

        if (action == 0) {
            if (deck.isEmpty()) {
                System.out.println("Vous ne pouvez pas passer votre tour");
                play();
            }
            return;
        }

        Card cardSelected = hand.get(action - 1);
        System.out.println("\n" + cardSelected.getDetails());

        System.out.println("\nChoisir une action:");
        System.out.println("0. Retour");
        System.out.println("1. Jouer pour des points");
        System.out.println("2. Placer dans la Vie Future");
        System.out.println("3. Jouer la capacité");
        System.out.println("Aide WIP (aide)");

        action = Integer.parseInt(Menu.getInstance().getInput("[0-3]|(?i)menu", "Veuillez entrer un nombre entre 0 et 3"));


        switch (action) {
            case 0 -> play();
            case 1 -> {
                System.out.println("Je mets " + cardSelected + " dans mes Oeuvres");
                hand.remove(cardSelected);
                addToDeeds(cardSelected);
            }
            case 2 -> {
                System.out.println("Je mets " + cardSelected + " dans ma Vie Future");
                hand.remove(cardSelected);
                addToFutureLife(cardSelected);
            }
            case 3 -> {
                System.out.println("Je joue la compétence de " + cardSelected);
                hand.remove(cardSelected);
                Menu.getInstance().getGame().getOppositePlayer().takeCard(cardSelected);
                cardSelected.ability();
            }
        }
    }

    /**
     * Actions done at the beginning of every turn
     *
     * @return True if the player reincarnates
     */
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

    /**
     * Reincarnate the player if he meets the conditions
     *
     * @return True if the player reincarnates
     */
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
        while ((hand.size() + deck.size()) < (Game.HAND + Game.DECK)) {
            addToDeck(Menu.getInstance().getGame().getWell().removeFirst());
        }
    }

    /**
     * Take a card from the opposite player
     *
     * @param card Card to take
     */
    public void takeCard(Card card) {
        System.out.println(this.name + " voulez vous prendre la carte ?");
        if (getChoice()) {
            addToFutureLife(card);
            return;
        }
        Menu.getInstance().getGame().addToRuins(card);
    }

    /**
     * Get a choice from the player (int)
     *
     * @param min Minimum value
     * @param max Maximum value
     * @return Choice of the player
     */
    public int getChoice(int min, int max) {
        String regex = "[" + min + "-" + max + "]|(?i)aide";
        String msg = "Veuillez entrer un nombre entre " + min + " et " + max;
        String choice = Menu.getInstance().getInput(regex, msg);
        if (choice.equalsIgnoreCase("aide")) {
            System.out.println("Ici vous avez un choix numérique, repérer auprès des numéros de la liste afficher ci-dessus.");
            System.out.println("Un détail précis de l'étape à laquelle vous êtes dans le jeu sera implémenté dans une version future du jeu.");
            System.out.println("Vous pouvez aussi écrire \"menu\" pour accéder au menu principal.\n");
            return getChoice(min, max);
        }
        return Integer.parseInt(choice);
    }

    /**
     * Ask if the player wants to use Karmic Ring(s)
     * If he doesn't have enough to reincarnate to move up on the Karmic Ladder, it will return false
     *
     * @param points Number of points the player has
     * @return True if the player wants to use Karmic Ring(s)
     */
    protected boolean useKarmicRing(int points) {
        if (points + getKarmicRing() < getKarmicLadder().getValue()) {
            System.out.println("Vous n'avez pas assez d'Anneaux Karmiques pour progresser sur l'Échelle Karmique");
            return false;
        }
        System.out.println("Voulez-vous utiliser " + karmicRing + " Anneau(x) Karmique(s) pour progresser sur l'Échelle Karmique ?");
        return getChoice();
    }

    /**
     * Add a card to the hand of the player
     *
     * @param card Card to add
     */
    public void addToHand(Card card) {
        hand.addLast(card);
    }

    /**
     * Add a card to the deck of the player
     *
     * @param card Card to add
     */
    public void addToDeck(Card card) {
        deck.addLast(card);
    }

    /**
     * Add a card to the Future Life of the player
     *
     * @param card Card to add
     */
    public void addToFutureLife(Card card) {
        futureLife.addFirst(card);
    }

    /**
     * Add a card to the Deeds of the player
     *
     * @param card Card to add
     */
    public void addToDeeds(Card card) {
        deeds.addFirst(card);
    }

    /**
     * Override of the toString method to return the name of the player
     *
     * @return Name of the player
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Get a choice from the player (boolean)
     *
     * @return Choice of the player
     */
    public boolean getChoice() {
        String regex = "(?i)oui|non|aide";
        String msg = "Veuillez entrer \"oui\" ou \"non\"";
        String choice = Menu.getInstance().getInput(regex, msg);
        if (choice.equalsIgnoreCase("aide")) {
            System.out.println("Ici vous avez un choix booléen, il faut répondre par oui ou non au choix proposé ci-dessus.");
            System.out.println("Un détail précis de l'étape à laquelle vous êtes dans le jeu sera implémenté dans une version future du jeu.");
            System.out.println("Vous pouvez aussi écrire \"menu\" pour accéder au menu principal.");
            return getChoice();
        }
        return choice.equalsIgnoreCase("oui");
    }

    /**
     * Get the deck of the player
     *
     * @return Deck
     */
    public Pile<Card> getDeck() {
        return deck;
    }

    /**
     * Get the Deeds of the player
     *
     * @return Deeds
     */
    public Pile<Card> getDeeds() {
        return deeds;
    }

    /**
     * Get the Future Life of the player
     *
     * @return Future Life
     */
    public Pile<Card> getFutureLife() {
        return futureLife;
    }

    /**
     * Get the hand of the player
     *
     * @return Hand
     */
    public Pile<Card> getHand() {
        return hand;
    }

    /**
     * Get the Karmic Ladder of the player
     *
     * @return Karmic Ladder
     */
    public KarmicLadder getKarmicLadder() {
        return karmicLadder;
    }

    /**
     * Get the number of Karmic Rings of the player
     *
     * @return Number of Karmic Rings
     */
    public int getKarmicRing() {
        return karmicRing;
    }

    /**
     * Get the name of the player
     *
     * @return Name
     */
    public String getName() {
        return name;
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

    /**
     * Check if the player has won
     *
     * @return True if the player has won
     */
    public boolean isWinner() {
        return karmicLadder == KarmicLadder.TRANSCENDENCE;
    }
}
