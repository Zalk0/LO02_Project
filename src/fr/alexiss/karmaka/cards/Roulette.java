package fr.alexiss.karmaka.cards;

import fr.alexiss.karmaka.Menu;
import fr.alexiss.karmaka.Player;
import fr.alexiss.karmaka.enums.CardColor;

public class Roulette extends Card {
    public Roulette() {
        super("Roulette", 2, CardColor.RED,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.");
    }

    @Override
    public void ability() {
        int nbDiscardedCards = 0;
        Player player = Menu.getInstance().getGame().getCurrentPlayer();

        if (player.getHand().isEmpty()) {
            System.out.println("Vous n'avez pas de carte dans votre main");
            return;
        }

        System.out.println("Carte(s) présente(s) dans votre main:\n");
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println((i + 1) + ". " + player.getHand().get(i));
        }

        // Choose card to discard or pass
        while (nbDiscardedCards < 2) {
            System.out.println("\nChoisir une carte à défausser :");
            System.out.println("Sélectionner une carte par son numéro");
            System.out.println("Passer (0)");
            System.out.println("Aide WIP (aide)");

            int choice = player.getChoice(0, player.getHand().size());

            if (choice == 0) {
                break;
            }

            Card cardSelected = player.getHand().get(choice - 1);
            System.out.println("\n" + cardSelected.getDetails());

            System.out.println("\nChoisir une action:");
            System.out.println("0. Retour");
            System.out.println("1. Defausser");

            choice = player.getChoice(0, 1);

            if (choice == 1) {
                nbDiscardedCards++;
                System.out.println("Je défausse " + cardSelected + ".");
                player.getHand().remove(cardSelected);
                Menu.getInstance().getGame().addToRuins(cardSelected);
            }

            System.out.println("Il y a " + nbDiscardedCards + " cartes défaussées.");
        }

        // Pick same number as discard + 1
        for (int i = 0; i <= nbDiscardedCards + 1; i++) {
            player.addToHand(Menu.getInstance().getGame().getWell().removeFirst());
        }
    }
}
